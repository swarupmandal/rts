package org.appsquad.sql;

public class ResourceAllocationTrackingSql {
	
	public static final String loadClientList = "select * from vw_client_details where is_delete = 'N'";
	
	public static final String loadReqIdList = "select * from vw_req_details where is_delete = 'N' and req_status_id = 1 and req_client_id = ? ";
	
	public static final String loadClNameSearch = "select * from vw_client_details where is_delete = 'N' and full_name like ? ";
	
	public static final String loadReqIdSearch = "select * from vw_req_details where is_delete = 'N' and req_status_id = 1 and req_client_id = ? and CAST(r_id AS TEXT) like ? ";

	public static final String loadTrackingBean = "select * from vw_resource_tracking_details where req_id = ? and client_id = ? ";
	
	public static final String loadTrackingBeanSearch = "select * from vw_resource_tracking_details where req_id = ? and client_id = ? and full_name like ? ";
	
	public static final String inSertLastStatus = "insert into rts_req_res_status_tracking (r_id, resource_id, status_id, created_by, updated_by) values (?, ?, ?, ?, ?) ";
	
	public static final String insertIntInterviewDate = "insert into rts_res_internal_interview_dates (r_id, resource_id, client_id, internal_interview_date, created_by, updated_by ) values (?, ?, ?, ?, ?, ?)";
	
	public static final String UpdateIntInterviewDate = "update rts_res_internal_interview_dates set internal_interview_date = ?,created_by = ?,updated_by= ? "
													  + "where r_id = ? and resource_id = ? and client_id = ? ";
	
	public static final String insertClientInterviewDate = "insert into rts_res_client_interview_dates (r_id, resource_id, client_id, client_interview_date, created_by, updated_by ) values (?, ?, ?, ?, ?, ?)";
	
	public static final String updateClientInterviewDate = "update rts_res_client_interview_dates set client_interview_date = ?,created_by = ?,updated_by = ? where r_id = ?, resource_id = ?, client_id = ? ";
	
	public static final String insertOnboardDate = "insert into rts_res_onboard_dates (r_id, resource_id, client_id, onboard_date, created_by, updated_by ) values (?, ?, ?, ?, ?, ?)";
	
	public static final String updateOnboardDate = "update rts_res_onboard_dates set onboard_date = ?,created_by = ?,updated_by = ? where r_id = ?, resource_id = ?, client_id = ? ";
	
	public static final String countStatusTrackingTable = "select count(*) from rts_req_res_status_tracking where r_id = ? and resource_id = ? and status_id = ? ";
	
}
