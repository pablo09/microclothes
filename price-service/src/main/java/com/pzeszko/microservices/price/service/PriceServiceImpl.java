package com.pzeszko.microservices.price.service;

import com.pzeszko.microservices.price.model.Price;
import com.pzeszko.microservices.price.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getPrice(String item) {
        return priceRepository.findByItemId(item);
    }
}
