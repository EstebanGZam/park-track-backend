package com.park_track.controller;

import com.park_track.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(maxAge = 3600)
public class SampleController {

    private final SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @DeleteMapping("/samples")
    public ResponseEntity<?> deleteSample(
            @RequestParam Long evaluatedId,
            @RequestParam Long id,
            @RequestParam Long testTypeId) {

        boolean deleted = sampleService.deleteSampleIfExists(evaluatedId, id, testTypeId);

        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}