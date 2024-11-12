package com.park_track.dto.evaluated_filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluatedFilterDTO {
    private String startDate;
    private String endDate;
    private String nameRangeStart;
    private String nameRangeEnd;
    private String typeOfEvaluated;
    private String sortBy;
    private String sortDirection;
}