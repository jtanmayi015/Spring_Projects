package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Team;

import static utils.DBUtils.*;

public class TeamDaoImpl {
	private Connection cn;
	private PreparedStatement pst1;
	
	public TeamDaoImpl() throws SQLException{
		cn=openConnection();
		pst1=cn.prepareStatement("select team_id,abbrevation from teams");
		System.out.println("team dao created");
	}
	//@Override
	public List<Team>getAllTeam()throws SQLException{
		List<Team> teams=new ArrayList<>();
		try(ResultSet rst= pst1.executeQuery()){
			teams.add(new Team(rst.getInt(1), rst.getString(2)));
		}
		return teams;
		
	}
	/*@Override
	public List<Team> getAllTeams() throws SQLException {
		List<Team> teams=new ArrayList<>();
		try(ResultSet rst=pst1.executeQuery())
		{
			while(rst.next())
				teams.add(new Team(rst.getInt(1), rst.getString(2)));
		}
		return teams;*/
	public void cleanUp()throws SQLException{
		if(pst1!=null)
			pst1.close();
		closeConnection();
	}
}
