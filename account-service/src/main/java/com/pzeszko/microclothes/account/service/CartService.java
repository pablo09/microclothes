package com.pzeszko.microclothes.account.service;

import com.pzeszko.microclothes.account.dto.CartDto;

/**
 * Created by Admin on 10.04.2017.
 */
public interface CartService {

    CartDto getCartForUser(String username);

    void removeFromCart(Long itemId);

    void addToCart(Long itemId);

    void emptyUserCart();
}
