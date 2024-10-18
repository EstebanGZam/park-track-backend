package com.park_track.entity;

import com.park_track.model.RawData;
import com.park_track.model.RawDataConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "samples")
@IdClass(SampleId.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sample {

	@Id
	private Long id;

	@Id
	@Column(name = "evaluated_id", nullable = false)
	private Long evaluatedId;

	@Id
	@Column(name = "test_type_id", nullable = false)
	private Long testTypeId;

	@Column(nullable = false, length = 1)
	private String onOffState;

	@Column(name = "date", nullable = false)
	private Timestamp date;

	@Column(nullable = false, length = 1)
	private String aptitudeForTheTest;

	@Column(name = "raw_data", columnDefinition = "jsonb")
    @Type(JsonBinaryType.class)  // Usa el tipo JSONB de hibernate-types
	private RawData rawData;
}