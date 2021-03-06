package org.appsquad.sql;

public class RoleMasterSql {
	public static final String insertRoleQuery = "insert into rts_role_master(master_role_name,created_by)values(?,?) ";
	public static final String fetchRoleQuery = "select * from rts_role_master where is_delete = 'N' order by id ";
	public static final String updateRoleQuery = "update rts_role_master set master_role_name = ? where id = ? ";
	public static final String deleteSql = "update rts_role_master set is_delete = 'D' where id = ? ";
	public static final String fetchUserQuery = "select * from rts_user_master where is_delete = 'N' order by id ";
	
	public static final String fetchUserQueryForTaskScreen = "select * from rts_user_master where is_delete = 'N' and user_id !=? order by id ";
	
	public static final String fetchUserQueryForreportSql = "select * from rts_user_master where is_delete = 'N' and user_id like ? order by id ";
	
	public static final String fetchUserQueryForTask = "select * from rts_user_master where is_delete = 'N' and user_id like ? and user_id !=? order by id ";
	
	/*public static final String fetchUserQueryForApproverSql = "select * from rts_user_role_mapping where master_role_name = 'APPROVER' order by id ";*/
	
	public static final String fetchUserQueryForApproverSql = "select * from rts_approver_based_on_page_access ";
	
	public static final String fetchUserQuerySearchsql = "select * from rts_approver_based_on_page_access where user_id like ? ";
	
	public static final String fetchUserQuerySearch = "select * from rts_user_master where is_delete = 'N' and user_id like ? order by id ";
	/*public static final String fetchUserQuerySearchsql = "select * from rts_user_role_mapping where master_role_name = 'APPROVER' and user_id like ? order by id ";*/
	public static final String insertMappingQuery = "insert into rts_user_role_mapper (user_id,role_id) values(?,?) ";
	public static final String fetchMappingQuery =   "select rurm.user_role_mapper_id,rum.user_id,rurm.role_id,rum.user_name,rrm.master_role_name,rum.id "
													+"from rts_user_role_mapper rurm,rts_user_master rum,rts_role_master rrm "
													+"where rurm.user_id = rum.id "
													+"and rurm.role_id = rrm.id "
													+"and rum.is_delete = 'N' "
													+"and rrm.is_delete = 'N' order by user_id ";
	
	public static final String updateMappingQuery = "update rts_user_role_mapper set role_id = ? where user_role_mapper_id = ? ";
	public static final String countSqlQuery = "select count(*) from rts_user_role_mapper where user_id = ? ";
	public static final String countRoleSqlQuery = "select count(*) from rts_role_master where master_role_name = ? ";
	public static final String countUserPresentsWrtRole = "select count(*) from rts_user_role_mapper where role_id = ? ";
	
	public static final String fetchRoleQueryForDrop = "select * from rts_role_master where is_delete = 'N' and master_role_name!= ? ";
}
