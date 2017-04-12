package com.pzeszko.microclothes.order.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Admin on 11.04.2017.
 */
@Entity
@Table(name = "ITEM")
@Data
public class Item {

    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @Column(name = "SIZE", nullable = false)
    private String size;

    @Embedded
    private Price price;
}
