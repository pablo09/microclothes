package com.pzeszko.microclothes.order.mapper;

import com.pzeszko.microclothes.order.dto.ItemDto;
import com.pzeszko.microclothes.order.dto.OrderDetailsDto;
import com.pzeszko.microclothes.order.model.Item;
import com.pzeszko.microclothes.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12.04.2017.
 */
@Service
public class OrderMapper {

    public OrderDetailsDto map(Order order) {
        OrderDetailsDto dto = new OrderDetailsDto();

        dto.setOrderDate(order.getOrderDate());

        List<ItemDto> itemsDto = new ArrayList<>();
        for(Item item: order.getItems()) {
            ItemDto itemDto = new ItemDto();

            itemDto.setName(item.getName());
            itemDto.setColor(item.getColor());
            itemDto.setSize(item.getSize());

            itemsDto.add(itemDto);
        }

        dto.setItems(itemsDto);

        return dto;
    }

}
