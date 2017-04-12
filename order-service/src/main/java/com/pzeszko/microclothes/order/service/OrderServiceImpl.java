package com.pzeszko.microclothes.order.service;

import com.pzeszko.microclothes.order.client.clothes.ClothesClient;
import com.pzeszko.microclothes.order.client.price.PriceClient;
import com.pzeszko.microclothes.order.client.shoes.ShoesClient;
import com.pzeszko.microclothes.order.client.stock.StockClient;
import com.pzeszko.microclothes.order.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 11.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private ClothesClient clothesClient;
    private PriceClient priceClient;
    private ShoesClient shoesClient;
    private StockClient stockClient;


    @Autowired
    public OrderServiceImpl(ClothesClient clothesClient, PriceClient priceClient, ShoesClient shoesClient, StockClient stockClient) {
        this.clothesClient = clothesClient;
        this.priceClient = priceClient;
        this.shoesClient = shoesClient;
        this.stockClient = stockClient;
    }


    @Override
    public void finalizeOrder(OrderDto orderDto) {

    }

    @Override
    public List<?> getOrders(String username) {
        return null;
    }
}
