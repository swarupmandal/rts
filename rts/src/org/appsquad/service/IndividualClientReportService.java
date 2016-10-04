package org.appsquad.service;

import java.sql.Date;
import java.util.ArrayList;

import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.dao.IndividualClientReportDao;

public class IndividualClientReportService {

	public static ArrayList<IndividualClientReportBean> loadRidList(int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidList(clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListwithDateRange(Date fromDate, Date toDate, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRange(fromDate, toDate, clientId);
		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidListwithDateRangeWithSkill(Date fromDate, Date toDate, int skillId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithDateRangeAndSkill(fromDate, toDate, skillId, clientId);
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
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkillDate(Date fromDate, Date toDate, int skillId,int statusId, int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidListWithStatusSkillDate(fromDate, toDate, skillId, statusId, clientId);
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
	
	public static ArrayList<IndividualClientReportBean> loadRidSummaryList(ArrayList<IndividualClientReportBean> summaryList){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidSummary(summaryList);
		return list;
	}
	
	
}
