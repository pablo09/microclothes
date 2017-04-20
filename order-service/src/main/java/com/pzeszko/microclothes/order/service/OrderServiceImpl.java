package com.pzeszko.microclothes.order.service;

import com.pzeszko.microclothes.order.client.account.AccountClient;
import com.pzeszko.microclothes.order.client.clothes.ClothesClient;
import com.pzeszko.microclothes.order.client.clothes.ClothesDto;
import com.pzeszko.microclothes.order.client.clothes.ClothesInfoRequestDto;
import com.pzeszko.microclothes.order.client.price.PriceClient;
import com.pzeszko.microclothes.order.client.price.PriceDto;
import com.pzeszko.microclothes.order.client.price.PricesRequestDto;
import com.pzeszko.microclothes.order.client.shoes.ShoesClient;
import com.pzeszko.microclothes.order.client.shoes.ShoesDto;
import com.pzeszko.microclothes.order.client.shoes.ShoesInfoRequestDto;
import com.pzeszko.microclothes.order.client.stock.StockClient;
import com.pzeszko.microclothes.order.client.stock.StockItemInfoRequestDto;
import com.pzeszko.microclothes.order.client.stock.StockItemSpecimen;
import com.pzeszko.microclothes.order.dto.OrderDetailsDto;
import com.pzeszko.microclothes.order.dto.OrderDto;
import com.pzeszko.microclothes.order.mapper.OrderMapper;
import com.pzeszko.microclothes.order.model.Item;
import com.pzeszko.microclothes.order.model.Order;
import com.pzeszko.microclothes.order.model.Price;
import com.pzeszko.microclothes.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Admin on 11.04.2017.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final ClothesClient clothesClient;
    private final PriceClient priceClient;
    private final ShoesClient shoesClient;
    private final StockClient stockClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AccountClient accountClient;

    @Transactional
    @Override
    public void finalizeOrder(OrderDto orderDto) {
        List<StockItemSpecimen> specimens = stockClient.getAllRequestedSpecimens(new StockItemInfoRequestDto(orderDto.getSpecimenIds()));

        List<String> itemIds = specimens.stream().map(s -> s.getItem()).collect(Collectors.toList());
        List<String> shoesIds = getItemIdsByType(specimens, "SHOES");
        List<String> clothesIds = getItemIdsByType(specimens, "CLOTHES");

        List<PriceDto> prices = priceClient.getPriceForRequestedItems(new PricesRequestDto(itemIds));
        List<ShoesDto> shoesList = shoesClient.getShoesInfo(new ShoesInfoRequestDto(shoesIds));
        List<ClothesDto> clothesList = clothesClient.getClothesInfo(new ClothesInfoRequestDto(clothesIds));

        Order order = createOrder(specimens, prices, shoesList, clothesList);

        orderRepository.save(order);
        emptyCart();
        stockClient.buyItem(new StockItemInfoRequestDto(orderDto.getSpecimenIds()));
    }

    private Order createOrder(List<StockItemSpecimen> specimens, List<PriceDto> prices, List<ShoesDto> shoesList, List<ClothesDto> clothesList) {
        Order order = new Order();
        List<Item> items = new ArrayList<>();

        order.setOrderDate(LocalDateTime.now());
        order.setUsername(getUsername());

        specimens.stream().forEach(s -> {
            Item item = new Item();

            if (s.getType().equals("SHOES")) {
                ShoesDto shoe = shoesList.stream().filter(s1 -> s1.getId().equals(s.getItem())).findAny().get();
                item.setName(shoe.getName());
            } else if (s.getType().equals("CLOTHES")) {
                ClothesDto clothes = clothesList.stream().filter(c -> c.getId().equals(s.getItem())).findAny().get();
                item.setName(clothes.getName());
            }

            item.setColor(s.getColor());
            item.setSize(s.getSize());
            item.setPrice(getPrice(prices, s));

            items.add(item);
        });

        order.setItems(items);
        return order;
    }

    private Price getPrice(List<PriceDto> prices, StockItemSpecimen s) {
        Price priceEntity = new Price();
        Optional<PriceDto> priceOptional = prices.stream().filter(p -> p.getItemId().equals(s.getItem())).findFirst();

        Optional.ofNullable(priceOptional).ifPresent(optional -> {
            PriceDto price = optional.get();
            priceEntity.setAmount(price.getAmount());
            priceEntity.setCurrency(price.getCurrency());
        });

        return priceEntity;
    }

    @Override
    public List<OrderDetailsDto> getOrders(String username) {
        return orderRepository.findByUsername(username).stream().map(order -> orderMapper.map(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailsDto> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> orderMapper.map(order)).collect(Collectors.toList());
    }

    private void emptyCart() {
        accountClient.emptyCart();
    }
    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private List<String> getItemIdsByType(List<StockItemSpecimen> specimens, String type) {
        return specimens.stream().filter(s -> s.getType().equals(type)).map(s -> s.getItem()).collect(Collectors.toList());
    }
}
