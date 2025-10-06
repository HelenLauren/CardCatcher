package br.pucpr.aulawebservice.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id técnico

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cardNumber; // ex: "150/151"

    @ManyToOne(optional = false)
    @JoinColumn(name = "official_collection_id")
    private OfficialCollection officialCollection;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    private BigDecimal price;

    // Relacionamento com as coleções criadas pelo usuário
    @ManyToMany(mappedBy = "cards")
    private Set<UserCollection> userCollections = new HashSet<>();

    // Constructors
    public Card() {}
    public Card(String name, String cardNumber, BigDecimal price, Category category, OfficialCollection collection) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.price = price;
        this.category = category;
        this.officialCollection = collection;
    }

    // Getters e Setters
    public Long getId() { 
        return id; 
    }
    
    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getCardNumber() { 
        return cardNumber; 
    }

    public void setCardNumber(String cardNumber) { 
        this.cardNumber = cardNumber; 
    }

    public OfficialCollection getOfficialCollection() { 
        return officialCollection; 
    }

    public void setOfficialCollection(OfficialCollection officialCollection) { 
        this.officialCollection = officialCollection; 
    }

    public Category getCategory() { 
        return category; 
    }

    public void setCategory(Category category) { 
        this.category = category; 
    }

    public BigDecimal getPrice() { 
        return price; 
    }

    public void setPrice(BigDecimal price) { 
        this.price = price; 
    }

    public Set<UserCollection> getUserCollections() {
        return userCollections; 
    }
}
