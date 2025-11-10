package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id", unique = true)
    private String apiId;

    private String name;
    private String series;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "symbol_url")
    private String symbolUrl;

    @OneToMany(mappedBy = "set", cascade = CascadeType.ALL)
    private List<Card> cards;

}
