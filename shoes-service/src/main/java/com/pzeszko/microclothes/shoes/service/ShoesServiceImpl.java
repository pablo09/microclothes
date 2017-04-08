package com.pzeszko.microclothes.shoes.service;


import com.pzeszko.microclothes.shoes.client.image.ImageClient;
import com.pzeszko.microclothes.shoes.client.image.ImageDto;
import com.pzeszko.microclothes.shoes.client.price.PriceClient;
import com.pzeszko.microclothes.shoes.client.price.PriceDto;
import com.pzeszko.microclothes.shoes.client.stock.StockClient;
import com.pzeszko.microclothes.shoes.client.stock.StockItemSpecimen;
import com.pzeszko.microclothes.shoes.dto.ShoeDetailsDto;
import com.pzeszko.microclothes.shoes.dto.ShoesDto;
import com.pzeszko.microclothes.shoes.mapper.ShoesMapper;
import com.pzeszko.microclothes.shoes.model.Shoes;
import com.pzeszko.microclothes.shoes.repository.ShoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Admin on 07.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class ShoesServiceImpl implements ShoesService {

    @Autowired
    private ShoesRepository shoesRepository;

    @Autowired
    private ImageClient imageClient;

    @Autowired
    private PriceClient priceClient;

    @Autowired
    private StockClient stockClient;

    @Autowired
    private ShoesMapper shoesMapper;

    @Override
    public List<Shoes> findAll() {
        return shoesRepository.findAll();
    }

    @Override
    public List<ShoesDto> findAllShoesDtos() {
        List<Shoes> shoesList = findAll();
        List<ImageDto> imageDtoList = imageClient.getImages();
        List<PriceDto> priceDtoList = priceClient.getAllPrices();

        return shoesList.stream().map(shoe -> shoesMapper.map(shoe, findImageForShoe(shoe, imageDtoList), findPriceForShoe(shoe, priceDtoList))).collect(Collectors.toList());
    }

    @Override
    public ShoeDetailsDto findShoe(String shoeId) {
        //TODO Dodac optionalFindOne i obluge tego wyjatku
        Shoes shoe = shoesRepository.findOne(shoeId);
        ImageDto image = imageClient.getImageForShoe(shoeId);
        PriceDto price = priceClient.getPriceForShoe(shoeId);
        List<StockItemSpecimen> specimens = stockClient.getAllItemsSpeciman(shoeId);

        return shoesMapper.mapDetails(shoe, image, price, specimens);
    }

    private ImageDto findImageForShoe(Shoes shoe, List<ImageDto> images) {
        Optional<ImageDto> imageOptional = images.stream().filter(image -> image.getItemId().equals(shoe.getId())).findFirst();

        if(imageOptional.isPresent()) {
            return imageOptional.get();
        } else {
            return null;
        }
    }

    private PriceDto findPriceForShoe(Shoes shoe, List<PriceDto> prices) {
        Optional<PriceDto> imageOptional = prices.stream().filter(price -> price.getItemId().equals(shoe.getId())).findFirst();

        if(imageOptional.isPresent()) {
            return imageOptional.get();
        } else {
            return null;
        }
    }
}
