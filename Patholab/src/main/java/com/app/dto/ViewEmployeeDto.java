package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Gender;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewEmployeeDto {
	 private String firstName;
	    private String lastName;
	    private String email;
//	    private String password;
//	    private SampleType sampleType;
	    private Gender gender;
	    private LocalDate dateOfBirth;
	    private String address;
//	    private boolean isAvailable;
	    private Long phoneNo;
//	    private Long adminId;
}
