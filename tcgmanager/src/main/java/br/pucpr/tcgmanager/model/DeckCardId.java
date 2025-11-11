package br.pucpr.tcgmanager.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeckCardId implements Serializable {
    private Long deckId;
    private Long cardId;
}
