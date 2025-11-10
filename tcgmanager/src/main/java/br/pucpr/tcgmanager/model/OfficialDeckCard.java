package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "official_deck_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialDeckCard {
    @EmbeddedId
    private OfficialDeckCardId id = new OfficialDeckCardId();

    @ManyToOne
    @MapsId("officialDeckId")
    private OfficialDeck officialDeck;

    @ManyToOne
    @MapsId("cardId")
    private Card card;
}
