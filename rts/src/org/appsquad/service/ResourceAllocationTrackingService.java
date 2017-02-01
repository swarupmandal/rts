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
		return ResourceAllocationTrackingDao.fetchClientDetails();
	}

	public static ArrayList<ClientInformationBean> fetchClientDetailsForReport(){
		return ResourceAllocationTrackingDao.fetchClientDetailsForReport();
	}

	public static ArrayList<ClientInformationBean> fetchClientDetailsForReportFirstTab(){
		return ResourceAllocationTrackingDao.fetchClientDetailsForReportFirstTab();
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReq(int clId){
		return ResourceAllocationTrackingDao.fetchReqirmentDetails(clId);
	}
	
	public static ArrayList<ClientInformationBean> fetchClientDetailsSearch(String name){
		return ResourceAllocationTrackingDao.fetchClientDetailsSearch(name);
	}
	
	public static ArrayList<ClientInformationBean> fetchClientDetailsSearchClient(String name){
		return ResourceAllocationTrackingDao.fetchClientDetailsSearchClient(name);
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReqSearch(int clId, int id){
		return ResourceAllocationTrackingDao.fetchReqirmentDetailsSearch(clId, id);
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReqSearch(int id){
		return ResourceAllocationTrackingDao.fetchReqirmentDetailsSearch(id);
	}
	
	public static ArrayList<ResourceAllocationTrackingBean> loadTrackingBeanList(int clId, Integer r_id){
		return ResourceAllocationTrackingDao.fetchResAllTrackingDetails(clId, r_id);
	}
	
	public static ArrayList<ResourceAllocationTrackingBean> loadTrackingBeanSearch(int clId, Integer r_id, String name){
		return ResourceAllocationTrackingDao.fetchResAllTrackingSearch(clId, r_id, name);
	}
	
	public static int insertFinalStatus(Integer rId, int resId, int statusId, String userId){
		return ResourceAllocationTrackingDao.inSertFinalStatus(rId, resId, statusId,userId);
	}
	
	public static int insertRejectStatusIntoMapper(Integer rId, Integer resId, Integer clientId, String userId){
		return ResourceAllocationTrackingDao.inSertRejectStatus(rId, resId,clientId,userId);
	}
	
	public static int insertInternalIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		return ResourceAllocationTrackingDao.intInterviewDate(rId, resId, clientId, date, userId, otherComments);
	}
	
	public static int updateInternalIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		return ResourceAllocationTrackingDao.updateInterviewDate(rId, resId, clientId, date, userId, otherComments);
	}
	
	public static int updateResourceTable(Integer resId){
		return ResourceAllocationTrackingDao.updateResourceTable(resId);
	}
	
	public static int updateRejectedStatus(Integer rId, int resId,int clientId,String typeName){
		return ResourceAllocationTrackingDao.updateRejectedStatus(rId, resId, clientId, typeName);
	}
	
	public static int insertClientIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComment){
		return ResourceAllocationTrackingDao.clientInterviewDate(rId, resId, clientId, date, userId, otherComment);
	}
	
	public static int updateClientIntDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		return ResourceAllocationTrackingDao.updateClientInterviewDate(rId, resId, clientId, date, userId, otherComments);
	}
	
	public static int insertOnboardDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		return ResourceAllocationTrackingDao.onboardDate(rId, resId, clientId, date, userId, otherComments);
	}
	
	public static int updateOnboardDate(Integer rId, int resId,int clientId, Date date, String userId, String other_comments){
		return ResourceAllocationTrackingDao.updateOnboardDate(rId, resId, clientId, date, userId, other_comments);
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
	
	public static ArrayList<ClientInformationBean> fetchSearchedClient(String clientName,ArrayList<ClientInformationBean> clientList){
		ArrayList<ClientInformationBean> searchedClient = new ArrayList<ClientInformationBean>();
		for(ClientInformationBean client : clientList){
			if(client.getFullName().toLowerCase().startsWith(clientName.toLowerCase())){
				ClientInformationBean clientBean = new ClientInformationBean();
				clientBean.setClientId(client.getClientId());
				clientBean.setFullName(client.getFullName());
				searchedClient.add(clientBean);
			}
		}
		return searchedClient;
	}
}
