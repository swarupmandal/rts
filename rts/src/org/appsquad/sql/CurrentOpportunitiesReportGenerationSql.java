package org.appsquad.sql;

public class CurrentOpportunitiesReportGenerationSql {
   public static final String reportSql = " select * from rts_tracking_details_based_on_approve_reject where approval_status = 'Approve' ";
   public static final String subReportSql = " select * from rts_current_oppur_report_status where rts_tracking_details_id = ?  and paid = 'No' ";
   public static final String oppurtunityWisereportForClientSql = "select * from rts_opportunitywise_visibility where clientname like ? ";
   public static final String oppurtunityWisereportForResourceSql = "select * from rts_opportunitywise_visibility where resource_name like ? ";
   public static final String fetchResourceNameInCurrentOppurtunityScreenSql = "select resource_name from rts_opportunitywise_visibility where rts_tracking_details_id = ? ";
   public static final String fetchDateInCurrentOppurtunityScreenSql = "select * from rts_opportunitywise_visibility where rts_tracking_details_id = ? ";
}
