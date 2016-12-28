package org.appsquad.sql;

public class CurrentOpportunitiesReportSql {
   public static final String fetchTrackingDetailsSql = "select * from rts_tracking_details_based_on_approve_reject where approval_status = 'Approve' ";
   public static final String fetchMonthNameSql = "select month_name from rts_month ";
   public static final String fetchYearSql = "select year from rts_year ";
   public static final String insertIntoTrackingFinalTableSql = "INSERT INTO rts_tracking_final_job_details(month, year, timesheet, invoice_copy, bill_no, "
													           +"bill_date, bill_amount, paid, chq_details,rts_tracking_details_id) "
													           +"VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?) ";
   
   public static final String countLastNumberSql = "select max(final_details_id) from rts_tracking_final_job_details ";   
   public static final String fetchFinalDetailsSql = "select * from rts_tracking_final_details where rts_tracking_details_id = ? ";
   public static final String fetchCountTrackingIdSql = "select count(*) from rts_tracking_final_job_details where rts_tracking_details_id = ? ";
   public static final String deleteTrackingIdSql = "delete from rts_tracking_final_job_details where rts_tracking_details_id = ? ";
   public static final String updateDetailsTableSql = "update rts_req_res_status_tracking_details set final_processed = 'Y' where rts_tracking_details_id = ? ";

   public static final String insertBillingDetailsSql = "INSERT INTO rts_tracking_final_job_details(month, year, timesheet, invoice_copy, bill_no, "
           									+"bill_date, bill_amount, paid, chq_details,rts_tracking_details_id, timesheet_name, invoice_copy_name) "
           									+"VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?) ";
   
   public static final String deleteBillingForTrackingIdSql = "DELETE FROM rts_tracking_final_job_details WHERE rts_tracking_details_id = ? ";

   public static final String fetchBillingDetailsSql = "select * from rts_tracking_final_job_details where rts_tracking_details_id = ? ";
}
