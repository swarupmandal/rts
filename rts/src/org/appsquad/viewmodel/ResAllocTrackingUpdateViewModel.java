package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceAllocationTrackingDao;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.ResourceAllocationTrackingService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ResAllocTrackingUpdateViewModel {
	   private Session sessions = null;
	   private String userId;
	   private Integer req_id;
	   private int clientId;
	   private Integer typeId;
	   private String typeName;
	   @Wire("#winResAllocTracking")
	   private Window winResAllocTracking;
	
	   ResourceAllocationTrackingBean trackingUpdateBean = new ResourceAllocationTrackingBean();
	   StatusMasterBean statusBean = new StatusMasterBean();
	   
	   ArrayList<StatusMasterBean> statusBeanList = new ArrayList<StatusMasterBean>();
	   
	   @AfterCompose
	   public void initSetUp(@ContextParam(ContextType.VIEW) Component view,
						  @ExecutionArgParam("trackingMap") ResourceAllocationTrackingBean bean,
						  @ExecutionArgParam("userId")String user,
						  @ExecutionArgParam("req_id") Integer r_id,
						  @ExecutionArgParam("clId")int clId) throws Exception{
		
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = user;
		req_id = r_id;
		clientId = clId;
		trackingUpdateBean = bean;
		System.out.println("FETCHED STATUS IS :"+trackingUpdateBean.getStatus()+"--------"+"FETCHED STATUS ID IS :"+trackingUpdateBean.getStatusId());
		DateFieldEnableLogic(trackingUpdateBean.getStatus());
		trackingUpdateBean.setOldStatus(trackingUpdateBean.getStatus());
		trackingUpdateBean.setOldStatusId(trackingUpdateBean.getStatusId());
		if(trackingUpdateBean.getInternalInterviewDate()!=null){
			trackingUpdateBean.setPreviousInternalInterviewDate(trackingUpdateBean.getInternalInterviewDate());
			trackingUpdateBean.setPreviousStatusId(trackingUpdateBean.getStatusId());
		}else{
			trackingUpdateBean.setPreviousInternalInterviewDate(null);
			trackingUpdateBean.setPreviousStatusId(trackingUpdateBean.getStatusId());
		}
		if(trackingUpdateBean.getClientInterviewDate()!=null){
			trackingUpdateBean.setPreviousClientInterviewDate(trackingUpdateBean.getClientInterviewDate());
		}else{
			trackingUpdateBean.setPreviousClientInterviewDate(null);
		}
		if(trackingUpdateBean.getOnboardDate()!=null){
			trackingUpdateBean.setPreviousOnboardDate(trackingUpdateBean.getOnboardDate());
		}else{
			trackingUpdateBean.setPreviousOnboardDate(null);
		}
		statusBeanList = ResourceMasterDao.onLoadStatusForTrackingUpdateScreen(trackingUpdateBean);
	}
	   
	public void DateFieldEnableLogic(String statusName){
		final String status1 = "INTERVIEW SCHEDULED";
		final String status2 = "TECHNICALLY SELECTED ";
		final String status3 = "ON-BOARDING ";
		
		switch (statusName) {
		  case status1:
		        trackingUpdateBean.setInternalInterviewDateDisable(false);
				trackingUpdateBean.setClientInterviewDateDisable(true);
				trackingUpdateBean.setOnboardInterviewDateDisable(true);
		        break;
		  case status2: 
		        trackingUpdateBean.setClientInterviewDateDisable(false);
				trackingUpdateBean.setInternalInterviewDateDisable(true);
				trackingUpdateBean.setOnboardInterviewDateDisable(true);
		        break;
		  case status3:
		        trackingUpdateBean.setOnboardInterviewDateDisable(false);
				trackingUpdateBean.setInternalInterviewDateDisable(true);
				trackingUpdateBean.setClientInterviewDateDisable(true);
		        break;      
		  default:
		        System.out.println("default");
		        break;
		}
	}
	   
	@Command
	@NotifyChange("*")
	public void onSelectStatus(){
		trackingUpdateBean.setStatusId(statusBean.getStatusId());
		trackingUpdateBean.setStatus(statusBean.getStatus());
		System.out.println("SELECTED STATUS NAME IS :"+trackingUpdateBean.getStatus()+"-----------"+"ID IS :"+trackingUpdateBean.getStatusId());
		System.out.println("OLD STATUS NAME IS :"+trackingUpdateBean.getOldStatus()+"-------"+trackingUpdateBean.getOldStatusId());
		if(trackingUpdateBean.getStatusId()<trackingUpdateBean.getOldStatusId()){
			statusBean.setStatus(trackingUpdateBean.getOldStatus());
			statusBean.setStatusId(trackingUpdateBean.getOldStatusId());
			trackingUpdateBean.setStatus(trackingUpdateBean.getOldStatus());
			trackingUpdateBean.setStatusId(trackingUpdateBean.getOldStatusId());
			statusBeanList = ResourceMasterDao.onLoadStatusForTrackingUpdateScreen(trackingUpdateBean);
			Messagebox.show("You Can't Select Status Backward!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		final String status1 = "INTERVIEW SCHEDULED";
		final String status2 = "TECHNICALLY SELECTED ";
		final String status3 = "ON-BOARDING ";
		
		switch (trackingUpdateBean.getStatus()) {
		  case status1:
		        trackingUpdateBean.setInternalInterviewDateDisable(false);
				trackingUpdateBean.setClientInterviewDateDisable(true);
				trackingUpdateBean.setOnboardInterviewDateDisable(true);
		        break;
		  case status2: 
		        trackingUpdateBean.setClientInterviewDateDisable(false);
				trackingUpdateBean.setInternalInterviewDateDisable(true);
				trackingUpdateBean.setOnboardInterviewDateDisable(true);
		        break;
		  case status3:
		        trackingUpdateBean.setOnboardInterviewDateDisable(false);
				trackingUpdateBean.setInternalInterviewDateDisable(true);
				trackingUpdateBean.setClientInterviewDateDisable(true);
		        break;      
		  default:
		        System.out.println("default");
		        break;
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(){
		int i = 0, j= 0, k= 0, l=0, comparison = 0, p = 0,m = 0,n = 0,r = 0,s = 0,a = 0;
		
		if(!(trackingUpdateBean.isInternalInterviewDateDisable())&& (trackingUpdateBean.isClientInterviewDateDisable()) && 
				                                                                       (trackingUpdateBean.isOnboardInterviewDateDisable())){
			System.out.println("1ST CONDITION");
			if(trackingUpdateBean.getInternalInterviewDate()!=null){
				if(statusBean.getStatusId()>0){
					if(trackingUpdateBean.getPreviousStatusId()==statusBean.getStatusId()){
					}else{
						System.out.println("inside method.");
						i = ResourceAllocationTrackingService.insertFinalStatus(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), trackingUpdateBean.getStatusId(), userId);
					}	
				}
				
				if(trackingUpdateBean.getPreviousInternalInterviewDate()!=null && trackingUpdateBean.getInternalInterviewDate()!=null){
					comparison = trackingUpdateBean.getPreviousInternalInterviewDate().compareTo(trackingUpdateBean.getInternalInterviewDate());
					System.out.println("inter interview date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						p = ResourceAllocationTrackingService.updateInternalIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getInternalInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getInternalInterviewDate()!=null && trackingUpdateBean.getPreviousInternalInterviewDate()==null){
					System.out.println("insert method ");
					j = ResourceAllocationTrackingService.insertInternalIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getInternalInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
				}
				
				if(trackingUpdateBean.getPreviousClientInterviewDate()!=null && trackingUpdateBean.getClientInterviewDate()!=null){
					comparison = trackingUpdateBean.getPreviousClientInterviewDate().compareTo(trackingUpdateBean.getClientInterviewDate());
					System.out.println("client date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						l = ResourceAllocationTrackingService.updateClientIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getClientInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getClientInterviewDate()!=null && trackingUpdateBean.getPreviousClientInterviewDate()==null){
					System.out.println("insert method ");
					k = ResourceAllocationTrackingService.insertClientIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getClientInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
				}
				
				if(trackingUpdateBean.getPreviousOnboardDate()!=null && trackingUpdateBean.getOnboardDate()!=null){
					comparison = trackingUpdateBean.getPreviousOnboardDate().compareTo(trackingUpdateBean.getOnboardDate());
					System.out.println("pnboard date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						m = ResourceAllocationTrackingService.updateOnboardDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getOnboardDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getOnboardDate()!=null && trackingUpdateBean.getPreviousOnboardDate()==null){
					System.out.println("insert method ");
					n = ResourceAllocationTrackingService.insertOnboardDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getOnboardDate(), userId, trackingUpdateBean.getOtherDetails());
				}
			}else{
				Messagebox.show("Select Internal Intervie Date!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else if(!(trackingUpdateBean.isClientInterviewDateDisable()) && (trackingUpdateBean.isInternalInterviewDateDisable()) && 
				  																			(trackingUpdateBean.isOnboardInterviewDateDisable())){
			System.out.println("2ND CONDITION");
			if(trackingUpdateBean.getClientInterviewDate()!=null){
				if(statusBean.getStatusId()>0){
					if(trackingUpdateBean.getPreviousStatusId()==statusBean.getStatusId()){
					}else{
						System.out.println("inside method.");
						i = ResourceAllocationTrackingService.insertFinalStatus(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), trackingUpdateBean.getStatusId(), userId);
					}	
				}
				
				if(trackingUpdateBean.getPreviousInternalInterviewDate()!=null && trackingUpdateBean.getInternalInterviewDate()!=null){
					comparison = trackingUpdateBean.getPreviousInternalInterviewDate().compareTo(trackingUpdateBean.getInternalInterviewDate());
					System.out.println("inter interview date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						p = ResourceAllocationTrackingService.updateInternalIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getInternalInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getInternalInterviewDate()!=null && trackingUpdateBean.getPreviousInternalInterviewDate()==null){
					System.out.println("insert method ");
					j = ResourceAllocationTrackingService.insertInternalIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getInternalInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
				}
				
				if(trackingUpdateBean.getPreviousClientInterviewDate()!=null && trackingUpdateBean.getClientInterviewDate()!=null){
					comparison = trackingUpdateBean.getPreviousClientInterviewDate().compareTo(trackingUpdateBean.getClientInterviewDate());
					System.out.println("client date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						l = ResourceAllocationTrackingService.updateClientIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getClientInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getClientInterviewDate()!=null && trackingUpdateBean.getPreviousClientInterviewDate()==null){
					System.out.println("insert method ");
					k = ResourceAllocationTrackingService.insertClientIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getClientInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
				}
				
				if(trackingUpdateBean.getPreviousOnboardDate()!=null && trackingUpdateBean.getOnboardDate()!=null){
					comparison = trackingUpdateBean.getPreviousOnboardDate().compareTo(trackingUpdateBean.getOnboardDate());
					System.out.println("pnboard date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						m = ResourceAllocationTrackingService.updateOnboardDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getOnboardDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getOnboardDate()!=null && trackingUpdateBean.getPreviousOnboardDate()==null){
					System.out.println("insert method ");
					n = ResourceAllocationTrackingService.insertOnboardDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getOnboardDate(), userId, trackingUpdateBean.getOtherDetails());
				}
			}else{
				Messagebox.show("Select Client Intervie Date!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else if(!(trackingUpdateBean.isOnboardInterviewDateDisable()) && (trackingUpdateBean.isInternalInterviewDateDisable()) && 
				 																					(trackingUpdateBean.isClientInterviewDateDisable())){
			System.out.println("3RD CONDITION");
			if(trackingUpdateBean.getOnboardDate()!=null){
				if(statusBean.getStatusId()>0){
					if(trackingUpdateBean.getPreviousStatusId()==statusBean.getStatusId()){
					}else{
						System.out.println("inside method.");
						i = ResourceAllocationTrackingService.insertFinalStatus(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), trackingUpdateBean.getStatusId(), userId);
					}	
				}
				
				if(trackingUpdateBean.getPreviousInternalInterviewDate()!=null && trackingUpdateBean.getInternalInterviewDate()!=null){
					comparison = trackingUpdateBean.getPreviousInternalInterviewDate().compareTo(trackingUpdateBean.getInternalInterviewDate());
					System.out.println("inter interview date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						p = ResourceAllocationTrackingService.updateInternalIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getInternalInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getInternalInterviewDate()!=null && trackingUpdateBean.getPreviousInternalInterviewDate()==null){
					System.out.println("insert method ");
					j = ResourceAllocationTrackingService.insertInternalIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getInternalInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
				}
				
				if(trackingUpdateBean.getPreviousClientInterviewDate()!=null && trackingUpdateBean.getClientInterviewDate()!=null){
					comparison = trackingUpdateBean.getPreviousClientInterviewDate().compareTo(trackingUpdateBean.getClientInterviewDate());
					System.out.println("client date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						l = ResourceAllocationTrackingService.updateClientIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getClientInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getClientInterviewDate()!=null && trackingUpdateBean.getPreviousClientInterviewDate()==null){
					System.out.println("insert method ");
					k = ResourceAllocationTrackingService.insertClientIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getClientInterviewDate(), userId, trackingUpdateBean.getOtherDetails());
				}
				
				if(trackingUpdateBean.getPreviousOnboardDate()!=null && trackingUpdateBean.getOnboardDate()!=null){
					comparison = trackingUpdateBean.getPreviousOnboardDate().compareTo(trackingUpdateBean.getOnboardDate());
					System.out.println("pnboard date comparision:"+comparison);
					if(!(comparison==0)){
						System.out.println("update method ");
						m = ResourceAllocationTrackingService.updateOnboardDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getOnboardDate(), userId, trackingUpdateBean.getOtherDetails());
					}
				}else if(trackingUpdateBean.getOnboardDate()!=null && trackingUpdateBean.getPreviousOnboardDate()==null){
					System.out.println("insert method ");
					n = ResourceAllocationTrackingService.insertOnboardDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getOnboardDate(), userId, trackingUpdateBean.getOtherDetails());
				}
			}else{
				Messagebox.show("Select Client Intervie Date!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else if(trackingUpdateBean.isClientInterviewDateDisable() && trackingUpdateBean.isInternalInterviewDateDisable() && 
																								trackingUpdateBean.isOnboardInterviewDateDisable()){
			System.out.println("4TH CONDITION");
			if(statusBean.getStatusId()>0){
				if(trackingUpdateBean.getPreviousStatusId()==statusBean.getStatusId()){
				}else{
					System.out.println("inside method.");
					i = ResourceAllocationTrackingService.insertFinalStatus(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), trackingUpdateBean.getStatusId(), userId);
				}	
			}
			
			if(i>0){
				if(trackingUpdateBean.getStatus().equalsIgnoreCase("DECLINED") || 
						trackingUpdateBean.getStatus().equalsIgnoreCase("TECHNICALLY REJECTED ") || 
						   trackingUpdateBean.getStatus().equalsIgnoreCase("REJECTED BY CLIENT ")){
					
					System.out.println("RESOURCE ID IS :"+trackingUpdateBean.getResourceMasterBean().getResourceId());
					System.out.println("REQ ID IS :"+req_id);
					System.out.println("Client Id is :"+clientId);
					System.out.println("Resource Type Name Is :"+trackingUpdateBean.getResourceType());
					typeName = ResourceAllocationTrackingDao.getTypeName(req_id);
					System.out.println("TYPE NAME IS :"+typeName);
					
					r = ResourceAllocationTrackingService.updateRejectedStatus(req_id, trackingUpdateBean.getResourceMasterBean().getResourceId(), clientId, typeName);
					System.out.println("R IS :"+r);
					if(r>0){
						s = ResourceAllocationTrackingService.insertRejectStatusIntoMapper(req_id, trackingUpdateBean.getResourceMasterBean().getResourceId(), clientId, userId);
					}
					if(s>0){
						a = ResourceAllocationTrackingService.updateResourceTable(trackingUpdateBean.getResourceMasterBean().getResourceId());
					}
				}
			}
		}
		
		if(i>0 && a>0){
			Messagebox.show("Successfully Rejected", "Information", Messagebox.OK, Messagebox.INFORMATION);
	    	winResAllocTracking.detach();
	    	BindUtils.postGlobalCommand(null, null, "reLoadTrack", null);	
		}else if(i>0 || j>0 || p>0 || k>0 || l>0 || n>0 || m>0){
	    	Messagebox.show("Date Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
	    	winResAllocTracking.detach();
	    	BindUtils.postGlobalCommand(null, null, "reLoadTrack", null);	
	    }
	}
	
	/**********************************************************************************************************************************************/
	
	public Session getSessions() {
		return sessions;
	}
	public void setSessions(Session sessions) {
		this.sessions = sessions;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ResourceAllocationTrackingBean getTrackingUpdateBean() {
		return trackingUpdateBean;
	}
	public void setTrackingUpdateBean(
			ResourceAllocationTrackingBean trackingUpdateBean) {
		this.trackingUpdateBean = trackingUpdateBean;
	}
	public StatusMasterBean getStatusBean() {
		return statusBean;
	}
	public void setStatusBean(StatusMasterBean statusBean) {
		this.statusBean = statusBean;
	}
	public ArrayList<StatusMasterBean> getStatusBeanList() {
		return statusBeanList;
	}
	public void setStatusBeanList(ArrayList<StatusMasterBean> statusBeanList) {
		this.statusBeanList = statusBeanList;
	}
	public Integer getReq_id() {
		return req_id;
	}
	public void setReq_id(Integer req_id) {
		this.req_id = req_id;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public Window getWinResAllocTracking() {
		return winResAllocTracking;
	}
	public void setWinResAllocTracking(Window winResAllocTracking) {
		this.winResAllocTracking = winResAllocTracking;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
