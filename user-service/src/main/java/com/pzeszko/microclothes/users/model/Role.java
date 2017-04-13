package com.pzeszko.microclothes.users.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Admin on 13.04.2017.
 */
@Entity
@Table(name = "ROLE")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

}
