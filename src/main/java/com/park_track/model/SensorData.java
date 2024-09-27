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
    private int ax;
    private int ay;
    private int az;
    private int gx;
    private int gy;
    private int gz;
}