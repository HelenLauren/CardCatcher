package br.pucpr.tcgmanager.model;
//esse arquivo é a relaçao entre uma carta e vários decks

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
    private DeckCardId id = new DeckCardId();

    @ManyToOne
    @MapsId("deckId")
    private Deck deck;

    @ManyToOne
    @MapsId("cardId")
    private Card card;
}
