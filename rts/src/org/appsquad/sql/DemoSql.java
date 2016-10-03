package org.appsquad.sql;

public class DemoSql {
	public  static final String FETCHSQL = "select id,concat(res_name, ' ',res_surname) AS fullname,res_experience,res_address,res_emailid,rts_contact_no from rts_resource_master where is_delete = 'N' ";

}
