package org.appsquad.sql;

public class IndividualClientReportSql {
	
	public static final String loadRidDetailsList = "select * from vw_individual_client_report_details where req_id = ? ";
	
					
					//******************************* only client selection ***************************
	
	public static final String loadRidList = "select * from vw_individual_client_report where client_id = ? ";
	
					
	
					//******************************  only date range selection ************************

	public static final String loadRidListWithDateRange = " select * from vw_individual_client_report where req_raise_date >= ?  and  req_raise_date <= ? and client_id = ? ";
	
	
	
					//****************************** client + date range + skill **********************
	
	public static final String loadRidListWithDateRangeAndSkill = "select * from vw_individual_client_report where req_raise_date >= ?  and  req_raise_date <= ? and req_skill_id = ? and client_id = ? ";
	
	
					//****************************** client + date range + skill **********************
	
	public static final String loadRidListWithSkill = "select * from vw_individual_client_report where req_skill_id = ? and client_id = ? ";
	
	
	
				  //******************************* client + status *********************************
	
	public static final String loadRidListWithStatus = "select * from vw_individual_client_report where client_id = ? ";
	
	public static final String loadRidDetailsListWithStatus = "select * from vw_individual_client_report_details where req_id = ? and final_status_id = ? ";
	
				  //************************* date + skill + status + client ************************
	
	public static final String loadRidListWithDateAndSkillAndStatus = "select * from vw_individual_client_report where req_raise_date >= ?  and  req_raise_date <= ? and req_skill_id = ? and client_id = ? ";
	
				  //************************** skill + status + client ********************************
	
	public static final String loadRidListWithSkillAndStatus = "select * from vw_individual_client_report where req_skill_id = ?  and client_id = ? ";
	
				  //************************** date + status + client ********************************	
	
	public static final String loadRidListWithDateStatus = "select * from vw_individual_client_report where req_raise_date >= ?  and  req_raise_date <= ? and client_id = ? ";
	
				  
	
				  //************************* summary ************************************************
	
	public static final String loadRidListSummay= "select * from vw_individual_client_report where req_id = ? ";
	
	public static final String loadStatusSummmary = "select final_status, total_count from vw_status_count where req_id = ? ";
	
	
	
	
	
	             // ******************************* individual req report**************************
	
	public static final String loadIndividualRid = "select * from vw_individual_client_report where req_id = ? ";
	
	public static final String loadIndividualRidDetails = "select * from vw_individual_client_report_details where req_id = ? ";
	
	 
	
				//******************************  only date range selection skill wise report ************************

     public static final String loadRidListWithDateRangeSkillWiseReport = " select * from vw_individual_client_report where req_raise_date >= ?  and  req_raise_date <= ? ";

		
		        //******************************  only date range selection date + skill wise report ************************
		
	public static final String loadRidListWithDateRangeAndSkillReport = "select * from vw_individual_client_report where req_raise_date >= ?  and  req_raise_date <= ? and req_skill_id = ? ";
		
				
	
}
