package org.appsquad.service;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.dao.RoleMasterDao;
import org.zkoss.zul.Messagebox;

public class RoleMasterService {
	private static boolean flag = false;
	private static int countNumber = 0;
	
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
		if(isValidAssignTab(roleMasterBean)){
			 flag = RoleMasterDao.updateAssignData(roleMasterBean);
		}
		return flag;
	}
	
	public static int getCountUserPresentWrtRole(RoleMasterBean roleMasterBean){
		return countNumber = RoleMasterDao.countUserPresentsWrtRole(roleMasterBean);
	}
	
    /************************************************************************************************************************************/
	
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		RoleMasterService.flag = flag;
	}
	public static int getCountNumber() {
		return countNumber;
	}
	public static void setCountNumber(int countNumber) {
		RoleMasterService.countNumber = countNumber;
	}
}
