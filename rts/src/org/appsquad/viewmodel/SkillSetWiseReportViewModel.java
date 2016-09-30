package org.appsquad.viewmodel;


import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceMasterDao;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;

public class SkillSetWiseReportViewModel {

	
	private Session sessions = null;
	private String userId;
	
	@Wire("#clntBb")
	private Bandbox clientBandBox;
	@Wire("#skillBb")
	private Bandbox skillBandBox;
	

	IndividualClientReportBean skilWiseReportBean = new IndividualClientReportBean();
	
	
	private ArrayList<IndividualClientReportBean> skilWiseReportBeanList = new ArrayList<IndividualClientReportBean>();
	private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
	private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
	private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
	
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		
		skillList = RequirementGenerationService.fetchSkillSetList();
		statusList = ResourceMasterDao.onLoadStatus();
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
		
		
	}

	
	@Command
	@NotifyChange("*")
	public void onSelctSkillName(){
		skillBandBox.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelctClientName(){
		clientBandBox.close();
	}
	
	
	
	
	
	@Command
	@NotifyChange("*")
	public void onClickSearch(){
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClear(){
		
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

	public Bandbox getClientBandBox() {
		return clientBandBox;
	}

	public void setClientBandBox(Bandbox clientBandBox) {
		this.clientBandBox = clientBandBox;
	}

	public Bandbox getSkillBandBox() {
		return skillBandBox;
	}

	public void setSkillBandBox(Bandbox skillBandBox) {
		this.skillBandBox = skillBandBox;
	}

	public IndividualClientReportBean getSkilWiseReportBean() {
		return skilWiseReportBean;
	}

	public void setSkilWiseReportBean(IndividualClientReportBean skilWiseReportBean) {
		this.skilWiseReportBean = skilWiseReportBean;
	}

	public ArrayList<IndividualClientReportBean> getSkilWiseReportBeanList() {
		return skilWiseReportBeanList;
	}

	public void setSkilWiseReportBeanList(
			ArrayList<IndividualClientReportBean> skilWiseReportBeanList) {
		this.skilWiseReportBeanList = skilWiseReportBeanList;
	}










	public ArrayList<SkillsetMasterbean> getSkillList() {
		return skillList;
	}










	public void setSkillList(ArrayList<SkillsetMasterbean> skillList) {
		this.skillList = skillList;
	}










	public ArrayList<StatusMasterBean> getStatusList() {
		return statusList;
	}










	public void setStatusList(ArrayList<StatusMasterBean> statusList) {
		this.statusList = statusList;
	}










	public ArrayList<ClientInformationBean> getClientList() {
		return clientList;
	}










	public void setClientList(ArrayList<ClientInformationBean> clientList) {
		this.clientList = clientList;
	}
	
	
}
