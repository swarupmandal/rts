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
import org.appsquad.service.RequirementGenerationService;
import org.appsquad.service.ResourceAllocationTrackingService;
import org.appsquad.utility.IndividualClientReportExcel;
import org.appsquad.utility.IndividualClientReportPdf;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;

public class IndividualRequirementReportViewModel {
   	
   private Connection connection = null;
   private Session sessions = null;
   private String userId;
   
   @Wire("#reqIdb")
   private Bandbox reqIDdBandBox;
   
   public RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
   public IndividualClientReportBean individualRequirementReportBean = new IndividualClientReportBean();
   
   private ArrayList<RequirementGenerationBean> requirementGenerationBeanList = new ArrayList<RequirementGenerationBean>();
   private ArrayList<IndividualClientReportBean> reportBeanList = new ArrayList<IndividualClientReportBean>();
   private ArrayList<IndividualClientReportBean> summaryBeanList = new ArrayList<IndividualClientReportBean>();
   
   ArrayList<StatusMasterBean> statusBeanList = new ArrayList<StatusMasterBean>();
   
   @AfterCompose
   public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
 		Selectors.wireComponents(view, this, false);
 		sessions = Sessions.getCurrent();
 		userId = (String) sessions.getAttribute("userId");
 		
 		requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
 		statusBeanList = ResourceMasterDao.onLoadStatus();
 		
