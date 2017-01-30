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
		return CurrentOpportunitiesDao.insertTrackingData(bean);
	}
	
	public static boolean updateTrackingDetailsService(CurrentOpportunitiesBean bean){
		return CurrentOpportunitiesDao.updateDetailsTableDao(bean);
	}
	
	public static boolean updateTrackingService(CurrentOpportunitiesBean bean){
		return CurrentOpportunitiesDao.updateTrackingTableDao(bean);
	}
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrentOpportunityDetails(){
		return CurrentOpportunitiesDao.loadCurrenOpportunity();
	}
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrentOpportunityDetailsForApprover(ArrayList<Integer> idList){
		return CurrentOpportunitiesDao.loadCurrenOpportunityForApprover(idList);
	}
	
	public static String fetchRoleNameWrtUserId(String userId){
		return CurrentOpportunitiesDao.fetchRoleNameService(userId);
	}
	
	public static Integer fetchCountNumberITrackingDetailsTable(Integer trackingId){
		return CurrentOpportunitiesDao.fetchCountTrackingIdService(trackingId);
	}
	
	public static ArrayList<Integer> fetchClientIdList(String userId){
		return CurrentOpportunitiesDao.fetchClientIdListWrtUserId(userId);
	}

}
