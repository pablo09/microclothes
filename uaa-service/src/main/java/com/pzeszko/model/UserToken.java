package com.pzeszko.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Admin on 26.04.2017.
 */
@Entity
@Table(name = "USER_TOKEN")
@Data
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "ACCESS_TOKEN")
    private String access_token;

    @Column(name = "TOKEN_TYPE")
    private String token_type;

    @Column(name = "REFRESH_TOKEN")
    private String refresh_token;

    @Column(name = "EXPIRES_IN")
    private Long expires_in;

    @Column(name = "SCOPE")
    private String scope;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
}
