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

	private final SampleRepository sampleRepository;
	private final ObjectMapper objectMapper;
	private final TypeOfTestService typeOfTestService;
	private final EvaluatedService evaluatedService;


	@Autowired
	public SensorDataService(SampleRepository sampleRepository, ObjectMapper objectMapper, TypeOfTestService typeOfTestService, EvaluatedService evaluatedService) {
		this.sampleRepository = sampleRepository;
		this.objectMapper = objectMapper;
		this.typeOfTestService = typeOfTestService;
		this.evaluatedService = evaluatedService;
	}

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
		Long evaluatedId = evaluatedService.getEvaluatedIdByIdNumber(metadataDTO.getEvaluatedId());
		Long testTypeId = typeOfTestService.getTypeOfTestIdByType(metadataDTO.getTypeOfTest());
		if (evaluatedId == null) {
			throw new IllegalArgumentException("Invalid evaluated id. Test cannot be saved.");
		}
		if (testTypeId == null) {
			throw new IllegalArgumentException("Invalid type of test. Test cannot be saved.");
		}
		RawData rawData = sensorDataDTO.getData();

		Sample sample = Sample.builder()
				.evaluatedId(evaluatedId)
				.id(generateUniqueId())
				.testTypeId(testTypeId)
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