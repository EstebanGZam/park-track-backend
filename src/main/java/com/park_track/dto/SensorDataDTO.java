package com.park_track.dto;

import com.park_track.model.RawData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SensorDataDTO {
    private MetadataDTO metadata;
    private RawData data;
}