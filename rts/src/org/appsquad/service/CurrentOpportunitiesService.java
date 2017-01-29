package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.CurrentOpportunitiesReportBean;
import org.appsquad.dao.CurrentOpportunitiesDao;
import org.zkoss.zul.Messagebox;

public class CurrentOpportunitiesService {
	
	public static boolean validateBillingDataToBeSubmitted(ArrayList<CurrentOpportunitiesReportBean> list){
		boolean flag = false;
		for(CurrentOpportunitiesReportBean bean: list){
			if(bean.getMonth()!=null){
				if(bean.getYear()!=null){
					if(bean.getFilePath()!=null){
						flag = true;
					}else{
						flag = false;
						break;
					}
				}else{
					flag = false;
					break;
				}
			}else{
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	
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
							Messagebox.show("Create Margin ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
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
							Messagebox.show("Create Margin ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
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
		boolean flagInsert = false;
		flagInsert = CurrentOpportunitiesDao.insertTrackingData(bean);
		return flagInsert;
	}
	
	public static boolean updateTrackingDetailsService(CurrentOpportunitiesBean bean){
		boolean flagDetailsUpdate = false;
		flagDetailsUpdate = CurrentOpportunitiesDao.updateDetailsTableDao(bean);
		return flagDetailsUpdate;
	}
	
	public static boolean updateTrackingService(CurrentOpportunitiesBean bean){
		boolean flagTrackingUpdate = false;
		flagTrackingUpdate = CurrentOpportunitiesDao.updateTrackingTableDao(bean);
		return flagTrackingUpdate;
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
		roleName = CurrentOpportunitiesDao.fetchRoleNameService(userId);
		return roleName;
	}
	
	public static Integer fetchCountNumberITrackingDetailsTable(Integer trackingId){
		int count = 0;
		count = CurrentOpportunitiesDao.fetchCountTrackingIdService(trackingId);
		return count;
	}
	
	public static ArrayList<Integer> fetchClientIdList(String userId){
		ArrayList<Integer> clientIdList = new ArrayList<Integer>();
		clientIdList = CurrentOpportunitiesDao.fetchClientIdListWrtUserId(userId);
		return clientIdList;
	}

	/**********************************************************************************************************************************************/
	
}
