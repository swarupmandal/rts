package org.appsquad.sql;

public class ChangePasswordsql {
	
	public static final String pssswordchangequery=" Update rts_user_master set password=? where user_id=?";
    public static final String countNumberWrtUserAndPassword = "select count(*) from rts_user_master where user_id= ? and password = ? ";
}
