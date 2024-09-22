package com.park_track.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hardware−controller")
public class HardwareController {

	@PostMapping("/receive-data")
	public ResponseEntity<?> receiveSensorData(@RequestBody String sample) {
		System.out.println(sample);
		return ResponseEntity.status(HttpStatus.OK).body(sample);
	}
}
