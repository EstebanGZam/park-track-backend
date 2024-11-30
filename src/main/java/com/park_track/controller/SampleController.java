package com.park_track.controller;

import com.park_track.dto.SampleDTO;
import com.park_track.dto.SensorDataDTO;
import com.park_track.dto.sample.SampleListDTO;
import com.park_track.dto.sample.SampleUpdateRequestDTO;
import com.park_track.service.EvaluatedService;
import com.park_track.service.FastAPIIntegrationService;
import com.park_track.service.SampleService;
import com.park_track.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
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
	private final FastAPIIntegrationService fastAPIService;

	@Autowired
	public SampleController(SampleService sampleService, SensorDataService sensorDataService, EvaluatedService evaluatedService, FastAPIIntegrationService fastAPIService) {
		this.sampleService = sampleService;
		this.sensorDataService = sensorDataService;
		this.evaluatedService = evaluatedService;
		this.fastAPIService = fastAPIService;
	}

	@PostMapping("/receive-data")
	public Mono<ResponseEntity<? extends Map<String, ? extends Object>>> receiveSensorData(@RequestBody SensorDataDTO sensorData) {
		return Mono.fromCallable(() -> {
			try {
				sensorDataService.saveSensorData(sensorData);
				Mono<Map> fftResult = fastAPIService.processSensorData(sensorData.getData());
				return ResponseEntity.ok(Map.of(
						"message", "Data received and saved successfully!",
						"fftAnalysis", fftResult.block()
				));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(Map.of(
								"message", "Error processing sensor data",
								"error", e.getMessage(),
								"timestamp", LocalDateTime.now()
						));
			}
		}).subscribeOn(Schedulers.boundedElastic());
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
		logger.info("Fetching sample details with notes");
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

	@PutMapping("/{evaluatedId}/{sampleId}/{testTypeId}")
	public ResponseEntity<?> updateSample(
			@PathVariable Long evaluatedId,
			@PathVariable Long sampleId,
			@PathVariable Long testTypeId,
			@RequestBody SampleUpdateRequestDTO updateRequest) {
		try {
			sampleService.updateSample(evaluatedId, sampleId, testTypeId, updateRequest);
			return ResponseEntity.ok(Map.of("message", "Sample updated successfully"));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
		}
	}
}