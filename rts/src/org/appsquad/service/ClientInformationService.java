package org.appsquad.service;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.dao.ClientInformationDao;
import org.zkoss.zul.Messagebox;

public class ClientInformationService {
	private static boolean flag = false;
	
	public static boolean isValid(ClientInformationBean clientInformationBean){
		if(clientInformationBean.getName()!=null){
			if(clientInformationBean.getSurName()!=null && clientInformationBean.getSurName().trim().length()>0){
				if(clientInformationBean.getCompanyName()!=null && clientInformationBean.getCompanyName().trim().length()>0){
					if(clientInformationBean.getAddress()!=null && clientInformationBean.getAddress().trim().length()>0){
						if(clientInformationBean.getStateBean().getStateName()!=null && clientInformationBean.getStateBean().getStateName().trim().length()>0){
							if(clientInformationBean.getCountryBean().getCountryName()!=null && clientInformationBean.getCountryBean().getCountryName().trim().length()>0){
								if(clientInformationBean.getPinZipCode()!=null && clientInformationBean.getPinZipCode().trim().length()==6){
									if(clientInformationBean.getContactNo()!=null && clientInformationBean.getContactNo().trim().length()==10){
										if(clientInformationBean.getEmailId()!=null && clientInformationBean.getEmailId().trim().length()>0){
											return true;
										}else {
											Messagebox.show("Enter Employee Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
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
								Messagebox.show("Enter Country","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else {
							Messagebox.show("Enter State","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else {
						Messagebox.show("Enter Address","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}
				}else {
					Messagebox.show("Enter Company Name","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
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
	
	public static boolean isValidForUpdate(ClientInformationBean clientInformationBean){
		if(clientInformationBean.getFullName()!=null){
			if(clientInformationBean.getSurName()!=null && clientInformationBean.getSurName().trim().length()>0){
				if(clientInformationBean.getCompanyName()!=null && clientInformationBean.getCompanyName().trim().length()>0){
					if(clientInformationBean.getAddress()!=null && clientInformationBean.getAddress().trim().length()>0){
						if(clientInformationBean.getStateBean().getStateName()!=null && clientInformationBean.getStateBean().getStateName().trim().length()>0){
							if(clientInformationBean.getCountryBean().getCountryName()!=null && clientInformationBean.getCountryBean().getCountryName().trim().length()>0){
								if(clientInformationBean.getPinZipCode()!=null && clientInformationBean.getPinZipCode().trim().length()==6){
									if(clientInformationBean.getContactNo()!=null && clientInformationBean.getContactNo().trim().length()==10){
										if(clientInformationBean.getEmailId()!=null && clientInformationBean.getEmailId().trim().length()>0){
											return true;
										}else {
											Messagebox.show("Enter Employee Email Id","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
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
								Messagebox.show("Enter Country","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else {
							Messagebox.show("Enter State","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else {
						Messagebox.show("Enter Address","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}
				}else {
					Messagebox.show("Enter Company Name","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
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
	
	public static boolean insertClientMasterData(ClientInformationBean clientInformationBean){
		if(isValid(clientInformationBean)){
			flag = ClientInformationDao.insertClientData(clientInformationBean);
		}
		return flag;
	}
	
	public static boolean updateClientMasterData(ClientInformationBean clientInformationBean){
		if(isValidForUpdate(clientInformationBean)){
			 flag = ClientInformationDao.updateClientData(clientInformationBean);
		}
		return flag;
	}
	
	public static void clearAllField(ClientInformationBean bean){
		bean.setName(null);
		bean.setSurName(null);
		bean.setCompanyName(null);
		bean.setAddress(null);
		bean.getStateBean().setStateName(null);
		bean.getCountryBean().setCountryName(null);
		bean.setPinZipCode(null);
		bean.setContactNo(null);
		bean.setEmailId(null);
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
