package com.park_track.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MetadataDTO {
    private String evaluatedId;
    private String typeOfTest;
    private String dateAndTime;
}