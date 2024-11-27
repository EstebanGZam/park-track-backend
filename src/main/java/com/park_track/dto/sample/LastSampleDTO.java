// LastSampleDTO.java
package com.park_track.dto.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class LastSampleDTO {
	private Long id;
	private Long evaluatedId;
	private Long testTypeId;
	private String onOffState;
	private Timestamp date;
	private String aptitudeForTheTest;
	private String rawData;
}
