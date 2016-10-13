package org.appsquad.sql;

public class RecourceAllocationUpdateSql {
	public static final String statusSetQuery = "select * from rts_status_master where master_status_name!= ? and is_delete = 'N' order by id ";
}
