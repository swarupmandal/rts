package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.SortCriteriaRidorStatusBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.IndividualRequirementReportDao;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.IndividualClientReportService;
import org.appsquad.service.ResourceAllocationTrackingService;
import org.appsquad.utility.Dateformatter;
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

import com.itextpdf.text.DocumentException;

public class SortcriteriaRidorStatusviewModel {
  public RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
  public IndividualClientReportBean rIdWiseReportBean = new IndividualClientReportBean();
	   
  private ArrayList<RequirementGenerationBean> requirementGenerationBeanList = new ArrayList<RequirementGenerationBean>();
  private ArrayList<IndividualClientReportBean> reportBeanList = new ArrayList<IndividualClientReportBean>();
  private ArrayList<IndividualClientReportBean> summaryBeanList = new ArrayList<IndividualClientReportBean>();	
	
  private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
  private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
  private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
  private ArrayList<SortCriteriaRidorStatusBean> detailList = new ArrayList<SortCriteriaRidorStatusBean>();
  
  private Connection connection = null;
  private Session sessions = null;
  private String userName ;
  private String userId;
  private String frmDate = "";
  private String toDate = "";
  
  @Wire("#reqIdb")
  private Bandbox reqIDdBandBox;
  
  @Wire("#clntBb")
  private Bandbox clnBandBox;

  @AfterCompose
  public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
		statusList = ResourceMasterDao.onLoadStatusForReport();
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
		
