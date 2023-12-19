package com.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class BillDto {
	 private boolean isPaid;
	    private Integer amount;
	    private Long phoneNo;
	    private Long patientId;
	    private Long testId;
	    private Long empId;
	    private LocalDate collectedDate;
	    private boolean isCollected;
	    private String createdBy;
	    private String modifyBy;
	    @JsonProperty(access = Access.READ_ONLY)
	    private double totalPrice;
}
	