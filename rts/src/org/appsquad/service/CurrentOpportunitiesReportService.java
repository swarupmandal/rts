package org.appsquad.service;

import org.appsquad.bean.CurrentOpportunitiesReportBean;
import org.appsquad.dao.CurrentOpportunitiesReportDao;

public class CurrentOpportunitiesReportService {
	public static boolean flagInsert = false;
	public static boolean flagValidate = false;
	public static boolean flagUpdate = false;
	
	public static boolean insertTrackingDetails(CurrentOpportunitiesReportBean bean){
		return flagInsert = CurrentOpportunitiesReportDao.insertTrackingData(bean);
	}
	
	public static boolean updateTrackingDetails(CurrentOpportunitiesReportBean bean){
		return flagUpdate = CurrentOpportunitiesReportDao.updateDetailsTableDao(bean);
	}
	
	public static Integer fetchCountNumberITrackingDetailsTable(Integer trackingId){
		int count = 0;
		return count = CurrentOpportunitiesReportDao.fetchCountTrackingIdService(trackingId);
	}

	/**************************************************************************************************************************************************/
	
	public static boolean isFlagInsert() {
		return flagInsert;
	}
	public static void setFlagInsert(boolean flagInsert) {
		CurrentOpportunitiesReportService.flagInsert = flagInsert;
	}
	public static boolean isFlagValidate() {
		return flagValidate;
	}
	public static void setFlagValidate(boolean flagValidate) {
		CurrentOpportunitiesReportService.flagValidate = flagValidate;
	}
	public static boolean isFlagUpdate() {
		return flagUpdate;
	}
	public static void setFlagUpdate(boolean flagUpdate) {
		CurrentOpportunitiesReportService.flagUpdate = flagUpdate;
	}
}
