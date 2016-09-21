package org.appsquad.service;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.dao.SkillSetMasterDao;
import org.appsquad.dao.StatusMasterDao;
import org.zkoss.zul.Messagebox;

public class StatusMasterService {
	private static boolean flag = false;

	public static boolean isValid(StatusMasterBean bean){
		if(bean.getStatus()!=null && bean.getStatus().trim().length()>0){
             return true;
		}else {
			Messagebox.show("Enter Status","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	
	public static void insertClientMasterData(StatusMasterBean statusMasterBean){
		if(isValid(statusMasterBean)){
			StatusMasterDao.insertSkillData(statusMasterBean);
		}
	}
	
	public static boolean deleteStatusMasterData(StatusMasterBean statusMasterBean){
			 flag = StatusMasterDao.deleteStatus(statusMasterBean);
		return flag;
	}
	
	public static void clearAllField(StatusMasterBean bean){
		bean.setStatus(null);
	}
	
	/********************************************************************************************************************************************/
	
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		StatusMasterService.flag = flag;
	}   
}
