package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id", unique = true)
    private String apiId;

    private String name;
    private String type;
    private String rarity;
    private Integer hp;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "set_id")
    private Set set;
}
