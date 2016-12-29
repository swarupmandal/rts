package org.appsquad.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bsh.ParseException;

public class Testing {

	public static void main(String[] args) throws Exception{
		String num1 = "326767",num2="326770",chknum="326770";
		if(Long.parseLong(chknum) >= Long.parseLong(num1) && Long.parseLong(chknum) <= Long.parseLong(num2)){
			System.out.println("Ok");
		}else{
			System.out.println("invalid");
		}
		/*System.out.println("main");
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

}
