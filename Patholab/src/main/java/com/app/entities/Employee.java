package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor // generates def ctor
@AllArgsConstructor // all args ctor
@Getter // all getters
@Setter // setters
@Table(name="employee")
public class Employee extends BaseEntity{


	    @NotBlank(message = "First name is required")
	    @Column(name = "first_name", length = 255)
	    private String firstName;

	    @NotBlank(message = "Last name is required")
	    @Column(name = "last_name", length = 255)
	    private String lastName;
	    
	    @OneToMany(cascade=CascadeType.ALL,mappedBy = "emp")
		   
	    private List<Patient> patient=new ArrayList<Patient>();
	    @NotNull(message = "Report ID is required")
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "emp")

		private List<Report> reports = new ArrayList<Report>();
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "admin_id")
	    private Admin adminId;

	    @Email(message = "Invalid email address")
	    @NotBlank(message = "Email is required")
	    @Column(unique = true)
	    private String email;

	    @NotBlank(message = "Password is required")
	    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
	             message = "Password must be at least 8 characters long and contain at least one letter and one number")
	    private String password;

	    @Enumerated(EnumType.STRING)
	    private Gender gender;
	    
	    @OneToMany(cascade=CascadeType.ALL,mappedBy = "emps")
		   
	    private List<Bill> bill=new ArrayList<Bill>();

	    @NotNull(message = "Availability status is required")
	    @Column(name = "is_available")
	    private boolean isAvailable;

	    @NotNull(message = "Phone number is required")
	    @Column(name = "phone_no")
	    private Long phoneNo;
	    @PastOrPresent(message = "Date of birth must be in the past or present")
	    @Column(name = "date_of_birth")
	    private LocalDate dateOfBirth;
	    
	    private String address;
	    
	    @NotNull(message = "Sample ID is required")
	    @OneToMany(cascade=CascadeType.ALL,mappedBy = "employeeId")
	    
	    private List<Sample> sampleId=new ArrayList<Sample>();

	    public void addPatient(Patient p) {
			patient.add(p);
			p.setEmp(this);
		}
		public void removePatient(Patient p) {
			patient.remove(p);
			p.setEmp(null);
		}

	    public void addBill(Bill b) {
			bill.add(b);
			b.setEmps(this);
		}
		public void removeBill(Bill b) {
			bill.remove(b);
			b.setEmps(null);
		}

	    public void addSample(Sample s) {
			sampleId.add(s);
			s.setEmployeeId(this);
		}
		public void removeSample(Sample s) {
			sampleId.remove(s);
			s.setEmployeeId(null);
		}
		
		public void addReport(Report r)
		{
	    	reports.add(r);
			r.setEmp(this);
		}
		public void removeReport(Report r)
		{
			reports.remove(r);
			r.setEmp(null);
		}
		
	    
}