		rIdWiseReportBean.setDetailsDivVis(true);
		rIdWiseReportBean.setSelectedRadioButton("detail");
	}
  
  
  @Command
  @NotifyChange("*")
  public void onChangeFromDate(){
	   reportBeanList.clear();
	   summaryBeanList.clear();
	   
	   if(rIdWiseReportBean.getFromDate() == null){
		   rIdWiseReportBean.setToDate(null);
	   }
  }
  
  @Command
  @NotifyChange("*")
  public void onChangeToDate(){
	   reportBeanList.clear();
	   summaryBeanList.clear();
	   
	   if(rIdWiseReportBean.getFromDate() != null){
		   if(rIdWiseReportBean.getToDate().after(rIdWiseReportBean.getFromDate())){
		     }else {
		    	 rIdWiseReportBean.setToDate(null);
			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }else {
		   rIdWiseReportBean.setToDate(null);
		   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
	   }
   }
  
    
  @Command
  @NotifyChange("*")
  public void onChangeReqId(){
	   if(rIdWiseReportBean.getR_idSearch() != null){
			requirementGenerationBeanList = ResourceAllocationTrackingService.fetchReqSearch(rIdWiseReportBean.getR_idSearch());
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
	 public void onChangeClientName(){
		   if(rIdWiseReportBean.getClientNameSearch() != null){
			   //clientList = ResourceAllocationTrackingService.fetchClientDetailsSearch(rIdWiseReportBean.getClientNameSearch());
			   clientList = ResourceAllocationTrackingService.fetchClientDetailsSearchClient(rIdWiseReportBean.getClientNameSearch());
		   }
		   reportBeanList.clear();
		   summaryBeanList.clear();
	 }
  
  
  	@Command
	@NotifyChange("*")
	public void onSelctClientName(){
		summaryBeanList.clear();
		reportBeanList.clear();
		clnBandBox.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClear(){		   
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   
		   rIdWiseReportBean.clientInformationBean.setFullName(null);
		   rIdWiseReportBean.clientInformationBean.setClientId(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   
		   rIdWiseReportBean.setFromDate(null);
		   rIdWiseReportBean.setToDate(null);
		   
		   requirementGenerationBean.setReq_id(null);
		   requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
		   
		   rIdWiseReportBean.statusMasterBean.setStatus(null);
		   rIdWiseReportBean.statusMasterBean.setStatusId(null);
		   statusList = ResourceMasterDao.onLoadStatusForReport();
		   
		   rIdWiseReportBean.setSelectedRadioButton("detail");	   
	}
	
	@Command
	@NotifyChange("*")
	public void onClickPdf() throws Exception{
		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		IndividualClientReportPdf pdf = new IndividualClientReportPdf();
		try{
			if(rIdWiseReportBean.getSelectedRadioButton().equals("detail")){
			}else {
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			if(reportBeanList.size()>0){
				if(rIdWiseReportBean.getSelectedRadioButton().equals("detail")){
				   pdf.getDetails(pdfPath, rIdWiseReportBean, reportBeanList, "Requirement Status Report");
				}else {
					pdf.getSummary(pdfPath, rIdWiseReportBean, summaryBeanList, "Requirement Status Summary");
				}
			}else {
				Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExcel(){
		if(reportBeanList.size()>0){
			if(rIdWiseReportBean.getSelectedRadioButton().equals("detail")){
				IndividualClientReportExcel.printCSV(reportBeanList, "Requirement Status Report" );
			}else {
				IndividualClientReportExcel.printSummaryCSV(summaryBeanList, "Requirement Status Summary");
			}
		}else {
			Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
  
	
	public void generationOfDetailsReport(){
		//when only both date are given
				if(rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
						requirementGenerationBean.getReq_id() ==null && rIdWiseReportBean.statusMasterBean.getStatusId() == null && 
						   rIdWiseReportBean.clientInformationBean.getClientId() == null){
					
					System.out.println("1ST METHOD");
					reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()));
			    	
			    	if(reportBeanList.size()==0){
			    		Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			    	}
				}
				
				
				//when both date and rid given
				if (rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
						requirementGenerationBean.getReq_id() !=null && rIdWiseReportBean.statusMasterBean.getStatusId() == null && 
						    rIdWiseReportBean.clientInformationBean.getClientId() == null) {
					
					System.out.println("2ND METHOD");
					reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithRidWiseReport(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()), requirementGenerationBean.getReq_id());
					
					if(reportBeanList.size()==0){
						Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			    	}
				}
				
				
				//when both date rid and status given
				if (rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
						requirementGenerationBean.getReq_id() !=null && rIdWiseReportBean.statusMasterBean.getStatusId() != null && 
						   rIdWiseReportBean.clientInformationBean.getClientId() == null) {
					
					System.out.println("3RD METHOD");
					reportBeanList = IndividualClientReportService.loadRidListWithStatusDateRidWiseReport(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()),requirementGenerationBean.getReq_id() , rIdWiseReportBean.statusMasterBean.getStatusId());
					
					if(reportBeanList.size()==0){
						Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			    	}
				}
				
				
				//when both date rid status and client given
				if (rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
						requirementGenerationBean.getReq_id() !=null && rIdWiseReportBean.statusMasterBean.getStatusId() != null && 
						   rIdWiseReportBean.clientInformationBean.getClientId() != null) {
					
					System.out.println("4TH METHOD");
					reportBeanList = IndividualClientReportService.loadRidListWithStatusRIdDateClientWiseReport(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()), requirementGenerationBean.getReq_id(), rIdWiseReportBean.statusMasterBean.getStatusId(), rIdWiseReportBean.clientInformationBean.getClientId());
					
					if(reportBeanList.size()==0){
						Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			    	}
				}
				
				//when date and client selected
				if (rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
						requirementGenerationBean.getReq_id() ==null && rIdWiseReportBean.statusMasterBean.getStatusId() == null && 
						   rIdWiseReportBean.clientInformationBean.getClientId() != null) {
					
					reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()),rIdWiseReportBean.clientInformationBean.getClientId());
					
					if(reportBeanList.size()==0){
						Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			    	}
				}
				
				//when date and Status selected
						if (rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
								requirementGenerationBean.getReq_id() ==null && rIdWiseReportBean.statusMasterBean.getStatusId() != null && 
								   rIdWiseReportBean.clientInformationBean.getClientId() == null) {
							
							reportBeanList = IndividualClientReportService.loadDateAndStatusInClientReportService(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()),rIdWiseReportBean.statusMasterBean.getStatusId());
							
							if(reportBeanList.size()==0){
								Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
					    	}
						}

						//when date and Status and client selected
						if (rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
								requirementGenerationBean.getReq_id() ==null && rIdWiseReportBean.statusMasterBean.getStatusId() != null && 
								   rIdWiseReportBean.clientInformationBean.getClientId() != null) {
							
							reportBeanList = IndividualClientReportService.loadRidListWithDateSatus(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()),rIdWiseReportBean.statusMasterBean.getStatusId(),rIdWiseReportBean.clientInformationBean.getClientId());
							
							if(reportBeanList.size()==0){
								Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
					    	}
						}		
						
				
						//when date and ReqId and client selected
						if (rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() != null && 
								requirementGenerationBean.getReq_id() !=null && rIdWiseReportBean.statusMasterBean.getStatusId() == null && 
								   rIdWiseReportBean.clientInformationBean.getClientId() != null) {
							
							reportBeanList = IndividualClientReportService.loadRidListWithStatusRIdDateClientWiseReport(Dateformatter.sqlDate(rIdWiseReportBean.getFromDate()), Dateformatter.sqlDate(rIdWiseReportBean.getToDate()),requirementGenerationBean.getReq_id(),rIdWiseReportBean.clientInformationBean.getClientId());
							
							if(reportBeanList.size()==0){
								Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
					    	}
						}			
						
				//when to Date not selected
				if(rIdWiseReportBean.getFromDate() != null && rIdWiseReportBean.getToDate() == null && 
						requirementGenerationBean.getReq_id() ==null && rIdWiseReportBean.statusMasterBean.getStatusId() == null && 
						   rIdWiseReportBean.clientInformationBean.getClientId() == null){
									Messagebox.show("Select To Date!!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				}
				
				
				//when no date selected
				if(rIdWiseReportBean.getFromDate() == null && rIdWiseReportBean.getToDate() == null && 
						requirementGenerationBean.getReq_id() ==null && rIdWiseReportBean.statusMasterBean.getStatusId() == null && 
						   rIdWiseReportBean.clientInformationBean.getClientId() == null){
									Messagebox.show("Select From Date And To Date", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				}
	}
	
	@Command
	@NotifyChange("*")
	public void onCheckDetailSummary(){
		   if(rIdWiseReportBean.getSelectedRadioButton().equals("detail")){
			   rIdWiseReportBean.setDetailsDivVis(true);
			   rIdWiseReportBean.setSummaryDivVis(false);
		   }else {
			   rIdWiseReportBean.setDetailsDivVis(false);
			   rIdWiseReportBean.setSummaryDivVis(true);
		 }
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSearch(){
		
		/******************NEW LOGIC - PROLAY **************************************************************************************************/
		
			if(rIdWiseReportBean.getSelectedRadioButton().equalsIgnoreCase("detail")){
				System.out.println("SEARCH BUTTON CLICKED FOR DETAIL REPORT");
				generationOfDetailsReport();
				System.out.println("DETAILS REPORT LIST SIZE IS :"+reportBeanList.size());
			}else{
				System.out.println("SEARCH BUTTON CLICKED FOR SUMMARY REPORT");
				generationOfDetailsReport();
				System.out.println("SUMMARY REPORT LIST SIZE IS :"+reportBeanList.size());
				if(rIdWiseReportBean.statusMasterBean.getStatusId()!=null){
					summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,rIdWiseReportBean.statusMasterBean.getStatusId());
				}else{
					rIdWiseReportBean.statusMasterBean.setStatusId(200);
					summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,rIdWiseReportBean.statusMasterBean.getStatusId());
				}
		  }
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStatusName(){
		summaryBeanList.clear();
		reportBeanList.clear();
		
		if(rIdWiseReportBean.statusMasterBean.getStatusId()==13){
			rIdWiseReportBean.statusMasterBean.setStatus(null);
			rIdWiseReportBean.statusMasterBean.setStatusId(null);
			statusList = ResourceMasterDao.onLoadStatusForReport();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClearReqId(){
		reqIDdBandBox.close();
		requirementGenerationBean.setReq_id(null);
		requirementGenerationBeanList = IndividualRequirementReportDao.fetchReqirmentDetails();
		summaryBeanList.clear();
		reportBeanList.clear();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClientClear(){
		clnBandBox.close();
		rIdWiseReportBean.clientInformationBean.setFullName(null);
		rIdWiseReportBean.clientInformationBean.setClientId(null);
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
		summaryBeanList.clear();
		reportBeanList.clear();
	}
	
    /************************************************************************************************************************************************/
  
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ArrayList<SortCriteriaRidorStatusBean> getDetailList() {
		return detailList;
	}
	public void setDetailList(ArrayList<SortCriteriaRidorStatusBean> detailList) {
		this.detailList = detailList;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public RequirementGenerationBean getRequirementGenerationBean() {
		return requirementGenerationBean;
	}
	public void setRequirementGenerationBean(
			RequirementGenerationBean requirementGenerationBean) {
		this.requirementGenerationBean = requirementGenerationBean;
	}
	public IndividualClientReportBean getrIdWiseReportBean() {
		return rIdWiseReportBean;
	}
	public void setrIdWiseReportBean(IndividualClientReportBean rIdWiseReportBean) {
		this.rIdWiseReportBean = rIdWiseReportBean;
	}
	public ArrayList<RequirementGenerationBean> getRequirementGenerationBeanList() {
		return requirementGenerationBeanList;
	}
	public void setRequirementGenerationBeanList(
			ArrayList<RequirementGenerationBean> requirementGenerationBeanList) {
		this.requirementGenerationBeanList = requirementGenerationBeanList;
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
