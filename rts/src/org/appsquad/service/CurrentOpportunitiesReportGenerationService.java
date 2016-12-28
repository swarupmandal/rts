package org.appsquad.service;

import java.sql.Date;
import java.util.ArrayList;

import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.CurrentOpportunitiesReportGenerationDao;

public class CurrentOpportunitiesReportGenerationService {
   
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportDetails(Date fromDate, Date toDate){
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = null;
		list = CurrentOpportunitiesReportGenerationDao.loadReportData(fromDate, toDate);
		return list;
	}
	
	public static ArrayList<ResourceMasterBean> loadResourceDetails(String name){
		ArrayList<ResourceMasterBean> resourceList = null;
		resourceList = CurrentOpportunitiesReportGenerationDao.onLoadResourceDetailsForSearch(name);
		return resourceList;
	}
	
	/*public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadOppurtunityWiseReportForClient(CurrentOpportunitiesReportGenerationBean bean){
		ArrayList<ResourceMasterBean> oppurtunityWiseReportForClientList = null;
	}*/
	
}