 		individualRequirementReportBean.setDetailsDivVis(true);
 		individualRequirementReportBean.setSelectedRadioButton("detail");
 		
 	}

   @Command
   @NotifyChange("*")
   public void onChangeReqId(){
	   if(individualRequirementReportBean.getR_idSearch() != null){
			requirementGenerationBeanList = ResourceAllocationTrackingService.fetchReqSearch(individualRequirementReportBean.getR_idSearch());
		}else {
			requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
		}
   }
   
   @Command
   @NotifyChange("*")
   public void onSelctReqId(){
	   reqIDdBandBox.close();
	   
	   summaryBeanList.clear();
	   reportBeanList.clear();
	   
	   reportBeanList = IndividualRequirementReportService.individualReqIdDetails(requirementGenerationBean.getReq_id());
	   
	   individualRequirementReportBean.setFromDate(null);
	   individualRequirementReportBean.setToDate(null);
	   
	   individualRequirementReportBean.skillsetMasterbean.setSkillset(null);
	   
	   individualRequirementReportBean.statusMasterBean.setStatus(null);
	   statusBeanList = ResourceMasterDao.onLoadStatus();
	   individualRequirementReportBean.setSelectedRadioButton("detail");
	   
   }
   
   @Command
   @NotifyChange("*")
   public void onSelectStatusName(){
	   
	   summaryBeanList.clear();
	   if(requirementGenerationBean.getReq_id() != null){
		   
		   reportBeanList = IndividualRequirementReportService.individualReqIdDetails(requirementGenerationBean.getReq_id(), individualRequirementReportBean.statusMasterBean.getStatusId());
		   individualRequirementReportBean.setSelectedRadioButton("detail");
	   }else {
		   individualRequirementReportBean.statusMasterBean.setStatus(null);
			reportBeanList.clear();
			statusBeanList = ResourceMasterDao.onLoadStatus();  
			Messagebox.show("Select Requirement id ", "ALERT", Messagebox.OK,Messagebox.EXCLAMATION);
	}
	   
	   
   }
   
   @Command
   @NotifyChange("*")
   public void onCheckDetailSummary(){
	   
	   if(individualRequirementReportBean.getSelectedRadioButton().equals("detail")){
		   individualRequirementReportBean.setDetailsDivVis(true);
		   individualRequirementReportBean.setSummaryDivVis(false);
		   
	   }else {
		   
		   individualRequirementReportBean.setDetailsDivVis(false);
		   summaryBeanList = IndividualClientReportService.loadRidSummaryList(reportBeanList);
		   individualRequirementReportBean.setSummaryDivVis(true);
		 
	}
	   
   }
   
   
   @Command
   @NotifyChange("*")
   public void onClickClear(){

	   
	   summaryBeanList.clear();
	   reportBeanList.clear();
	   
	   requirementGenerationBean.setReq_id(null);
	   requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
	   
	   individualRequirementReportBean.statusMasterBean.setStatus(null);
	   statusBeanList = ResourceMasterDao.onLoadStatus();
	   individualRequirementReportBean.setSelectedRadioButton(null);
   }
   
   @Command
   @NotifyChange("*")
   public void onClickExcel(){

		if(individualRequirementReportBean.getSelectedRadioButton().equals("detail")){
		
			IndividualClientReportExcel.printCSV(reportBeanList);
		  /*if(reportBeanList.size()>0);	
		  ArrayList<IndividualClientReportBean> detailList = new ArrayList<IndividualClientReportBean>();
			for(IndividualClientReportBean bean : reportBeanList){
				if(bean.isDetailChecked()){
					detailList.add(bean);
				}
			}
			if(detailList.size()>0){
				IndividualClientReportExcel.printCSV(detailList);
			}else {
				Messagebox.show("NO DATA SELECTED ", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION );
			}*/
			
		}else {
			IndividualClientReportExcel.printSummaryCSV(summaryBeanList);
			/*if(summaryBeanList.size()>0);
			ArrayList<IndividualClientReportBean> summList = new ArrayList<IndividualClientReportBean>();
			for(IndividualClientReportBean bean : summaryBeanList){
				if(bean.isSummaryChecked()){
					summList.add(bean);
				}
			}
			
			if(summList.size()>0){
				IndividualClientReportExcel.printSummaryCSV(summList);
			}else {
				Messagebox.show("NO DATA SELECTED ", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION );
			}*/
			
		}
	
   }
   
   @Command
   @NotifyChange("*")
   public void onClickPdf(){

		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		//String totalPdfPath = pdfPath + "report.pdf";
		String totalPdfPath = "C:\\pdf test\\Report_Pdf.pdf";
		
		IndividualClientReportPdf pdf = new IndividualClientReportPdf();
		
		try{
			
		if(individualRequirementReportBean.getSelectedRadioButton().equals("detail")){
		
			pdf.getDetails(totalPdfPath, individualRequirementReportBean, reportBeanList);
		  /*if(reportBeanList.size()>0);	
		  ArrayList<IndividualClientReportBean> detailList = new ArrayList<IndividualClientReportBean>();
			for(IndividualClientReportBean bean : reportBeanList){
				if(bean.isDetailChecked()){
					detailList.add(bean);
				}
			}
			if(detailList.size()>0){
				pdf.getDetails(totalPdfPath, individualRequirementReportBean, detailList);
			}else {
				Messagebox.show("NO DATA SELECTED ", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION );
			}*/
			
		}else {
			pdf.getSummary(totalPdfPath, individualRequirementReportBean, summaryBeanList);
			/*if(summaryBeanList.size()>0);
			ArrayList<IndividualClientReportBean> summList = new ArrayList<IndividualClientReportBean>();
			for(IndividualClientReportBean bean : summaryBeanList){
				if(bean.isSummaryChecked()){
					summList.add(bean);
				}
			}
			if(summList.size()>0){
				pdf.getSummary(totalPdfPath, individualRequirementReportBean, summList);
			}else {
				Messagebox.show("NO DATA SELECTED ", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION );
			}*/
			
		}
	
		}catch(Exception e){
			e.printStackTrace();
		}
	
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



public ArrayList<IndividualClientReportBean> getReportBeanList() {
	return reportBeanList;
}



public void setReportBeanList(
		ArrayList<IndividualClientReportBean> reportBeanList) {
	this.reportBeanList = reportBeanList;
}



public ArrayList<IndividualClientReportBean> getSummaryBeanList() {
	return summaryBeanList;
}



public void setSummaryBeanList(
		ArrayList<IndividualClientReportBean> summaryBeanList) {
	this.summaryBeanList = summaryBeanList;
}




   
   
}
