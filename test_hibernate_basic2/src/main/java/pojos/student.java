package pojos;
import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.*;

import pojos.Course;
@Entity
@Table(name="Dac_Students")
public class student {
//studentId (PK) ,first name , last name,email,password,confirmPassword,course(enum),
	//admission fees (double) ,dob : LocalDate/birthDate  , profilePic : byte[]
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  //@Id gives primayKey =>auto increment
	@Column(name="Stud_id")							   //Change column name 
	private Integer studentId;
	@Column(name="first_name",length=30)
	private String firstName;
	@Column(name="last_Name",length=20)
	private String lastName;
	@Column(length=30)
	private String email;
	@Column(name="password")
	private String password;
	@Transient
	private String confirmPassword;
	@Enumerated(EnumType.STRING)
	@Column(name="Course")
	private Course course;
	@Column(name="Fees")
	private Double addmissionFees;
	@Column(name="dob")
	private LocalDate date;
	@Lob												//LOb=large object
	private byte[] profilePic;
	
	public student( String firstName, String lastName, String email, String password,
			String confirmPassword, Course course, Double addmissionFees, LocalDate date) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.course = course;
		this.addmissionFees = addmissionFees;
		this.date = date;
		
	}

	public student() {
		super();
	}

	@Override
	public String toString() {
		return "student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", course=" + course
				+ ", addmissionFees=" + addmissionFees + ", date=" + date + ", profilePic="
				+ Arrays.toString(profilePic) + "]";
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Double getAddmissionFees() {
		return addmissionFees;
	}

	public void setAddmissionFees(Double addmissionFees) {
		this.addmissionFees = addmissionFees;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	
	
	
}
