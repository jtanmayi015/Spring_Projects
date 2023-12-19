package beans;

import java.sql.SQLException;

import dao.UserDaoImpl;
import pojos.User;

//WC managed server side attribute
public class UserBean {
//state : 
	private String email;
	private String password;
	private UserDaoImpl userDao;
	private User validatedUserDetails;
	
	//def ctor : MANDATORY : JSP using JB scenario
	public UserBean() throws SQLException{
		// create dao instance
		userDao=new UserDaoImpl();
		System.out.println("user bean created....");
	}
	//getters n setters

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

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public User getValidatedUserDetails() {
		return validatedUserDetails;
	}

	public void setValidatedUserDetails(User validatedUserDetails) {
		this.validatedUserDetails = validatedUserDetails;
	}
	//add a B.L method user validation
	public String validateUser() throws SQLException
	{
		System.out.println("in validate user "+email+" "+password);
		validatedUserDetails=userDao.authenticateUser(email, password);
		if(validatedUserDetails == null)
		{
			//auth : failed 
			return "login";
		}
		//auth success
		if(validatedUserDetails.getRole().equals("voter"))
		{
			return "candidate_list";
		}
		return "admin_status";
	}
	
	
}
