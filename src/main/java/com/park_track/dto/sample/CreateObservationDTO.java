package com.park_track.dto.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateObservationDTO {
    private String description;
}
