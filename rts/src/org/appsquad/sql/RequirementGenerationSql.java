package org.appsquad.sql;

public class RequirementGenerationSql {
	
	public static final String loadClientName = "	select id, name, surname from rts_clients_master where is_delete = 'N' ";
	
	public static final String loadSkillSetName = "	select id, master_skill_set_name, skill_set_details from rts_skill_master where is_delete = 'N' ";
	
	public static final String loadocStatus = " select id, status from rts_open_close_status_master where is_delete = 'N' and status!= 'OPEN' "; //oc-open/close

	public static final String insertReqGen = "INSERT INTO rts_requirement_master( " +
									           " req_client_id, req_skill_id, req_jobtype, req_job_details, " +
									           " req_no_of_per_res, req_no_of_con_res ,req_raise_date,req_close_date,  " +
									           " cont_no,email_id,req_status_id,req_closure_reason, created_by, updated_by,overall_status_id " +
									           " ) " +
									           " VALUES (?, ?, ?, ?, " +
									           " ?, ?, ?, ?, " + 
									           " ?, ?, ?, ?, ?, ?,?) " ;
	
	public static final String updateReqGen =  " UPDATE rts_requirement_master " +
											   " SET req_no_of_per_res=?, req_no_of_con_res=?,req_raise_date=?, req_close_date=?, " +
											   " cont_no=?, email_id=?, req_status_id=?,req_closure_reason=?,updated_by=? " +
											   " where r_id = ?";
	
	public static final String loadReqGenMasterData = "select * from vw_req_generation_details where req_status_id = 1";
	
	public static final String fetchStatusId = "select id from rts_open_close_status_master where status = 'OPEN' ";
	
	public static final String FETCHCLIENTEMAILANDCONTACTNO = "select emailid,contactno from rts_clients_master where id = ? ";
}
