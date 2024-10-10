package com.park_track.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RawData {
    private Map<String, Map<String, SensorData>> sensors = new HashMap<>();

    @JsonAnySetter
    public void setSensorProperty(String key, Map<String, SensorData> value) {
        sensors.put(key, value);
    }
}