package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "decks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deck {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    private List<DeckCard> deckCards;
}
