package com.park_track.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sexes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sex {
	@Id
	private Long id;

	@Column(nullable = false, length = 100)
	private String sex;

}