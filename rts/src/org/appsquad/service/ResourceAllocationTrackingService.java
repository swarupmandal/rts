package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.dao.ResourceAllocationTrackingDao;

public class ResourceAllocationTrackingService {
	
	public static ArrayList<ClientInformationBean> fetchClientDetails(){
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		list = ResourceAllocationTrackingDao.fetchClientDetails();
		return list;
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReq(int clId){
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		list = ResourceAllocationTrackingDao.fetchReqirmentDetails(clId);
		return list;
	}
	
	public static ArrayList<ClientInformationBean> fetchClientDetailsSearch(String name){
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		list = ResourceAllocationTrackingDao.fetchClientDetailsSearch(name);
		return list;
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReqSearch(int clId, int id){
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		list = ResourceAllocationTrackingDao.fetchReqirmentDetailsSearch(clId, id);
		return list;
	}

}
