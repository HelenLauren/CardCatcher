package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    private List<Deck> decks;

    @OneToMany(mappedBy = "user")
    private List<UserCollectionCard> collection;
}
