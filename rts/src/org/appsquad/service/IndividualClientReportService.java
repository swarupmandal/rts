package org.appsquad.service;

import java.sql.Date;
import java.util.ArrayList;

import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.dao.IndividualClientReportDao;

public class IndividualClientReportService {

	public static ArrayList<IndividualClientReportBean> loadRidList(int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidList(clientId);
		//list = IndividualClientReportDao.loadRidListTest(clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListwithDateRange(Date fromDate, Date toDate, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRange(fromDate, toDate, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListwithDateRange(Date fromDate, Date toDate){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRangeSkillReport(fromDate, toDate);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListwithDateRangeWithSkill(Date fromDate, Date toDate, int skillId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRangeAndSkill(fromDate, toDate, skillId, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListwithDateRangeWithSkillReport(Date fromDate, Date toDate, int skillId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRangeAndSkillReport(fromDate, toDate, skillId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithSkill(int skillId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithSkill(skillId, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatus(int statusId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatus(statusId, clientId);
		return list;
	}
	
	
	public static ArrayList<IndividualClientReportBean> loadClientListWithStatusService(int statusId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadClientListWithStatusDao(statusId, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkillDate(Date fromDate, Date toDate, int skillId,int statusId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatusSkillDate(fromDate, toDate, skillId, statusId, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkillDateWiseReport(Date fromDate, Date toDate, int skillId,int statusId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatusSkillDateWiseReport(fromDate, toDate, skillId, statusId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusDateRidWiseReport(Date fromDate, Date toDate, int rId,int statusId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatusRIdDateWiseReport(fromDate, toDate, rId, statusId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusAndSkill(int skillId,int statusId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatusSkill(skillId, statusId, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithDateSatus(Date fromDate, Date toDate ,int statusId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatusDate(fromDate, toDate, statusId, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadDateAndStatusInClientReportService(Date fromDate, Date toDate ,int statusId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadDateAndStatusForClientReportDao(fromDate, toDate, statusId);
		return list;
	}

	
	public static ArrayList<IndividualClientReportBean> loadDateAndCientInClientReportService(Date fromDate, Date toDate ,int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadDateAndClientForClientReportDao(fromDate, toDate, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidSummaryList(ArrayList<IndividualClientReportBean> summaryList){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidSummary(summaryList);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidSummaryListTest(ArrayList<IndividualClientReportBean> summaryList,Integer statusId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidSummaryTest(summaryList,statusId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListwithDateRangeWithRidWiseReport(Date fromDate, Date toDate, int rId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRangeAndRIdWise(fromDate, toDate, rId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusRIdDateClientWiseReport(Date fromDate, Date toDate, int rId,int statusId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatusRIdDateClientWise(fromDate, toDate, rId, statusId, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusRIdDateClientWiseReport(Date fromDate, Date toDate, int rId,int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRangeClientAndRID(fromDate, toDate, rId, clientId);
		return list;
	}
	
	
	
}
