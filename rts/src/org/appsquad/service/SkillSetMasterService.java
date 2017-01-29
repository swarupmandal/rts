package org.appsquad.service;

import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.dao.SkillSetMasterDao;
import org.zkoss.zul.Messagebox;

public class SkillSetMasterService {
	
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
	
	public static boolean insertClientMasterData(SkillsetMasterbean skillsetMasterbean){
		boolean flagInsert = false;
		if(isValid(skillsetMasterbean)){
			flagInsert = SkillSetMasterDao.insertSkillData(skillsetMasterbean);
		}
		return flagInsert;
	}
	
	public static int countNumberPresentSkillName(SkillsetMasterbean skillsetMasterbean){
		return SkillSetMasterDao.countSkillName(skillsetMasterbean);
	}
	
	public static boolean deleteSkillDetails(SkillsetMasterbean skillsetMasterbean){
		 boolean flagDelete = false;
	     flagDelete = SkillSetMasterDao.deleteSkillDetails(skillsetMasterbean);
	     return flagDelete;
	}
	
	public static void clearAllField(SkillsetMasterbean bean){
		bean.setSkillset(null);
		bean.setSkillsetdetails(null);
	}
	
	public static boolean updateSkillMasterData(SkillsetMasterbean masterbean){
		boolean flagUpdate = false;
		if(isValid(masterbean)){
			flagUpdate = SkillSetMasterDao.updateSkillData(masterbean);
		}
		return flagUpdate;
	}
	
}
