package org.appsquad.sql;

public class ClientInformationsql {
	
	public static final String stateQuery = "select state_id,state_name from rts_state_master ";
	
	public static final String countryQuery = "select country_id,country_name from rts_country_master ";
	
	public static final String skillSetQuery = "select * from rts_skill_master ";
	
	public static final String statusSetQuery = "select * from rts_status_master ";
	
	public static final String insertClientInfo = " INSERT INTO rts_clients_master(name,surname, companyname, officeaddress, state, "
                                           +" country, zipcode, contactno, emailid, created_by,state_id, country_id) "
                                           +" VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?) ";
	
	public static final String fetchClientDeatils = "select * from rts_clients_master ";
	
	public static final String clientDetailsUpdate = "UPDATE rts_clients_master set name = ?,companyname = ?,"
			                                 + "officeaddress = ?,state = ?,country = ?,zipcode = ?,contactno = ?,emailid = ?,state_id= ?,"
			                                 + "country_id = ? where id = ? ";

}
 