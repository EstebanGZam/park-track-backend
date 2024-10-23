package com.park_track.controller;

import com.park_track.service.EvaluatedService;
import com.park_track.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(maxAge = 3600)
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
		System.out.println("evaluatedId = " + evaluatedId);
        System.out.println("sampleId = " + id);
        System.out.println("testTypeId = " + testTypeId);
		boolean deleted = sampleService.deleteSampleIfExists(evaluatedId, id, testTypeId);
		if (deleted) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}