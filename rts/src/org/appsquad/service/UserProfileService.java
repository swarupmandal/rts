package org.appsquad.service;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.dao.UserProfileDao;
import org.zkoss.zul.Messagebox;

public class UserProfileService {
	
	private static boolean flag = false;
	
	public static boolean isValid(UserprofileBean userprofileBean){
		if(userprofileBean.getUsername()!=null && userprofileBean.getUsername().trim().length()>0){
			if(userprofileBean.getUserid()!=null && userprofileBean.getUserid().trim().length()>0){
				if(userprofileBean.getPassword()!=null && userprofileBean.getPassword().trim().length()>0){
					if(userprofileBean.getEmail()!=null && userprofileBean.getEmail().trim().length()>0){
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
	
	public static void insertUserMasterData(UserprofileBean userprofileBean){
		if(isValid(userprofileBean)){
			UserProfileDao.insertUserData(userprofileBean);
		}
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
}
