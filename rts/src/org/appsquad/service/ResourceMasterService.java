package org.appsquad.service;

import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.zkoss.zul.Messagebox;

public class ResourceMasterService {
	private static boolean flag = false;
	private static boolean flagInsert = false;
	private static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
	
	public static boolean isValid(ResourceMasterBean resourceMasterBean){
		if(resourceMasterBean.getName()!=null && resourceMasterBean.getName().trim().length()>0){
			if(resourceMasterBean.getSurName()!=null && resourceMasterBean.getSurName().trim().length()>0){
				if(resourceMasterBean.getYearOfExperience() != null){
					if(resourceMasterBean.getSkillsetMasterbean().getSkillset()!=null && resourceMasterBean.getSkillsetMasterbean().getSkillset().trim().length()>0){
						if(resourceMasterBean.getAddress()!=null && resourceMasterBean.getAddress().trim().length()>0){
							if(resourceMasterBean.getCountryBean().getCountryName()!=null){
									if(resourceMasterBean.getPicCode()!=null && resourceMasterBean.getPicCode().trim().length()>0){
										if(resourceMasterBean.getContactNumber()!=null && resourceMasterBean.getContactNumber().trim().length()>0){
											if(resourceMasterBean.getEmailId()!=null){
											if(resourceMasterBean.getEmailId().matches(EMAIL_REGEX)){
												if(resourceMasterBean.getStateBean().getStateName()!=null){
													return true;
												}else {
													Messagebox.show("Enter State","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
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
		if(isValid(resourceMasterBean)){
			flagInsert = ResourceMasterDao.insertClientData(resourceMasterBean);
		}
		return flagInsert;
	}
	
	public static boolean updateResourceMasterData(ResourceMasterBean resourceMasterBean){
		if(isValid(resourceMasterBean)){
			 flag = ResourceMasterDao.updateResourceData(resourceMasterBean);
		}
		return flag;
	}
	
	public static void clearAllField(ResourceMasterBean bean){
		bean.setName(null);
		bean.setSurName(null);
		bean.setYearOfExperience(null);
		bean.getSkillsetMasterbean().setSkillset(null);
		bean.setAddress(null);
		bean.getStateBean().setStateName(null);
		bean.getCountryBean().setCountryName(null);
		bean.setPicCode(null);
		bean.setContactNumber(null);
		bean.setEmailId(null);
		bean.setCtc(null);
		bean.setFilePath(null);
		bean.setProfit(null);
	}
	
	/*******************************************************************************************************************************************/
	
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		ResourceMasterService.flag = flag;
	}

	public static boolean isFlagInsert() {
		return flagInsert;
	}

	public static void setFlagInsert(boolean flagInsert) {
		ResourceMasterService.flagInsert = flagInsert;
	}

	public static String getEMAIL_REGEX() {
		return EMAIL_REGEX;
	}

	public static void setEMAIL_REGEX(String eMAIL_REGEX) {
		EMAIL_REGEX = eMAIL_REGEX;
	}
}
