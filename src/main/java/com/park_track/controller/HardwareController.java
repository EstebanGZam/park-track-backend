package com.park_track.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class HardwareController {

	private static final Logger logger = LoggerFactory.getLogger(HardwareController.class);

	@Autowired
	private SensorDataService sensorDataService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/receive_data")
	public ResponseEntity<String> receiveSensorData(@RequestBody SensorDataDTO sensorData) {
		try {
			String sensorDataJson = objectMapper.writeValueAsString(sensorData);
			logger.info("Received sensor data: {}", sensorDataJson);
		} catch (JsonProcessingException e) {
			logger.error("Error processing JSON: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error processing data");
		}
		return ResponseEntity.ok("Data received successfully!");
	}
}