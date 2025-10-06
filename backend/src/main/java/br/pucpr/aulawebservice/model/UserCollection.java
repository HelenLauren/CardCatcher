package br.pucpr.aulawebservice.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name; // Ex: "Pokémons fofos", "Trocas", etc.

    @ManyToOne(optional=false)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany
    @JoinTable(
        name = "user_collection_cards",
        joinColumns = @JoinColumn(name = "collection_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Set<Card> cards = new HashSet<>();

    public UserCollection() {}
    public UserCollection(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    // Getters e setters
    public Long getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }
    
    public User getOwner() { 
        return owner; 
    }

    public void setOwner(User owner) {
        this.owner = owner; 
    }

    public Set<Card> getCards() { 
        return cards;
    }
}
