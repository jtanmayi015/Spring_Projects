package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="admin")
public class Admin extends BaseEntity{


    @NotBlank(message = "Admin name is required")
    @Column(name = "admin_name", length = 255)
    private String adminName;

    @NotBlank(message = "Admin last name is required")
    @Column(name = "admin_lastname", length = 255)
    private String adminLastName;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(name="email",unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
             message = "Password must be at least 8 characters long and contain at least one letter and one number")
    @Column(name="password")
    private String password;


    @NotNull(message = "Test ID is required")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testId")
	private Test test;

    @NotNull(message = "Employee ID is required")
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "adminId") // mappedBy refers to the field in the child entity
  
    private List<Employee> employeeId=new ArrayList<Employee>();
  
   @NotNull(message = "Sample type is required")
    @Column(name="sample_type")
    private SampleType sampleType;
    
    @NotNull(message = "Date of birth is required")
    @PastOrPresent(message = "Date of birth must be in the past or present")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    public void addEmployee(Employee e) {
		employeeId.add(e);
		e.setAdminId(this);
	}
	public void removeEmployee(Employee e) {
		employeeId.remove(e);
		e.setAdminId(null);
	}
}
