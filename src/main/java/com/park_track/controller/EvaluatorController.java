package com.park_track.controller;

import java.util.List;

import com.park_track.dto.evaluator.EvaluatorRegisterDTO;
import com.park_track.dto.evaluator.EvaluatorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.park_track.dto.UserDTO;
import com.park_track.service.EvaluatorService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/evaluators")
@RequiredArgsConstructor
public class EvaluatorController {

	private final EvaluatorService evaluatorService;

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllEvaluators() {
		return new ResponseEntity<>(evaluatorService.getAllEvaluators(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id_number}")
	public ResponseEntity<Void> deleteEvaluator(@PathVariable("id_number") String id_number) {
		Boolean wasDeleted = evaluatorService.deleteEvaluatorByIdNumber(id_number);
		return wasDeleted ? new ResponseEntity<>(HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addEvaluator(@RequestBody EvaluatorRegisterDTO evaluatorRegisterDTO) {
		EvaluatorResponseDTO createdEvaluator = evaluatorService.createEvaluator(evaluatorRegisterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluator);
	}

}
