package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.dao.CurrentOpportunitiesDao;

public class CurrentOpportunitiesService {	
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrentOpportunityDetails(){
		ArrayList<CurrentOpportunitiesBean> list = new ArrayList<CurrentOpportunitiesBean>();
		list = CurrentOpportunitiesDao.loadCurrenOpportunity();
		return list;
	}
	
}
