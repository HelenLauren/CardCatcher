package br.pucpr.aulawebservice.dto;

import br.pucpr.aulawebservice.model.UserCollectionCard;

public record UserCollectionCardDTO(Long id, CardDTO card, String tradeStatus) {
    public static UserCollectionCardDTO fromEntity(UserCollectionCard c) {
        return new UserCollectionCardDTO(c.getId(), CardDTO.fromEntity(c.getCard()), c.getTradeStatus().name());
    }
}
