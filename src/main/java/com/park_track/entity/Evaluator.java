package com.park_track.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluators")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evaluator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String idNumber;

	@Column(nullable = false, length = 100)
	private String firstName;

	@Column(nullable = false, length = 100)
	private String lastName;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false)
	private Boolean isDeleted;

	@OneToOne(optional = false)  // El evaluador debe tener un usuario
	@OnDelete(action = OnDeleteAction.CASCADE) //Si se borra el evaluador se debe borrar tambien si usuario
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
	private User user;

}
