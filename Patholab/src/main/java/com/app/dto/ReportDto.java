package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class ReportDto {
//	private Long reportId;
    private Long testId;
    private Long emp;
    private Long patient;
    private String resultValue;
    private String comment;
    private LocalDate createdOn;
    private String createdBy;
    private LocalDate modifyOn;
    private String modifyBy;
}
