package com.park_track.controller;

import com.park_track.dto.sample.SampleListDTO;
import com.park_track.service.EvaluatedService;
import com.park_track.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sample")
@CrossOrigin("*")
public class SampleController {
	private final SampleService sampleService;
	private final EvaluatedService evaluatedService;

	@Autowired
	public SampleController(SampleService sampleService, EvaluatedService evaluatedService) {
		this.evaluatedService = evaluatedService;
		this.sampleService = sampleService;
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

	@GetMapping("/samples/{evaluatedId}")
	public ResponseEntity<List<SampleListDTO>> getSamplesByEvaluatedId(@PathVariable Long evaluatedId) {
		List<SampleListDTO> samples = sampleService.getSamplesByEvaluatedId(evaluatedId);
		return ResponseEntity.ok(samples);
	}
}