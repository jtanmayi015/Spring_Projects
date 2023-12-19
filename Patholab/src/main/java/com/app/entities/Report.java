package com.app.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
public class Report extends BaseEntity {

	    
	    @NotNull(message = "test_id is required")
	    @ManyToOne
	    @JoinColumn(name = "test_id")
	    private Test testId;

	    @NotNull(message = "employee_id is required")
	    @ManyToOne
		@JoinColumn(name = "empId")// mappedBy refers to the field in the child entity
	    private Employee emp;
	    


	    
	    @ManyToMany
	    @JoinTable(
	        name = "report_patient",
	        joinColumns = @JoinColumn(name = "report_id"),
	        inverseJoinColumns = @JoinColumn(name = "patient_id")
	    )
	    private Set<Patient> patients = new HashSet<>();
	    
	    
	    @Column(name = "result_value", length = 255)
	    private String resultValue;

	    @Column(name = "comment", columnDefinition = "TEXT")
	    private String comment;

	    @Column(name = "created_on")
	    private LocalDate createdOn;

	    @Column(name = "created_by", length = 255)
	    private String createdBy;

	    @Column(name = "modify_on")
	    private LocalDate modifyOn;

	    @Column(name = "modify_by", length = 255)
	    private String modifyBy;
	    
	    public void addPatient(Patient p)
		{
	    	patients.add(p);
			p.getReports().add(this);
		}
		public void removePatient(Patient p)
		{
			patients.remove(p);
			p.getReports().remove(null);
		}

}
