package org.appsquad.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ClientInformationDao;
import org.zkoss.zul.Messagebox;

public class ClientInformationService {
	private static boolean flag = false;
	private static boolean flagDelete = false;
	private static int countNumber = 0;
	private static String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
	
	public static boolean isValid(ClientInformationBean clientInformationBean){
		if(clientInformationBean.getClientOriginalName()!=null && clientInformationBean.getClientOriginalName().trim().length()>0){
			if(clientInformationBean.getName()!=null && clientInformationBean.getName().trim().length()>0){
				if(clientInformationBean.getSurName()!=null && clientInformationBean.getSurName().trim().length()>0){
					if(clientInformationBean.getAddress()!=null && clientInformationBean.getAddress().trim().length()>0){
							if(clientInformationBean.getCountry()!=null && clientInformationBean.getCountry().trim().length()>0){
								if(clientInformationBean.getState()!=null && clientInformationBean.getState().trim().length()>0){
								if(clientInformationBean.getPinZipCode()!=null && clientInformationBean.getPinZipCode().trim().length()==6){
									if(clientInformationBean.getContactNo()!=null && clientInformationBean.getContactNo().trim().length()==10){
										if(clientInformationBean.getEmailId()!=null && clientInformationBean.getEmailId().trim().length()>0){
										   if(clientInformationBean.getEmailId().matches(EMAIL_REGEX)){
											return true;
										   }else{
											   Messagebox.show("Enter Proper Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
											   return false;
											}
										}else {
											Messagebox.show("Enter Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
											return false;
										}
									}else {
										Messagebox.show("Enter Proper Conatct Number","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
										return false;
									}
								}else {
									Messagebox.show("Enter Proper Pin Code","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
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
					Messagebox.show("Enter SurName","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
					return false;
				}
			}else {
				Messagebox.show("Enter Name","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else {
			Messagebox.show("Enter Client Name","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean insertClientMasterData(ClientInformationBean clientInformationBean){
		if(isValid(clientInformationBean)){
			flag = ClientInformationDao.insertClientData(clientInformationBean);
		}
		return flag;
	}
	
	public static boolean updateClientMasterData(ClientInformationBean clientInformationBean){
		if(isValid(clientInformationBean)){
			 flag = ClientInformationDao.updateClientData(clientInformationBean);
		}
		return flag;
	}
	
	public static boolean deleteClientMasterData(ClientInformationBean clientInformationBean){
		return flagDelete = ClientInformationDao.deleteClientData(clientInformationBean);
	}
	
	public static int countClientPresentWrtRequirementService(ClientInformationBean clientInformationBean){
		return countNumber = ClientInformationDao.countClientPresentWrtRequirementDao(clientInformationBean);
	}
	
	public static ArrayList<UserprofileBean> loadUser(Connection connection){
		ArrayList<UserprofileBean> list = new ArrayList<UserprofileBean>();
		list= ClientInformationDao.onLoadUserProfile(connection);
		return list;
	}
	
	public static void clearAllField(ClientInformationBean bean){
		bean.setName(null);
		bean.setSurName(null);
		bean.setCompanyName(null);
		bean.setClientOriginalName(null);
		bean.setAddress(null);
		bean.setCountry(null);
		bean.setState(null);
		bean.setPinZipCode(null);
		bean.setContactNo(null);
		bean.setEmailId(null);
	}

	/*************************************************************************************************************************************************/
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public static boolean isFlagDelete() {
		return flagDelete;
	}
	public static void setFlagDelete(boolean flagDelete) {
		ClientInformationService.flagDelete = flagDelete;
	}
	public static String getEMAIL_REGEX() {
		return EMAIL_REGEX;
	}
	public static void setEMAIL_REGEX(String eMAIL_REGEX) {
		EMAIL_REGEX = eMAIL_REGEX;
	}
	public static int getCountNumber() {
		return countNumber;
	}
	public static void setCountNumber(int countNumber) {
		ClientInformationService.countNumber = countNumber;
	}
}
