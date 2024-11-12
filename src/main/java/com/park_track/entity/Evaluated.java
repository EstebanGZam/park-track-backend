package com.park_track.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluated", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_number"})})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evaluated {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String idNumber;

	@Column(nullable = false, length = 100)
	private String firstName;

	@Column(nullable = false, length = 100)
	private String lastName;

	@Column(nullable = false)
	private java.util.Date dateOfBirth;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 1)
	private String familyHistoryParkinson;

	@Column(nullable = false)
	private double height;

	@Column(nullable = false)
	private double weight;

	@ManyToOne
	@JoinColumn(name = "type_of_evaluated_id", nullable = false)
	private TypeOfEvaluated typeOfEvaluated;

	@ManyToOne
	@JoinColumn(name = "sex_id", nullable = false)
	private Sex sex;

}