package org.appsquad.sql;

public class ClientInformationsql {
	public static final String stateQuery = "select state_id,state_name from rts_state_master where country_id = ? order by state_id ";
	public static final String countryQuery = "select country_id,country_name from rts_country_master order by country_id ";
	public static final String skillSetQuery = "select * from rts_skill_master order by id ";
	public static final String statusSetQuery = "select * from rts_status_master where is_delete = 'N' order by id ";
	public static final String statusSetQueryForReport = "select * from rts_status_master where is_delete IN('N','C') order by id ";
	public static final String insertClientInfo = " INSERT INTO rts_clients_master(name,surname, clientname, officeaddress, state, "
                                           +" country, zipcode, contactno, emailid, created_by) "
                                           +" VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?) ";
	
	public static final String fetchClientDeatils = "select * from rts_clients_master where is_delete = 'N' order by id ";
	public static final String clientDetailsUpdate = "UPDATE rts_clients_master set officeaddress = ?,state = ?,zipcode = ?,"
			                                       + "contactno = ?,emailid = ?,updated_by = ? "
			                                       + "where id = ? ";
	
	public static final String deleteClientDetailsSql = "update rts_clients_master set is_delete = 'D' where id = ? ";
	public static final String countClientPresentWrtRequirementSql = "select count(*) from rts_requirement_master where req_client_id = ? ";

}
 