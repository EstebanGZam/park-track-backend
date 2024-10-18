package com.park_track.service;

import com.park_track.dto.SensorDataDTO;
import com.park_track.dto.MetadataDTO;
import com.park_track.dto.SampleDTO;
import com.park_track.entity.Sample;
import com.park_track.entity.SampleId;
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
	private final TypeOfTestService typeOfTestService;
	private final EvaluatedService evaluatedService;

	@Autowired
	public SensorDataService(SampleRepository sampleRepository, 
			TypeOfTestService typeOfTestService, EvaluatedService evaluatedService) {
		this.sampleRepository = sampleRepository;
		this.typeOfTestService = typeOfTestService;
		this.evaluatedService = evaluatedService;
	}

	public void saveSensorData(SensorDataDTO sensorDataDTO) throws IllegalArgumentException {
		LocalDateTime parsedDate;

		MetadataDTO metadataDTO = sensorDataDTO.getMetadata();
		// Aqui, obtiene la fecha y evalua que esta sea valida.
		try {
			if (metadataDTO.getDateAndTime() == null) {
				throw new IllegalArgumentException("DateAndTime cannot be null");
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
			parsedDate = LocalDateTime.parse(metadataDTO.getDateAndTime(), formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format. Expected dd/MM/yyyy:HH:mm:ss", e);
		}
		// Verifica que el evaluado y el tipo de test existan
		Long evaluatedId = evaluatedService.getEvaluatedIdByIdNumber(metadataDTO.getEvaluatedId());
		Long testTypeId = typeOfTestService.getTypeOfTestIdByType(metadataDTO.getTypeOfTest());
		if (evaluatedId == null) {
			throw new IllegalArgumentException("Invalid evaluated id. Test cannot be saved.");
		}
		if (testTypeId == null) {
			throw new IllegalArgumentException("Invalid type of test. Test cannot be saved.");
		}
		// Estons son los datos puros que vienen en el objeto data
		RawData rawDataFromSensor = sensorDataDTO.getData();
		RawData rawDataConvertedToCommonUnits = rawDataFromSensor.convertToCommonUnits();

		Sample sample = Sample.builder()
				.evaluatedId(evaluatedId)
				.id(generateUniqueId())
				.testTypeId(testTypeId)
				.date(Timestamp.valueOf(parsedDate))
				.onOffState("1")
				.aptitudeForTheTest("1")
				.rawData(rawDataConvertedToCommonUnits)
				.build();

		// Quite lo que estaba antes aca, debido a que a la hora de guardar se estaba
		// comiendo decimales
		// en el cambio a double y m/s^2, es mejor dejar que el repositorio se encargue
		// de toda la logica de guardado que hacerlo nosotros mismos.
		sampleRepository.save(sample);

	}

	private Long generateUniqueId() {
		return System.currentTimeMillis();
	}

	public SampleDTO getSampleByID(long sampleId, long evaluatedId, long testTypeID) {
		Sample sample = sampleRepository.getReferenceById(new SampleId(sampleId, evaluatedId, testTypeID));
		if (sample == null) {
			return null;
		}
		return SampleDTO.builder()
				.id(sample.getId())
				.evaluatedId(sample.getEvaluatedId())
				.testTypeId(sample.getTestTypeId())
				.onOffState(sample.getOnOffState())
				.date(sample.getDate())
				.aptitudeForTheTest(sample.getAptitudeForTheTest())
				.rawData(sample.getRawData()) // You can also choose to map specific fields from rawData
				.build();
	}
}