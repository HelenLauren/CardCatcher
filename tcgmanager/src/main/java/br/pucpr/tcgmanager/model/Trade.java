package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name="offered_by")
    private User offeredBy;

    @ManyToOne @JoinColumn(name="requested_by")
    private User requestedBy;

    @ManyToOne @JoinColumn(name="offered_card_id")
    private Card offeredCard;

    @ManyToOne @JoinColumn(name="requested_card_id")
    private Card requestedCard;

    private String status = "PENDING"; // PENDING, ACCEPTED, REJECTED
    private LocalDateTime createdAt = LocalDateTime.now();
}
