package dao;
import java.util.List;
import pojos.Course;
import pojos.student;
public interface studentDao {
	String insertStudentDetails(student newStud);
	
	List<student> studentLogin(String email,String password );
	List<student> getSelectedStudent(Course course);
	//Offer scholarship to a particular  student
	String offerScholarship(Integer id,Double amount);
}
