package tester;
import static utils.hibernateUtils.getFactory;
import org.hibernate.*;

public class testHibernate {
public static void main(String[] args) {
		try(SessionFactory sf=getFactory()){
			System.out.println("Hibernate is running");
		}catch(Exception e) {
			e.printStackTrace();
		}
}
}
