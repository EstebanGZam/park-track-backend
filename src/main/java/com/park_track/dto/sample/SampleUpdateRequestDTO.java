package com.park_track.dto.sample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleUpdateRequestDTO {
    private String onOffState;
    private String aptitudeForTheTest;
    private List<String> notes;

    // Validación: Si onOffState es OFF, notes no puede estar vacía
    public boolean isValid() {
        if ("OFF".equalsIgnoreCase(onOffState) && (notes == null || notes.isEmpty())) {
            return false;
        }
        return true;
    }
}
