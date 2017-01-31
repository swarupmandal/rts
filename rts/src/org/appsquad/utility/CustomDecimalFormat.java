package org.appsquad.utility;

import java.text.DecimalFormat;

public class CustomDecimalFormat {
	public static Float floatFormat(Float value){
		if(value != null){
			return Float.valueOf(new DecimalFormat("######.##").format(value) );
		}else{
			return null;
		}
	}
	
	public static Double doubleFormat(Float value){
		if(value != null){
			return Double.valueOf(new DecimalFormat("######.##").format(value) );
		}else{
			return null;
		}
	}
	
	public static Double doubleFormat(Double value){
		if(value != null){
			return Double.valueOf(new DecimalFormat("######.###").format(value) );
		}else{
			return null;
		}
	}
	
	public static Double reqWiseDouble(Double value){
		if(value != null){
			return Double.valueOf(new DecimalFormat("######.##").format(value) );
		}else{
			return null;
		}
	}
}
