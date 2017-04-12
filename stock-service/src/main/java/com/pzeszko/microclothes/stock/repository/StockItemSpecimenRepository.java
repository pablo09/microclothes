package com.pzeszko.microclothes.stock.repository;

import com.pzeszko.microclothes.stock.model.StockItemSpecimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
@Repository
public interface StockItemSpecimenRepository extends JpaRepository<StockItemSpecimen, Long> {

    List<StockItemSpecimen> findByItemItemId(String itemId);

}
