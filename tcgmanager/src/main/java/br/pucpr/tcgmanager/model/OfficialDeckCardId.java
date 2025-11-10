package br.pucpr.tcgmanager.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OfficialDeckCardId implements Serializable {
    private Long officialDeckId;
    private Long cardId;
}
