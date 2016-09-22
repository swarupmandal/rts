package org.appsquad.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformatter {

	String date1;

	public static String formatdate(Date date) {
		
		if (date != null) {
			
			/*return new SimpleDateFormat("dd-MMM-yyyy").format(date);*/
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		} else {
		
			return "";
		}
	}
	
	public static String toStringDate(String yyyyMMDD){
		  String reformattedDate = "";
		  SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		  SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		  
		   try { 
		       reformattedDate = myFormat.format(fromUser.parse(yyyyMMDD));
		   } catch (ParseException e) {
		       e.printStackTrace();
		   }
		   return reformattedDate;
		 }
	/************* current date *****************/
	
	public static Date date(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		return date;
	}
	
	/************** util to sql date ****************/
	
	public static java.sql.Date sqlDate(Date date){
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		return dateSql;
		
	}
	
	
	
	public static Dateformatter getInstance() {
		return new Dateformatter();
	}
	
}
