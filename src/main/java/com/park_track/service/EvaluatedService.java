package com.park_track.service;

import com.park_track.dto.EvaluatedDTO;
import com.park_track.entity.Evaluated;
import com.park_track.entity.Sex;
import com.park_track.entity.TypeOfEvaluated;
import com.park_track.repository.EvaluatedRepository;
import com.park_track.repository.SexRepository;
import com.park_track.repository.TypeOfEvaluatedRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluatedService {

	private final EvaluatedRepository evaluatedRepository;
	private final SexRepository sexRepository;
	private final TypeOfEvaluatedRepository typeOfEvaluatedRepository;

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

	public Boolean deleteEvaluatedByIdNumber(String id) {
		if (evaluatedRepository.existsByIdNumber(id)) {
			evaluatedRepository.deleteByIdNumber(id);
			return true;
		}
		return false;
	}

	public List<EvaluatedDTO> getAllEvaluated() {

		List<Evaluated> evaluated = evaluatedRepository.findAll();

		// Convertimos los resultados en una lista con ciertos campos filtrados
		return evaluated.stream()
				.map(user -> EvaluatedDTO.builder()
						.dateOfBirth(user.getDateOfBirth())
						.email(user.getEmail())
						.familyHistoryParkinson(user.getFamilyHistoryParkinson())
						.firstName(user.getFirstName())
						.height(user.getHeight())
						.idNumber(user.getIdNumber())
						.lastName(user.getLastName())
						.weight(user.getWeight())
						.build())
				.collect(Collectors.toList());
	}

	public void createEvaluated(EvaluatedDTO evaluated) {
		Sex sex = sexRepository.findSexByString(evaluated.getSex());
		TypeOfEvaluated typeOfEvaluated = typeOfEvaluatedRepository
				.findTypeOfEvaluatedByString(evaluated.getTypeOfEvaluated());

		if (sex == null || typeOfEvaluated == null) {
			throw new RuntimeException("sex or typeOfEvaluated not found not found");
		}

		Evaluated evaluatedBeinCreated = Evaluated.builder()
				.evaluatorId(evaluated.getEvaluatorId())
				.dateOfBirth(evaluated.getDateOfBirth())
				.email(evaluated.getEmail())
				.familyHistoryParkinson(evaluated.getFamilyHistoryParkinson())
				.firstName(evaluated.getFirstName())
				.height(evaluated.getHeight())
				.idNumber(evaluated.getIdNumber())
				.lastName(evaluated.getLastName())
				.weight(evaluated.getWeight())
				.sex(sex)
				.typeOfEvaluated(typeOfEvaluated)
				.build();

		evaluatedRepository.save(evaluatedBeinCreated);

	}
}
