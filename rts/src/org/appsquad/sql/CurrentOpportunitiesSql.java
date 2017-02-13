package org.appsquad.sql;

public class CurrentOpportunitiesSql {
	
	// 9 = selected by client(in future if id of selected by client id changed to some other id then 9 will be changed to some other id)
	/*public static final String loadOpportumityDetailsQuery = "select * from vw_opportunity_details where status_id = 9 ";*/
	
	public static final String loadOpportumityDetailsQuery = "select * from vw_opportunity_details where status_id = (select id from rts_status_master where is_pre_bill = 'Y') ";
	
	public static final String loadClientSpecificUserQuery = "select rts_user_client_mapper_id, client_id, user_id, user_name, user_email from rts_user_client_mapper ";

	public static final String loadUserDetailsList = "select distinct user_id from rts_user_client_mapper where client_id = ? and is_delete = 'N' ";
	
	public static final String fetchRoleName = "select master_role_name from rts_user_role_mapping where user_id = ? ";
	
	public static final String fetchClientIdSql = "select client_id from rts_user_client_mapper where user_id = ? ";
	
	public static final String countTreatSql = "select count(*) from rts_user_client_mapper a1,rts_clients_master a2 "
											  +"where a1.client_id = a2.id "
											  +"and a2.clientname like ? "
											  +"and a1.user_id like ? " ;
	
	public static final String insertTrackingSql = "INSERT INTO rts_req_res_status_tracking_details(rts_req_res_status_tracking_id, tenure_from, "
                                                   +"tenure_to, charge_out_rate, resource_salary, margin, approval_status,approvar_person,user_id,percentage) "
                                                   +"VALUES (?,?,?,?,?,?,?,?,?,?) ";

	//public static final String fetchTrackingDetailsWrtTrackingIdSql = "select * from rts_tracking_details where rts_req_res_status_tracking_id = ? ";
	
	public static final String fetchTrackingDetailsWrtTrackingIdSql = "select a.rts_tracking_details_id,a.rts_req_res_status_tracking_id,a.tenure_from,a.tenure_to,a.charge_out_rate, "
								+ "a.resource_salary,a.margin,a.approval_status,a.approvar_person, "
								+ "a.final_processed,a.user_id,a.percentage,b.email as user_id_email,(select email from rts_user_master where user_id = a.approvar_person) as approver_person_email_id "
								+ "from rts_req_res_status_tracking_details a,rts_user_master b "
								+ "where a.user_id = b.user_id "
								+ "and rts_req_res_status_tracking_id = ? "
								+ "order by rts_tracking_details_id ";
	
	public static final String fetchCountTrackingIdSql = "select count(*) from rts_tracking_details where rts_req_res_status_tracking_id = ? ";
	
	public static final String deleteTrackingIdSql = "delete from rts_req_res_status_tracking_details where rts_req_res_status_tracking_id = ? ";
	
	public static final String countTrackingIdSql = "select count(*) from rts_req_res_status_tracking_details where rts_req_res_status_tracking_id = ? ";
	
	public static final String updateDetailsTableSql = "update rts_req_res_status_tracking_details set approval_status = ? where rts_req_res_status_tracking_id = ? ";
	
	public static final String updateTrackingTableSql = "update rts_req_res_status_tracking set approval_status = ? where rts_req_res_status_tracking_id = ? ";
	
	public static final String fetchApproverNameSql = "select approvar_person from rts_req_res_status_tracking_details where rts_req_res_status_tracking_id = ? ";
	
	public static final String fetchEmailWrtUserIdSql = "select email from rts_user_master where user_id = ? ";
	
 }
