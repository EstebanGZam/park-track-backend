package com.park_track.dto.listOfEvaluated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluatedResponseDTO {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String typeOfEvaluated;
}
