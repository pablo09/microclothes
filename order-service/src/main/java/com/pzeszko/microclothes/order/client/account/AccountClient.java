package com.pzeszko.microclothes.order.client.account;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Admin on 12.04.2017.
 */
@FeignClient("account-service")
public interface AccountClient {

    @RequestMapping(value = "/emptyCart", method = RequestMethod.POST)
    ResponseEntity<Void> emptyCart();
}
