package dao;
import java.util.List;

import org.hibernate.*;
import org.hibernate.query.Query;

import pojos.Course;
import pojos.student;
import tester.StudentLogin;

import org.hibernate.*;
import static utils.hibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;
public class studentDaoImpl implements studentDao {

	@Override
	public String insertStudentDetails(student newStud) {
		String mesg="failed to insert student details";
		// TODO Auto-generated method stub
		Session session = getFactory().openSession();
		Transaction tx=session.beginTransaction();
		System.out.println("is session open "+session.isOpen()+"conn with db"+session.isConnected());
		try {
			Serializable id=session.save(newStud);
			tx.commit();
			System.out.println("after commit : is session open"+session.isOpen()+"conn with db"+session.isConnected());;
			mesg="Inserted student Details with Id";
		}
		catch(RuntimeException e) {
			if(tx !=null)
				tx.rollback();
			throw e;
		}
		finally {
		if(session != null)
			session.close();
		}
		return mesg;
	}
	@Override
	public List<student> studentLogin(String email,String password ) {
		String mesg1="Login sucessful";
		Session session=getFactory().getCurrentSession();
		String jpql = "select st from student st where st.email=:email and st.password=:password";
		List<student> st =null;
		Transaction tx=session.beginTransaction();

		try {
			st=session.createQuery(jpql,student.class).
			setParameter("email", email).
			setParameter("password",password).getResultList();
			tx.commit();
			
			
		}
		catch(RuntimeException e) {
			if(tx !=null)
				tx.rollback();
			throw e;
		}
		return st;
		
	}
	public List<student> getSelectedStudent(Course course){
		List<student> stud=null;
		String jpql="select st from student st where st.course=:course";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			stud=session.createQuery(jpql,student.class).setParameter("course", course).getResultList();
			tx.commit();
		}
		catch(RuntimeException e) {
			if(tx !=null)
				tx.rollback();
			throw e;
		}
		return stud;
		}
	public String offerScholarship(Integer id,Double amount){
		String mesg="Scolarship applied successfully";
		student s=null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		System.out.println(tx.isActive());
		try {
			s=session.get(student.class,id);
			s.setAddmissionFees(s.getAddmissionFees()-amount);
			tx.commit();
		}
		catch(RuntimeException e) {
			if(tx !=null)
				tx.rollback();
			throw e;
		}
		return null;
		
	}
}
