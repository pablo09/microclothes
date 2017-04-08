package com.pzeszko.microservices;

import com.pzeszko.image.ImageServiceApplication;
import com.pzeszko.image.dto.ImageDto;
import com.pzeszko.image.service.ImageLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;


/**
 * Created by Admin on 07.04.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageServiceApplication.class)
@WebAppConfiguration
public class LoadImageTest {

    @Autowired
    private ImageLoader imageLoader;

    @Test
    public void loadImages() throws IOException {
        List<ImageDto> images = imageLoader.loadImages();

        Assert.assertNotEquals(images.size(), 0);
        Assert.assertNotEquals(images.get(0).getItemId(), null);
        Assert.assertNotEquals(images.get(0).getContent(), null);

        Assert.assertEquals(1, images.stream().filter(image -> image.getItemId().equals("2017_CRAZY_EXPLOSIVE_LOW_PK")).count());
    }
}
