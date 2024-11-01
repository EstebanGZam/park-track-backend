package com.park_track.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park_track.dto.EvaluatedDTO;
import com.park_track.service.EvaluatedService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/evaluated")
public class EvaluatedController {

    private final EvaluatedService evaluatedService;

    @GetMapping
    public ResponseEntity<List<EvaluatedDTO>> getAllEvaluated() {
        return new ResponseEntity<List<EvaluatedDTO>>(evaluatedService.getAllEvaluated(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvaluated(@PathParam("id") String idNumber) {

        Boolean wasDeleted = evaluatedService.deleteEvaluatedByIdNumber(idNumber);
        if (wasDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<Void> createEvaluated(@RequestBody EvaluatedDTO evaluated) {
        try {

            evaluatedService.createEvaluated(evaluated);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
