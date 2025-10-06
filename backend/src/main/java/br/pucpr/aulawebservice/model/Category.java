package br.pucpr.aulawebservice.model;

import javax.persistence.*;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String name;

    // getters/setters, constructors
}
