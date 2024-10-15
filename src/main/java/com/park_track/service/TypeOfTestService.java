package com.park_track.service;

import com.park_track.dto.TypeOfTestDTO;
import com.park_track.entity.TypeOfTest;
import com.park_track.repository.TypeOfTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeOfTestService {

	private final TypeOfTestRepository typeOfTestRepository;


	@Autowired
	public TypeOfTestService(TypeOfTestRepository typeOfTestRepository) {
		this.typeOfTestRepository = typeOfTestRepository;
	}

	public Optional<TypeOfTestDTO> getTypeOfTestByType(String type) {
		Optional<TypeOfTest> typeOfTestOptional = typeOfTestRepository.findByType(type);

		// Convertir la entidad a DTO si se encuentra el tipo
		return typeOfTestOptional.map(typeOfTest -> TypeOfTestDTO.builder()
				.id(typeOfTest.getId())
				.type(typeOfTest.getType())
				.description(typeOfTest.getDescription())
				.build());
	}

	public Long getTypeOfTestIdByType(String type) {
		Optional<TypeOfTest> typeOfTestOptional = typeOfTestRepository.findByType(type);
		return typeOfTestOptional.map(TypeOfTest::getId).orElse(null);
	}

}
