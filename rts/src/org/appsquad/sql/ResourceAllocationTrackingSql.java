package org.appsquad.sql;

public class ResourceAllocationTrackingSql {
	
	public static String loadClientList = "select * from vw_client_details where is_delete = 'N'";

}
