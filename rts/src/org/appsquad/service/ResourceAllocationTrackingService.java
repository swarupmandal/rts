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

	public static ArrayList<ClientInformationBean> fetchClientDetailsForReport(){
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		list = ResourceAllocationTrackingDao.fetchClientDetailsForReport();
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
	
	public static ArrayList<ClientInformationBean> fetchClientDetailsSearchClient(String name){
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		list = ResourceAllocationTrackingDao.fetchClientDetailsSearchClient(name);
		return list;
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReqSearch(int clId, int id){
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		list = ResourceAllocationTrackingDao.fetchReqirmentDetailsSearch(clId, id);
		return list;
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReqSearch(int id){
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		list = ResourceAllocationTrackingDao.fetchReqirmentDetailsSearch(id);
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
	
	public static int insertRejectStatusIntoMapper(Integer rId, Integer resId, Integer clientId, String userId){
		int q =0;
		q = ResourceAllocationTrackingDao.inSertRejectStatus(rId, resId,clientId,userId);
		return q;
	}
	
	public static int insertInternalIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int i = 0;
		i = ResourceAllocationTrackingDao.intInterviewDate(rId, resId, clientId, date, userId, otherComments);
		return i;
	}
	
	public static int updateInternalIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int j = 0;
		j = ResourceAllocationTrackingDao.updateInterviewDate(rId, resId, clientId, date, userId, otherComments);
		return j;
	}
	
	public static int updateResourceTable(Integer resId){
		int j = 0;
		j = ResourceAllocationTrackingDao.updateResourceTable(resId);
		return j;
	}
	
	public static int updateRejectedStatus(Integer rId, int resId,int clientId,String typeName){
		int p = 0;
		p = ResourceAllocationTrackingDao.updateRejectedStatus(rId, resId, clientId, typeName);
		return p;
	}
	
	public static int insertClientIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComment){
		int i = 0;
		i = ResourceAllocationTrackingDao.clientInterviewDate(rId, resId, clientId, date, userId, otherComment);
		return i;
	}
	
	public static int updateClientIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int q = 0;
		q = ResourceAllocationTrackingDao.updateClientInterviewDate(rId, resId, clientId, date, userId, otherComments);
		return q;
	}
	
	public static int insertOnboardDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int i = 0;
		i = ResourceAllocationTrackingDao.onboardDate(rId, resId, clientId, date, userId, otherComments);
		return i;
	}
	
	public static int updateOnboardDate(Integer rId, int resId,int clientId, Date date, String userId, String other_comments){
		int s = 0;
		s = ResourceAllocationTrackingDao.updateOnboardDate(rId, resId, clientId, date, userId, other_comments);
		return s;
	}
	
	public static boolean isValidate(Integer clid, Integer r_id){
		if(clid !=null){
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
