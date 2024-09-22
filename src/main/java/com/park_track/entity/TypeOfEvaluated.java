package com.park_track.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "types_of_evaluated")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfEvaluated {
	@Id
	private Long id;

	@Column(nullable = false, length = 100)
	private String type;

}