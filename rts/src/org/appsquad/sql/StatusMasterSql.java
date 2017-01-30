package org.appsquad.sql;

public class StatusMasterSql {
	public static final String insertStatusQuery = "INSERT INTO rts_status_master(created_by,master_status_name,is_pre_bill,is_initial,is_final)"
												+ "VALUES (?,?,?,?,?) ";
    public static final String fetchStatusQuery = "select * from rts_status_master where is_delete = 'N' order by id ";
    public static final String deleteStatusQuery = "update rts_status_master set is_delete = 'D' where id = ? ";
    public static final String countStatusSql = "select count(*) from rts_status_master where master_status_name = ? and is_delete = 'N' ";
    public static final String updateStatusSql = "update rts_status_master set master_status_name = ?,is_pre_bill = ?,is_initial=?, "
    		+ "is_final=? where id = ? ";
    public static final String updateOtherStatusSql = "update rts_status_master set is_pre_bill = 'N' where id <> ?";
    public static final String updateOtherInitialStatusSql = "update rts_status_master set is_initial = 'N' where id <> ?";
    public static final String updateOtherFinalStatusSql = "update rts_status_master set is_final = 'N' where id <> ?";
}