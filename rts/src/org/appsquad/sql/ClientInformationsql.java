package org.appsquad.sql;

public class ClientInformationsql {
	
	public static String stateQuery = "select state_id,state_name from rts_state_master ";
	
	public static String countryQuery = "select country_id,country_name from rts_country_master ";
	
	public static String insertClientInfo = " INSERT INTO rts_clients_master(name,surname, companyname, officeaddress, state, "
                                           +" country, zipcode, contactno, emailid, created_by,state_id, country_id) "
                                           +" VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?) ";
	
	public static String fetchClientDeatils = "select * from rts_clients_master ";

}
