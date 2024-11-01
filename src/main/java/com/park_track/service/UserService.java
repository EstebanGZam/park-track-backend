package com.park_track.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.park_track.dto.UserDTO;
import com.park_track.model.Role;
import com.park_track.model.User;
import com.park_track.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllDoctors() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            if (user.getRole() == Role.DOCTOR) {
                usersDTO.add(
                        UserDTO
                                .builder()
                                .username(user.getUsername())
                                .build());
            }
        }

        return usersDTO;
    }

}
