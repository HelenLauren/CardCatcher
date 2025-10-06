package br.pucpr.aulawebservice.dto;

import br.pucpr.aulawebservice.model.UserCollection;
import java.util.List;
import java.util.stream.Collectors;

public record UserCollectionDTO(Long id, String name, String owner, List<UserCollectionCardDTO> cards) {
    public static UserCollectionDTO fromEntity(UserCollection col) {
        return new UserCollectionDTO(
                col.getId(),
                col.getName(),
                col.getOwner().getUsername(),
                col.getCards().stream().map(UserCollectionCardDTO::fromEntity).collect(Collectors.toList())
        );
    }
}
