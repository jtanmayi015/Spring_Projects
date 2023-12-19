package tester;
import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import static utils.hibernateUtils.getFactory;

import dao.studentDao;
import dao.studentDaoImpl;
import pojos.Course;
import pojos.student;


	
public class studentFromSpecificCourse {
	public static void main(String[] args) {
		try(Scanner sc= new Scanner(System.in);
				SessionFactory sf=getFactory()){
			studentDaoImpl dao=new studentDaoImpl();
			System.out.println("Enter perticular course:");
			dao.getSelectedStudent(Course.valueOf(sc.next().toUpperCase()));
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}