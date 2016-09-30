package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.dao.IndividualClientReportDao;

public class IndividualClientReportService {

	public static ArrayList<IndividualClientReportBean> loadRidList(int clientId){
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		list = IndividualClientReportDao.loadRidList(clientId);
		return list;
	}
	
}
