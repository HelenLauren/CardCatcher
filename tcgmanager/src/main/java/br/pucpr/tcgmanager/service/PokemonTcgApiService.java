package br.pucpr.tcgmanager.service;

import br.pucpr.tcgmanager.model.Card;
import br.pucpr.tcgmanager.model.Set;
import br.pucpr.tcgmanager.repository.CardRepository;
import br.pucpr.tcgmanager.repository.SetRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class PokemonTcgApiService {

    private final SetRepository setRepository;
    private final CardRepository cardRepository;

    private static final String BASE_URL = "https://api.pokemontcg.io/v2/cards";

    public Card importCardByName(String name) {
        try {
            String encodedName = URLEncoder.encode("\"" + name + "\"", StandardCharsets.UTF_8);
            String apiUrl = BASE_URL + "?q=name:" + encodedName;

            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            try (InputStream inputStream = conn.getInputStream()) {
                JsonNode root = mapper.readTree(inputStream);
                JsonNode data = root.path("data");

                if (!data.isArray() || data.isEmpty()) {
                    throw new RuntimeException("Nenhuma carta encontrada com o nome: " + name);
                }

                JsonNode cardData = data.get(0);

                // ---------- SET ----------
                JsonNode setNode = cardData.path("set");
                String setApiId = setNode.path("id").asText("");
                String setName = setNode.path("name").asText("Unknown Set");

                // Verifica se o set já existe
                Set set = setRepository.findByApiId(setApiId)
                        .orElseGet(() -> setRepository.save(Set.builder()
                                .apiId(setApiId)
                                .name(setName)
                                .series(setNode.path("series").asText(""))
                                .logoUrl(setNode.path("images").path("logo").asText(""))
                                .symbolUrl(setNode.path("images").path("symbol").asText(""))
                                .build()));

                // ---------- CARD ----------
                String apiId = cardData.path("id").asText("");
                String type = cardData.path("types").isArray() && cardData.path("types").size() > 0
                        ? cardData.path("types").get(0).asText()
                        : "Unknown";

                Card card = Card.builder()
                        .apiId(apiId)
                        .name(cardData.path("name").asText("Unknown"))
                        .type(type)
                        .rarity(cardData.path("rarity").asText("Unknown"))
                        .hp(parseHp(cardData.path("hp").asText()))
                        .imageUrl(cardData.path("images").path("small").asText(""))
                        .set(set)
                        .build();

                // Evita duplicar cartas já existentes no banco
                return cardRepository.findByApiId(apiId)
                        .orElseGet(() -> cardRepository.save(card));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar carta: " + e.getMessage(), e);
        }
    }

    private Integer parseHp(String hpText) {
        try {
            return Integer.parseInt(hpText);
        } catch (Exception e) {
            return null;
        }
    }
}
