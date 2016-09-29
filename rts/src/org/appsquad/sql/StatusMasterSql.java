package org.appsquad.sql;

public class StatusMasterSql {
	public static final String insertStatusQuery = "INSERT INTO rts_status_master(created_by,master_status_name)VALUES (?,?) ";
    public static final String fetchStatusQuery = "select * from rts_status_master where is_delete = 'N' ";
    public static final String deleteStatusQuery = "update rts_status_master set is_delete = 'D' where id = ? ";
    public static final String countStatusSql = "select count(*) from rts_status_master where master_status_name = ? and is_delete = 'N' ";
}