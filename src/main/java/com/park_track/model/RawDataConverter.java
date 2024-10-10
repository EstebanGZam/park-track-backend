package com.park_track.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter(autoApply = true)
public class RawDataConverter implements AttributeConverter<RawData, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(RawData rawData) {
        if (rawData == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(rawData);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting RawData to JSON", e);
        }
    }

    @Override
    public RawData convertToEntityAttribute(String rawDataJson) {
        if (rawDataJson == null) {
            return null;
        }
        try {
            return objectMapper.readValue(rawDataJson, RawData.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to RawData", e);
        }
    }
}