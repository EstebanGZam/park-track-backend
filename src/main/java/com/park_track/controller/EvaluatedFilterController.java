package com.park_track.controller;

import com.park_track.dto.evaluatedFilter.EvaluatedFilterDTO;
import com.park_track.dto.listOfEvaluated.EvaluatedResponseDTO;
import com.park_track.service.interfaces.EvaluatedFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/evaluatedfilter")
public class EvaluatedFilterController {
    private final EvaluatedFilterService evaluatedService;

    @GetMapping
    public ResponseEntity<List<EvaluatedResponseDTO>> getAllEvaluated(EvaluatedFilterDTO filterDTO) {
        return new ResponseEntity<>(evaluatedService.getAllEvaluated(filterDTO), HttpStatus.OK);
    }
}