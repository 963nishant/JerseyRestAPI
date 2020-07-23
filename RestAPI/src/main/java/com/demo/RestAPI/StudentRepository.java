package com.demo.RestAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

	Connection con = null;

	public StudentRepository(){

		String url = "jdbc:mysql://localhost/restapi";
		String user = "root";
		String password = "root";
		try{

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public List<Student> getStdlist() {

		List<Student> stdlist = new ArrayList<>();
		String sql = "select * from Student";
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while(rs.next()){

				Student s = new Student();
				s.setName(rs.getString(1));
				s.setId(rs.getInt(2));

				stdlist.add(s);

			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return stdlist;
	}

	public Student getStudentId(int id){
		
		String sql = "select * from Student where id"+id;
		Student s = new Student();
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if(rs.next()){
				
				s.setName(rs.getString(1));
				s.setId(rs.getInt(2));

			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return s;

	}
	
	public void createStudent(Student s) {
		String sql = "insert into Student values(?,?)";
		
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, s.getName());
			st.setInt(2, s.getId());
			st.executeUpdate();
		} catch(Exception e){
			System.out.println(e);
			
		}
	}

	public void updateStudent(Student s) {
		String sql = "update Student set name=? where id=?";
		
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, s.getName());
			st.setInt(2, s.getId());
			st.executeUpdate();
		} catch(Exception e){
			System.out.println(e);
			
		}
		
	}

	public void deleteStudentData(int id) {
		String sql = "delete from Student where id=?";
		
		try{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		} catch(Exception e){
			System.out.println(e);
			
		}
	}
}
