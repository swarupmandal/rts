package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.IndividualRequirementReportBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.IndividualClientReportDao;
import org.appsquad.dao.IndividualRequirementReportDao;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.dao.SortCriteriaDao;
import org.appsquad.service.IndividualClientReportService;
import org.appsquad.service.IndividualRequirementReportService;
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

public class IndividualRequirementReportViewModel {
   	
   private Connection connection = null;
   private Session sessions = null;
   private String userId;
   
   @Wire("#reqIdb")
   private Bandbox reqIDdBandBox;
   
   public RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
   public IndividualClientReportBean individualRequirementReportBean = new IndividualClientReportBean();
   
   private ArrayList<RequirementGenerationBean> requirementGenerationBeanList = new ArrayList<RequirementGenerationBean>();
   private ArrayList<IndividualClientReportBean> individualRequirementReportBeanList = new ArrayList<IndividualClientReportBean>();
   ArrayList<StatusMasterBean> statusBeanList = new ArrayList<StatusMasterBean>();
   
   @AfterCompose
   public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
 		Selectors.wireComponents(view, this, false);
 		sessions = Sessions.getCurrent();
 		userId = (String) sessions.getAttribute("userId");
 		
 		requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
 		statusBeanList = ResourceMasterDao.onLoadStatus();
 		
 	}

   
   
   @Command
   @NotifyChange("*")
   public void onSelctReqId(){
	   reqIDdBandBox.close();
	   
	   System.out.println(requirementGenerationBean.getReq_id());
	   individualRequirementReportBeanList = IndividualRequirementReportService.individualReqIdDetails(requirementGenerationBean.getReq_id());
	   
   }
   
   @Command
   @NotifyChange("*")
   public void onSelectStatusName(){
	   
   }
   
   @Command
   @NotifyChange("*")
   public void onCheckRepairRedo(){
	   
   }
   
   
public Connection getConnection() {
	return connection;
}

public void setConnection(Connection connection) {
	this.connection = connection;
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



public RequirementGenerationBean getRequirementGenerationBean() {
	return requirementGenerationBean;
}

public void setRequirementGenerationBean(
		RequirementGenerationBean requirementGenerationBean) {
	this.requirementGenerationBean = requirementGenerationBean;
}

public ArrayList<RequirementGenerationBean> getRequirementGenerationBeanList() {
	return requirementGenerationBeanList;
}


public void setRequirementGenerationBeanList(
		ArrayList<RequirementGenerationBean> requirementGenerationBeanList) {
	this.requirementGenerationBeanList = requirementGenerationBeanList;
}



public Bandbox getReqIDdBandBox() {
	return reqIDdBandBox;
}



public void setReqIDdBandBox(Bandbox reqIDdBandBox) {
	this.reqIDdBandBox = reqIDdBandBox;
}



public ArrayList<StatusMasterBean> getStatusBeanList() {
	return statusBeanList;
}



public void setStatusBeanList(ArrayList<StatusMasterBean> statusBeanList) {
	this.statusBeanList = statusBeanList;
}



public IndividualClientReportBean getIndividualRequirementReportBean() {
	return individualRequirementReportBean;
}



public void setIndividualRequirementReportBean(
		IndividualClientReportBean individualRequirementReportBean) {
	this.individualRequirementReportBean = individualRequirementReportBean;
}



public ArrayList<IndividualClientReportBean> getIndividualRequirementReportBeanList() {
	return individualRequirementReportBeanList;
}



public void setIndividualRequirementReportBeanList(
		ArrayList<IndividualClientReportBean> individualRequirementReportBeanList) {
	this.individualRequirementReportBeanList = individualRequirementReportBeanList;
}
   
   
}
