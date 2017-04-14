package com.pzeszko.microclothes.users.service;

import com.pzeszko.microclothes.users.model.User;
import com.pzeszko.microclothes.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 13.04.2017.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(String username) {
        return userRepository.findOne(username);
    }
}
