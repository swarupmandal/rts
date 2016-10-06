package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
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
		}  
	  
	   @Command
	   @NotifyChange("*")
	   public void onChangeClientName(){
		   System.out.println("--");
	   }
	    
	   
	   @Command
	   @NotifyChange("*")
	   public void onSelctClientName(){
		   clnBandBox.close();
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   
		   reportBeanList = IndividualClientReportService.loadRidList(individualClientReportBean.clientInformationBean.getClientId());
		   
		   individualClientReportBean.setFromDate(null);
		   individualClientReportBean.setToDate(null);
		   
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   
	   }
	    
	   @Command
	   @NotifyChange("*")
	   public void onChangeFromDate(){
		   
		   reportBeanList.clear();
		   summaryBeanList.clear();
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
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
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   if(individualClientReportBean.getFromDate() != null){
			   
			   if(individualClientReportBean.getToDate().after(individualClientReportBean.getFromDate())){
				    
				    if(individualClientReportBean.clientInformationBean.getClientId() != null){
				    	
				    	if(individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null){
				    	reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.clientInformationBean.getClientId());
				    	}
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
			  
			  
			   if(individualClientReportBean.getFromDate()==null && individualClientReportBean.getToDate() == null && individualClientReportBean.skillsetMasterbean.getId()== null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithStatus(individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId()); 
			   }
			   else if(individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null && individualClientReportBean.skillsetMasterbean.getId() != null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDate(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
			   }
			   else if(individualClientReportBean.skillsetMasterbean.getId() != null && (individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null)){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithStatusAndSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
			   }
			   else if((individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null) && individualClientReportBean.skillsetMasterbean.getId() == null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithDateSatus(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.statusMasterBean.getStatusId(), individualClientReportBean.clientInformationBean.getClientId());
			   }else {
				 
			   }
		   
		  }else {
			  
			individualClientReportBean.statusMasterBean.setStatus(null);
			statusList = ResourceMasterDao.onLoadStatus();  
			Messagebox.show("Select Client Name ", "ALERT", Messagebox.OK,Messagebox.EXCLAMATION);
		}
	   }
	
	   @Command
	   @NotifyChange("*")
	   public void onChangeSkillName(){
		   
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onSelctSkillName(){
		   skStBandBox.close();
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   if(individualClientReportBean.clientInformationBean.getClientId() != null){
			
			   if(individualClientReportBean.getFromDate() != null && individualClientReportBean.getToDate() != null){
			    
				   reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkill(Dateformatter.sqlDate(individualClientReportBean.getFromDate()), Dateformatter.sqlDate(individualClientReportBean.getToDate()), individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
			    }
			   if(individualClientReportBean.getFromDate() == null && individualClientReportBean.getToDate() == null){
				   
				   reportBeanList = IndividualClientReportService.loadRidListWithSkill(individualClientReportBean.skillsetMasterbean.getId(), individualClientReportBean.clientInformationBean.getClientId());
			   }
			   
			   
		   }else {
			  
			individualClientReportBean.skillsetMasterbean.setSkillset(null);
			
			skillList = RequirementGenerationService.fetchSkillSetList();
			   
			Messagebox.show("Client Not Selected", "ALERT", Messagebox.OK,Messagebox.EXCLAMATION);
		}
		   
		   
		   
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onClickSearch(){
		  //reportBeanList = IndividualClientReportService.loadRidList(individualClientReportBean.clientInformationBean.getClientId());
		   
		   //System.out.println("List Size >>> >> > " +reportBeanList.size());
		   for(IndividualClientReportBean rrb : reportBeanList){
			  
		   }
		      
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onClickClear(){
		   
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   individualClientReportBean.clientInformationBean.setFullName(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   individualClientReportBean.setFromDate(null);
		   individualClientReportBean.setToDate(null);
		   
		   individualClientReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   individualClientReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
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
		IndividualClientReportExcel.printCSV(reportBeanList);
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickPdf(){
		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		//String totalPdfPath = pdfPath + "Report_Pdf.pdf";
		
		//String totalPdfPath = "C:\\Users\\swarup\\Desktop\\pdf test\\Report_Pdf.pdf";
		String totalPdfPath = "C:\\pdf test\\Report_Pdf.pdf";
		IndividualClientReportPdf pdf = new IndividualClientReportPdf();
		try {
			pdf.getDetails(totalPdfPath, individualClientReportBean, reportBeanList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
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
