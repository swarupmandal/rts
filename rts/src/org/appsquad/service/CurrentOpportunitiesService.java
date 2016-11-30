package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.dao.CurrentOpportunitiesDao;
import org.zkoss.zul.Messagebox;

public class CurrentOpportunitiesService {	
	public static boolean flagInsert = false;
	public static boolean flagDetailsUpdate = false;
	public static boolean flagTrackingUpdate = false;
	public static boolean flagValidate = false;
	
	public static boolean validateForDataEntryOperator(CurrentOpportunitiesBean bean){
		if(bean.getTenureFromsql()!=null){
			if(bean.getTenureTosql()!=null){
				if(bean.getChargeoutRate()!=null){
					if(bean.getResourceSalary()!=null){
						if(bean.getMargin()!=null){
							if(bean.getBean().getUserID()!=null){
								return true;
							}else{
								Messagebox.show("Select Approver Person ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else{
							Messagebox.show("Select Margin ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else{
						Messagebox.show("Select Resource Salary ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}
				}else{
					Messagebox.show("Select Charge Out Rate ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
					return false;
				}
			}else{
				Messagebox.show("Select Tenture To Date ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Select Tenture From Date ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean validateForApprover(CurrentOpportunitiesBean bean){
		if(bean.getTenureFromsql()!=null){
			if(bean.getTenureTosql()!=null){
				if(bean.getChargeoutRate()!=null){
					if(bean.getResourceSalary()!=null){
						if(bean.getMargin()!=null){
							if(bean.getApproval()!=null){
								return true;
							}else{
								Messagebox.show("Select Approve Status ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
								return false;
							}
						}else{
							Messagebox.show("Select Margin ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
							return false;
						}
					}else{
						Messagebox.show("Select Resource Salary ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
						return false;
					}
				}else{
					Messagebox.show("Select Charge Out Rate ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
					return false;
				}
			}else{
				Messagebox.show("Select Tenture To Date ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Select Tenture From Date ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	public static boolean insertTrackingDetails(CurrentOpportunitiesBean bean){
		return flagInsert = CurrentOpportunitiesDao.insertTrackingData(bean);
	}
	
	public static boolean updateTrackingDetailsService(CurrentOpportunitiesBean bean){
		return flagDetailsUpdate = CurrentOpportunitiesDao.updateDetailsTableDao(bean);
	}
	
	public static boolean updateTrackingService(CurrentOpportunitiesBean bean){
		return flagTrackingUpdate = CurrentOpportunitiesDao.updateTrackingTableDao(bean);
	}
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrentOpportunityDetails(){
		ArrayList<CurrentOpportunitiesBean> list = new ArrayList<CurrentOpportunitiesBean>();
		list = CurrentOpportunitiesDao.loadCurrenOpportunity();
		return list;
	}
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrentOpportunityDetailsForApprover(ArrayList<Integer> idList){
		ArrayList<CurrentOpportunitiesBean> list = new ArrayList<CurrentOpportunitiesBean>();
		list = CurrentOpportunitiesDao.loadCurrenOpportunityForApprover(idList);
		return list;
	}
	
	public static String fetchRoleNameWrtUserId(String userId){
		String roleName = "";
		return roleName = CurrentOpportunitiesDao.fetchRoleNameService(userId);
	}
	
	public static Integer fetchCountNumberITrackingDetailsTable(Integer trackingId){
		int count = 0;
		return count = CurrentOpportunitiesDao.fetchCountTrackingIdService(trackingId);
	}
	
	public static ArrayList<Integer> fetchClientIdList(String userId){
		ArrayList<Integer> clientIdList = new ArrayList<Integer>();
		clientIdList = CurrentOpportunitiesDao.fetchClientIdListWrtUserId(userId);
		return clientIdList;
	}

	/**********************************************************************************************************************************************/
	
	public static boolean isFlagInsert() {
		return flagInsert;
	}
	public static void setFlagInsert(boolean flagInsert) {
		CurrentOpportunitiesService.flagInsert = flagInsert;
	}
	public static boolean isFlagDetailsUpdate() {
		return flagDetailsUpdate;
	}
	public static void setFlagDetailsUpdate(boolean flagDetailsUpdate) {
		CurrentOpportunitiesService.flagDetailsUpdate = flagDetailsUpdate;
	}
	public static boolean isFlagTrackingUpdate() {
		return flagTrackingUpdate;
	}
	public static void setFlagTrackingUpdate(boolean flagTrackingUpdate) {
		CurrentOpportunitiesService.flagTrackingUpdate = flagTrackingUpdate;
	}
	public static boolean isFlagValidate() {
		return flagValidate;
	}
	public static void setFlagValidate(boolean flagValidate) {
		CurrentOpportunitiesService.flagValidate = flagValidate;
	}
}
