package org.appsquad.sql;

public class ResourceAllocationSql {
    public static final String fetchRequirementSql = "select * from vw_req_skill_details where req_client_id = ? ";
    public static final String fetchTypeSql = "select * from rts_type_master where is_delete = 'N' ";
    public static final String fetchSkillName = "select master_skill_set_name from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String fetchRequiredResourceNumberPer = "select req_no_of_per_res from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String fetchRequiredResourceNumberCon = "select req_no_of_con_res from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String fetchResourceDetails = "select id,res_name,res_surname,res_experience,res_address,res_emailid from rts_resource_master ";
}
