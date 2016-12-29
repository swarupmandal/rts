package org.appsquad.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection createConnection(){
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(DbConstants.JDBCURL, DbConstants.USERNAME,DbConstants.PASSWORD);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	/*public static void main(String[] args) {
		createConnection();
	}*/
}
