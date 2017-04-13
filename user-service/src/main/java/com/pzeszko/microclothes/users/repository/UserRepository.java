package com.pzeszko.microclothes.users.repository;

import com.pzeszko.microclothes.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 13.04.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>{
}
