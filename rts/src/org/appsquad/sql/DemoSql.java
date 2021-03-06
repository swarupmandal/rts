package org.appsquad.sql;

public class DemoSql {
	public  static final String FETCHSQLFORSKILL = "select id,concat(res_name, ' ',res_surname) AS fullname,res_experience,res_address,res_emailid,rts_contact_no from rts_resource_master where is_delete = 'N' "
												  +"and rts_skill_name = ? order by id ";
	
	public static final String FETCHSQLFORSKILLANDDATE = "select * from vw_cv_details_skill_date where rts_skill_name = ? and TO_CHAR(req_raise_date::DATE, 'YYYY-MM-DD') BETWEEN ? AND ? ";
	public static final String FETCHSQLFORSKILLANDDATEANDCLIENT = "select * from vw_cv_details_skill_date where rts_skill_name = ? AND client_fullname = ? ";
	public static final String FETCHCVPATHWRTRESOURCE = "select res_upcv from rts_resource_master where id = ? ";
}
