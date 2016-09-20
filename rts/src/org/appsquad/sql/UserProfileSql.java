package org.appsquad.sql;

public class UserProfileSql {
   public static final String insertUserData = "INSERT INTO rts_user_master(user_id, user_name, password,address,contactno,email) VALUES (?, ?, ?, ?, ?, ?) ";
   public static final String fetchUserDeatils = "select * from rts_user_master ";
   public static final String updateUserDetails = "update rts_user_master set user_id = ?,user_name = ?,password = ?,address = ?,contactno = ?,email = ? where id = ? ";
}
