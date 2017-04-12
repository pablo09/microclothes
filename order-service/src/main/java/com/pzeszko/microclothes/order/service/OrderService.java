package com.pzeszko.microclothes.order.service;

import com.pzeszko.microclothes.order.dto.OrderDetailsDto;
import com.pzeszko.microclothes.order.dto.OrderDto;

import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
public interface OrderService {

    void finalizeOrder(OrderDto orderDto);

    List<OrderDetailsDto> getOrders(String username);
}
