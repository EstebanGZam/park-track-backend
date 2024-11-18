package com.park_track.service;

import com.park_track.dto.UserResponseDTO;
import com.park_track.entity.Evaluated;
import com.park_track.entity.Evaluator;
import com.park_track.mapper.UserMapper;
import com.park_track.repository.EvaluatedRepository;
import com.park_track.repository.EvaluatorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final EvaluatorRepository evaluatorRepository;
    private final EvaluatedRepository evaluatedRepository;
    private final EvaluatorService evaluatorService;
    private final EvaluatedService evaluatedService;

    public UserService(EvaluatorRepository evaluatorRepository, EvaluatedRepository evaluatedRepository, EvaluatorService evaluatorService, EvaluatedService evaluatedService) {
        this.evaluatorRepository = evaluatorRepository;
        this.evaluatedRepository = evaluatedRepository;
        this.evaluatorService = evaluatorService;
        this.evaluatedService = evaluatedService;
    }

    public List<UserResponseDTO> getFilteredUsers(String role, String typeOfEvaluated) {
        List<UserResponseDTO> users = new ArrayList<>();
        if(role != null) {
            List<Evaluator> evaluators = evaluatorRepository.findAll();
            for (Evaluator evaluator : evaluators) {
                users.add(UserMapper.toUserResponseDTO(evaluator));
            }
        }else if(typeOfEvaluated != null) {
            List<Evaluated> evaluateds = evaluatedRepository.findByTypeOfEvaluated(typeOfEvaluated);
            for (Evaluated evaluated : evaluateds) {
                users.add(UserMapper.toUserResponseDTO(evaluated));
            }
        }else {
            users = getAllUsers();
        }
        return users;
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

    public ResponseEntity<List<UserResponseDTO>> deleteUser(String idNumber, String role, String typeOfEvaluated) {
        Boolean wasDeleted = false;

        if (role != null) {
            wasDeleted = evaluatorService.deleteEvaluatorByIdNumber(idNumber);
        } else if (typeOfEvaluated != null) {
            wasDeleted = evaluatedService.deleteEvaluatedByIdNumber(idNumber);
        }

        if (wasDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
