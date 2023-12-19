package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TotalBillDto {
	 
	    private Long patientId;
	    private String patientFirstName;
	    private String patientLastName;
	    private String address;
	    private String email;
	    private Long phoneNo;
	    private LocalDate date;
	    private double totalPrice;
	    private List<ViewBillDto> bills;
}
