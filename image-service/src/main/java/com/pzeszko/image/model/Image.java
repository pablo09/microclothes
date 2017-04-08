package com.pzeszko.image.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Admin on 07.04.2017.
 */
@Entity
@Table(name = "IMAGE")
@Data
public class Image {

    @Id
    private Long id;

    @Column(name = "ITEM_ID", nullable = false)
    private String itemId;

    @Column(name = "LOCATION", nullable = false)
    private String location;

}
