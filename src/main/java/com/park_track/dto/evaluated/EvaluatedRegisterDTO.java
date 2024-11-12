package com.park_track.dto.evaluated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatedRegisterDTO {
	@JsonProperty("id_number")
	private String idNumber;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("date_of_birth")
	private Date dateOfBirth;
	private String email;
	@JsonProperty("family_history_parkinson")
	private String familyHistoryParkinson;
	private double height;
	private double weight;
	@JsonProperty("evaluated_type")
	private String typeOfEvaluated; // Nombre del tipo de evaluado
	private String sex; // Nombre del sexo
}
