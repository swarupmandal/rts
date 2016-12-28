package org.appsquad.sql;

public class TaskModifySql {

	public static final String updateTaskSql = "UPDATE rts_task_details "
				+" SET update_date=current_timestamp,  remarks_or_results=?, " 
				+" actual_completion_date=?, task_status_id=? "
				+" WHERE rts_task_id=? and assigned_to=?";
}
