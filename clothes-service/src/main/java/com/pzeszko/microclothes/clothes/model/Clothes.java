package com.pzeszko.microclothes.clothes.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Admin on 08.04.2017.
 */
@Entity
@Table(name = "CLOTHES")
@Data
public class Clothes {

    @Id()
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "DESCRIPTION")
    private String description;


}
