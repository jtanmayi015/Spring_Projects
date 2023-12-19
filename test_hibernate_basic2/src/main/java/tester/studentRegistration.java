package tester;
import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import static utils.hibernateUtils.getFactory;

import dao.studentDao;
import dao.studentDaoImpl;
import pojos.Course;
import pojos.student;
public class studentRegistration {
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				SessionFactory sf=getFactory()){
			studentDaoImpl dao= new studentDaoImpl();
			System.out.println("Enter student details: String firstName, String lastName, String email, String password,\r\n"
					+ "String confirmPassword, Course course, Double addmissionFees, LocalDate date");
			student stud= new student(sc.next(),sc.next(),sc.next(),sc.next(),sc.next(),Course.valueOf(sc.next().toUpperCase()),sc.nextDouble(),LocalDate.parse(sc.next()));
			System.out.println(dao.insertStudentDetails(stud));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
