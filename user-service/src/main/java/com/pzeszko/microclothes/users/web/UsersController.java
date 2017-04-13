package com.pzeszko.microclothes.users.web;

import com.pzeszko.microclothes.users.model.User;
import com.pzeszko.microclothes.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Admin on 13.04.2017.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {

    private final UserService userService;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByName(@PathVariable String username) {
        return ResponseEntity.ok(userService.findUser(username));
    }
}
