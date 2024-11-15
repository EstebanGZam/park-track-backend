package com.park_track.controller;

import com.park_track.dto.UserResponseDTO;
import com.park_track.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userlist")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
