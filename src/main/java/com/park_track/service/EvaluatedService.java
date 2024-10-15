package com.park_track.service;

import com.park_track.dto.EvaluatedDTO;
import com.park_track.entity.Evaluated;
import com.park_track.repository.EvaluatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluatedService {

	private final EvaluatedRepository evaluatedRepository;

	@Autowired
	public EvaluatedService(EvaluatedRepository evaluatedRepository) {
		this.evaluatedRepository = evaluatedRepository;
	}

	public Optional<EvaluatedDTO> getEvaluatedByIdNumber(String idNumber) {
		Optional<Evaluated> evaluatedOptional = evaluatedRepository.findByIdNumber(idNumber);
		return evaluatedOptional.map(this::convertToDTO);
	}

	private EvaluatedDTO convertToDTO(Evaluated evaluated) {
		return EvaluatedDTO.builder()
				.evaluatorId(evaluated.getEvaluatorId())
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

	public Long getEvaluatedIdByIdNumber(String idNumber) {
		Optional<Evaluated> evaluatedOptional = evaluatedRepository.findByIdNumber(idNumber);
		return evaluatedOptional.map(Evaluated::getEvaluatorId).orElse(null);
	}
}
