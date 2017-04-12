package com.pzeszko.microclothes.order.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Admin on 12.04.2017.
 */
@Data
public class OrderDetailsDto {
    private List<ItemDto> items;
    private LocalDateTime orderDate;
}
