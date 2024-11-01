package com.park_track.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park_track.dto.UserDTO;
import com.park_track.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllDoctors() {
        return new ResponseEntity<List<UserDTO>>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable ("username") String username) {

        Boolean wasDeleted = doctorService.deleteDoctorByUsername(username);
        if (wasDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
