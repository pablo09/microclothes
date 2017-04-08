package com.pzeszko.microclothes.stock.mapper;

import com.pzeszko.microclothes.stock.dto.StockItemSpecimenDto;
import com.pzeszko.microclothes.stock.model.StockItemSpecimen;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 08.04.2017.
 */
@Service
public class StockItemSpecimenMapper {

    public StockItemSpecimenDto map(StockItemSpecimen specimen) {
        StockItemSpecimenDto dto = new StockItemSpecimenDto();

        dto.setItemId(specimen.getItem().getItemId());
        dto.setColor(specimen.getItem().getColor());
        dto.setAmount(specimen.getAmount());
        dto.setSize(specimen.getSize());
        dto.setType(specimen.getItem().getType().name());

        return dto;
    }
}
