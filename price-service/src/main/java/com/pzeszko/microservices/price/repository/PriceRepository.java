package com.pzeszko.microservices.price.repository;

import com.pzeszko.microservices.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 07.04.2017.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findByItemId(String itemId);
}
