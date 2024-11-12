package com.park_track.controller;

import com.park_track.dto.listOfEvaluated.EvaluatedResponseDTO;
import com.park_track.service.interfaces.ListOfEvaluatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/listofevaluated")
public class ListOfEvaluatedController {
    private final ListOfEvaluatedService evaluatedService;

    @GetMapping
    public ResponseEntity<List<EvaluatedResponseDTO>> getAllEvaluated() {
        return new ResponseEntity<>(evaluatedService.getAllEvaluated(), HttpStatus.OK);
    }
}