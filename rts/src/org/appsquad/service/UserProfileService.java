package org.appsquad.service;

import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.UserProfileDao;
import org.zkoss.zul.Messagebox;

public class UserProfileService {
	private static boolean flag = false;
	private static boolean flagInsert = false;
	private static boolean flagCount = false;
	private static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
	
	public static boolean isValid(UserprofileBean userprofileBean){
				if(userprofileBean.getUsername()!=null && userprofileBean.getUsername().trim().length()>0){
					if(userprofileBean.getUserid()!=null && userprofileBean.getUserid().trim().length()>0){
						if(userprofileBean.getPassword()!=null && userprofileBean.getPassword().trim().length()>0){
							if(userprofileBean.getEmail()!=null && userprofileBean.getEmail().trim().length()>0){
								   if(userprofileBean.getEmail().matches(EMAIL_REGEX)){
									   if(userprofileBean.getAddress()!=null && userprofileBean.getAddress().trim().length()>0){
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
	
	public static boolean updateUserMasterData(UserprofileBean userprofileBean){
		if(isValid(userprofileBean)){
			 flag = UserProfileDao.updateUserData(userprofileBean);
		}
		return flag;
	}

	/********************************************************************************************************************************/
	
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		UserProfileService.flag = flag;
	}
	public static boolean isFlagInsert() {
		return flagInsert;
	}
	public static void setFlagInsert(boolean flagInsert) {
		UserProfileService.flagInsert = flagInsert;
	}
	public static boolean isFlagCount() {
		return flagCount;
	}
	public static void setFlagCount(boolean flagCount) {
		UserProfileService.flagCount = flagCount;
	}
	public static String getEMAIL_REGEX() {
		return EMAIL_REGEX;
	}
	public static void setEMAIL_REGEX(String eMAIL_REGEX) {
		EMAIL_REGEX = eMAIL_REGEX;
	}
}
