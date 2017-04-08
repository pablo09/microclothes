package com.pzeszko.microclothes.stock;

import com.pzeszko.microclothes.stock.model.StockItem;
import com.pzeszko.microclothes.stock.model.StockItemSpecimen;
import com.pzeszko.microclothes.stock.service.StockItemService;
import com.pzeszko.microclothes.stock.service.StockItemSpecimenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockServiceApplication.class)
@WebAppConfiguration
public class StockServiceTest {

    @Autowired
    private StockItemService stockItemService;

    @Autowired
    private StockItemSpecimenService stockItemSpecimenService;

    @Test
    public void testStockService() {
        List<StockItem> items = stockItemService.findAll();
        List<StockItemSpecimen> specimens = stockItemSpecimenService.findByItemId("2017_SUPERSTAR_BOOST");
        Assert.assertTrue(items.size() > 0);
        Assert.assertTrue(stockItemSpecimenService.findByItemId("2017_SUPERSTAR_BOOST") != null);
        Assert.assertTrue(stockItemSpecimenService.findByItemId("2017_SUPERSTAR_BOOST").size() > 0);
    }
}
