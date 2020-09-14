package com.srv.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try(Connection myConnection = DriverManager.getConnection(jdbcUrl, user, pass)){
			
			System.out.println("Connected Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
