package org.appsquad.sql;

public class IndividualRequirementReportSql {

 	public static final String loadReqIdDetails = "select * from vw_req_details where is_delete = 'N' order by r_id " ;
 	
 	public static final String loadClientDetails = "select * from vw_individual_client_report where req_id = ? ";
	
	public static final String loadRidDetailsList = "select * from vw_individual_client_report_details where req_id = ? ";
	
	
}
