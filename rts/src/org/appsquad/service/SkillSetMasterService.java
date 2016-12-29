package org.appsquad.service;

import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.dao.SkillSetMasterDao;
import org.zkoss.zul.Messagebox;

public class SkillSetMasterService {
	private static boolean flag = false;
	private static boolean flagInsert = false;
	private static boolean flagDelete = false;
	
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
		if(isValid(skillsetMasterbean)){
			flagInsert = SkillSetMasterDao.insertSkillData(skillsetMasterbean);
		}
		return flagInsert;
	}
	
	public static int countNumberPresentSkillName(SkillsetMasterbean skillsetMasterbean){
		return SkillSetMasterDao.countSkillName(skillsetMasterbean);
	}
	
	public static boolean deleteSkillDetails(SkillsetMasterbean skillsetMasterbean){
	     return flagDelete = SkillSetMasterDao.deleteSkillDetails(skillsetMasterbean);
	}
	
	public static void clearAllField(SkillsetMasterbean bean){
		bean.setSkillset(null);
		bean.setSkillsetdetails(null);
	}
	
	public static boolean updateSkillMasterData(SkillsetMasterbean masterbean){
		if(isValid(masterbean)){
			 flag = SkillSetMasterDao.updateSkillData(masterbean);
		}
		return flag;
	}
	
	/***********************************************************************************************************************************************/
	
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		SkillSetMasterService.flag = flag;
	}
	public static boolean isFlagInsert() {
		return flagInsert;
	}
	public static void setFlagInsert(boolean flagInsert) {
		SkillSetMasterService.flagInsert = flagInsert;
	}
	public static boolean isFlagDelete() {
		return flagDelete;
	}
	public static void setFlagDelete(boolean flagDelete) {
		SkillSetMasterService.flagDelete = flagDelete;
	}
}
