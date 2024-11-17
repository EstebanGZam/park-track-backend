package com.park_track.mapper;

import com.park_track.dto.UserResponseDTO;
import com.park_track.entity.Evaluated;
import com.park_track.entity.Evaluator;

public class UserMapper {
    public static UserResponseDTO toUserResponseDTO(Evaluator evaluator) {
        if (evaluator == null) return null;

        return new UserResponseDTO(
                evaluator.getIdNumber(),
                evaluator.getFirstName(),
                evaluator.getLastName(),
                evaluator.getEmail(),
                null,
                evaluator.getUser().getRole().name()
        );
    }

    public static UserResponseDTO toUserResponseDTO(Evaluated evaluated) {
        if (evaluated == null) return null;

        return new UserResponseDTO(
                evaluated.getIdNumber(),
                evaluated.getFirstName(),
                evaluated.getLastName(),
                evaluated.getEmail(),
                evaluated.getTypeOfEvaluated().getType(),
                null
        );
    }
}
