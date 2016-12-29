package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.dao.IndividualRequirementReportDao;

public class IndividualRequirementReportService {

	
	public static ArrayList<RequirementGenerationBean> fetchReqDetails(){
	
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		list = IndividualRequirementReportDao.fetchReqirmentDetails();
		return list;
		
	}
	
	public static ArrayList<IndividualClientReportBean> individualReqIdDetails(int rid){
		
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualRequirementReportDao.loadRidList(rid);
		return list;
		
	}
	
	public static ArrayList<IndividualClientReportBean> individualReqIdDetails(int reqId, int statusId){
		
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualRequirementReportDao.loadRidListWithStatus(reqId, statusId);
		return list;
		
	}
	
	
}
