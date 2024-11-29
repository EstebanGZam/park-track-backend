package com.park_track.controller;

import com.park_track.dto.SampleDTO;
import com.park_track.dto.SensorDataDTO;
import com.park_track.dto.sample.SampleListDTO;
import com.park_track.service.EvaluatedService;
import com.park_track.service.SampleService;
import com.park_track.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("samples")
@CrossOrigin("*")
public class SampleController {
	private final Logger logger = LoggerFactory.getLogger(SampleController.class);
	private final SampleService sampleService;
	private final SensorDataService sensorDataService;
	private final EvaluatedService evaluatedService;

	@Autowired
	public SampleController(SampleService sampleService, SensorDataService sensorDataService, EvaluatedService evaluatedService) {
		this.sampleService = sampleService;
		this.sensorDataService = sensorDataService;
		this.evaluatedService = evaluatedService;
	}

	@PostMapping("/receive-data")
	public ResponseEntity<?> receiveSensorData(@RequestBody SensorDataDTO sensorData) {
		try {
			sensorDataService.saveSensorData(sensorData);
			return ResponseEntity.ok("Data received and saved successfully!");
		} catch (Exception e) {
			logger.error("Error while saving sensor data: {}", e.getMessage(), e);
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "An error occurred while saving sensor data.");
			errorResponse.put("error", e.getMessage());
			errorResponse.put("timestamp", LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@GetMapping("/{evaluatedId}")
	public ResponseEntity<?> getSamplesByEvaluatedId(@PathVariable Long evaluatedId) {
		List<SampleListDTO> samples = sampleService.getSamplesByEvaluatedId(evaluatedId);
		return ResponseEntity.ok(samples);
	}

	@GetMapping
	public ResponseEntity<SampleDTO> getSampleById(@RequestParam("sampleID") long sampleId,
												   @RequestParam("evaluatedId") long evaluatedId,
												   @RequestParam("testTypeId") long testTypeID) {
		logger.info("endpoint sample hit");
		SampleDTO sample = sensorDataService.getSampleByID(sampleId, evaluatedId, testTypeID);
		return sample == null ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(sample, HttpStatus.OK);
	}

	@DeleteMapping("/samples")
	public ResponseEntity<?> deleteSample(
			@RequestParam String evaluatedIdNumber,
			@RequestParam Long id,
			@RequestParam Long testTypeId) {
		Long evaluatedId = evaluatedService.getEvaluatedIdByIdNumber(evaluatedIdNumber);
		boolean deleted = sampleService.deleteSampleIfExists(evaluatedId, id, testTypeId);
		if (deleted) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}