package com.park_track.service;

import com.park_track.dto.evaluated.EvaluatedRegisterDTO;
import com.park_track.dto.evaluated.EvaluatedResponseDTO;
import com.park_track.entity.Evaluated;
import com.park_track.entity.Sex;
import com.park_track.entity.TypeOfEvaluated;
import com.park_track.exceptions.SexNotFoundException;
import com.park_track.exceptions.TypeOfEvaluatedNotFoundException;
import com.park_track.mapper.EvaluatedMapper;
import com.park_track.repository.EvaluatedRepository;
import com.park_track.repository.SexRepository;
import com.park_track.repository.TypeOfEvaluatedRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluatedService {

	private final EvaluatedRepository evaluatedRepository;
	private final SexRepository sexRepository;
	private TypeOfEvaluatedRepository typeOfEvaluatedRepository;
	private final EvaluatedMapper evaluatedMapper;

	@Autowired
	public EvaluatedService(EvaluatedRepository evaluatedRepository, SexRepository sexRepository, TypeOfEvaluatedRepository typeOfEvaluatedRepository, EvaluatedMapper evaluatedMapper) {
		this.evaluatedRepository = evaluatedRepository;
		this.sexRepository = sexRepository;
		this.typeOfEvaluatedRepository = typeOfEvaluatedRepository;
		this.evaluatedMapper = evaluatedMapper;
	}

	public Optional<EvaluatedRegisterDTO> getEvaluatedByIdNumber(String idNumber) {
		Optional<Evaluated> evaluatedOptional = evaluatedRepository.findByIdNumber(idNumber);
		return evaluatedOptional.map(evaluatedMapper::convertEvaluatedToDTO);
	}

	public Long getEvaluatedIdByIdNumber(String idNumber) {
		Optional<Evaluated> evaluatedOptional = evaluatedRepository.findByIdNumber(idNumber);
		return evaluatedOptional.map(Evaluated::getId).orElse(null);
	}

	public Boolean deleteEvaluatedByIdNumber(String id) {
		if (evaluatedRepository.existsByIdNumber(id)) {
			evaluatedRepository.deleteByIdNumber(id);
			return true;
		}
		return false;
	}

	public List<EvaluatedResponseDTO> getAllEvaluated() {
		List<Evaluated> evaluated = evaluatedRepository.findAll();
		// Usamos el mapper para convertir los evaluados en EvaluatedResponseDTO
		return evaluated.stream()
				.map(evaluatedMapper::toEvaluatedResponseDTO)  // Usamos el Mapper para la conversión
				.collect(Collectors.toList());
	}

	@Transactional
	public EvaluatedResponseDTO createEvaluated(EvaluatedRegisterDTO evaluatedRegisterDTO) {
		Sex sex = sexRepository.findSexByString(evaluatedRegisterDTO.getSex());
		if (sex == null) {
			throw new SexNotFoundException("Sex not found: " + evaluatedRegisterDTO.getSex());
		}

		TypeOfEvaluated typeOfEvaluated = typeOfEvaluatedRepository
				.findTypeOfEvaluatedByString(evaluatedRegisterDTO.getTypeOfEvaluated());
		if (typeOfEvaluated == null) {
			throw new TypeOfEvaluatedNotFoundException("Type of Evaluated not found: " + evaluatedRegisterDTO.getTypeOfEvaluated());
		}

		Evaluated evaluated = Evaluated.builder()
				.idNumber(evaluatedRegisterDTO.getIdNumber())
				.dateOfBirth(evaluatedRegisterDTO.getDateOfBirth())
				.email(evaluatedRegisterDTO.getEmail())
				.familyHistoryParkinson(evaluatedRegisterDTO.getFamilyHistoryParkinson())
				.firstName(evaluatedRegisterDTO.getFirstName())
				.lastName(evaluatedRegisterDTO.getLastName())
				.height(evaluatedRegisterDTO.getHeight())
				.weight(evaluatedRegisterDTO.getWeight())
				.sex(sex)
				.typeOfEvaluated(typeOfEvaluated)
				.build();

		Evaluated savedEvaluated = evaluatedRepository.save(evaluated);

		return evaluatedMapper.toEvaluatedResponseDTO(savedEvaluated);
	}
}
