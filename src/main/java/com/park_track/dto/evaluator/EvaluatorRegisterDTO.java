package com.park_track.dto.evaluator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatorRegisterDTO {
	private String id_number;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String role;
}
