package com.park_track.controller;

import java.util.List;

import com.park_track.dto.EvaluatorRegisterDTO;
import com.park_track.dto.EvaluatorResponseDTO;
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

	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Void> deleteEvaluator(@PathVariable("username") String username) {
		Boolean wasDeleted = evaluatorService.deleteEvaluatorByUsername(username);
		return wasDeleted ? new ResponseEntity<>(HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addEvaluator(@RequestBody EvaluatorRegisterDTO evaluatorRegisterDTO) {
		EvaluatorResponseDTO createdEvaluator = evaluatorService.createEvaluator(evaluatorRegisterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluator);
	}
}
