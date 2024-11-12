package com.park_track.mapper;

import com.park_track.dto.EvaluatorResponseDTO;
import com.park_track.entity.Evaluator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class EvaluatorMapper {

	public static EvaluatorResponseDTO toEvaluatorResponseDTO(Evaluator evaluator) {
		if (evaluator == null) {
			return null;  // Manejar el caso de null para evitar errores
		}

		return new EvaluatorResponseDTO(
				evaluator.getId(),
				evaluator.getIdNumber(),
				evaluator.getUser().getRole().name(),
				evaluator.getFirstName(),
				evaluator.getLastName(),
				evaluator.getEmail()
		);
	}
}
