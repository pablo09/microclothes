package com.pzeszko.microclothes.account.repository;

import com.pzeszko.microclothes.account.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 10.04.2017.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUsername(String username);
}
