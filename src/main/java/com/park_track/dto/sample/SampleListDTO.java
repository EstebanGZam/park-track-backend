package com.park_track.dto.sample;

import com.park_track.model.RawData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleListDTO {
    private Long id;
    private Long typeOfTestId;
    private Timestamp date;
    private String description;
    private String onOffState;
    private RawData rawData;
    private String aptitudeForTheTest;
    private List<String> comments;

}
