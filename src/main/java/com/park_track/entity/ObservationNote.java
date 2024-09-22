package com.park_track.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "observation_notes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObservationNote {
	@Id
	private Long id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "sample_evaluated_id", referencedColumnName = "evaluated_id"),
			@JoinColumn(name = "sample_id", referencedColumnName = "id"),
			@JoinColumn(name = "sample_test_type_id", referencedColumnName = "test_type_id")
	})
	private Sample sample;

	@Column(nullable = false, length = 250)
	private String description;

}