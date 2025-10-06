package br.pucpr.aulawebservice.dto;

import br.pucpr.aulawebservice.model.Card;
import br.pucpr.aulawebservice.model.Category;
import br.pucpr.aulawebservice.model.OfficialCollection;
import java.math.BigDecimal;

public record CardDTO(
        Long id,
        String name,
        String cardNumber,
        BigDecimal price,
        String categoryName,
        String collectionName
) {
    public static CardDTO fromEntity(Card card) {
        String categoryName = card.getCategory() != null ? card.getCategory().getName() : null;
        String collectionName = card.getOfficialCollection() != null ? card.getOfficialCollection().getName() : null;
        return new CardDTO(
                card.getId(),
                card.getName(),
                card.getCardNumber(),
                card.getPrice(),
                categoryName,
                collectionName
        );
    }
}
