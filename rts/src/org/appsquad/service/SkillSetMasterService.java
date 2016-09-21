package org.appsquad.service;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.dao.SkillSetMasterDao;
import org.zkoss.zul.Messagebox;

public class SkillSetMasterService {
	private static boolean flag = false;
	
	public static boolean isValid(SkillsetMasterbean bean){
		if(bean.getSkillset()!=null && bean.getSkillset().trim().length()>0){
			if(bean.getSkillsetdetails()!=null && bean.getSkillsetdetails().trim().length()>0){
				return true;
			}else {
				Messagebox.show("Enter Skill Details","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else {
			Messagebox.show("Enter Skill Set","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static void insertClientMasterData(SkillsetMasterbean skillsetMasterbean){
		if(isValid(skillsetMasterbean)){
			SkillSetMasterDao.insertSkillData(skillsetMasterbean);
		}
	}
	
	public static void clearAllField(SkillsetMasterbean bean){
		bean.setSkillset(null);
		bean.setSkillsetdetails(null);
	}
	
	public static boolean updateSkillMasterData(SkillsetMasterbean masterbean){
		if(isValid(masterbean)){
			 flag = SkillSetMasterDao.updateClientData(masterbean);
		}
		return flag;
	}

	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		SkillSetMasterService.flag = flag;
	}

}
