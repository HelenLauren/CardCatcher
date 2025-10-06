package br.pucpr.aulawebservice.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OfficialCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @OneToMany(mappedBy = "officialCollection", cascade = CascadeType.ALL, orphanRemoval = true)
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
}
