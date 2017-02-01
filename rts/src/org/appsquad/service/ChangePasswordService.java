package org.appsquad.service;

import org.appsquad.bean.ChangePasswordbean;
import org.appsquad.dao.ChangePasswordDao;

public class ChangePasswordService {
  
	  public static int getCountNumberWrtUserAndPaswword(ChangePasswordbean changePasswordbean){
		  return ChangePasswordDao.countNumberUserIdAndPassword(changePasswordbean);
	  }
	  
}
