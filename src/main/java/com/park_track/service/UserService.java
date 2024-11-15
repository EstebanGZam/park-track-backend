package com.park_track.service;

import com.park_track.dto.UserResponseDTO;
import com.park_track.entity.Evaluated;
import com.park_track.entity.Evaluator;
import com.park_track.mapper.UserMapper;
import com.park_track.repository.EvaluatedRepository;
import com.park_track.repository.EvaluatorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final EvaluatorRepository evaluatorRepository;
    private final EvaluatedRepository evaluatedRepository;

    public UserService(EvaluatorRepository evaluatorRepository, EvaluatedRepository evaluatedRepository) {
        this.evaluatorRepository = evaluatorRepository;
        this.evaluatedRepository = evaluatedRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> users = new ArrayList<>();

        List<Evaluator> evaluators = evaluatorRepository.findAll();
        for (Evaluator evaluator : evaluators) {
            users.add(UserMapper.toUserResponseDTO(evaluator));
        }

        List<Evaluated> evaluateds = evaluatedRepository.findAll();
        for (Evaluated evaluated : evaluateds) {
            users.add(UserMapper.toUserResponseDTO(evaluated));
        }

        return users;
    }
}
