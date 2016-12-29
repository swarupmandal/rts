package org.appsquad.sql;

public class RequirementGenerationSql {
	
	public static final String selReqAllocationEqual="SELECT COALESCE(req_no_of_per_res,0) + COALESCE(req_no_of_con_res,0) as reqiured_res,COALESCE(num_of_per_res_allocated,0) + COALESCE(num_of_con_res_allocated,0) as allocated_res "
													+"from rts_requirement_master where r_id= ? ";
	
	public static final String loadClientName = "	select id, name, surname,clientname from rts_clients_master where is_delete = 'N' order by id ";
	
	public static final String loadSkillSetName = "	select id, master_skill_set_name, skill_set_details from rts_skill_master where is_delete = 'N' ";
	
	public static final String loadSkillSetNameSearch = "	select id, master_skill_set_name, skill_set_details from rts_skill_master where is_delete = 'N' and master_skill_set_name LIKE ?";
	
	public static final String loadocStatus = " select id, status from rts_open_close_status_master where is_delete = 'N' and status!= 'OPEN' "; 

	public static final String insertReqGen = "INSERT INTO rts_requirement_master( " +
									           " req_client_id, req_skill_id, req_jobtype, req_job_details, " +
									           " req_no_of_per_res, req_no_of_con_res ,req_raise_date,req_close_date,  " +
									           " cont_no,email_id,req_status_id,req_closure_reason, created_by, updated_by,overall_status_id,type_id,req_target_date " +
									           " ) " +
									           " VALUES (?, ?, ?, ?, " +
									           " ?, ?, ?, ?, " + 
									           " ?, ?, ?, ?, ?, ?,?,?,?) " ;
	
	public static final String updateReqGen =  " UPDATE rts_requirement_master " +
											   " SET req_no_of_per_res=?, req_no_of_con_res=?,req_raise_date=?, req_close_date=?, " +
											   " cont_no=?, email_id=?, req_status_id=?,req_closure_reason=?,updated_by=?,req_job_details = ? " +
											   " where r_id = ?";
	
	public static final String loadReqGenMasterData = "select * from vw_req_generation_details where req_status_id = 1 order by full_name ";
	
	public static final String loadReqGenMasterDataSql = "select * from vw_req_generation_details where req_status_id = 1 order by r_id ";
	
	public static final String fetchStatusId = "select id from rts_open_close_status_master where status = 'OPEN' ";
	
	public static final String FETCHCLIENTEMAILANDCONTACTNO = "select emailid,contactno from rts_clients_master where id = ? ";
	
	public static final String FETCHTYPE = "select * from rts_type_master where is_delete = 'N' ";
	
	public static final String countReqId = "select count(*) from rts_req_resource_mapper where req_id = ? ";
	
	public static final String countReqIdInOnboardTable = "select count(*) from rts_res_onboard_dates where r_id = ? ";
}
