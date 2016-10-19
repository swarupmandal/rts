package org.appsquad.sql;

public class AssignedClientNameForResourceSql {
  public static final String FETCHDETAILS = "select * from vw_client_details_for_resource where resource_id = ? ";
  public static final String COUNTUSERPRESENTORNOTWRTRESOURCE = "select count(*) from rts_req_resource_mapper where resource_id = ? ";
}
