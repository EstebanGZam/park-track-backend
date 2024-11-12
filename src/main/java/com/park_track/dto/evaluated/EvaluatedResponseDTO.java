package com.park_track.dto.evaluated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatedResponseDTO {
	private Long id;
	private String idNumber;
	private String typeOfEvaluated;
	private String firstName;
	private String lastName;
	private String email;
}
