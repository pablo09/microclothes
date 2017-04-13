package com.pzeszko.microclothes.users.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 13.04.2017.
 */
@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private List<Role> roles;
}
