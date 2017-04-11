package com.pzeszko.microservices.price.repository;

import com.pzeszko.microservices.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 07.04.2017.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findByItemId(String itemId);

    @Query("SELECT p FROM Price p WHERE p.itemId IN :items")
    List<Price> findByItemId(@Param("items") List<String> items);

    List<Price> findByItemIdIn(List<String> items);
}
