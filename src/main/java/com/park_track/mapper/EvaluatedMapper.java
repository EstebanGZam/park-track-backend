package com.park_track.mapper;

import com.park_track.dto.evaluated.EvaluatedRegisterCamelCaseDTO;
import com.park_track.dto.evaluated.EvaluatedRegisterDTO;
import com.park_track.dto.evaluated.EvaluatedResponseDTO;
import com.park_track.entity.Evaluated;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public class EvaluatedMapper {

	//	EvaluatedResponseDTO toEvaluatedResponseDTO(Evaluated evaluated);
	public EvaluatedResponseDTO toEvaluatedResponseDTO(Evaluated evaluated) {
		if (evaluated == null) {
			return null;  // Manejar el caso de null para evitar errores
		}

		return new EvaluatedResponseDTO(
				evaluated.getId(),
				evaluated.getIdNumber(),
				evaluated.getFirstName(),
				evaluated.getLastName(),
				evaluated.getEmail(),
				evaluated.getTypeOfEvaluated().getType()
		);
	}

	public EvaluatedRegisterDTO convertEvaluatedToDTO(Evaluated evaluated) {
		return EvaluatedRegisterDTO.builder()
				.idNumber(evaluated.getIdNumber())
				.firstName(evaluated.getFirstName())
				.lastName(evaluated.getLastName())
				.dateOfBirth(evaluated.getDateOfBirth())
				.email(evaluated.getEmail())
				.familyHistoryParkinson(evaluated.getFamilyHistoryParkinson())
				.height(evaluated.getHeight())
				.weight(evaluated.getWeight())
				.typeOfEvaluated(evaluated.getTypeOfEvaluated().getType()) // Obtener tipo de evaluado
				.sex(evaluated.getSex().getSex()) // Obtener sexo
				.build();
	}


}
