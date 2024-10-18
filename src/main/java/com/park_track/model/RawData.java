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

    private static final double ACCEL_SENSITIVITY = 16384.0; // for ±2g
    private static final double GYRO_SENSITIVITY = 131.0; // for ±250°/s
    private static final double GRAVITY = 9.81; // m/s²
    private static final double DEG_TO_RAD = Math.PI / 180;

    private Map<String, Map<String, SensorData>> sensors = new HashMap<>();

    @JsonAnySetter
    public void setSensorProperty(String key, Map<String, SensorData> value) {
        sensors.put(key, value);
    }

    // Method to convert all sensor data to common units and return a new RawData object
        public RawData convertToCommonUnits() {
        Map<String, Map<String, SensorData>> convertedData = new HashMap<>();

        for (Map.Entry<String, Map<String, SensorData>> sensorEntry : sensors.entrySet()) {
            Map<String, SensorData> convertedSamples = new HashMap<>();

            for (Map.Entry<String, SensorData> sampleEntry : sensorEntry.getValue().entrySet()) {
                SensorData rawData = sampleEntry.getValue();

                // Convert accelerometer data to m/s²
                double ax = (rawData.getAx() / ACCEL_SENSITIVITY) * GRAVITY;
                double ay = (rawData.getAy() / ACCEL_SENSITIVITY) * GRAVITY;
                double az = (rawData.getAz() / ACCEL_SENSITIVITY) * GRAVITY;

                // Convert gyroscope data to rad/s
                double gx = (rawData.getGx() / GYRO_SENSITIVITY) * DEG_TO_RAD;
                double gy = (rawData.getGy() / GYRO_SENSITIVITY) * DEG_TO_RAD;
                double gz = (rawData.getGz() / GYRO_SENSITIVITY) * DEG_TO_RAD;

                // Create a new SensorData object with double values
                SensorData convertedDataSample = SensorData.builder()
                        .ax(ax)
                        .ay(ay)
                        .az(az)
                        .gx(gx)
                        .gy(gy)
                        .gz(gz)
                        .build();

                convertedSamples.put(sampleEntry.getKey(), convertedDataSample);
            }
            convertedData.put(sensorEntry.getKey(), convertedSamples);
        }

        // Return a new RawData object with the converted data
        return RawData.builder()
                .sensors(convertedData)
                .build();
    }
}