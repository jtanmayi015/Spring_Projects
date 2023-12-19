package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleDto {
	private Long sampleId;
	private Long sampleTypeId;
    private Long testId;
    private Long employeeId;
    private Long patientId;
    private boolean isCollected;
    private LocalDate createdOn;
    private String createdBy;
    private LocalDate modifyOn;
    private String modifyBy;
}
