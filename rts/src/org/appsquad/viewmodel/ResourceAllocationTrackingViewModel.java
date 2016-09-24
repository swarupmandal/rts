package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.service.RequirementGenerationService;
import org.appsquad.service.ResourceAllocationTrackingService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class ResourceAllocationTrackingViewModel {

	ResourceAllocationTrackingBean trackingBean = new ResourceAllocationTrackingBean();
	ClientInformationBean clientInformationBean = new ClientInformationBean();
	RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
	
	
	ArrayList<ResourceAllocationTrackingBean> trackingBeanList = new ArrayList<ResourceAllocationTrackingBean>();
	ArrayList<ClientInformationBean> clientInformationBeanList = new ArrayList<ClientInformationBean>();
	ArrayList<RequirementGenerationBean> requirementGenerationBeanList = new ArrayList<RequirementGenerationBean>();
	
	
	Session session = null;
	private String userName;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)throws Exception {

		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userName = (String) session.getAttribute("userId");
	
		clientInformationBeanList = ResourceAllocationTrackingService.fetchClientDetails();
		
	}

	@Command
	@NotifyChange("*")
	public void onChangeClientName(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@Command
	@NotifyChange("*")
	public void onSelctClientName(){
		System.out.println("Client name >>> >> > " + clientInformationBean.getClientId() + " - " + clientInformationBean.getFullName());
	}
	
	public ResourceAllocationTrackingBean getTrackingBean() {
		return trackingBean;
	}

	public void setTrackingBean(ResourceAllocationTrackingBean trackingBean) {
		this.trackingBean = trackingBean;
	}

	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}

	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}

	public RequirementGenerationBean getRequirementGenerationBean() {
		return requirementGenerationBean;
	}

	public void setRequirementGenerationBean(
			RequirementGenerationBean requirementGenerationBean) {
		this.requirementGenerationBean = requirementGenerationBean;
	}

	public ArrayList<ResourceAllocationTrackingBean> getTrackingBeanList() {
		return trackingBeanList;
	}

	public void setTrackingBeanList(
			ArrayList<ResourceAllocationTrackingBean> trackingBeanList) {
		this.trackingBeanList = trackingBeanList;
	}

	public ArrayList<ClientInformationBean> getClientInformationBeanList() {
		return clientInformationBeanList;
	}

	public void setClientInformationBeanList(
			ArrayList<ClientInformationBean> clientInformationBeanList) {
		this.clientInformationBeanList = clientInformationBeanList;
	}

	public ArrayList<RequirementGenerationBean> getRequirementGenerationBeanList() {
		return requirementGenerationBeanList;
	}

	public void setRequirementGenerationBeanList(
			ArrayList<RequirementGenerationBean> requirementGenerationBeanList) {
		this.requirementGenerationBeanList = requirementGenerationBeanList;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
