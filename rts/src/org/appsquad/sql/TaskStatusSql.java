package org.appsquad.sql;

public class TaskStatusSql {

	public static final String loadAllTaskStatus = "select * from rts_task_status where is_active='Y' and is_delete='N'"
			+ " order by task_status_id";
}
