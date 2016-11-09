package org.appsquad.sql;

public class CurrentOpportunitiesSql {
	
	// 9 = selected by client(in future if id of selected by client id changed to some other id then 9 will be changed to some other id)
	public static final String loadOpportumityDetailsQuery = "select * from vw_opportunity_details where status_id = 9 ";
	
	public static final String loadClientSpecificUserQuery = "select rts_user_client_mapper_id, client_id, user_id, user_name, user_email from rts_user_client_mapper ";

	public static final String loadUserDetailsList = "select distinct user_id, user_name, user_email from rts_user_client_mapper where client_id = ?";
	
}
