package com.pzeszko.microclothes.account.service;

import com.pzeszko.microclothes.account.client.clothes.ClothesClient;
import com.pzeszko.microclothes.account.client.clothes.ClothesDto;
import com.pzeszko.microclothes.account.client.clothes.ClothesInfoRequestDto;
import com.pzeszko.microclothes.account.client.price.PriceClient;
import com.pzeszko.microclothes.account.client.price.PriceDto;
import com.pzeszko.microclothes.account.client.price.PricesRequestDto;
import com.pzeszko.microclothes.account.client.shoes.ShoesClient;
import com.pzeszko.microclothes.account.client.shoes.ShoesDto;
import com.pzeszko.microclothes.account.client.shoes.ShoesInfoRequestDto;
import com.pzeszko.microclothes.account.client.stock.StockClient;
import com.pzeszko.microclothes.account.client.stock.StockItemInfoRequestDto;
import com.pzeszko.microclothes.account.client.stock.StockItemSpecimen;
import com.pzeszko.microclothes.account.dto.CartDto;
import com.pzeszko.microclothes.account.dto.ItemInfoDto;
import com.pzeszko.microclothes.account.model.Cart;
import com.pzeszko.microclothes.account.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Admin on 10.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private StockClient stockClient;

    @Autowired
    private PriceClient priceClient;

    @Autowired
    private ShoesClient shoesClient;

    @Autowired
    private ClothesClient clothesClient;

    @Override
    public CartDto getCartForUser(String username) {
        Cart cart = cartRepository.findByUsername(username);

        List<StockItemSpecimen> specimens = stockClient.getAllRequestedSpecimens(new StockItemInfoRequestDto(cart.getStockItemIds()));

        List<String> itemIds = specimens.stream().map(s -> s.getItem()).collect(Collectors.toList());
        List<String> shoesIds = getItemIdsByType(specimens, "SHOES");
        List<String> clothesIds = getItemIdsByType(specimens, "CLOTHES");

        List<PriceDto> prices = priceClient.getPriceForRequestedItems(new PricesRequestDto(itemIds));
        List<ShoesDto> shoesList = shoesClient.getShoesInfo(new ShoesInfoRequestDto(shoesIds));
        List<ClothesDto> clothesList = clothesClient.getClothesInfo(new ClothesInfoRequestDto(clothesIds));

        CartDto cartDto = new CartDto();
        List<ItemInfoDto> items = new ArrayList<>();

        specimens.stream().forEach(s -> {
            ItemInfoDto itemInfo = new ItemInfoDto();

            addIteminfo(shoesList, clothesList, s, itemInfo);

            itemInfo.setSpecimenId(s.getItemId());
            itemInfo.setSize(s.getSize());
            itemInfo.setColor(s.getColor());

            addPriceInfo(prices, s, itemInfo);

            items.add(itemInfo);
        });


        cartDto.setItems(items);

        return cartDto;

    }

    private void addPriceInfo(List<PriceDto> prices, StockItemSpecimen s, ItemInfoDto itemInfo) {
        Optional<PriceDto> priceOptional = prices.stream().filter(p -> p.getItemId().equals(s.getItem())).findFirst();
        if(priceOptional.isPresent()) {
            PriceDto price = priceOptional.get();
            itemInfo.setPrice(new com.pzeszko.microclothes.account.dto.PriceDto(price.getAmount(), price.getCurrency()));
        }
    }

    private void addIteminfo(List<ShoesDto> shoesList, List<ClothesDto> clothesList, StockItemSpecimen s, ItemInfoDto itemInfo) {
        if(s.getType().equals("SHOES")) {
            ShoesDto shoe = shoesList.stream().filter(s1 -> s1.getId().equals(s.getItem())).findAny().get();
            itemInfo.setName(shoe.getName());
        } else if (s.getType().equals("CLOTHES")) {
            ClothesDto clothes = clothesList.stream().filter(c -> c.getId().equals(s.getItem())).findAny().get();
            itemInfo.setName(clothes.getName());
        }
    }

    @Transactional
    @Override
    public void removeFromCart(Long itemId) {
        Cart cart = findUserCart();
        List<Long> items = new ArrayList<>(cart.getStockItemIds());
        items.remove(itemId);

        cart.setStockItemIds(items);
    }

    @Transactional
    @Override
    public void addToCart(Long itemId) {
        Cart cart = findUserCart();
        List<Long> items = new ArrayList<>(cart.getStockItemIds());
        items.add(itemId);

        cart.setStockItemIds(items);
    }

    @Transactional
    @Override
    public void emptyUserCart() {
        Cart cart = findUserCart();
        cart.setStockItemIds(null);
    }

    private Cart findUserCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartRepository.findByUsername(username);
    }

    private List<String> getItemIdsByType(List<StockItemSpecimen> specimens, String type) {
        return specimens.stream().filter(s -> s.getType().equals(type)).map(s -> s.getItem()).collect(Collectors.toList());
    }
}
