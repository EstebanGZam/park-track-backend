package com.park_track.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park_track.dto.UserDTO;
import com.park_track.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllDoctors(){
        return new ResponseEntity<List<UserDTO>>(userService.getAllDoctors(),HttpStatus.OK);
    }
}
