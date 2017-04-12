package com.pzeszko.microclothes.order.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
@Entity
@Table(name = "ORDER")
@Data
public class Order {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate;

    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<Item> items;
}
