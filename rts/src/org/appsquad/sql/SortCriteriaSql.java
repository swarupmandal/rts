package org.appsquad.sql;

public class SortCriteriaSql {
	public static final String statusQuery = "select * from rts_status_master where is_delete IN ('N','A') ";
}
