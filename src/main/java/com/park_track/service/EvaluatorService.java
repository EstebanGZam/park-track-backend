package com.park_track.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.park_track.dto.UserDTO;
import com.park_track.entity.User;
import com.park_track.model.Role;
import com.park_track.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluatorService {

	private final UserRepository userRepository;

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

}
