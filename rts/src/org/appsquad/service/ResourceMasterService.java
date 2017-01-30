package org.appsquad.service;

import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.zkoss.zul.Messagebox;

public class ResourceMasterService {
	private static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
		
	public static boolean isValid(ResourceMasterBean resourceMasterBean){
		if(resourceMasterBean.getName()!=null && resourceMasterBean.getName().trim().length()>0){
			if(resourceMasterBean.getSurName()!=null && resourceMasterBean.getSurName().trim().length()>0){
				if(resourceMasterBean.getYearOfExperience() != null){
					if(resourceMasterBean.getSkillsetMasterbean().getSkillset()!=null && resourceMasterBean.getSkillsetMasterbean().getSkillset().trim().length()>0){
						if(resourceMasterBean.getAddress()!=null && resourceMasterBean.getAddress().trim().length()>0){
							if(resourceMasterBean.getCountry()!=null && resourceMasterBean.getCountry().trim().length()>0){
								if(resourceMasterBean.getState()!=null && resourceMasterBean.getState().trim().length()>0){
									if(resourceMasterBean.getPicCode()!=null && resourceMasterBean.getPicCode().trim().length()==6){
										if(resourceMasterBean.getContactNumber()!=null && resourceMasterBean.getContactNumber().trim().length()==10){
											if(resourceMasterBean.getEmailId()!=null && resourceMasterBean.getEmailId().trim().length()>0){
												if(resourceMasterBean.getEmailId().matches(EMAIL_REGEX)){
													if(resourceMasterBean.getFileName()!=null){
														return true;
													}else{
														Messagebox.show("Please upload cv to proceed","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
														return false;
													}
												}else {
													Messagebox.show("Enter Proper Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
													return false;
												}
											}else {
												Messagebox.show("Enter Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
												return false;
											}
										}else {
											Messagebox.show("Enter Contact Number","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
											return false;
										}
									}else {
										Messagebox.show("Enter Pincode","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
										return false;
									}
								}else {
									Messagebox.show("Enter State","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
									return false;
								}
							}else {
								Messagebox.show("Enter Country","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else {
							Messagebox.show("Enter Address","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else {
						Messagebox.show("Enter Skill Set","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}
				}else {
					Messagebox.show("Enter Year Of Experience","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
					return false;
				}
			}else {
				Messagebox.show("Enter SurName","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else {
			Messagebox.show("Enter Name","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	

	public static boolean insertClientMasterData(ResourceMasterBean resourceMasterBean){
		boolean flag = false;
		if(isValid(resourceMasterBean)){
			flag = ResourceMasterDao.insertClientData(resourceMasterBean);
		}
		return flag;
	}
	
	public static boolean updateResourceMasterData(ResourceMasterBean resourceMasterBean){
		boolean flagUpdate = false;
		if(isValid(resourceMasterBean)){
			flagUpdate = ResourceMasterDao.updateResourceData(resourceMasterBean);
		}
		return flagUpdate;
	}
	
	public static void clearAllField(ResourceMasterBean bean){
		bean.setName(null);
		bean.setSurName(null);
		bean.setYearOfExperience(null);
		bean.getSkillsetMasterbean().setSkillset(null);
		bean.setAddress(null);
		bean.setState(null);
		bean.setCountry(null);
		bean.setPicCode(null);
		bean.setContactNumber(null);
		bean.setEmailId(null);
		bean.setCtc(null);
		bean.setFilePath(null);
		bean.setProfit(null);
		bean.setOtherInfo(null);
	}
	
	public static int countResourceNumberInMapperTable(ResourceMasterBean resourceMasterBean){
		int countNumber = 0;
		countNumber = ResourceMasterDao.countLastNumberOfResource(resourceMasterBean);
		return countNumber;
	}
	
	public static boolean deleteResourceMasterData(ResourceMasterBean resourceMasterBean){
		 boolean flagDelete = false;
	     flagDelete = ResourceMasterDao.deleteResourceData(resourceMasterBean);
	     return flagDelete;
	}


	/*******************************************************************************************************************************************/
	
	public static String getEMAIL_REGEX() {
		return EMAIL_REGEX;
	}
	public static void setEMAIL_REGEX(String eMAIL_REGEX) {
		EMAIL_REGEX = eMAIL_REGEX;
	}
	
}
