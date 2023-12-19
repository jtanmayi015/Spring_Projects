package utils;
import org.hibernate.cfg.*;
import org.hibernate.*;
public class hibernateUtils {
private static SessionFactory factory;
 	static {
 		factory= new Configuration()	//empty config.org
 				.configure()			//populate from hibernate cfg.xml
 				.buildSessionFactory(); //add singleton sf
 	}
 	public static SessionFactory getFactory() {
 		return factory;
 	}
}
