package org.appsquad.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.LoginSql;
import org.appsquad.utility.Pstm;

public class LoginService {

	final static Logger logger = Logger.getLogger(LoginService.class);
	public static String getEmailIdOfUser(String userID){
		String emailID = null;
		try {
			SQL:{
					Connection connection = DbConnection.createConnection();
					PreparedStatement preparedStatement = null;
					ResultSet resultSet = null;
					try {
						preparedStatement = Pstm.createQuery(connection, LoginSql.userEmailQuery, 
								Arrays.asList(userID));
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							emailID = resultSet.getString("email");
						}
					} catch (Exception e) {
						logger.fatal("--------------------- " + e);
						e.printStackTrace();
					}finally{
						if(connection!=null){
							connection.close();
						}
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return emailID;
	}
}
