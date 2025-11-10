package br.pucpr.tcgmanager.model;
//deck oficial criado pelo admin

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id")
    private String apiId;

    private String name;
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;

    @OneToMany(mappedBy = "officialDeck", cascade = CascadeType.ALL)
    private List<OfficialDeckCard> cards;
}
