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
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		list = RequirementGenerationDao.fetchClientNameList();
		return list;
	}
	
	public static ArrayList<SkillsetMasterbean> fetchSkillSetList(){
		ArrayList<SkillsetMasterbean> list = new ArrayList<SkillsetMasterbean>();
		list = RequirementGenerationDao.fetchSkillSetList();
		return list;
	}
	
	public static ArrayList<StatusMasterBean> fetchStatusList(){
		ArrayList<StatusMasterBean> list = new ArrayList<StatusMasterBean>();
		list = RequirementGenerationDao.fetchStatusList();
		return list;
	}
	
	public static int isertDet(RequirementGenerationBean bean){
		int i = RequirementGenerationDao.onClikSubmit(bean);
		return i;
	}
	
	public static ArrayList<RequirementGenerationBean> loadReqGenMasterData(){
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		list = RequirementGenerationDao.fetchReqGenMasterData();
		return list;
	}
	
	public static ArrayList<ResourceTypeBean> loadTypeList(){
		ArrayList<ResourceTypeBean> list = new ArrayList<ResourceTypeBean>();
		list = RequirementGenerationDao.onLoadType();
		return list;
	}
	
	public static int updateReqGenMaster(RequirementGenerationBean bean){
		int i = RequirementGenerationDao.onClikUpdate(bean);
		return i;
	}
	
	public static int countWrtReqId(RequirementGenerationBean bean){
		int count = RequirementGenerationDao.countWrtRequirementId(bean);
		return count;
	}
	
	public static boolean isValid(RequirementGenerationBean bean){
		if(bean.getClientId()>0){
				if(bean.getReqSkillId()>0){
					if(bean.getJobType() != null){
						if(bean.getDetailedJob() !=null){
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
