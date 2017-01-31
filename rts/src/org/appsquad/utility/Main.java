package org.appsquad.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;

public class Main {	
	public static void prac(CurrentOpportunitiesReportGenerationBean bean){
		int month = 0;
		String monthName = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(bean.getCurrentOpportunitiesBean().getTentureFromUtil());
		month = cal.get(Calendar.MONTH);
		monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
		System.out.println(month+"----"+monthName);
		
		for(int i =1;i<7;i++){
			Calendar newCal = Calendar.getInstance();
			newCal.add(Calendar.DATE, i);
			Date dateLast =  newCal.getTime();
			newCal.setTime(dateLast);
			month = newCal.get(Calendar.MONTH);
			monthName = newCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
			System.out.println(month+"----"+monthName);
		}
		
		/*if(bean.getCurrentOpportunitiesBean().getTentureFromUtil().compareTo(bean.getCurrentOpportunitiesBean().getTentureToUtil())==0){
			
		}else{
			cal.add(Calendar.DATE, 1);
			Date dateLast =  cal.getTime();
			cal.setTime(bean.getCurrentOpportunitiesBean().getTentureToUtil());
			month = cal.get(Calendar.MONTH);
			monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
			System.out.println(month+"----"+monthName);
		}*/
		
		
		/*Instant instant = Instant.ofEpochMilli(bean.getCurrentOpportunitiesBean().getTentureFromUtil().getTime()); 
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); 
		LocalDate local1 = localDateTime.toLocalDate();
        System.out.println(local1);
		
		Instant instant2 = Instant.ofEpochMilli(bean.getCurrentOpportunitiesBean().getTentureToUtil().getTime()); 
		LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()); 
		LocalDate local2 = localDateTime2.toLocalDate();
		System.out.println(local2);
		
		for (LocalDate date1 = local1; date1.isBefore(local2); date1 = date1.plusDays(1))
		for (Date date10 = bean.getCurrentOpportunitiesBean().getTentureFromUtil();  date10.before(bean.getCurrentOpportunitiesBean().getTentureToUtil()); date10 = date10.plusDays(1))
		{
			Calendar cal = Calendar.getInstance();
			cal.seT
			String monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
			System.out.println(monthName);
		}
		
        while(bean.getCurrentOpportunitiesBean().getTentureFromUtil().before(bean.getCurrentOpportunitiesBean().getTentureToUtil())){
        	Calendar cal = Calendar.getInstance();
    		int month = cal.get(Calendar.MONTH);
    		String monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
            System.out.println(month);
            System.out.println(monthName);
            bean.getCurrentOpportunitiesBean().setTentureFromUtil();
        }*/
	}
	
	public static void check(CurrentOpportunitiesReportGenerationBean bean) throws Exception{
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        String date1 = formater.format(bean.getCurrentOpportunitiesBean().getTentureFromUtil());
        String date2 = formater.format(bean.getCurrentOpportunitiesBean().getTentureToUtil());
        
        beginCalendar.setTime(formater.parse(date1));
		finishCalendar.setTime(formater.parse(date2));

        while (beginCalendar.before(finishCalendar)) {
            // add one month to date per loop
            String date = formater.format(beginCalendar.getTime()).toUpperCase();
            System.out.println(date);
            beginCalendar.add(Calendar.MONTH, 1);
        }
	}
	
    public static void main(String[] args) {
        convert();

    }
    
    public static void convert(){
    	Double price = 32.0;
        DecimalFormat decim = new DecimalFormat("0.00");
        Double price2 = Double.parseDouble(decim.format(price));
        String s = decim.format(price);
        System.out.println("Converted output is :"+s);
    }
}