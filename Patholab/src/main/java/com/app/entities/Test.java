package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor // generates def ctor
@AllArgsConstructor // all args ctor
@Getter // all getters
@Setter // setters
@Table(name = "test")
public class Test extends BaseEntity {


		
		@ManyToMany // mandatory!
		@JoinTable(name="patient_test",
		joinColumns = @JoinColumn(name="test_id"),
		inverseJoinColumns = @JoinColumn(name="patient_id")
		)
		private Set<Patient> patient=new HashSet<>();

		@NotBlank(message = "Test Name is required")
		@Column(name = "Test_Name", length = 255)
		private String testName;

		@NotNull(message = "Test Price is required")
		@Column(name = "Test_Price")
		private Integer testPrice;

		@OneToMany(cascade=CascadeType.ALL,mappedBy = "test")
		private List <Admin> admin=new ArrayList<Admin>();

		@OneToMany(cascade=CascadeType.ALL,mappedBy = "tests")
		   
	    private List<Bill> bill=new ArrayList<Bill>();

		@Column(name = "created_on")
		private LocalDate createdOn;

		@NotBlank(message = "Created By is required")
		@Column(name = "created_by", length = 255)
		private String createdBy;

		@Column(name = "modify_on")
		private LocalDate modifyOn;

		@Column(name = "modify_by", length = 255)
		private String modifyBy;

		@NotNull(message = "Sample ID is required")
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "testId")

		private List<Sample> sampleId = new ArrayList<Sample>();
		
		@OneToMany(mappedBy = "testId")
	    private List<Report> reports = new ArrayList<>();

		
		public void addPatient(Patient p)
		{
			patient.add(p);
			p.getTest().add(this);
		}
		public void removePatient (Patient p)
		{
			patient.remove(p);
			p.getTest().remove(null);
		}
		public void addAdmin(Admin a)
		{
	    	admin.add(a);
			a.setTest(this);
		}
		public void removeAdmin(Admin a)
		{
			admin.remove(a);
			a.setTest(null);
		}
		public void addBill(Bill b)
		{
	    	bill.add(b);
			b.setTests(this);
		}
		public void removeBill(Bill b)
		{
			bill.remove(b);
			b.setTests(null);
		}
		public void addSample(Sample s)
		{
	    	sampleId.add(s);
			s.setTestId(this);
		}
		public void removeSample(Sample s)
		{
			sampleId.remove(s);
			s.setTestId(null);
		}
	}
