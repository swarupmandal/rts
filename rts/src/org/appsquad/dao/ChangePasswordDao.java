package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ChangePasswordsql;
import org.appsquad.utility.Pstm;


public class ChangePasswordDao {
	
	final static Logger logger = Logger.getLogger(ChangePasswordDao.class);
	
	public static boolean changePassword(String userId, String password){
		boolean updateFlag = false;
		int count = 0;
		try {
			Connection connection=DbConnection.createConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement= Pstm.createQuery(connection, ChangePasswordsql.pssswordchangequery, Arrays.asList(password, userId));
				//System.out.println("Changed Password query -- >>> >> >  " + preparedStatement);
				
				logger.info("change password - " + preparedStatement.unwrap(PreparedStatement.class));
			    
				count = preparedStatement.executeUpdate();
				if (count>0) {
					updateFlag=true;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
			
			
		} catch (Exception e) {
		   
		   logger.fatal(e);	
		   e.printStackTrace();
		}
		return updateFlag;
	}

}
