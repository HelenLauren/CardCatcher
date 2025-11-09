package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_collection_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCollectionCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Card card;

    private Integer quantity;
}
