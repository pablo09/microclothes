package com.pzeszko.microclothes.stock.repository;

import com.pzeszko.microclothes.stock.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 08.04.2017.
 */
@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {
}
