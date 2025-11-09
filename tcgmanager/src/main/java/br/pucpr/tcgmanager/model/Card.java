package br.pucpr.tcgmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String rarity;
    private Integer hp;

    // Construtores
    public Card() {}

    public Card(String name, String type, String rarity, Integer hp) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.hp = hp;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getRarity() { return rarity; }
    public void setRarity(String rarity) { this.rarity = rarity; }

    public Integer getHp() { return hp; }
    public void setHp(Integer hp) { this.hp = hp; }
}
