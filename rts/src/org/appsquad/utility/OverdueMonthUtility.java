package org.appsquad.utility;

import org.appsquad.bean.OverdueMonthBean;

public class OverdueMonthUtility {
	public static void populateMonthNumber(Integer monthID,OverdueMonthBean bean){
		switch(monthID) {
		
		case 1:
			bean.setMonth("Month- 1");
			break;
			
		case 2:
			bean.setMonth("Month- 2");
			break;
			
		case 3:
			bean.setMonth("Month- 3");
			break;
		
		case 4:
			bean.setMonth("Month- 4");
			break;
			
		case 5:
			bean.setMonth("Month- 5");
			break;
			
		case 6:
			bean.setMonth("Month- 6");
			break;
			
		case 7:
			bean.setMonth("Month- 7");
			break;
			
		case 8:
			bean.setMonth("Month- 8");
			break;
			
		case 9:
			bean.setMonth("Month- 9");
			break;
			
		case 10:
			bean.setMonth("Month- 10");
			break;
			
		case 11:
			bean.setMonth("Month- 11");
			break;
			
		case 12:
			bean.setMonth("Month- 12");
			break;
			
		default:
            break;	
		
		}
	}
}
