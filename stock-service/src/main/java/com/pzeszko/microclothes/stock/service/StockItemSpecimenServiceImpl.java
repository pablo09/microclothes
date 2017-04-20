package com.pzeszko.microclothes.stock.service;

import com.pzeszko.microclothes.stock.dto.StockItemDto;
import com.pzeszko.microclothes.stock.dto.StockItemInfoRequestDto;
import com.pzeszko.microclothes.stock.dto.StockItemSpecimenDto;
import com.pzeszko.microclothes.stock.exception.ErrorCode;
import com.pzeszko.microclothes.stock.exception.MicroclothesException;
import com.pzeszko.microclothes.stock.mapper.StockItemSpecimenMapper;
import com.pzeszko.microclothes.stock.model.StockItemSpecimen;
import com.pzeszko.microclothes.stock.repository.StockItemSpecimenRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

    @Autowired
    private EntityManager entityManager;

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

    @Transactional
    @Override
    public boolean buyItems(StockItemInfoRequestDto request) {
        List<StockItemSpecimen> actualItems = stockItemSpecimenRepository.findAll(request.getIds());
        Map<Long, Long> numberOfItems = request.getIds().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for(StockItemSpecimen actualItem: actualItems) {
            Integer oldAmount = actualItem.getAmount();
            Integer requestedAmount = numberOfItems.get(actualItem.getId()).intValue();
            Integer newAmount;

            if(oldAmount < numberOfItems.get(actualItem.getId())) {
                throw new MicroclothesException(ErrorCode.ITEMS_NOT_AVAILABLE);
            } else {
                 newAmount = oldAmount - requestedAmount;
            }

            actualItem.setAmount(newAmount);
        }

        return true;
    }

    @Override
    public Long getItemsNumber(String itemId) {
        Session session = entityManager.unwrap(Session.class);
        org.hibernate.Query query = session.createQuery(  // Compliant
                "FROM students where fname = " + itemId);
        List<Long> result = query.list();
        if(result != null && !result.isEmpty()) {
            return result.get(0);
        }

        return 0L;
    }

}
