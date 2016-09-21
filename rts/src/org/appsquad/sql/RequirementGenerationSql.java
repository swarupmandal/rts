package org.appsquad.sql;

public class RequirementGenerationSql {
	
	public static final String loadClientName = "	select id, name, surname from rts_clients_master where is_delete = 'N' ";
	
	public static final String loadSkillSetName = "	select id, master_skill_set_name, skill_set_details from rts_skill_master where is_delete = 'N' ";
	
	public static final String loadocStatus = " select id, status from rts_open_close_status_master where is_delete = 'N' "; //oc-open/close

	public static final String insertReqGen = "INSERT INTO rts_requirement_master( " +
									           " req_client_id, req_skill_id, req_jobtype, req_job_details, " +
									           " req_no_of_per_res, req_no_of_con_res ,req_raise_date,req_close_date,  " +
									           " cont_no,email_id,req_status_id,req_closure_reason, created_by, updated_by " +
									           " ) " +
									           " VALUES (?, ?, ?, ?, " +
									           " ?, ?, ?, ?, " + 
									           " ?, ?, ?, ?, ?, ?) " ;
	
	
}
