package com.rental.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	
public static Connection connect() {
		
	
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/CRMRentalService", "root", "mysql");
			// For testing
			System.out.print("DB_connected_Successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	
}
	
}
