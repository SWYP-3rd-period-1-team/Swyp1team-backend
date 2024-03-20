package com.swig.zigzzang.global.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class BooleanArrayToStringConverter implements AttributeConverter<Boolean[], String> {

    @Override
    public String convertToDatabaseColumn(Boolean[] attribute) {
        if (attribute == null || attribute.length == 0) {
            return "";
        }
        return Arrays.stream(attribute)
                .map(value -> value ? "1" : "0")
                .collect(Collectors.joining(","));
    }

    @Override
    public Boolean[] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty())
            return new Boolean[0];

        String[] pieces = dbData.split(",");
        Boolean[] result = new Boolean[pieces.length];

        for (int i = 0; i < pieces.length; i++) {
            if ("1".equals(pieces[i])) {
                result[i] = true;
            } else if ("0".equals(pieces[i])) {
                result[i] = false;
            }
        }

        return result;
    }
}
