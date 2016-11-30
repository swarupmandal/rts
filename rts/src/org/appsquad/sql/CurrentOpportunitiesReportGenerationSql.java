package org.appsquad.sql;

public class CurrentOpportunitiesReportGenerationSql {
   public static final String reportSql = " select * from rts_current_oppur_report_status where bill_date >= ?  and  bill_date <= ? ";
   public static final String subReportSql = " select * from rts_current_oppur_report_status where rts_tracking_details_id = ? ";
}
