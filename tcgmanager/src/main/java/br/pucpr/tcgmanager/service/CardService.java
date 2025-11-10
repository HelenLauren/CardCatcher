package br.pucpr.tcgmanager.service;

import br.pucpr.tcgmanager.model.Card;
import br.pucpr.tcgmanager.model.Set;
import br.pucpr.tcgmanager.repository.CardRepository;
import br.pucpr.tcgmanager.repository.SetRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CardService {

    private final SetRepository setRepository;
    private final CardRepository cardRepository;

    private static final String API_URL = "https://api.pokemontcg.io/v2/cards?q=name:";

    public Card fetchCardFromApi(String cardName) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + cardName.replace(" ", "%20");

            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode data = root.path("data");
            if (data.isEmpty() || data.get(0) == null) {
                throw new RuntimeException("Nenhuma carta encontrada com o nome: " + cardName);
            }

            JsonNode cardNode = data.get(0);

            // --- Cria ou busca o Set ---
            JsonNode setNode = cardNode.path("set");
            String setApiId = setNode.path("id").asText();
            String setName = setNode.path("name").asText();

            Set set = setRepository.findByApiId(setApiId)
                    .orElseGet(() -> setRepository.save(
                            Set.builder()
                                    .apiId(setApiId)
                                    .name(setName)
                                    .series(setNode.path("series").asText())
                                    .logoUrl(setNode.path("images").path("logo").asText())
                                    .symbolUrl(setNode.path("images").path("symbol").asText())
                                    .build()
                    ));

            // --- Cria o Card ---
            Card card = Card.builder()
                    .apiId(cardNode.path("id").asText())
                    .name(cardNode.path("name").asText())
                    .type(cardNode.path("types").isArray() && cardNode.path("types").size() > 0
                            ? cardNode.path("types").get(0).asText()
                            : "Unknown")
                    .rarity(cardNode.path("rarity").asText())
                    .hp(parseHp(cardNode.path("hp").asText()))
                    .imageUrl(cardNode.path("images").path("small").asText())
                    .set(set)
                    .build();

            return cardRepository.save(card);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar carta: " + e.getMessage(), e);
        }
    }

    private Integer parseHp(String hp) {
        try {
            return Integer.parseInt(hp);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
