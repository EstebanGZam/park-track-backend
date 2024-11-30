package com.park_track.dto;

import java.util.Map;

public class SampleDetailsResponseDTO {
    private SampleDTO sampleDetails;
    private Map<String, Object> fftAnalysis;

    // Getters y Setters
    public SampleDTO getSampleDetails() {
        return sampleDetails;
    }

    public void setSampleDetails(SampleDTO sampleDetails) {
        this.sampleDetails = sampleDetails;
    }

    public Map<String, Object> getFftAnalysis() {
        return fftAnalysis;
    }

    public void setFftAnalysis(Map<String, Object> fftAnalysis) {
        this.fftAnalysis = fftAnalysis;
    }
}