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

    private String apiId; // ID vindo da pokemon tcg API
    private String name;
    private String type;
    private String rarity;
    private Integer hp;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;
}
