package com.pzeszko.microclothes.stock.service;

import com.pzeszko.microclothes.stock.dto.StockItemDto;
import com.pzeszko.microclothes.stock.dto.StockItemInfoRequestDto;
import com.pzeszko.microclothes.stock.dto.StockItemSpecimenDto;
import com.pzeszko.microclothes.stock.mapper.StockItemSpecimenMapper;
import com.pzeszko.microclothes.stock.model.StockItemSpecimen;
import com.pzeszko.microclothes.stock.repository.StockItemSpecimenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Admin on 08.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class StockItemSpecimenServiceImpl implements  StockItemSpecimenService {

    @Autowired
    private StockItemSpecimenRepository stockItemSpecimenRepository;

    @Autowired
    private StockItemSpecimenMapper stockItemSpecimenMapper;

    @Override
    public List<StockItemSpecimen> findAllStockItemSpecimens() {
        return stockItemSpecimenRepository.findAll();
    }

    @Override
    public List<StockItemSpecimen> findByItemId(String itemId) {
        return stockItemSpecimenRepository.findByItemItemId(itemId);
    }

    @Override
    public List<StockItemSpecimenDto> findStockDtosByItemId(String itemId) {
        return stockItemSpecimenRepository.findByItemItemId(itemId).stream().map(item -> stockItemSpecimenMapper.map(item)).collect(Collectors.toList());
    }

    @Override
    public List<StockItemSpecimenDto> findStockItemSpecimensByIds(StockItemInfoRequestDto request) {
        return stockItemSpecimenRepository.findAll(request.getIds()).stream().map(item -> stockItemSpecimenMapper.map((item))).collect(Collectors.toList());
    }

    @Override
    public List<StockItemDto> getItemIdsForStockItems(StockItemInfoRequestDto request) {
        List<StockItemSpecimen> stockItems = stockItemSpecimenRepository.findAll(request.getIds());
        return stockItems.stream().map(stock -> new StockItemDto(stock.getItem().getItemId(), stock.getId())).collect(Collectors.toList());
    }

    @Override
    public void buyItems(StockItemInfoRequestDto request) {
        List<StockItemSpecimen> stockItems = stockItemSpecimenRepository.findAll(request.getIds());

        Map<Long, Long> numberOfItems = stockItems.stream().collect(Collectors.groupingBy(StockItemSpecimen::getId, Collectors.counting()));

    }
}
