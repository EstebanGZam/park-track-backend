package com.park_track.dto;

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
public class SampleDTO {

    private Long id;
    private Long evaluatedId;
    private Long testTypeId;
    private String onOffState;
    private Timestamp date;
    private String aptitudeForTheTest;
    private List<ObservationNoteDTO> observationNotes;
    private RawData rawData; // You can include RawData fields or simplify based on your needs

}
