package com.park_track.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park_track.dto.AuthResponseDTO;
import com.park_track.dto.LoginRequestDTO;
import com.park_track.service.AuthService;

import lombok.RequiredArgsConstructor;

/**
 * This class will be used for routes for everything that has to do with authentication.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

	private final AuthService authService;

	@PostMapping("login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
		return ResponseEntity.ok(authService.login(request));
	}

}
