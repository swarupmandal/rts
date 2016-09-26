package org.appsquad.sql;

public class ResourceMasterSql {
	
	public static final String insertResourceQuery = "INSERT INTO rts_resource_master(res_name, res_surname, res_experience, "
			                                       + "res_address, res_emailid,created_by,rts_skill_id,rts_country_id,"
			                                       + " rts_pincode,rts_contact_no, rts_status_id,rts_ctc,rts_skill_name,rts_country_name,rts_state_name, res_upcv)"
												   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

    public static final String fetchResourceQyery = "select * from rts_resource_master ";
    
    public static final String fetchResourceView = "select * from rts_resource_master ";
    
    public static final String updateResourceQuery = "update rts_resource_master set res_name = ?,res_surname = ?, res_experience =? ,res_address = ?, "
    		                                        +"res_emailid = ?,rts_skill_id = ?,rts_country_id = ?,rts_pincode = ?,rts_contact_no = ?, "
    		                                        +"rts_status_id = ?,rts_ctc = ?,rts_skill_name= ?,rts_country_name= ?,rts_state_name= ?, res_upcv = ? "
    		                                        +" where id = ? ";
    
}
