package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestDto {
	private String testName;
	private Integer testPrice;
	private LocalDate createdOn;
	private String createdBy;
	private LocalDate modifyOn;
	private String modifyBy;
}
