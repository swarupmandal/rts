package org.appsquad.sql;

public class DemoSql {
	public  static final String FETCHSQLFORSKILL = " select id,concat(res_name, ' ',res_surname) AS fullname,res_experience,res_address,res_emailid,rts_contact_no from rts_resource_master where is_delete = 'N' "
												  +"and non_allocable_or_not = 'N' and rts_skill_name = ? ";
    
}
