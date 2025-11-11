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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final SetRepository setRepository;

    private static final String API_URL = "https://api.pokemontcg.io/v2/cards?q=name:";

    /**
     * Busca cartas na API Pokémon TCG pelo nome digitado
     */
    public List<Card> searchByNameFromApi(String name) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + name.replace(" ", "%20");
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode data = root.path("data");

            List<Card> results = new ArrayList<>();
            for (JsonNode node : data) {
                results.add(mapToCard(node));
            }

            return results;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cartas da API: " + e.getMessage(), e);
        }
    }

    /**
     * Importa e persiste uma carta no banco de dados (usando o nome como referência)
     */
    public Card importCardByName(String name) {
        List<Card> cards = searchByNameFromApi(name);
        if (cards.isEmpty()) throw new RuntimeException("Nenhuma carta encontrada para: " + name);

        Card card = cards.get(0);

        // Se o set ainda não existe, salva
        Set set = setRepository.findByApiId(card.getSet().getApiId())
                .orElseGet(() -> setRepository.save(card.getSet()));

        // Atualiza o set da carta
        card.setSet(set);

        // Se a carta já existe, atualiza
        return cardRepository.findByApiId(card.getApiId())
                .map(existing -> {
                    existing.setName(card.getName());
                    existing.setType(card.getType());
                    existing.setRarity(card.getRarity());
                    existing.setHp(card.getHp());
                    existing.setImageUrl(card.getImageUrl());
                    existing.setSet(set);
                    return cardRepository.save(existing);
                })
                .orElseGet(() -> cardRepository.save(card));
    }

    private Card mapToCard(JsonNode data) {
        JsonNode setNode = data.path("set");
        Set set = Set.builder()
                .apiId(setNode.path("id").asText())
                .name(setNode.path("name").asText())
                .series(setNode.path("series").asText())
                .logoUrl(setNode.path("images").path("logo").asText())
                .symbolUrl(setNode.path("images").path("symbol").asText())
                .releaseDate(parseDate(setNode.path("releaseDate").asText()))
                .build();

        return Card.builder()
                .apiId(data.path("id").asText())
                .name(data.path("name").asText())
                .type(data.path("types").isArray() && data.path("types").size() > 0
                        ? data.path("types").get(0).asText() : "Unknown")
                .rarity(data.path("rarity").asText())
                .hp(parseHp(data.path("hp").asText()))
                .imageUrl(data.path("images").path("small").asText())
                .set(set)
                .build();
    }

    private Integer parseHp(String hp) {
        try {
            return Integer.parseInt(hp);
        } catch (Exception e) {
            return 0;
        }
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
