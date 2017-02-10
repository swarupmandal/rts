package org.appsquad.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import org.appsquad.bean.UserprofileBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.LoginSql;
import org.appsquad.utility.PasswordEncryption;
import org.appsquad.utility.Pstm;
import org.appsquad.viewmodel.SendEmail;

public class ForgotPasswordService {

	public static boolean isMailSend(UserprofileBean user,String emailBody){
		return SendEmail.sendPasswordInEmail(user, emailBody);
	}
	
	public static void upDatePassword(String userId, String password){
		try {
			SqL:{
					Connection connection = DbConnection.createConnection();
					PreparedStatement preparedStatement = null;	
					try {
						String encryptedPasssword = PasswordEncryption.easeyEncrypt(password);
						preparedStatement = Pstm.createQuery(connection, LoginSql.updatePassword, 
								Arrays.asList(encryptedPasssword, userId));
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
