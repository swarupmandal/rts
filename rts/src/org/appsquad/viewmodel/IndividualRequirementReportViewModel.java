package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.IndividualRequirementReportDao;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.IndividualClientReportService;
import org.appsquad.service.IndividualRequirementReportService;
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
 		statusBeanList = ResourceMasterDao.onLoadStatusForReport();
 		
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
   }
   
   @Command
   @NotifyChange("*")
   public void onSelectStatusName(){
	   summaryBeanList.clear();
	   reportBeanList.clear();
	   
	   if(individualRequirementReportBean.statusMasterBean.getStatusId()==13){
		   individualRequirementReportBean.statusMasterBean.setStatus(null);
		   individualRequirementReportBean.statusMasterBean.setStatusId(null);
		   statusBeanList = ResourceMasterDao.onLoadStatusForReport();
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
		   individualRequirementReportBean.setSummaryDivVis(true);
	    }
   }
   
   
   @Command
   @NotifyChange("*")
   public void onClickClear(){
	   summaryBeanList.clear();
	   reportBeanList.clear();
	   
	   requirementGenerationBean.setReq_id(null);
	   individualRequirementReportBean.setR_idSearch(null);
	   requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
	   
	   individualRequirementReportBean.statusMasterBean.setStatus(null);
	   individualRequirementReportBean.statusMasterBean.setStatusId(null);
	   statusBeanList = ResourceMasterDao.onLoadStatusForReport();
	   individualRequirementReportBean.setSelectedRadioButton("detail");
   }
   
   @Command
   @NotifyChange("*")
   public void onClickExcel(){
	   if(reportBeanList.size()>0){
			if(individualRequirementReportBean.getSelectedRadioButton().equals("detail")){
				IndividualClientReportExcel.printCSV(reportBeanList, "Individual Requirement Report");
			}else {
				IndividualClientReportExcel.printSummaryCSV(summaryBeanList, "Individual Requirement Summary");
			}
	   }else {
		   Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
	   }
   }
   
   @Command
   @NotifyChange("*")
   public void onClickPdf(){
		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		IndividualClientReportPdf pdf = new IndividualClientReportPdf();
		try{
			if(reportBeanList.size()>0){	
				if(individualRequirementReportBean.getSelectedRadioButton().equals("detail")){
					pdf.getDetails(pdfPath, individualRequirementReportBean, reportBeanList, "Individual Requirement Report");
				}else {
					pdf.getSummary(pdfPath, individualRequirementReportBean, summaryBeanList, "Individual Requirement Summary");
				}
			}else {
				Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
   }
   
   public void generationOfDetailsReport(){
	   
	 //when only rid Selected
	   if(requirementGenerationBean.getReq_id() != null && individualRequirementReportBean.statusMasterBean.getStatusId() == null){
		   reportBeanList = IndividualRequirementReportService.individualReqIdDetails(requirementGenerationBean.getReq_id());
		   	 
		   	 if(reportBeanList.size()==0){
		   		Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		   	 }
	   }
	   
	   //when rid and status both selected
	   if(requirementGenerationBean.getReq_id() != null && individualRequirementReportBean.statusMasterBean.getStatusId() != null){
		   reportBeanList = IndividualRequirementReportService.individualReqIdDetails(requirementGenerationBean.getReq_id(), individualRequirementReportBean.statusMasterBean.getStatusId());
	       
	         if(reportBeanList.size()==0){
	        	 Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		   	 }
	   }
	   
	   //when nothing selected
		if(requirementGenerationBean.getReq_id() == null && individualRequirementReportBean.statusMasterBean.getStatusId() == null){
			Messagebox.show("Select Requirement ID ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		//when only status selected
		if(requirementGenerationBean.getReq_id() == null && individualRequirementReportBean.statusMasterBean.getStatusId() != null){
		    Messagebox.show("Select Requirement ID ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
   }
   
   
   @Command
   @NotifyChange("*")
   public void onClickSearch(){
	   
		/******************NEW LOGIC - PROLAY **************************************************************************************************/
	   
	    if(individualRequirementReportBean.getSelectedRadioButton().equalsIgnoreCase("detail")){
	    	System.out.println("SEARCH BUTTON CLICKED FOR DETAIL REPORT");
			generationOfDetailsReport();
			System.out.println("DETAILS REPORT LIST SIZE IS :"+reportBeanList.size());
	    }else{
	    	System.out.println("SEARCH BUTTON CLICKED FOR SUMMARY REPORT");
			generationOfDetailsReport();
			System.out.println("SUMMARY REPORT LIST SIZE IS :"+reportBeanList.size());
			 if(individualRequirementReportBean.statusMasterBean.getStatusId()!=null){
				 summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,individualRequirementReportBean.statusMasterBean.getStatusId());
			 }else{
				 individualRequirementReportBean.statusMasterBean.setStatusId(200);
				 summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,individualRequirementReportBean.statusMasterBean.getStatusId());
			 }
	    }
   }
   
    /*****************************************************************************************************************************************/
   
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
