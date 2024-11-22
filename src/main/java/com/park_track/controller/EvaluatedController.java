package com.park_track.controller;


import com.park_track.dto.evaluated.EvaluatedRegisterCamelCaseDTO;
import com.park_track.dto.evaluated.EvaluatedRegisterDTO;
import com.park_track.dto.evaluated_filter.EvaluatedFilterDTO;
import com.park_track.dto.evaluated.EvaluatedResponseDTO;
import com.park_track.entity.Evaluated;
import com.park_track.service.EvaluatedService;
import com.park_track.service.interfaces.EvaluatedFilterService;
import com.park_track.service.interfaces.ListOfEvaluatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/evaluated")
public class EvaluatedController {

	private final EvaluatedService evaluatedService;
	private final EvaluatedFilterService evaluatedFilterService;
	private final ListOfEvaluatedService listOfEvaluatedService;

	@GetMapping
	public ResponseEntity<List<EvaluatedResponseDTO>> getAllEvaluated() {
		return new ResponseEntity<>(evaluatedService.getAllEvaluated(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteEvaluated(@PathVariable("id") String idNumber) {
		Boolean wasDeleted = evaluatedService.deleteEvaluatedByIdNumber(idNumber);
		if (wasDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> addEvaluated(@RequestBody EvaluatedRegisterDTO evaluatedRegisterDTO) {
		System.out.println(evaluatedRegisterDTO);
		EvaluatedResponseDTO createdEvaluated = evaluatedService.createEvaluated(evaluatedRegisterDTO);
		System.out.println(createdEvaluated);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluated);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<EvaluatedResponseDTO>> getAllEvaluated(EvaluatedFilterDTO filterDTO) {
		return new ResponseEntity<>(evaluatedFilterService.getAllEvaluated(filterDTO), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<EvaluatedResponseDTO>> getAllEvaluatedList() {
		return new ResponseEntity<>(listOfEvaluatedService.getAllEvaluated(), HttpStatus.OK);
	}

	@GetMapping("/details/{idNumber}")
	public ResponseEntity<EvaluatedRegisterCamelCaseDTO> getEvaluatedDetails(@PathVariable String idNumber) {
		Optional<EvaluatedRegisterDTO> evaluatedDTOOptional = evaluatedService.getEvaluatedByIdNumber(idNumber);
		return evaluatedDTOOptional.map(dto -> ResponseEntity.ok(evaluatedService.convertToCamelCaseDTO(dto)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}











}