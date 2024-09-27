package com.park_track.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RawDataConverter implements AttributeConverter<RawData, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(RawData rawData) {
        if (rawData == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(rawData);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error serializing RawData to JSON", e);
        }
    }

    @Override
    public RawData convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, RawData.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error deserializing JSON to RawData", e);
        }
    }
}