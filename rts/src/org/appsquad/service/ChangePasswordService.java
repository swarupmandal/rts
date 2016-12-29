package org.appsquad.service;

import org.appsquad.bean.ChangePasswordbean;
import org.appsquad.dao.ChangePasswordDao;

public class ChangePasswordService {
	  private static int count = 0;
  
	  public static int getCountNumberWrtUserAndPaswword(ChangePasswordbean changePasswordbean){
		  return count = ChangePasswordDao.countNumberUserIdAndPassword(changePasswordbean);
	  }
	  
	  
}
