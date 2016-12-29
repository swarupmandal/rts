package org.appsquad.sql;

public class SkillSetMasterSql {
	
   public static final String insertSkillSetQuery = "INSERT INTO rts_skill_master(master_skill_set_name,created_by,skill_set_details) "
		                                           +" VALUES (?,?,?)";
   
   public static final String fetchSkillSetDetails = "select id ,master_skill_set_name,skill_set_details from rts_skill_master where is_delete = 'N' ";
   public static final String updateSkillSetDetails = "update rts_skill_master set master_skill_set_name = ?,skill_set_details = ? where id= ? ";
   public static final String countSkillNameSql = "select count(*) from rts_skill_master where master_skill_set_name = ? ";
   public static final String deleteSkillSql = "update rts_skill_master set is_delete = 'D' where id = ? ";
}
