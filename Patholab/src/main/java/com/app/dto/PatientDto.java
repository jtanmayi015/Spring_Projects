package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.entities.Gender;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientDto {
//	 private Long patientId;

	    @NotBlank(message = "First name is required")
	    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	    private String firstName;

	    @NotBlank(message = "Last name is required")
	    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	    private String lastName;

	    

	    // Other fields and getters/setters
	    
	    private String email;
	    private String password;
	    @NotNull(message = "Gender is required")
	    private Gender gender;
//	    private String medicalHistory;
//	    private LocalDate createdOn;
//	    private String createdBy;
//	    private LocalDate modifyOn;
//	    private String modifyBy;
	    private LocalDate dateOfBirth;
	    private String address;
	    private Long phoneNo;
	    private Long empId; 
}
