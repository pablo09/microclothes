package com.pzeszko.microclothes.account.model.converter;

import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 10.04.2017.
 */
@Converter
public class ListToStringConverter implements AttributeConverter<List<Long>, String> {

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }

        return StringUtils.join(attribute, ",");
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().length() == 0) {
            return new ArrayList<Long>();
        }

        String[] data = dbData.split(",");
        Long[] lData = new Long[data.length];
        for(int i = 0; i < data.length; i++ ) {
            lData[i] = Long.valueOf(data[i]);
        }

        return Arrays.asList(lData);
    }
}