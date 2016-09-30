package org.appsquad.sql;

public class IndividualClientReportSql {
	
	public static final String loadRidList = "select * from vw_individual_client_report where client_id = ? ";
	
	public static final String loadRidDetailsList = "select * from vw_individual_client_report_details where req_id = ? ";

}
