package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.IndividualClientReportService;
import org.appsquad.service.RequirementGenerationService;
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

public class IndividualClientReportViewModel {
	  IndividualClientReportBean individualClientReportBean = new IndividualClientReportBean();
	  private ArrayList<IndividualClientReportBean> reportBeanList = new ArrayList<IndividualClientReportBean>();
	  private ArrayList<IndividualClientReportBean> summaryBeanList = new ArrayList<IndividualClientReportBean>();
	  private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
	  private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
	  private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
	  private Connection connection = null;
	  private Session sessions = null;
	  private String userName ;
	  private String userId;
	  
	  @Wire("#clname")
	  private Bandbox clnBandBox;
		
	  @Wire("#skSt")
	  private Bandbox skStBandBox;
	  
	  @AfterCompose
	  public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
			Selectors.wireComponents(view, this, false);
			sessions = Sessions.getCurrent();
			userId = (String) sessions.getAttribute("userId");
			individualClientReportBean.setUserId(userId);
			skillList = RequirementGenerationService.fetchSkillSetList();
			statusList = ResourceMasterDao.onLoadStatusForReport();
			clientList = ResourceAllocationTrackingService.fetchClientDetails();
			
			individualClientReportBean.setDetailsDivVis(true);
			individualClientReportBean.setSelectedRadioButton("detail");
		}  
	  
	   @Command
	   @NotifyChange("*")
	   public void onChangeClientName(){
		   if(individualClientReportBean.getClientNameSearch() != null){
			   //clientList = ResourceAllocationTrackingService.fetchClientDetailsSearch(individualClientReportBean.getClientNameSearch());
			   clientList = ResourceAllocationTrackingService.fetchClientDetailsSearchClient(individualClientReportBean.getClientNameSearch());
		   }
		   reportBeanList.clear();
		   summaryBeanList.clear();
		   
	   }
	    
	   @Command
	   @NotifyChange("*")
	   public void onSelctClientName(){
		   clnBandBox.close();
		   summaryBeanList.clear();
		   reportBeanList.clear();
	   }
	    
	   @Command
	   @NotifyChange("*")
	   public void onChangeFromDate(){
		   reportBeanList.clear();
		   summaryBeanList.clear(); 
		   if(individualClientReportBean.getFromDate() == null){
			   individualClientReportBean.setToDate(null);
		   }
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onChangeToDate(){
		   summaryBeanList.clear();
		   reportBeanList.clear();
		  
		   if(individualClientReportBean.getFromDate() != null){
			   if(individualClientReportBean.getToDate().after(individualClientReportBean.getFromDate()) ||
					   individualClientReportBean.getToDate().compareTo(individualClientReportBean.getFromDate()) == 0){
				   System.out.println("FROM DATE AND TO DATE ARE OK TO PROCEED.");
			     }else {
				    individualClientReportBean.setToDate(null);
				    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
			   }   
		   }else {
			   individualClientReportBean.setToDate(null);
			   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }
	   
	   
	   @Command
	   @NotifyChange("*")
	   public void onSelectStatusName(){
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   if(individualClientReportBean.statusMasterBean.getStatus().equalsIgnoreCase("-CLEAR-")){
			   individualClientReportBean.statusMasterBean.setStatus(null);
			   individualClientReportBean.statusMasterBean.setStatusId(null);
			   statusList = ResourceMasterDao.onLoadStatusForReport();
		   }
	   }
	
	   @Command
	   @NotifyChange("*")
	   public void onChangeSkillName(){
		   if(individualClientReportBean.getSkillSetSearch() != null){
			   skillList = RequirementGenerationService.skillSetListSearch(individualClientReportBean.getSkillSetSearch());
		   }else {
			reportBeanList.clear();
			summaryBeanList.clear();
		   }
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onSelctSkillName(){
		   skStBandBox.close();
		   summaryBeanList.clear();
		   reportBeanList.clear();
	   }
	   
	   
	@Command
	@NotifyChange("*")
	public void onClickExcel(){
		if(reportBeanList.size()>0){
			if(individualClientReportBean.getSelectedRadioButton().equals("detail")){
			   IndividualClientReportExcel.printCSV(reportBeanList,"Individual Client Report");
			}else {
			   IndividualClientReportExcel.printSummaryCSV(summaryBeanList,"Individual Client Summary");
			}
		}else {
			Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickPdf() throws Exception{
		String realPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		IndividualClientReportPdf pdf = new IndividualClientReportPdf();
		try {
			if(reportBeanList.size()>0){
				if(individualClientReportBean.getSelectedRadioButton().equals("detail")){
				    pdf.getDetails(realPath, individualClientReportBean, reportBeanList, "Individual Client Report");
				}else {
					pdf.getSummary(realPath, individualClientReportBean, summaryBeanList, "Individual Client Summary");
				}
			}else {
				Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	 
	   @Command
	   @NotifyChange("*")
	   public void onClickClear(){
		   
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   
		   individualClientReportBean.skillsetMasterbean.setId(null);
		   individualClientReportBean.statusMasterBean.setStatusId(null);
		   individualClientReportBean.clientInformationBean.setClientId(null);
		   
		   individualClientReportBean.clientInformationBean.setFullName(null);
		   
		   individualClientReportBean.setSkillSetSearch(null);
		   individualClientReportBean.setClientNameSearch(null);
		   
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   individualClientReportBean.setFromDate(null);
		   individualClientReportBean.setToDate(null);
		   
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatusForReport();
		   individualClientReportBean.setSelectedRadioButton("detail");
		   
	   }
	
	 
	 @Command
	 @NotifyChange("*")
	 public void onClearSkillSet(){
		 skStBandBox.close();
		 individualClientReportBean.skillsetMasterbean.setSkillset(null);
		 individualClientReportBean.skillsetMasterbean.setId(null);
		 individualClientReportBean.setSkillSetSearch(null);
		 skillList = RequirementGenerationService.fetchSkillSetList();
		 reportBeanList.clear();
		 summaryBeanList.clear();
	 }
	
	 
	 public void generationOfDetailsReport(){
		 //when only client selected
	     if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() == null){
			
	    	 reportBeanList = IndividualClientReportService.loadRidList(individualClientReportBean.clientInformationBean.getClientId());
	    	 if(reportBeanList.size() == 0){
	    		 Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	 }
		 }
 
	     //when client and both date selected
		 if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() == null){
			 
			reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.clientInformationBean.getClientId());
		    	
		    if(reportBeanList.size() == 0){
		    	Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	} 
			 
		 }
 
		//when client both date and skill selected
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() != null && individualClientReportBean.statusMasterBean.getStatusId() == null){
		
	 		reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkill(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
	 		
	 		if(reportBeanList.size() == 0){
	 			Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	}
	 		
		}

		//when client and skill selected
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() != null && individualClientReportBean.statusMasterBean.getStatusId() == null){
	
			reportBeanList = IndividualClientReportService.loadRidListWithSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
		    
			if(reportBeanList.size() == 0){
				Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}

		//when status and client selected
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() != null){
			
			reportBeanList = IndividualClientReportService.loadClientListWithStatusService(individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
			   
				if(reportBeanList.size() == 0){
					Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}	
		}

		//when skill status and client given
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() != null && individualClientReportBean.statusMasterBean.getStatusId() != null){
			
	 		reportBeanList = IndividualClientReportService.loadRidListWithStatusAndSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
	 		   
				if(reportBeanList.size() == 0){
					Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
				
		}

		//when date status and client given
		if(individualClientReportBean.clientInformationBean.getClientId() != null && 
				individualClientReportBean.getFromDate() != null && 
				  individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() == null && 
				      individualClientReportBean.statusMasterBean.getStatusId() != null){
			
			reportBeanList = IndividualClientReportService.loadRidListWithDateSatus(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
				
				if(reportBeanList.size() == 0){
					Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}

		//when date status client and skill given-prolay
		if(individualClientReportBean.clientInformationBean.getClientId() != null && 
				individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && 
				  individualClientReportBean.skillsetMasterbean.getId() != null && 
				     individualClientReportBean.statusMasterBean.getStatusId() != null){
			
			reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDate(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()),individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
				
				if(reportBeanList.size() == 0){
					Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}


		//when nothing selected
		if(individualClientReportBean.clientInformationBean.getClientId() == null && individualClientReportBean.getFromDate() == null && 
				individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() == null && 
				   individualClientReportBean.statusMasterBean.getStatusId() == null){
			Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}

		//when client and from date selected
		if(individualClientReportBean.clientInformationBean.getClientId() != null && 
				 individualClientReportBean.getFromDate() != null && 
				   individualClientReportBean.getToDate() == null && 
				    individualClientReportBean.skillsetMasterbean.getId() == null && 
				     individualClientReportBean.statusMasterBean.getStatusId() == null){
							Messagebox.show("Select To Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		//when skill set selected
		if(individualClientReportBean.clientInformationBean.getClientId() == null && 
				 individualClientReportBean.getFromDate() == null && 
				   individualClientReportBean.getToDate() == null && 
				    individualClientReportBean.skillsetMasterbean.getId() != null && 
				     individualClientReportBean.statusMasterBean.getStatusId() == null){
							Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		//when status is selected
		if(individualClientReportBean.clientInformationBean.getClientId() == null && 
				 individualClientReportBean.getFromDate() == null && 
				   individualClientReportBean.getToDate() == null && 
				    individualClientReportBean.skillsetMasterbean.getId() == null && 
				     individualClientReportBean.statusMasterBean.getStatusId() != null){
							Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		if(individualClientReportBean.clientInformationBean.getClientId() == null && 
				 individualClientReportBean.getFromDate() != null && 
				   individualClientReportBean.getToDate() != null && 
				    individualClientReportBean.skillsetMasterbean.getId() == null && 
				     individualClientReportBean.statusMasterBean.getStatusId() != null){
							Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		
		if(individualClientReportBean.clientInformationBean.getClientId() == null && 
				 individualClientReportBean.getFromDate() != null && 
				   individualClientReportBean.getToDate() != null && 
				    individualClientReportBean.skillsetMasterbean.getId() != null && 
				     individualClientReportBean.statusMasterBean.getStatusId() != null){
							Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		if(individualClientReportBean.clientInformationBean.getClientId() == null && 
				 individualClientReportBean.getFromDate() == null && 
				   individualClientReportBean.getToDate() == null && 
				    individualClientReportBean.skillsetMasterbean.getId() != null && 
				     individualClientReportBean.statusMasterBean.getStatusId() != null){
							Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		if(individualClientReportBean.clientInformationBean.getClientId() == null && 
				 individualClientReportBean.getFromDate() != null && 
				   individualClientReportBean.getToDate() != null && 
				    individualClientReportBean.skillsetMasterbean.getId() == null && 
				     individualClientReportBean.statusMasterBean.getStatusId() == null){
							Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
	 }
	
	 @Command
	 @NotifyChange("*")
	 public void onClickSearch(){
			 if(individualClientReportBean.getSelectedRadioButton().equalsIgnoreCase("detail")){
				 System.out.println("SEARCH BUTTON CLICKED FOR DETAIL REPORT");
				 generationOfDetailsReport();
				 System.out.println("DETAILS REPORT LIST SIZE IS :"+reportBeanList.size());
			 }else{
				 System.out.println("SEARCH BUTTON CLICKED FOR SUMMARY REPORT");
				 generationOfDetailsReport();
				 System.out.println("DETAILS REPORT LIST SIZE IS :"+reportBeanList.size());
				 if(individualClientReportBean.statusMasterBean.getStatusId()!=null){
					 summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,individualClientReportBean.statusMasterBean.getStatusId());
				 }else{
					 individualClientReportBean.statusMasterBean.setStatusId(200);
					 summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,individualClientReportBean.statusMasterBean.getStatusId());
				 }
			}
	 }
	 
	 @Command
	   @NotifyChange("*")
	   public void onCheckDetailSummary(){
		   if(individualClientReportBean.getSelectedRadioButton().equals("detail")){
			 individualClientReportBean.setDetailsDivVis(true);
			 individualClientReportBean.setSummaryDivVis(false);
		   }else {
			 individualClientReportBean.setDetailsDivVis(false);
			 individualClientReportBean.setSummaryDivVis(true);  
		   }
	   }
	
	 
	/**********************************************************************************************************************************************/
	 
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
	public IndividualClientReportBean getIndividualClientReportBean() {
		return individualClientReportBean;
	}
	public void setIndividualClientReportBean(
			IndividualClientReportBean individualClientReportBean) {
		this.individualClientReportBean = individualClientReportBean;
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
	public Bandbox getClnBandBox() {
		return clnBandBox;
	}
	public void setClnBandBox(Bandbox clnBandBox) {
		this.clnBandBox = clnBandBox;
	}
	public Bandbox getSkStBandBox() {
		return skStBandBox;
	}
	public void setSkStBandBox(Bandbox skStBandBox) {
		this.skStBandBox = skStBandBox;
	}
}
