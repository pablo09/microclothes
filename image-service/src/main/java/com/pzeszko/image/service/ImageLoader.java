package com.pzeszko.image.service;

import com.pzeszko.image.dto.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 07.04.2017.
 */
@Service
public class ImageLoader {

    private ResourceLoader resourceLoader;

    @Autowired
    public ImageLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<ImageDto> loadImages() throws IOException {
        Resource[] images = loadResources("classpath:/images/*");

        List<ImageDto> imageList = Arrays.stream(images).map(image -> {
            String base64Data = null;

            try {
                byte[] data = Files.readAllBytes(image.getFile().toPath());
                base64Data = Base64.getEncoder().encodeToString(data);
            } catch (IOException e) {
                e.printStackTrace();;
            }

            return new ImageDto(getFilenameWIthNoExt(image.getFilename()), base64Data);
        }).collect(Collectors.toList());
        if(true) {
            throw new RuntimeException("Unexpected error occured");
        }
        return imageList;
    }

    private String getFilenameWIthNoExt(String file) {
        return file.split("\\.")[0];
    }

    private Resource[] loadResources(String pattern) throws IOException {
        return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern);
    }
}
