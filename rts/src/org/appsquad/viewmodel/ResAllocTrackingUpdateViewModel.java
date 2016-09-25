package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class ResAllocTrackingUpdateViewModel {
	
	   private Session sessions = null;
	   private String userId;
	
	   ResourceAllocationTrackingBean trackingUpdateBean = new ResourceAllocationTrackingBean();
	   StatusMasterBean statusBean = new StatusMasterBean();
	   
	   
	   ArrayList<StatusMasterBean> statusBeanList = new ArrayList<StatusMasterBean>();
	   
	   
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view,
						  @ExecutionArgParam("trackingMap") ResourceAllocationTrackingBean bean,
						  @ExecutionArgParam("userId")String user) throws Exception{
		
		Selectors.wireComponents(view, this, false);
		
		sessions = Sessions.getCurrent();
		userId = user;
		
		trackingUpdateBean = bean;
		statusBeanList = ResourceMasterDao.onLoadStatus();
		
	}
	
	
	

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

}
