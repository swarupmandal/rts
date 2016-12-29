package org.appsquad.sql;

public class SortCriteriaSql {
	public static final String statusQuery = "select * from rts_status_master where is_delete IN ('N','A') ";
   
	public static final String detailsSqlQuery = "select rrst.r_id AS requirement_id, "
    		+"rsm.master_status_name, "
    		+"concat(rrm.res_name, ' ', rrm.res_surname) AS resource_name, "
    		+"rrm.id AS resource_id, "
    		+"rrm.res_experience AS resource_ecperience, "
    		+"rrm.rts_contact_no AS resource_contact_no, "
    		+"rrm.res_emailid AS resource_email_id, "
    		+"rm.master_skill_set_name AS skill_name, "
    		+"rrii.internal_interview_date, "
    		+"rrci.client_interview_date,rum.req_raise_date AS open_date, "
    		+"rum.req_close_date AS close_date,concat(rcm.name, ' ', rcm.surname) AS client_name "
    		+"from rts_req_res_status_tracking rrst, "
    		+"rts_status_master rsm, rts_resource_master rrm, "
    		+"rts_skill_master rm,rts_res_internal_interview_dates rrii, "
    		+"rts_res_client_interview_dates rrci,rts_requirement_master rum,"
    		+"rts_req_resource_mapper rrrm,rts_clients_master rcm  "
    		+"where rrst.status_id = rsm.id "
    		+"and rrst.resource_id = rrm.id "
    		+"and rrm.rts_skill_id = rm.id "
    		+"and rrst.r_id = rrii.r_id "
    		+"and rrst.resource_id = rrii.resource_id "
    		+"and rrst.r_id = rrci.r_id "
    		+"and rrst.resource_id = rrci.resource_id "
    		+"and rrst.r_id = rum.r_id "
    		+"and rrst.r_id = rrrm.req_id "
    		+"and rrst.resource_id = rrrm.resource_id "
    		+"and rrrm.client_id = rcm.id "
    		+"and rsm.master_status_name LIKE ? "
    		+"and concat(rcm.name, ' ', rcm.surname) LIKE ? "
    		+"and rm.master_skill_set_name LIKE ? "
    		+"and TO_CHAR(rum.req_raise_date::DATE, 'YYYY-MM-DD') BETWEEN ? AND ? "
    		+"group by rrst.r_id,rsm.master_status_name, "
    		+"resource_name,rrm.res_experience,rrm.rts_contact_no,rrm.res_emailid, "
    		+"rm.master_skill_set_name,rrii.internal_interview_date,rrci.client_interview_date,"
    		+ "rrm.id,rum.req_raise_date,rum.req_close_date,client_name "
    		+"order by resource_name ";
}
