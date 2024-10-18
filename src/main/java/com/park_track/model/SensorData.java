package com.park_track.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SensorData {
    private double ax;
    private double ay;
    private double az;
    private double gx;
    private double gy;
    private double gz;
}