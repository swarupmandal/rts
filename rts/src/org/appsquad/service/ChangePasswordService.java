package org.appsquad.service;

import org.appsquad.bean.ChangePasswordbean;
import org.appsquad.dao.ChangePasswordDao;

public class ChangePasswordService {
  
	  public static int getCountNumberWrtUserAndPaswword(ChangePasswordbean changePasswordbean){
		  int count = 0;
		  count = ChangePasswordDao.countNumberUserIdAndPassword(changePasswordbean);
		  return count;
	  }
	  
}
