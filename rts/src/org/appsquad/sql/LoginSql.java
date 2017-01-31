package org.appsquad.sql;

public class LoginSql {
	public static final String loginQuery = "Select * from rts_user_master where user_id = ? "
			+ "and password = ? and  is_delete = 'N' ";
	public static final String userEmailQuery = "Select email from rts_user_master where user_id = ?"
			+ " and is_delete ='N'";
	public static final String updatePassword = "UPDATE rts_user_master set password = ? where"
			+ " user_id = ?";
}
