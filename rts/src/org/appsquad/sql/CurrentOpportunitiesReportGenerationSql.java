package org.appsquad.sql;

public class CurrentOpportunitiesReportGenerationSql {
   /*public static final String reportSql = " select * from rts_tracking_details_based_on_approve_reject where approval_status = 'Approve' ";*/
   /*public static final String subReportSql = " select * from rts_current_oppur_report_status where rts_tracking_details_id = ?  and paid = 'No' ";*/
	
   public static final String reportSql = "select * from rts_tracking_details_based_on_approve_reject where approval_status = 'Approve' order by rts_tracking_details_id ";	
   
   public static final String reportSql1 = "select * from rts_tracking_details_based_on_approve_reject where approval_status = 'Approve' and client_serial_id = ? order by rts_tracking_details_id ";	
   
   
   
   public static final String subReportSql = " select * from rts_unpaid_details_for_report where rts_tracking_details_id = ? ";
   
   public static final String subReportSql1 = " select * from rts_paid_unpaid_details_for_report where rts_tracking_details_id = ? ";
   
   public static final String subReportSql2 = " select * from rts_paid_unpaid_details_for_report where rts_tracking_details_id = ? and bill_date >= ? and bill_date<= ? ";
   
   
   public static final String subReportSql4 = " select * from rts_paid_unpaid_details_for_report where rts_tracking_details_id = ? and bill_date >= ? and bill_date<= ? and paid = ? ";
   
   
   public static final String subReportSql3 = " select * from rts_paid_unpaid_details_for_report where rts_tracking_details_id = ? and paid = ? ";
   
   
   public static final String oppurtunityWisereportForResourceSql = "select * from rts_opportunitywise_visibility where resource_name like ? ";
   
   public static final String oppurtunityWiseReportresourceSql = "select * from rts_tracking_details_based_on_approve_reject where resource_name like ? and approval_status = 'Approve'";
   
   public static final String oppurtunityWiseReportresourceAllSql = "select * from rts_tracking_details_based_on_approve_reject where approval_status = 'Approve'";
   
   
   public static final String fetchResourceNameInCurrentOppurtunityScreenSql = "select resource_name from rts_tracking_details_based_on_approve_reject where rts_tracking_details_id = ? ";
   public static final String fetchDateInCurrentOppurtunityScreenSql = "select * from rts_opportunitywise_visibility where rts_tracking_details_id = ? ";

   public static final String oppurtunityWisereportForClientSql = "select * from rts_tracking_details_based_on_approve_reject where clientname like ? and approval_status = 'Approve'";

   public static final String oppurtunityWisereportForAllSql = "select * from rts_tracking_details_based_on_approve_reject where approval_status = 'Approve'";

   
   public static final String marginSetOppurtunityWiseSql = "select charge_out_rate,resource_salary,margin,percentage from rts_req_res_status_tracking_details where rts_tracking_details_id = ? ";
}
