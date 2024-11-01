package com.park_track.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.park_track.dto.UserDTO;
import com.park_track.entity.User;
import com.park_track.model.Role;
import com.park_track.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllDoctors() {
        List<User> doctors = userRepository.findByRole(Role.DOCTOR);

        // Convertimos los resultados en una lista de UserDTO
        return doctors.stream()
                .map(user -> UserDTO.builder()
                        .username(user.getUsername())
                        .build())
                .collect(Collectors.toList());
    }

    public Boolean deleteDoctorByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            return true;
        }
        return false;
    }

}
