package com.app.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NoArgsConstructor // generates def ctor
@AllArgsConstructor // all args ctor
@Getter // all getters
@Setter // setters
@ToString
@Table(name="bill")
public class Bill extends BaseEntity{


    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @NotNull(message = "Amount is required")
    private Integer amount;

    @NotNull(message = "Phone number is required")
    @Digits(integer = 10, fraction = 0, message = "Phone number should not exceed 10 digits")
    @Column(name="phone_no")
    private Long phoneNo;
    
    @NotNull(message = "Test ID is required")
    @ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
    

    @NotNull(message = "Test ID is required")
    @ManyToOne
	@JoinColumn(name = "testId")
	private Test tests;
    
    @NotNull(message = "Emp ID is required")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empId")
	private Employee emps;

    @NotNull(message = "Collected date is required")
    @Column(name="collected_date")
    private LocalDate collectedDate;

    @Column(name = "is_collected", nullable = false)
    private boolean isCollected;

    @NotBlank(message = "Created by is required")
    @Size(max = 255, message = "Created by should not exceed 255 characters")
    @Column(name="created_by")
    private String createdBy;

    @Column(name="modify_on")
    private LocalDate modifyOn;

    @Size(max = 255, message = "Modify by should not exceed 255 characters")
    @Column(name="modify_by")
    private String modifyBy;
    private double totalTestPrice;
    
    
    
    
   

}
