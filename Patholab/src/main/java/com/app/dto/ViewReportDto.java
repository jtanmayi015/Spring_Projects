package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter@ToString
public class ViewReportDto {
//	private Long testId;
//    private Long emp;
//    private Long patient;
	private String patientFirstName;
	private String patientLastName;
	private String address;
	private Long phoneNo;
	private String email;
    private String resultValue;
    private String comment;
    private LocalDate createdOn;
    private String createdBy;
    private LocalDate modifyOn;
    private String modifyBy;
}
