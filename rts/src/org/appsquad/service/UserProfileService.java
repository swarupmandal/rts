package org.appsquad.service;

import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.UserProfileDao;
import org.zkoss.zul.Messagebox;

public class UserProfileService {
	private static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
	
	public static boolean isValid(UserprofileBean userprofileBean){
				if(userprofileBean.getUsername()!=null && userprofileBean.getUsername().trim().length()>0){
					if(userprofileBean.getUserid()!=null && userprofileBean.getUserid().trim().length()>0){
						if(userprofileBean.getPassword()!=null && userprofileBean.getPassword().trim().length()>0){
							if(userprofileBean.getEmail()!=null && userprofileBean.getEmail().trim().length()>0){
								   if(userprofileBean.getEmail().matches(EMAIL_REGEX)){
									   if(userprofileBean.getAddress()!=null ){
										   if(userprofileBean.getContactno()!=null && userprofileBean.getContactno().trim().length()>0){
											   return true;
										   }else {
											   Messagebox.show("Enter Conatct Number!","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
											   return false;
										   }
								}else {
									Messagebox.show("Enter Address!","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
									return false;
								}
								   } else {
									Messagebox.show("Enter Proper Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
									return false;
								}
							}else {
								Messagebox.show("Enter Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else {
							Messagebox.show("Enter Password!","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else {
						Messagebox.show("Enter User-Id!","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}
				}else {
					Messagebox.show("Enter User Name!","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
					return false;
				}
			}
	
			
	public static boolean insertUserMasterData(UserprofileBean userprofileBean){
		boolean flagInsert = false;
		if(isValid(userprofileBean)){
			flagInsert = UserProfileDao.insertUserData(userprofileBean);
		}
		return flagInsert;
	}
	
	public static void clearAllField(UserprofileBean bean){
		bean.setAddress(null);
		bean.setContactno(null);
		bean.setEmail(null);
		bean.setPassword(null);
		bean.setPassword(null);
		bean.setUserid(null);
		bean.setUsername(null);
	}
	
	public static int countUserIdPresentInTable(UserprofileBean userprofileBean){
		    int count = 0;
	    	count = UserProfileDao.countPresentUserDetails(userprofileBean);
	    	return count;
	}
	
	public static boolean updateUserMasterData(UserprofileBean userprofileBean){
		boolean flagUpdate = false;
		if(isValid(userprofileBean)){
			flagUpdate = UserProfileDao.updateUserData(userprofileBean);
		}
		return flagUpdate;
	}

	/********************************************************************************************************************************/
	
	public static String getEMAIL_REGEX() {
		return EMAIL_REGEX;
	}
	public static void setEMAIL_REGEX(String eMAIL_REGEX) {
		EMAIL_REGEX = eMAIL_REGEX;
	}
}
