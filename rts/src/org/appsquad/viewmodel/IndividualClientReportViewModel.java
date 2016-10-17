package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.dao.SortCriteriaDao;
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

import com.itextpdf.text.DocumentException;

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
			statusList = ResourceMasterDao.onLoadStatus();
			clientList = ResourceAllocationTrackingService.fetchClientDetails();
			
			individualClientReportBean.setDetailsDivVis(true);
			individualClientReportBean.setSelectedRadioButton("detail");
		}  
	  
	   @Command
	   @NotifyChange("*")
	   public void onChangeClientName(){
		   
		   if(individualClientReportBean.getClientNameSearch() != null){
			   clientList = ResourceAllocationTrackingService.fetchClientDetailsSearch(individualClientReportBean.getClientNameSearch());
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
		   
		   //functionality gone to SEARCH button
		   //reportBeanList = IndividualClientReportService.loadRidList(individualClientReportBean.clientInformationBean.getClientId());
		   
		   individualClientReportBean.setFromDate(null);
		   individualClientReportBean.setToDate(null);
		   
		   
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   individualClientReportBean.skillsetMasterbean.setId(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   individualClientReportBean.statusMasterBean.setStatusId(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   individualClientReportBean.setSelectedRadioButton("detail");
		   
	   }
	    
	   @Command
	   @NotifyChange("*")
	   public void onChangeFromDate(){
		   
		   reportBeanList.clear();
		   summaryBeanList.clear();
		   
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   individualClientReportBean.skillsetMasterbean.setId(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   individualClientReportBean.statusMasterBean.setStatusId(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   if(individualClientReportBean.getFromDate() == null){
			   individualClientReportBean.setToDate(null);
		   }
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onChangeToDate(){
		   summaryBeanList.clear();
		   
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   individualClientReportBean.skillsetMasterbean.setId(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   individualClientReportBean.statusMasterBean.setStatusId(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   if(individualClientReportBean.getFromDate() != null){
			   
			   if(individualClientReportBean.getToDate().after(individualClientReportBean.getFromDate())){
				    
				    if(individualClientReportBean.clientInformationBean.getClientId() != null){
				    	
				    	//functionality gone to search button 
				    	/*if(individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null){
				    	reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.clientInformationBean.getClientId());
				    	individualClientReportBean.setSelectedRadioButton("detail");
				    	}*/
				    }else {
				    	Messagebox.show("Select Client Name", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
					}
				   
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
		  if(individualClientReportBean.clientInformationBean.getClientId() != null){
			  
			  
			   /*if(individualClientReportBean.getFromDate()==null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId()== null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithStatus(individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId()); 
				   individualClientReportBean.setSelectedRadioButton("detail");
			   }
			   else if(individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() != null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDate(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
				   individualClientReportBean.setSelectedRadioButton("detail");
			   }
			   else if(individualClientReportBean.skillsetMasterbean.getId() != null && (individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null)){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithStatusAndSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
				   individualClientReportBean.setSelectedRadioButton("detail");
			   }
			   else if((individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null) && individualClientReportBean.skillsetMasterbean.getId() == null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithDateSatus(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
				   individualClientReportBean.setSelectedRadioButton("detail");
			   }else {
				 
			   }*/
		   
		  }else {
			
			reportBeanList.clear();
			
			individualClientReportBean.statusMasterBean.setStatus(null);
			individualClientReportBean.statusMasterBean.setStatusId(null);
			statusList = ResourceMasterDao.onLoadStatus();  
			Messagebox.show("Select Client Name ", "ALERT", Messagebox.OK,Messagebox.EXCLAMATION);
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
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   individualClientReportBean.statusMasterBean.setStatusId(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   if(individualClientReportBean.clientInformationBean.getClientId() != null){
			
			   //functionality gone to search method
			   /*if(individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null){
			    
				   reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkill(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
				   individualClientReportBean.setSelectedRadioButton("detail");
			    }
			   if(individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
				   individualClientReportBean.setSelectedRadioButton("detail");
			   }*/
			   
			   
		   }else {
			  
			individualClientReportBean.skillsetMasterbean.setSkillset(null);
			individualClientReportBean.skillsetMasterbean.setId(null);
			skillList = RequirementGenerationService.fetchSkillSetList();
			   
			Messagebox.show("Client Not Selected", "ALERT", Messagebox.OK,Messagebox.EXCLAMATION);
		}
		   
		   
		   
	   }
	   
	   /**
	    * for search button. now invisible
	    */
	   
	  
	   
	   @Command
	   @NotifyChange("*")
	   public void onClickClear(){
		   
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   
		   individualClientReportBean.skillsetMasterbean.setId(null);
		   individualClientReportBean.statusMasterBean.setStatusId(null);
		   individualClientReportBean.clientInformationBean.setClientId(null);
		   
		   individualClientReportBean.clientInformationBean.setFullName(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   individualClientReportBean.setFromDate(null);
		   individualClientReportBean.setToDate(null);
		   
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   individualClientReportBean.setSelectedRadioButton(null);
		   
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onCheckDetailSummary(){
		   
		   if(individualClientReportBean.getSelectedRadioButton().equals("detail")){
			 individualClientReportBean.setDetailsDivVis(true);
			 individualClientReportBean.setSummaryDivVis(false);
			   
		   }else {
			   
			   individualClientReportBean.setDetailsDivVis(false);
			   summaryBeanList = IndividualClientReportService.loadRidSummaryList(reportBeanList);
			   individualClientReportBean.setSummaryDivVis(true);
			 
		}
		   
	   }
	   
	   
	@Command
	@NotifyChange("*")
	public void onClickExcel(){
		if(reportBeanList.size()>0){
		if(individualClientReportBean.getSelectedRadioButton().equals("detail")){
		
			IndividualClientReportExcel.printCSV(reportBeanList,"Individual Client Report");
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
			
			IndividualClientReportExcel.printSummaryCSV(summaryBeanList,"Individual Client Summary");
			
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
	 public void onClickSearch(){
		 
		 //when only client selected
	     if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() == null){
			
	    	 reportBeanList = IndividualClientReportService.loadRidList(individualClientReportBean.clientInformationBean.getClientId());
	    	 if(reportBeanList.size() == 0){
	    		 Messagebox.show("No Data Found!! ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	 }
		 }
	     //when client and both date selected
		 if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() == null){
			 
			reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.clientInformationBean.getClientId());
		    individualClientReportBean.setSelectedRadioButton("detail");
		    	
		    if(reportBeanList.size() == 0){
	    		 Messagebox.show("No Data Found!! ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	} 
			 
		 }
		//when client both date and skill selected
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() != null && individualClientReportBean.statusMasterBean.getStatusId() == null){
		
	 		reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkill(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
	 		individualClientReportBean.setSelectedRadioButton("detail");
	 		
	 		if(reportBeanList.size() == 0){
	    		 Messagebox.show("No Data Found!! ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	}
	 		
		}
		//when client and skill selected
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() != null && individualClientReportBean.statusMasterBean.getStatusId() == null){
	
			reportBeanList = IndividualClientReportService.loadRidListWithSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
		    individualClientReportBean.setSelectedRadioButton("detail");
			
			if(reportBeanList.size() == 0){
			 Messagebox.show("No Data Found!!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
		//when status and client selected
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() != null){
			
			reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDate(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
			individualClientReportBean.setSelectedRadioButton("detail");
			   
				if(reportBeanList.size() == 0){
				 Messagebox.show("No Data Found!! ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}	
		}
		//when skill status and client given
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() != null){
			
	 		reportBeanList = IndividualClientReportService.loadRidListWithStatusAndSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
	 		individualClientReportBean.setSelectedRadioButton("detail");
			   
				if(reportBeanList.size() == 0){
				 Messagebox.show("No Data Found!! ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
				
		}
		//when date status and client given
		if(individualClientReportBean.clientInformationBean.getClientId() != null && individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() != null){
			
			reportBeanList = IndividualClientReportService.loadRidListWithDateSatus(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
			individualClientReportBean.setSelectedRadioButton("detail");
				
				if(reportBeanList.size() == 0){
				 Messagebox.show("No Data Found!! ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
				
		}
		//when nothing selected
		if(individualClientReportBean.clientInformationBean.getClientId() == null && individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId() == null && individualClientReportBean.statusMasterBean.getStatusId() == null){
			Messagebox.show("Select Client ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
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
