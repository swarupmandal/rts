package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceTypeBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.RequirementGenerationDao;
import org.zkoss.zul.Messagebox;

public class RequirementGenerationService {
	private static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
	
	public static ArrayList<ClientInformationBean> fetchClientNameList(){
		return RequirementGenerationDao.fetchClientNameList();
	}
	
	public static ArrayList<SkillsetMasterbean> fetchSkillSetList(){
		return RequirementGenerationDao.fetchSkillSetList();
	}
	
	public static ArrayList<SkillsetMasterbean> skillSetListSearch(String name){
		return RequirementGenerationDao.skillSetSearch(name);
	}
	
	public static ArrayList<StatusMasterBean> fetchStatusList(){
		return RequirementGenerationDao.fetchStatusList();
	}
	
	public static int isertDet(RequirementGenerationBean bean){
		int i = RequirementGenerationDao.onClikSubmit(bean);
		return i;
	}
	
	public static ArrayList<RequirementGenerationBean> loadReqGenMasterData(){
		return RequirementGenerationDao.fetchReqGenMasterData();
	}
	
	public static ArrayList<ResourceTypeBean> loadTypeList(){
		return RequirementGenerationDao.onLoadType();
	}
	
	public static int updateReqGenMaster(RequirementGenerationBean bean){
		return RequirementGenerationDao.onClikUpdate(bean); 
	}
	
	public static int countWrtReqId(RequirementGenerationBean bean){
		return  RequirementGenerationDao.countWrtRequirementId(bean);
	}
	
	public static boolean getReqAllEqual(int rId){
		boolean flag = false;
		flag = RequirementGenerationDao.isequalResource(rId);
		return flag;
	}
	
	public static boolean isValid(RequirementGenerationBean bean){
		if(bean.getClientId()>0){
				if(bean.getReqSkillId()>0){
					if(bean.getJobType() != null){
						if(bean.getDetailedJob() !=null){
							if(bean.getResourceTypeBean().getResourceTypeName()!=null && bean.getResourceTypeBean().getResourceTypeName().trim().length()>0){
								if(bean.getNofPerResource() !=null || bean.getNofConResource() != null){
									if(bean.getRaiseDatesql() != null){
										if(bean.getContactNo() != null){
											if(bean.getEmail()!=null && bean.getEmail().trim().length()>0){
												   if(bean.getEmail().matches(EMAIL_REGEX)){
												return true;
											}else {
												Messagebox.show("Enter Proper Email Id ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
												return false;
											   }
											}else {
												Messagebox.show("Enter Email Id ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
												return false;
										       }
												   
											}else {
												Messagebox.show("Enter Contact No. ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
												return false;
										}
									}else {
										Messagebox.show("Select Raise Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
										return false;
									}
							}else {
								Messagebox.show("Enter Number of Permanent or Contract Resource ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
							}else{
								Messagebox.show("Enter Resource Type", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else {
							Messagebox.show("Enter Job Detail", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else {
						Messagebox.show("Enter Job Type", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}	
			}else {
				Messagebox.show("Select Skill", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else {
				Messagebox.show("Select Name", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
		}		
	}
	
	public static boolean isValidForUpdate(RequirementGenerationBean bean){
		if(bean.getClientId()>0){
				if(bean.getReqSkillId()>0){
					if(bean.getJobType() != null){
						if(bean.getDetailedJob() !=null){
							if(bean.getNofPerResource() !=null || bean.getNofConResource() != null){
									if(bean.getRaiseDate() != null){
										if(bean.getContactNo() != null){
											if(bean.getEmail()!=null && bean.getEmail().trim().length()>0){
												   if(bean.getEmail().matches(EMAIL_REGEX)){
												     return true;
											}else {
												Messagebox.show("Enter Proper Email Id ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
												return false;
											   }
											}else {
												Messagebox.show("Enter Email Id ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
												return false;
										       }
												   
											}else {
												Messagebox.show("Enter Contact No. ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
												return false;
										}
									}else {
										Messagebox.show("Select Raise Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
										return false;
									}
							}else {
								Messagebox.show("Enter Number of Permanent or Contract Resource ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else {
							Messagebox.show("Enter Job Detail", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else {
						Messagebox.show("Enter Job Type", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}	
			}else {
				Messagebox.show("Select Skill", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else {
				Messagebox.show("Select Name", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
		}		
	}
}
