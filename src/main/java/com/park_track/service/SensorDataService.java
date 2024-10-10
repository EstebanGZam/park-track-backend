package com.park_track.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.park_track.dto.SensorDataDTO;
import com.park_track.dto.MetadataDTO;
import com.park_track.entity.Sample;
import com.park_track.model.RawData;
import com.park_track.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class SensorDataService {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveSensorData(SensorDataDTO sensorDataDTO) throws Exception {
        LocalDateTime parsedDate;

        MetadataDTO metadataDTO = sensorDataDTO.getMetadata();
        try {
            if (metadataDTO.getDateAndTime() == null) {
                throw new IllegalArgumentException("DateAndTime cannot be null");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
            parsedDate = LocalDateTime.parse(metadataDTO.getDateAndTime(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected dd/MM/yyyy:HH:mm:ss", e);
        }

        RawData rawData = sensorDataDTO.getData();

        Sample sample = Sample.builder()
                .evaluatedId(Long.parseLong(metadataDTO.getEvaluatedId()))
                .id(generateUniqueId())
                .testTypeId(1L)
                .date(Timestamp.valueOf(parsedDate))
                .onOffState("1")
                .aptitudeForTheTest("1")
                .rawData(rawData)
                .build();

        String rawDataJson = objectMapper.writeValueAsString(sample.getRawData());

        sampleRepository.insertSampleWithJsonbCast(
                sample.getEvaluatedId(),
                sample.getId(),
                sample.getTestTypeId(),
                sample.getOnOffState(),
                sample.getDate(),
                sample.getAptitudeForTheTest(),
                rawDataJson
        );
    }

    private Long generateUniqueId() {
        return System.currentTimeMillis();
    }
}