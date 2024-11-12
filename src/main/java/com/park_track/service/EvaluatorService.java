package com.park_track.service;

import java.util.List;
import java.util.stream.Collectors;

import com.park_track.dto.EvaluatorRegisterDTO;
import com.park_track.dto.EvaluatorResponseDTO;
import com.park_track.entity.Evaluator;
import com.park_track.mapper.EvaluatorMapper;
import com.park_track.repository.EvaluatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.park_track.dto.UserDTO;
import com.park_track.entity.User;
import com.park_track.model.Role;
import com.park_track.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EvaluatorService {
	private EvaluatorRepository evaluatorRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Autowired
	public EvaluatorService(EvaluatorRepository evaluatorRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.evaluatorRepository = evaluatorRepository;
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	public List<UserDTO> getAllEvaluators() {
		List<User> doctors = userRepository.findByRole(Role.EVALUATOR);
		// We convert the results into a list of UserDTO
		return doctors.stream()
				.map(user -> UserDTO.builder()
						.username(user.getUsername())
						.build())
				.collect(Collectors.toList());
	}

	public Boolean deleteEvaluatorByUsername(String username) {
		if (userRepository.existsByUsername(username)) {
			userRepository.deleteByUsername(username);
			return true;
		}
		return false;
	}

	@Transactional
	public EvaluatorResponseDTO createEvaluator(EvaluatorRegisterDTO evaluatorRegisterDTO) {
		// Crear el objeto User
		User user = User.builder()
				.username(evaluatorRegisterDTO.getId_number())
				.password(passwordEncoder.encode(evaluatorRegisterDTO.getPassword()))
				.role(Role.valueOf(evaluatorRegisterDTO.getRole()))  // Asegúrate de que el rol en el DTO coincida con el enum Role
				.build();

		// Guardar ambos objetos en la base de datos
		userRepository.save(user);

		// Crear el objeto Evaluator y asignar el usuario
		Evaluator evaluator = Evaluator.builder()
				.idNumber(evaluatorRegisterDTO.getId_number())
				.firstName(evaluatorRegisterDTO.getFirst_name())
				.lastName(evaluatorRegisterDTO.getLast_name())
				.email(evaluatorRegisterDTO.getEmail())
				.isDeleted(false)  // Por defecto no está eliminado
				.user(user)  // Asignar el User recién creado
				.build();
		Evaluator savedEvaluator = evaluatorRepository.save(evaluator);

		return EvaluatorMapper.toEvaluatorResponseDTO(savedEvaluator);
	}


}
