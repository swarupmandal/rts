package org.appsquad.sql;

public class RoleMasterSql {
	public static final String insertRoleQuery = "insert into rts_role_master(master_role_name,created_by)values(?,?) ";
	public static final String fetchRoleQuery = "select * from rts_role_master where is_delete = 'N' ";
	public static final String updateRoleQuery = "update rts_role_master set master_role_name = ? where id = ? ";
	public static final String deleteSql = "update rts_role_master set is_delete = 'D' where id = ? ";
}
