package com.park_track.controller;

import com.park_track.dto.TypeOfTestDTO;
import com.park_track.service.TypeOfTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(maxAge = 3600)
public class WebController {

	private final TypeOfTestService typeOfTestService;

	@Autowired
	public WebController(TypeOfTestService typeOfTestService) {
		this.typeOfTestService = typeOfTestService;
	}

	@GetMapping("/getTestDescription")
	public ResponseEntity<TypeOfTestDTO> getTestDescription(@RequestParam String testType) {
		System.out.println(testType);
		Optional<TypeOfTestDTO> typeOfTestDTOOptional = typeOfTestService.getTypeOfTestByType(testType);

		return typeOfTestDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
