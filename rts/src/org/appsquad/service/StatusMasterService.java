package org.appsquad.service;

import java.util.ArrayList;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.StatusMasterDao;
import org.zkoss.zul.Messagebox;

public class StatusMasterService {
	
	public static boolean isValid(StatusMasterBean bean){
		if(bean.getStatus()!=null && bean.getStatus().trim().length()>0){
             return true;
		}else {
			Messagebox.show("Enter Status","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean updateClientMasterData(StatusMasterBean statusMasterBean){
		boolean flagUpdate = false;
		if(isValid(statusMasterBean)){
			flagUpdate = StatusMasterDao.updateStatusData(statusMasterBean);
		}
		return flagUpdate;
	}
	
	public static void updateOthersInitialStatus(StatusMasterBean statusMasterBean){
		StatusMasterDao.updateOthersInitalStatus(statusMasterBean);
	}
	
	public static void updateOtherStatus(StatusMasterBean statusMasterBean){
		StatusMasterDao.updateOthersStatus(statusMasterBean);
	}
	
	public static void updateOthersFinalStatus(StatusMasterBean statusMasterBean){
		StatusMasterDao.updateOthersFinalStatus(statusMasterBean);
	}
	
	public static boolean setForInitialStage(ArrayList<StatusMasterBean> statusMasterBeanList){
		boolean isInitalAppliciable =  false;
		int countStatus = 0 ;
		for(StatusMasterBean statusMasterBean : statusMasterBeanList){
			if(statusMasterBean.isInitial()){
				countStatus ++;
			}
		}
		if(countStatus > 0){
			isInitalAppliciable = true;
		}else{
			isInitalAppliciable = false;
		}
		return isInitalAppliciable;
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
	
	public static boolean setForFinalStage(ArrayList<StatusMasterBean> statusMasterBeanList){
		boolean isFinalStageApplicable =  false;
		int countStatus = 0 ;
		for(StatusMasterBean statusMasterBean : statusMasterBeanList){
			if(statusMasterBean.isFinalStage()){
				countStatus ++;
			}
		}
		if(countStatus > 0){
			isFinalStageApplicable = true;
		}else{
			isFinalStageApplicable = false;
		}
		return isFinalStageApplicable;
	}
	
	public static boolean insertClientMasterData(StatusMasterBean statusMasterBean){
		boolean flagInsert = false;
		if(isValid(statusMasterBean)){
			flagInsert = StatusMasterDao.insertStatusData(statusMasterBean);
		}
		return flagInsert;
	}
	
	public static boolean deleteStatusMasterData(StatusMasterBean statusMasterBean){
		boolean flagDelete = false;
		flagDelete = StatusMasterDao.deleteStatus(statusMasterBean);
		return flagDelete;
	}
	
	public static void clearAllField(StatusMasterBean bean){
		bean.setStatus(null);
	}
	
}
