package com.pzeszko.microclothes.order.repository;

import com.pzeszko.microclothes.order.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 11.04.2017.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
}
