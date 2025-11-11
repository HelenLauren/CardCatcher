package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @ManyToOne(optional=false)
    private Card card;

    private Integer priority = 1;
}
