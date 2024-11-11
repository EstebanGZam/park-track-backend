package com.park_track.controller;

import com.park_track.dto.SampleDTO;
import com.park_track.dto.SensorDataDTO;
import com.park_track.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/hardware_controller")
@CrossOrigin("*")
public class HardwareController {

	private static final Logger logger = LoggerFactory.getLogger(HardwareController.class);

	private final SensorDataService sensorDataService;

	@Autowired
	public HardwareController(SensorDataService sensorDataService) {
		this.sensorDataService = sensorDataService;
	}

	@PostMapping("/receive_data")
	public ResponseEntity<String> receiveSensorData(@RequestBody SensorDataDTO sensorData) {
		try {
			sensorDataService.saveSensorData(sensorData);
		} catch (Exception e) {
			logger.error("Error saving data: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error saving data");
		}
		return ResponseEntity.ok("Data received and saved successfully!");
	}

	// Por ahora la muestra se busca por ID, que es esa clave compuesta SampleId, esto lo podemos cambiar más adelante, de hecho se debería.
	@GetMapping("/sample")
	public ResponseEntity<SampleDTO> getSampleById(@RequestParam("sampleID") long sampleId,
												   @RequestParam("evaluatedId") long evaluatedId, @RequestParam("testTypeId") long testTypeID) {
		logger.info("endpoint sample hit");
		SampleDTO sample = sensorDataService.getSampleByID(sampleId, evaluatedId, testTypeID);
		if (sample == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(sample, HttpStatus.OK);
		}
	}
}