package org.appsquad.sql;

public class LogAuditSqlClass {
	
   public static final String insertIntoLogAuditTable = "INSERT INTO rts_log_audit_master(main_screen_name, child_screen_name, user_id,operation,created_date,operation_id) "
		   												+"VALUES (?,?,?,?,?,?) ";
   
   public static final String fetchLogDetailsSql = "select * from rts_log_audit_master where created_date>= ? and created_date<=? order by operation_id ";
   
}
