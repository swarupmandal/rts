package org.appsquad.sql;

public class UserProfileSql {
   public static final String insertUserData = "INSERT INTO rts_user_master(user_id, user_name, password,address,contactno,email,created_by) VALUES (?, ?, ?, ?, ?, ?,?) ";
   public static final String fetchUserDeatils = "select * from rts_user_master where is_delete = 'N' order by id ";
   public static final String updateUserDetails = "update rts_user_master set user_id = ?,user_name = ?,password = ?,address = ?,contactno = ?,email = ? where id = ? ";
   public static final String countNumberSql = "select count(*) from rts_user_master where user_id = ? ";
   public static final String deleteUserSql = "update rts_user_master set is_delete = 'D' where id = ? ";
}
