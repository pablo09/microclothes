package com.pzeszko.microclothes.account.web;

import com.pzeszko.microclothes.account.dto.CartDto;
import com.pzeszko.microclothes.account.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Admin on 10.04.2017.
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/")
    public CartDto findCartForUser(Principal principal) {
        return cartService.getCartForUser(principal.getName());
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> addToCart(@PathVariable("itemId") Long itemId) {
        cartService.addToCart(itemId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeFromCart(@PathVariable("itemId") Long itemId) {
        cartService.removeFromCart(itemId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/emptyCart", method = RequestMethod.POST)
    public ResponseEntity<Void> emptyCart() {
        cartService.emptyUserCart();
        return ResponseEntity.ok().build();
    }
}
