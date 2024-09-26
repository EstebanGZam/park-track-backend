package com.park_track.controller;

import com.park_track.dto.SensorDataDTO;
import com.park_track.entity.Sample;
import com.park_track.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/hardware_controller")
public class HardwareController {

	private static final Logger logger = LoggerFactory.getLogger(HardwareController.class);

	@Autowired
	private SensorDataService sensorDataService;

	@PostMapping("/receive_data")
	public ResponseEntity<?> receiveSensorData(@RequestBody SensorDataDTO sensorDataDTO) {
		logger.info("Received data: " + sensorDataDTO);
		try {
			Sample savedSample = sensorDataService.saveSensorData(sensorDataDTO);
			return ResponseEntity.status(HttpStatus.OK).body(savedSample);
		} catch (Exception e) {
			logger.error("Error processing sensor data", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar los datos: " + e.getMessage());
		}
	}
}