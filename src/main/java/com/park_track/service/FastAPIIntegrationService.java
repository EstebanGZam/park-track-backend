package com.park_track.service;

import com.park_track.model.RawData;
import com.park_track.model.SensorData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class FastAPIIntegrationService {
    private final WebClient webClient;

    public FastAPIIntegrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8000")
                .build();
    }

    public Mono<Map> processSensorData(RawData rawData) {
        return webClient.post()
                .uri("/process-sensor-data/")
                .bodyValue(convertToFastAPIFormat(rawData))
                .retrieve()
                .bodyToMono(Map.class);
    }

    private Object convertToFastAPIFormat(RawData rawData) {
        Map<String, Map<String, Map<String, Object>>> transformedData = new HashMap<>();

        for (Map.Entry<String, Map<String, SensorData>> sensorEntry : rawData.getSensors().entrySet()) {
            Map<String, Map<String, Object>> sampleMap = new HashMap<>();

            for (Map.Entry<String, SensorData> sample : sensorEntry.getValue().entrySet()) {
                SensorData sensorData = sample.getValue();
                Map<String, Object> sampleData = new HashMap<>();
                sampleData.put("ax", sensorData.getAx());
                sampleData.put("ay", sensorData.getAy());
                sampleData.put("az", sensorData.getAz());
                sampleData.put("gx", sensorData.getGx());
                sampleData.put("gy", sensorData.getGy());
                sampleData.put("gz", sensorData.getGz());
                sampleData.put("timestamp", sensorData.getTimestamp());

                sampleMap.put(sample.getKey(), sampleData);
            }

            transformedData.put(sensorEntry.getKey(), sampleMap);
        }

        Map<String, Object> fastAPIPayload = new HashMap<>();
        fastAPIPayload.put("sensors", transformedData);

        return fastAPIPayload;
    }
}