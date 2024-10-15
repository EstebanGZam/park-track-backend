package com.park_track.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfTestDTO {
	private Long id;
	private String type;
	private String description;
}
