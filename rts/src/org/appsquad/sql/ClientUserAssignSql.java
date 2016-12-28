package org.appsquad.sql;

public class ClientUserAssignSql {
   public static final String insertUserClientAssignSql = "INSERT INTO rts_user_client_mapper(client_id, user_id) VALUES (?, ?) ";
   public static final String countUserClientMappingSql = "select count(*) from rts_user_client_mapper where client_id = ? and user_id = ? and is_delete = 'N' ";
   public static final String fetchUserClientAssignDetailsSql = "select * from rts_client_user_mapping where client_delete = 'N' and asign_delete = 'N' and user_delete = 'N' ";
   public static final String deleteAssignDataSql = "update rts_user_client_mapper set is_delete = 'D' where rts_user_client_mapper_id = ? ";
}
