package org.appsquad.validator;

import java.util.List;

public class ValidationClass {
	public static boolean validation(List<?> params)
	{
		boolean flagValidate = false;
		if(params !=null){
			for(int i=0;i<params.size();i++){
				if(params.get(i)!=null){
					flagValidate = true;
				}else{
					flagValidate = false;
					break;
				}
			}
		}
		return flagValidate;
	}
}
