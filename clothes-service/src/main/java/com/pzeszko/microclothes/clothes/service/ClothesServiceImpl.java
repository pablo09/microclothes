package com.pzeszko.microclothes.clothes.service;

import com.pzeszko.microclothes.clothes.client.image.ImageClient;
import com.pzeszko.microclothes.clothes.client.image.ImageDto;
import com.pzeszko.microclothes.clothes.client.price.PriceClient;
import com.pzeszko.microclothes.clothes.client.price.PriceDto;
import com.pzeszko.microclothes.clothes.client.stock.StockClient;
import com.pzeszko.microclothes.clothes.dto.ClothesDto;
import com.pzeszko.microclothes.clothes.mapper.ClothesMapper;
import com.pzeszko.microclothes.clothes.model.Clothes;
import com.pzeszko.microclothes.clothes.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Admin on 08.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class ClothesServiceImpl implements ClothesService {

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private ImageClient imageClient;

    @Autowired
    private PriceClient priceClient;

    @Autowired
    private StockClient stockClient;

    @Autowired
    private ClothesMapper clothesMapper;

    @Override
    public List<ClothesDto> findAll() {
        List<Clothes> clothes = clothesRepository.findAll();
        List<ImageDto> images = imageClient.getImages();
        List<PriceDto> priceDtos = priceClient.getAllPrices();

        return clothes.stream().map(c -> clothesMapper.map(c, findImageForClothes(c, images), findPriceForClothes(c, priceDtos))).collect(Collectors.toList());
    }

    @Override
    public ClothesDto findOne(String itemId) {
        return null;
    }

    private ImageDto findImageForClothes(Clothes clothes, List<ImageDto> images) {
        Optional<ImageDto> imageOptional = images.stream().filter(image -> image.getItemId().equals(clothes.getId())).findFirst();

        if(imageOptional.isPresent()) {
            return imageOptional.get();
        } else {
            return null;
        }
    }

    private PriceDto findPriceForClothes(Clothes clothes, List<PriceDto> prices) {
        Optional<PriceDto> imageOptional = prices.stream().filter(price -> price.getItemId().equals(clothes.getId())).findFirst();

        if(imageOptional.isPresent()) {
            return imageOptional.get();
        } else {
            return null;
        }
    }
}
