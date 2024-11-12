package com.park_track.controller;

import com.park_track.dto.EvaluatedDTO;
import com.park_track.dto.evaluatedFilter.EvaluatedFilterDTO;
import com.park_track.dto.listOfEvaluated.EvaluatedResponseDTO;
import com.park_track.service.EvaluatedService;
import com.park_track.service.interfaces.EvaluatedFilterService;
import com.park_track.service.interfaces.ListOfEvaluatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/evaluated")
public class EvaluatedController {

	private final EvaluatedService evaluatedService;
	private final EvaluatedFilterService evaluatedFilterService;
	private final ListOfEvaluatedService listOfEvaluatedService;

	@GetMapping
	public ResponseEntity<List<EvaluatedDTO>> getAllEvaluated() {
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

	@PostMapping("/create")
	public ResponseEntity<Void> createEvaluated(@RequestBody EvaluatedDTO evaluated) {
		try {
			evaluatedService.createEvaluated(evaluated);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/filter")
	public ResponseEntity<List<EvaluatedResponseDTO>> getAllEvaluated(EvaluatedFilterDTO filterDTO) {
		return new ResponseEntity<>(evaluatedFilterService.getAllEvaluated(filterDTO), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<EvaluatedResponseDTO>> getAllEvaluatedList() {
		return new ResponseEntity<>(listOfEvaluatedService.getAllEvaluated(), HttpStatus.OK);
	}
}