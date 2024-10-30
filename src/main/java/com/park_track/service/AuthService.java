package com.park_track.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.park_track.JWT.JWTService;
import com.park_track.dto.AuthResponseDTO;
import com.park_track.dto.LoginRequestDTO;
import com.park_track.dto.RegisterRequestDTO;
import com.park_track.model.User;
import com.park_track.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthResponseDTO login(LoginRequestDTO request) {
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(userDetails);
            return AuthResponseDTO.builder()
                    .token(token)
                    .status(true)
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return AuthResponseDTO.builder()
                    .token(null)
                    .status(false)
                    .build();
        }
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        boolean exists = userRepository.existsByUsername(request.getUsername());
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        Boolean status;
        String token = "";
        if (!exists) {
            userRepository.save(user);
            status = true;
            token = jwtService.getToken(user);
        } else {
            status = false;
        }
        return AuthResponseDTO.builder()
                .token(token)
                .status(status)
                .build();
    }

}
