package br.pucpr.aulawebservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name; // ex: "Pokémons Fofos", "Deck Água", "Trocas"

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "userCollection", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCollectionCard> cards = new HashSet<>();

    public UserCollection() {}
    public UserCollection(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

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

    public Set<UserCollectionCard> getCards() { 
        return cards; 
        }

    public void setCards(Set<UserCollectionCard> cards) { 
        this.cards = cards; 
        }
}
