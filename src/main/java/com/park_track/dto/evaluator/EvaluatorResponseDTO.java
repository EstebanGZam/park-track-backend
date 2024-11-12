package com.park_track.dto.evaluator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatorResponseDTO {
	private Long id;
	private String idNumber;
	private String role;
	private String firstName;
	private String lastName;
	private String email;
}
