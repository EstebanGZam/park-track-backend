package com.park_track.service;

import com.park_track.dto.SensorDataDTO;
import com.park_track.dto.MetadataDTO;
import com.park_track.entity.Sample;
import com.park_track.repository.SampleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SensorDataService {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataService.class);

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Sample saveSensorData(SensorDataDTO sensorDataDTO) throws Exception {
        LocalDateTime parsedDate;

        MetadataDTO metadataDTO = sensorDataDTO.getMetadata();
        try {
            if (metadataDTO.getDateAndTime() == null) {
                throw new IllegalArgumentException("DateAndTime cannot be null");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
            parsedDate = LocalDateTime.parse(metadataDTO.getDateAndTime(), formatter);
        } catch (DateTimeParseException e) {
            logger.error("Error parsing date: " + metadataDTO.getDateAndTime(), e);
            throw new IllegalArgumentException("Invalid date format. Expected dd/MM/yyyy:HH:mm:ss", e);
        }

        Sample sample = Sample.builder()
                .evaluatedId(Long.parseLong(metadataDTO.getEvaluatedId()))
                .testTypeId(1L)
                .date(Timestamp.valueOf(parsedDate))
                .onOffState("1")
                .aptitudeForTheTest("1")
                .rawData(objectMapper.writeValueAsString(sensorDataDTO.getData()))
                .build();

        return sampleRepository.save(sample);
    }
}