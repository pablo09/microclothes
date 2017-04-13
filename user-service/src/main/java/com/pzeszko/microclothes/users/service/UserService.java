package com.pzeszko.microclothes.users.service;

import com.pzeszko.microclothes.users.model.User;

import java.util.List;

/**
 * Created by Admin on 13.04.2017.
 */
public interface UserService {

    List<User> findUsers();

    User findUser(String username);
}
