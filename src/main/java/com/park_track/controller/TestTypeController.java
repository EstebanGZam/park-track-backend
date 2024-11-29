package com.park_track.controller;

import com.park_track.dto.TypeOfTestDTO;
import com.park_track.service.EvaluatedService;
import com.park_track.service.TypeOfTestService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/test-type")
@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
public class TestTypeController {

	private final TypeOfTestService typeOfTestService;
	private final EvaluatedService evaluatedService;

	@GetMapping("/get-test-description")
	public ResponseEntity<TypeOfTestDTO> getTestDescription(@RequestParam String testType) {
		System.out.println(testType);
		Optional<TypeOfTestDTO> typeOfTestDTOOptional = typeOfTestService.getTypeOfTestByType(testType);

		return typeOfTestDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
