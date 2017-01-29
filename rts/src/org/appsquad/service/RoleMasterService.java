package org.appsquad.service;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.dao.RoleMasterDao;
import org.zkoss.zul.Messagebox;

public class RoleMasterService {
	
	public static boolean isValid(RoleMasterBean bean){
		if(bean.getRoll()!=null && bean.getRoll().trim().length()>0){
             return true;
		}else {
			Messagebox.show("Enter Roll","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean isValidAssignTab(RoleMasterBean bean){
		if(bean.getUserprofileBean().getUsername()!=null && bean.getUserprofileBean().getUsername().trim().length()>0){
             if(bean.getDownBean().getRoll()!=null && bean.getDownBean().getRoll().trim().length()>0){
            	 return true;
             }else {
     			Messagebox.show("Enter Roll","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
    			return false;
    		}
		}else {
			Messagebox.show("Enter User Name!","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static void insertClientMasterData(RoleMasterBean roleMasterBean){
		if(isValid(roleMasterBean)){
			RoleMasterDao.insertSkillData(roleMasterBean);
		}
	}
    
	public static void insertAssignData(RoleMasterBean roleMasterBean){
		if(isValidAssignTab(roleMasterBean)){
			RoleMasterDao.insertAssignData(roleMasterBean);
		}
	}
	
	public static void clearAllField(RoleMasterBean roleMasterBean){
		roleMasterBean.setRoll(null);
	}
	
	public static void clearAllFieldAssign(RoleMasterBean roleMasterBean){
		roleMasterBean.getUserprofileBean().setUsername(null);
		roleMasterBean.getDownBean().setRoll(null);;
	}
	
	public static boolean updateAssignRoleData(RoleMasterBean roleMasterBean){
		boolean flagUpdate = false;
		if(isValidAssignTab(roleMasterBean)){
			flagUpdate = RoleMasterDao.updateAssignData(roleMasterBean);
		}
		return flagUpdate;
	}
	
	public static int getCountUserPresentWrtRole(RoleMasterBean roleMasterBean){
		int countNumber = 0;
		countNumber = RoleMasterDao.countUserPresentsWrtRole(roleMasterBean);
		return countNumber;
	}
	
}
