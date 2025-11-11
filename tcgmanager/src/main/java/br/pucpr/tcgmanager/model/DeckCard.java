package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deck_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeckCard {
    @EmbeddedId
    private DeckCardId id;

    @ManyToOne
    @MapsId("deckId")
    private Deck deck;

    @ManyToOne
    @MapsId("cardId")
    private Card card;
}
