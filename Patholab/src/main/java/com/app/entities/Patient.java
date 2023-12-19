	package com.app.entities;
	
	import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
	@Entity
	
	@Table(name = "patient")
	@NoArgsConstructor // generates def ctor // all args ctor
	@Getter // all getters
	@Setter // setters
	@ToString
	public class Patient extends BaseEntity{

		 @NotBlank(message = "First name is required")
		 @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
		 @Column(name="patient_first_name")
		 private String firstName;
	
		 @NotBlank(message = "Last name is required")
		 @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
		 @Column(name="patient_last_name")
		 private String lastName;
		 
		 @Enumerated(EnumType.STRING)
		 @NotNull(message = "Gender is required")
		 private Gender gender;
		 
		 @NotBlank(message = "Address is required")
		    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
		    private String address;
	
		    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
		    @Column(name="phone_no")
		    private Long phoneNo;
		    
		    @Email(message = "Invalid email address")
		    @NotBlank(message = "Email is required")
		    @Column(unique = true)
		    private String email;
	
		    @NotBlank(message = "Password is required")
		    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
		             message = "Password must be at least 8 characters long and contain at least one letter and one number")
		    private String password;
	
	
		   // @NotNull(message = "Created on date is required")
		    @PastOrPresent(message = "Created on date must be in the past or present")
		    @Column(name="created_on")
		    private LocalDate createdOn;
	
		   // @NotBlank(message = "Created by is required")
		    @Size(min = 2, max = 50, message = "Created by must be between 2 and 50 characters")
		    @Column(name="created_by")
		    private String createdBy;
	
	
		    @ManyToMany(mappedBy = "patient")
			private Set<Test> test = new HashSet<>();
		    
		    @NotNull(message = "Date of birth is required")
		    @PastOrPresent(message = "Date of birth must be in the past or present")
		    @Column(name = "date_of_birth")
		    private LocalDate dateOfBirth;
		    
		    @NotNull(message = "Sample ID is required")
		    @OneToMany(cascade=CascadeType.ALL,mappedBy = "patientId") 
		    private List<Sample> sampleId=new ArrayList<Sample>();
		    @ManyToOne
			@JoinColumn(name = "empId")
		    @JsonIgnore// mappedBy refers to the field in the child entity
		    private Employee emp;
		    

		    @ManyToMany(mappedBy = "patients")
		    private Set<Report> reports = new HashSet<>();

		    @NotNull(message = "BillId ID is required")
		    @OneToMany(cascade=CascadeType.ALL,mappedBy = "patient") 
		    private List<Bill> bill=new ArrayList<Bill>();
		    
		    public void addTest(Test t)
			{
				test.add(t);
				t.getPatient().add(this);
			}
			public void removeTest(Test t)
			{
				test.remove(t);
				t.getPatient().remove(null);
			}

		    public void addSample(Sample s)
			{
		    	sampleId.add(s);
				s.setPatientId(this);
			}
			public void removeSample(Sample s)
			{
				sampleId.remove(s);
				s.setPatientId(null);
			}
			
			  public void addBill(Bill b)
				{
			    	bill.add(b);
					b.setPatient(this);
				}
				public void removeBill(Bill b)
				{
					bill.remove(b);
					b.setPatient(null);
				}
				
				 public void addReport(Report r)
					{
				    	reports.add(r);
						r.getPatients().add(this);
					}
					public void removeReport(Report r)
					{
						reports.remove(r);
						r.getPatients().remove(null);
					}
					
		    
	}
