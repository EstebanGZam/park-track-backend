package com.park_track.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.park_track.dto.evaluated.EvaluatedResponseDTO;
import com.park_track.dto.evaluator.EvaluatorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String typeOfEvaluated;
    private String role;

    public UserResponseDTO fromEvaluatorResponseDTO(EvaluatorResponseDTO evaluator) {
        return new UserResponseDTO(
                evaluator.getIdNumber(),
                evaluator.getFirstName(),
                evaluator.getLastName(),
                evaluator.getEmail(),
                null,
                evaluator.getRole()
        );
    }

    public UserResponseDTO fromEvaluatedResponseDTO(EvaluatedResponseDTO evaluated) {
        return new UserResponseDTO(
                evaluated.getIdNumber(),
                evaluated.getFirstName(),
                evaluated.getLastName(),
                evaluated.getEmail(),
                evaluated.getTypeOfEvaluated(),
                null
        );
    }
}
