package org.appsquad.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import org.appsquad.database.DbConnection;
import org.appsquad.sql.LoginSql;
import org.appsquad.utility.Pstm;
import org.appsquad.viewmodel.SendEmail;

public class ForgotPasswordService {

	public static boolean isMailSend(String toMail,String emailBody){
		return SendEmail.sendPasswordInEmail(toMail, emailBody);
	}
	
	public static void upDatePassword(String userId, String password){
		try {
			SqL:{
					Connection connection = DbConnection.createConnection();
					PreparedStatement preparedStatement = null;	
					try {
						preparedStatement = Pstm.createQuery(connection, LoginSql.updatePassword, 
								Arrays.asList(password, userId));
						int count = preparedStatement.executeUpdate();
						if(count > 0)
							System.out.println("New Password updated by system. . .");
					} catch (Exception e) {
						// TODO: handle exception
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
	}
}
