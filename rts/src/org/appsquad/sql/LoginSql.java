package org.appsquad.sql;

public class LoginSql {
	public static final String loginQuery = "Select * from rts_user_master where user_id = ? and password = ? and  is_delete = 'N' ";
}
