package com.park_track.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatedDTO {
	private Long evaluatorId;
	private String idNumber;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private String familyHistoryParkinson;
	private double height;
	private double weight;
	private String typeOfEvaluated; // Nombre del tipo de evaluado
	private String sex; // Nombre del sexo
}
