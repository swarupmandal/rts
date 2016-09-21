package org.appsquad.service;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.dao.StatusMasterDao;
import org.zkoss.zul.Messagebox;

public class RoleMasterService {
	private static boolean flag = false;
	
	public static boolean isValid(RoleMasterBean bean){
		if(bean.getRoll()!=null && bean.getRoll().trim().length()>0){
             return true;
		}else {
			Messagebox.show("Enter Roll","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static void insertClientMasterData(RoleMasterBean roleMasterBean){
		if(isValid(roleMasterBean)){
			RoleMasterDao.insertSkillData(roleMasterBean);
		}
	}
    
	public static void clearAllField(RoleMasterBean roleMasterBean){
		roleMasterBean.setRoll(null);
	}
	
    /************************************************************************************************************************************/
	
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		RoleMasterService.flag = flag;
	}
}
