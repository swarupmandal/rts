package org.appsquad.utility;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bsh.ParseException;

public class Testing {

	public static void main(String[] args) throws Exception{
		String date1 = "2016-01-01";
        String date2 = "2016-11-11";

        getMonthsBetween(date1 , date2);
		
		
		
		
		
		
		
		
		
		
		/*String num1 = "326767",num2="326770",chknum="326770";
		if(Long.parseLong(chknum) >= Long.parseLong(num1) && Long.parseLong(chknum) <= Long.parseLong(num2)){
			System.out.println("Ok");
		}else{
			System.out.println("invalid");
		}
		System.out.println("main");
        String date1 = "2017-01-12";
        String date2 = "2016-01-12";

        DateFormat formater = new SimpleDateFormat("yyyy-mm-dd");

        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        beginCalendar.setTime(formater.parse(date1));
		finishCalendar.setTime(formater.parse(date2));

        while (beginCalendar.before(finishCalendar)) {
            // add one month to date per loop
            String date =     formater.format(beginCalendar.getTime()).toUpperCase();
            System.out.println(date);
            beginCalendar.add(Calendar.MONTH, 1);
        }*/
       
    }
	
	public static List<String> getMonthsBetween(String fromDate, String toDate) {
	    List<String> returningMonthList = new ArrayList<String>();
	    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        try {
			beginCalendar.setTime(formater.parse(fromDate));
			finishCalendar.setTime(formater.parse(toDate));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (beginCalendar.before(finishCalendar)) {
            // add one month to date per loop
            String date =     formater.format(beginCalendar.getTime()).toUpperCase();
           // System.out.println(date);
            String[] month = date.split("-");
          //  System.out.println("Year: "+month[0]+" Month :"+month[1]);
            returningMonthList.add(month[1]);
            beginCalendar.add(Calendar.MONTH, 1);
        }
		for(String month : returningMonthList)
			System.out.println(month);
		return returningMonthList;
	}

}
