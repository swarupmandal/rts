package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.bean.StatusMasterBean;
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
import org.zkoss.zul.Messagebox;

public class ResAllocTrackingUpdateViewModel {
	
	   private Session sessions = null;
	   private String userId;
	   private Integer req_id;
	   private int clientId;
	
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
		statusBeanList = ResourceMasterDao.onLoadStatus();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStatus(){
		trackingUpdateBean.setStatusId(statusBean.getStatusId());
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(){
		int i = 0, j= 0, k= 0, l=0;
		i = ResourceAllocationTrackingService.insertFinalStatus(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), trackingUpdateBean.getStatusId(), userId);
		
		j = ResourceAllocationTrackingService.insertInternalIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getInternalInterviewDate(), userId);
		
		k = ResourceAllocationTrackingService.insertClientIntDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getClientInterviewDate(), userId);
		
		l = ResourceAllocationTrackingService.insertOnboardDate(req_id, trackingUpdateBean.resourceMasterBean.getResourceId(), clientId, trackingUpdateBean.getOnboardDate(), userId);
		
	    if(i>0 || j>0 || k>0 || l>0){
	    	Messagebox.show("Saved Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
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
}
