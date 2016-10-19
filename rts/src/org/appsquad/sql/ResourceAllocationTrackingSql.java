package org.appsquad.sql;

public class ResourceAllocationTrackingSql {
	public static final String loadClientList = "select * from vw_client_details where is_delete = 'N' order by id ";
	public static final String loadReqIdList = "select * from vw_req_details where is_delete = 'N' and req_status_id = 1 and req_client_id = ? ";
	public static final String loadClNameSearch = "select * from vw_client_details where is_delete = 'N' and full_name like ? ";
	public static final String loadReqIdSearch = "select * from vw_req_details where is_delete = 'N' and req_status_id = 1 and req_client_id = ? and CAST(r_id AS TEXT) like ? ";
	
	public static final String loadReqIdSearchWithRid = "select * from vw_req_details where is_delete = 'N' and req_status_id = 1 and CAST(r_id AS TEXT) like ? ";
	
	
	public static final String loadTrackingBean = "select * from vw_resource_tracking_details where req_id = ? and client_id = ? ";
	public static final String loadTrackingBeanSearch = "select * from vw_resource_tracking_details where req_id = ? and client_id = ? and full_name like ? ";
	public static final String inSertLastStatus = "insert into rts_req_res_status_tracking (r_id, resource_id, status_id, created_by, updated_by) values (?, ?, ?, ?, ?) ";
	public static final String insertIntInterviewDate = "insert into rts_res_internal_interview_dates (r_id, resource_id, client_id, internal_interview_date, created_by, updated_by,other_comments ) values (?, ?,?, ?, ?, ?, ?)";
	public static final String UpdateIntInterviewDate = "update rts_res_internal_interview_dates set internal_interview_date = ?,created_by = ?,updated_by= ?,other_comments = ? "
													  + "where r_id = ? and resource_id = ? and client_id = ? ";
	
	public static final String insertClientInterviewDate = "insert into rts_res_client_interview_dates (r_id, resource_id, client_id, client_interview_date, created_by, updated_by, other_comments ) values (?, ?,?, ?, ?, ?, ?)";
	
	public static final String updateClientInterviewDate = " update rts_res_client_interview_dates set client_interview_date = ?, created_by = ?, updated_by = ?, other_comments = ? where r_id = ? and resource_id = ? and client_id = ? ";
	
	public static final String insertOnboardDate = "insert into rts_res_onboard_dates (r_id, resource_id, client_id, onboard_date, created_by, updated_by, other_comments ) values (?,?, ?, ?, ?, ?, ?)";
	
	public static final String updateOnboardDate = "update rts_res_onboard_dates set onboard_date = ?,created_by = ?,updated_by = ?, other_comments = ? where r_id = ? and resource_id = ? and client_id = ? ";
	
	public static final String countStatusTrackingTable = "select count(*) from rts_req_res_status_tracking where r_id = ? and resource_id = ? and status_id = ? ";
			
	public static final String getTypeIdWrtReqId = "select rtm.type_name from rts_requirement_master rrm,rts_type_master rtm "
												  +"where rrm.type_id = rtm.type_id and rrm.r_id = ? ";	
	
	public static final String updateRejectedSqlPer = "update rts_requirement_master set num_of_per_res_allocated = ? where r_id= ? ";
	public static final String updateRejectedSqlCon = "update rts_requirement_master set num_of_con_res_allocated = ? where r_id= ? ";
	
	public static final String fetchConData = "select num_of_con_res_allocated from rts_requirement_master where r_id= ? ";
	public static final String fetchPerData = "select num_of_per_res_allocated from rts_requirement_master where r_id= ? ";
	
	public static final String insertIntoRejectMapper = "insert into rts_reject_mapper (req_id,res_id,client_id,created_by) values (?,?,?,?) ";
	
	public static final String updateResourceTable = "update rts_resource_master set non_allocable_or_not = 'N' where id = ? ";
}
