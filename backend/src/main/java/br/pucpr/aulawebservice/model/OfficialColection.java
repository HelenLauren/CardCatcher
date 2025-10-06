package br.pucpr.aulawebservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OfficialCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name; // ex: "Base Set", "XY", "Scarlet & Violet"

    @OneToMany(mappedBy = "officialCollection")
    private Set<Card> cards = new HashSet<>();

    public OfficialCollection() {}
    public OfficialCollection(String name) { 
        this.name = name;
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

    public Set<Card> getCards() { 
        return cards; 
        }

    public void setCards(Set<Card> cards) { 
        this.cards = cards; 
        }
}
