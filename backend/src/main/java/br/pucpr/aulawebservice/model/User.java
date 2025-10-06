package br.pucpr.aulawebservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<UserCollection> userCollections = new HashSet<>();

    // getters/setters
}
