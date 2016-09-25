package org.appsquad.sql;

public class ResourceAllocationTrackingSql {
	
	public static final String loadClientList = "select * from vw_client_details where is_delete = 'N'";
	
	public static final String loadReqIdList = "select * from vw_req_details where is_delete = 'N' and req_status_id = 1 and req_client_id = ? ";
	
	public static final String loadClNameSearch = "select * from vw_client_details where is_delete = 'N' and full_name like ? ";
	
	public static final String loadReqIdSearch = "select * from vw_req_details where is_delete = 'N' and req_status_id = 1 and req_client_id = ? and CAST(r_id AS TEXT) like ? ";

}
