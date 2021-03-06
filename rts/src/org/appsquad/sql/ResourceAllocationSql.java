package org.appsquad.sql;

public class ResourceAllocationSql {
	
    public static final String fetchRequirementSql = "select * from vw_req_skill_details where req_client_id = ? and overall_status_name!='CLOSE' order by r_id ";
    public static final String fetchTypeSql = "select * from rts_type_master where is_delete = 'N' ";
    public static final String fetchSkillName = "select master_skill_set_name from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String fetchRequiredResourceNumberPer = "select req_no_of_per_res from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String fetchRequiredResourceNumberCon = "select req_no_of_con_res from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String fetchRequiredResourceNumberPerAllocated = "select num_of_per_res_allocated from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String fetchRequiredResourceNumberConAllocated = "select num_of_con_res_allocated from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String updateResourceTableSql = "update rts_resource_master set non_allocable_or_not = 'Y' where id = ? ";
    public static final String updateResourceTablePerSql = "update rts_requirement_master set num_of_per_res_allocated = ? where req_client_id = ? and r_id = ? "; 
    public static final String updateResourceTableConSql = "update rts_requirement_master set num_of_con_res_allocated = ? where req_client_id = ? and r_id = ? "; 
    public static final String insertIntoMapperSql = "INSERT INTO rts_req_resource_mapper(req_id, resource_id, client_id, created_by,res_type_id) VALUES (?, ?, ?,?,?) ";
    public static final String insertIntoTrackingHistoryTableSql = "INSERT INTO rts_req_res_status_tracking(r_id, resource_id, status_id,created_by) VALUES (?, ?,?, ?) "; 
    public static final String fetchStatusIdSql = "select id from rts_status_master where is_initial = 'Y' and is_delete = 'N' ";
    public static final String fetchResourceDetails = "select id,res_name,res_surname,res_experience,res_address,res_emailid,"
    		                                        + "rts_skill_name from rts_resource_master where rts_skill_name  = ? and is_delete = 'N' order by id ";
    public static final String fetchAllFieldSql = "select * from vw_req_skill_details where req_client_id = ? and r_id = ? ";
    public static final String countFromMapperSql = "select count(*) from rts_reject_mapper where req_id = ? and res_id = ? ";
    public static final String countFromMapperSqlForResAndClient = "select count(*) from rts_req_resource_mapper where resource_id = ? and client_id = ? and req_id = ? ";

    
    public static final String fetchResourceDetailsForCurOppurScreen = "select id,res_name,res_surname,res_experience,res_address,res_emailid,"
                                                                     + "rts_skill_name from rts_resource_master where is_delete = 'N' order by id ";
    
    public static final String fetchResourceDetailsForCurOppurScreenForSerach = "select * from rts_resource_details where resource_full_name like ? order by id ";

}
