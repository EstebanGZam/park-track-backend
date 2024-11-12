package com.park_track.dto.evaluated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluatedRequestDTO {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String typeOfEvaluated;
}