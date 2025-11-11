package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tradelist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @ManyToOne(optional=false)
    private Card card;

    private Integer quantity = 1;
}
