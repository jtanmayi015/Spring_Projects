package tester;
import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import static utils.hibernateUtils.getFactory;

import dao.studentDao;
import dao.studentDaoImpl;
import pojos.Course;
import pojos.student;
public class offerScolarship {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Scanner sc= new Scanner(System.in);
				SessionFactory sf=getFactory()){
			studentDaoImpl dao=new studentDaoImpl();
			System.out.println("Enter student id Scolarship Amount :");
			dao.offerScholarship(sc.nextInt(),sc.nextDouble());
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

}
