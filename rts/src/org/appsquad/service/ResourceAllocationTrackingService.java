package org.appsquad.service;

import java.util.ArrayList;
import java.util.Date;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.dao.ResourceAllocationTrackingDao;
import org.zkoss.zul.Messagebox;

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
	
	public static ArrayList<ResourceAllocationTrackingBean> loadTrackingBeanList(int clId, Integer r_id){
		ArrayList<ResourceAllocationTrackingBean> list = new ArrayList<ResourceAllocationTrackingBean>();
		list = ResourceAllocationTrackingDao.fetchResAllTrackingDetails(clId, r_id);
		return list;
	}
	
	public static ArrayList<ResourceAllocationTrackingBean> loadTrackingBeanSearch(int clId, Integer r_id, String name){
		ArrayList<ResourceAllocationTrackingBean> list = new ArrayList<ResourceAllocationTrackingBean>();
		list = ResourceAllocationTrackingDao.fetchResAllTrackingSearch(clId, r_id, name);
		return list;
	}
	
	public static int insertFinalStatus(Integer rId, int resId, int statusId, String userId){
		int i =0;
		i = ResourceAllocationTrackingDao.inSertFinalStatus(rId, resId, statusId,userId);
		return i;
	}
	
	public static int insertInternalIntDate(Integer rId, int resId,int clientId, Date date, String userId){
		int i = 0;
		i = ResourceAllocationTrackingDao.intInterviewDate(rId, resId, clientId, date, userId);
		return i;
	}
	
	public static int insertClientIntDate(Integer rId, int resId,int clientId, Date date, String userId){
		int i = 0;
		i = ResourceAllocationTrackingDao.clientInterviewDate(rId, resId, clientId, date, userId);
		return i;
	}
	
	public static int insertOnboardDate(Integer rId, int resId,int clientId, Date date, String userId){
		int i = 0;
		i = ResourceAllocationTrackingDao.onboardDate(rId, resId, clientId, date, userId);
		return i;
	}
	
	
	public static boolean isValidate(int clid, Integer r_id){
		if(clid>0){
			if(r_id != null){
				 return true;
				}else {
				  Messagebox.show("Select Requiredment Id", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);	
				  return false;
			}
		}else {
			Messagebox.show("Select Client", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}

}
