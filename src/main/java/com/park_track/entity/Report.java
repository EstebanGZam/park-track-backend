package com.park_track.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "reports")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "evaluated_id", nullable = false)
	private Evaluated evaluated;

	@Column(nullable = false)
	private Timestamp dateOfExtraction;

	@ManyToOne
	@JoinColumn(name = "report_type_id", nullable = false)
	private TypeOfReport reportType;

}