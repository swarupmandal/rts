package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.StatusMasterDao;
import org.zkoss.zul.Messagebox;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class StatusMasterService {
	private static boolean flag = false;
	private static boolean flagInsert = false;
	private static boolean flagUpdate = false;
	
	public static boolean isValid(StatusMasterBean bean){
		if(bean.getStatus()!=null && bean.getStatus().trim().length()>0){
             return true;
		}else {
			Messagebox.show("Enter Status","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean updateClientMasterData(StatusMasterBean statusMasterBean){
		if(isValid(statusMasterBean)){
			flagUpdate = StatusMasterDao.updateStatusData(statusMasterBean);
		}
		return flagUpdate;
	}
	
	public static void updateOtherStatus(StatusMasterBean statusMasterBean){
		StatusMasterDao.updateOthersStatus(statusMasterBean);
	}
	
	public static boolean setForPreBill(ArrayList<StatusMasterBean> statusMasterBeanList){
		boolean isPreBillAppliciable =  false;
		int countStatus = 0 ;
		for(StatusMasterBean statusMasterBean : statusMasterBeanList){
			if(statusMasterBean.isPreBilled()){
				countStatus ++;
			}
		}
		if(countStatus > 0){
			isPreBillAppliciable = true;
		}else{
			isPreBillAppliciable = false;
		}
		return isPreBillAppliciable;
	}
	
	public static boolean insertClientMasterData(StatusMasterBean statusMasterBean){
		if(isValid(statusMasterBean)){
			flagInsert = StatusMasterDao.insertStatusData(statusMasterBean);
		}
		return flagInsert;
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
	public static boolean isFlagInsert() {
		return flagInsert;
	}
	public static void setFlagInsert(boolean flagInsert) {
		StatusMasterService.flagInsert = flagInsert;
	}
	public static boolean isFlagUpdate() {
		return flagUpdate;
	}
	public static void setFlagUpdate(boolean flagUpdate) {
		StatusMasterService.flagUpdate = flagUpdate;
	}   
}
