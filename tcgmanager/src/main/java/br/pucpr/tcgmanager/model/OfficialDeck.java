package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "official_decks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialDeck {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiId;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "officialDeck", cascade = CascadeType.ALL)
    private List<OfficialDeckCard> cards;
}
