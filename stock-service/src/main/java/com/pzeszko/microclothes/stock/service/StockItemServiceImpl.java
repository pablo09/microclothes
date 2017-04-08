package com.pzeszko.microclothes.stock.service;

import com.pzeszko.microclothes.stock.model.StockItem;
import com.pzeszko.microclothes.stock.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class StockItemServiceImpl implements StockItemService {

    @Autowired
    private StockItemRepository stockItemRepository;


    @Override
    public List<StockItem> findAll() {
        return stockItemRepository.findAll();
    }
}
