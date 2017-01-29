package org.appsquad.service;

import org.appsquad.bean.CurrentOpportunitiesReportBean;
import org.appsquad.dao.CurrentOpportunitiesReportDao;

public class CurrentOpportunitiesReportService {
	
	public static boolean insertTrackingDetails(CurrentOpportunitiesReportBean bean){
		boolean flagInsert = false;
		flagInsert = CurrentOpportunitiesReportDao.insertTrackingData(bean);
		return flagInsert;
	}
	
	public static boolean updateTrackingDetails(CurrentOpportunitiesReportBean bean){
		boolean flagUpdate = false;
		flagUpdate = CurrentOpportunitiesReportDao.updateDetailsTableDao(bean);
		return flagUpdate;
	}
	
	public static Integer fetchCountNumberITrackingDetailsTable(Integer trackingId){
		int count = 0;
		count = CurrentOpportunitiesReportDao.fetchCountTrackingIdService(trackingId);
		return count;
	}

}
