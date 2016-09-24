package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.dao.ResourceAllocationTrackingDao;

public class ResourceAllocationTrackingService {
	
	public static ArrayList<ClientInformationBean> fetchClientDetails(){
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		list = ResourceAllocationTrackingDao.fetchClientDetails();
		return list;
	}

}
