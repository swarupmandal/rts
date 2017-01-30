package org.appsquad.viewmodel;
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

public class SkillSetWiseReportViewModel {
	private Session sessions = null;
	private String userId;
	
	@Wire("#clntBb")
	private Bandbox clientBandBox;
	@Wire("#skSt")
	private Bandbox skillBandBox;
	
	IndividualClientReportBean skilWiseReportBean = new IndividualClientReportBean();
	
	private ArrayList<IndividualClientReportBean> skilWiseReportBeanList = new ArrayList<IndividualClientReportBean>();
	private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
	private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
	private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
	private ArrayList<IndividualClientReportBean> reportBeanList = new ArrayList<IndividualClientReportBean>();
	private ArrayList<IndividualClientReportBean> summaryBeanList = new ArrayList<IndividualClientReportBean>();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		skillList = RequirementGenerationService.fetchSkillSetList();
		statusList = ResourceMasterDao.onLoadStatusForReport();
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
		skilWiseReportBean.setDetailsDivVis(true);
		skilWiseReportBean.setSelectedRadioButton("detail");
		
	}

	   @Command
	   @NotifyChange("*")
	   public void onChangeFromDate(){
		   reportBeanList.clear();
		   summaryBeanList.clear();
		   if(skilWiseReportBean.getFromDate() == null){
			   skilWiseReportBean.setToDate(null);
		   }
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onChangeToDate(){
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   
		   if(skilWiseReportBean.getFromDate() != null){
			   if(skilWiseReportBean.getToDate().after(skilWiseReportBean.getFromDate())){
			     }else {
			    	 skilWiseReportBean.setToDate(null);
				     Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
			   }
		   }else {
			         skilWiseReportBean.setToDate(null);
			         Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }
	
	@Command
	@NotifyChange("*")
	public void onSelctSkillName(){
		   skillBandBox.close();
		   summaryBeanList.clear();
		   reportBeanList.clear();   
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeSkillName(){
		skillList = RequirementGenerationService.skillSetListSearch(skilWiseReportBean.getSkillSetSearch());
		reportBeanList.clear();
		summaryBeanList.clear();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelctClientName(){
		summaryBeanList.clear();
		reportBeanList.clear();
		clientBandBox.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onCheckDetailSummary(){
		   if(skilWiseReportBean.getSelectedRadioButton().equals("detail")){
			  skilWiseReportBean.setDetailsDivVis(true);
			  skilWiseReportBean.setSummaryDivVis(false);
		   }else {
			  skilWiseReportBean.setDetailsDivVis(false);
			  skilWiseReportBean.setSummaryDivVis(true); 
		   }
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeClientName(){
		clientList = ResourceAllocationTrackingService.fetchClientDetailsSearchClient(skilWiseReportBean.getClientNameSearch());
		reportBeanList.clear();
		summaryBeanList.clear();
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClickPdf(){
		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		IndividualClientReportPdf pdf = new IndividualClientReportPdf();
		try{
			if(reportBeanList.size()>0){	
				if(skilWiseReportBean.getSelectedRadioButton().equals("detail")){
					pdf.getDetails(pdfPath, skilWiseReportBean, reportBeanList, "Skill Set Report");
				}else {
					pdf.getSummary(pdfPath, skilWiseReportBean, summaryBeanList, "Skill Set Summary");
				}
			}else {
				Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExcel(){
		if(reportBeanList.size()>0){
			if(skilWiseReportBean.getSelectedRadioButton().equals("detail")){
				IndividualClientReportExcel.printCSV(reportBeanList, "Skill Set Report");
			}else {
				IndividualClientReportExcel.printSummaryCSV(summaryBeanList,"Skill Set Summary");
			}
		}else {
			Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStatusName(){
		summaryBeanList.clear();
		reportBeanList.clear();
		
		if(skilWiseReportBean.statusMasterBean.getStatus().equalsIgnoreCase("-CLEAR-")){
			 skilWiseReportBean.statusMasterBean.setStatus(null);
			 skilWiseReportBean.statusMasterBean.setStatusId(null);
			 statusList = ResourceMasterDao.onLoadStatusForReport();
		}
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClearSkillSet(){
		skillBandBox.close();
		reportBeanList.clear();
		summaryBeanList.clear();
		skilWiseReportBean.skillsetMasterbean.setSkillset(null);
		skilWiseReportBean.skillsetMasterbean.setId(null);
		skilWiseReportBean.setSkillSetSearch(null);
		skillList = RequirementGenerationService.fetchSkillSetList();
	}
	
	@Command
	@NotifyChange("*")
	public void onClearClientName(){
		clientBandBox.close();
		reportBeanList.clear();
		summaryBeanList.clear();
		skilWiseReportBean.clientInformationBean.setFullName(null);
		skilWiseReportBean.clientInformationBean.setClientId(null);
		skilWiseReportBean.setClientNameSearch(null);
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickClear(){
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   
		   skilWiseReportBean.clientInformationBean.setFullName(null);
		   skilWiseReportBean.clientInformationBean.setClientId(null);
		   skilWiseReportBean.setClientNameSearch(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   
		   skilWiseReportBean.setFromDate(null);
		   skilWiseReportBean.setToDate(null);
		   
		   skilWiseReportBean.skillsetMasterbean.setSkillset(null);
		   skilWiseReportBean.setSkillSetSearch(null);
		   skilWiseReportBean.skillsetMasterbean.setId(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   skilWiseReportBean.statusMasterBean.setStatus(null);
		   skilWiseReportBean.statusMasterBean.setStatusId(null);
		   statusList = ResourceMasterDao.onLoadStatusForReport();
		   skilWiseReportBean.setSelectedRadioButton("detail");
		   
	}
	
	public void generationOfDetailsReport(){
		//when only date selected
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && skilWiseReportBean.skillsetMasterbean.getId()== null && skilWiseReportBean.statusMasterBean.getStatusId()==null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()));
	    	if(reportBeanList.size()==0){
	    		Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	}
		}

		//when date and skill selected
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && skilWiseReportBean.skillsetMasterbean.getId() != null && skilWiseReportBean.statusMasterBean.getStatusId()==null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkillReport(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId());

	    	if(reportBeanList.size()==0){
	    		Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	}
		}

		//when date skill status
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && skilWiseReportBean.skillsetMasterbean.getId() != null && skilWiseReportBean.statusMasterBean.getStatusId() !=null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDateWiseReport(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId(), skilWiseReportBean.statusMasterBean.getStatusId());
			
	    	if(reportBeanList.size()==0){
	    		Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	}
		}

		//when date skill status client selected
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && skilWiseReportBean.skillsetMasterbean.getId() != null && skilWiseReportBean.statusMasterBean.getStatusId() !=null && skilWiseReportBean.clientInformationBean.getClientId()!= null){
			 reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDate(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId(), skilWiseReportBean.statusMasterBean.getStatusId(), skilWiseReportBean.clientInformationBean.getClientId());
	    	 
	    	 if(reportBeanList.size()==0){
	    		 Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	    	 }
		}

		//when date skill and client selected
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && skilWiseReportBean.skillsetMasterbean.getId() != null && 
				skilWiseReportBean.statusMasterBean.getStatusId() ==null && skilWiseReportBean.clientInformationBean.getClientId()!= null){
			reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkill(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId(), skilWiseReportBean.clientInformationBean.getClientId());
			
			if(reportBeanList.size()==0){
				Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		    }
		}

		//when date and status selected
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && 
				skilWiseReportBean.skillsetMasterbean.getId() == null && 
				  skilWiseReportBean.statusMasterBean.getStatusId() !=null && 
				     skilWiseReportBean.clientInformationBean.getClientId()== null){
			
			reportBeanList = IndividualClientReportService.loadDateAndStatusInClientReportService(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.statusMasterBean.getStatusId());
			
			if(reportBeanList.size()==0){
				Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		    }
		}

		//when date and client selected
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && 
				skilWiseReportBean.skillsetMasterbean.getId() == null && 
				  skilWiseReportBean.statusMasterBean.getStatusId() ==null && 
				     skilWiseReportBean.clientInformationBean.getClientId()!= null){
			
			reportBeanList = IndividualClientReportService.loadDateAndCientInClientReportService(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.clientInformationBean.getClientId());
			
			if(reportBeanList.size()==0){
				Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		    }
		}
		
		//when date and skill and client
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && 
				skilWiseReportBean.skillsetMasterbean.getId() != null && 
				  skilWiseReportBean.statusMasterBean.getStatusId() ==null && 
				     skilWiseReportBean.clientInformationBean.getClientId()!= null){
			
			reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkill(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId(),skilWiseReportBean.clientInformationBean.getClientId());
			
			if(reportBeanList.size()==0){
				Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		    }
		}

		//when date and status and client
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && 
				skilWiseReportBean.skillsetMasterbean.getId() == null && 
				  skilWiseReportBean.statusMasterBean.getStatusId() !=null && 
				     skilWiseReportBean.clientInformationBean.getClientId()!= null){
			
			reportBeanList = IndividualClientReportService.loadRidListWithDateSatus(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.statusMasterBean.getStatusId(),skilWiseReportBean.clientInformationBean.getClientId());
			
			if(reportBeanList.size()==0){
				Messagebox.show("No Data Found For This Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		    }
		}		

		//when nothing selected
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() == null && 
				skilWiseReportBean.statusMasterBean.getStatusId() ==null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			
			  Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			  
		}

		//when from date selected
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() == null && 
				skilWiseReportBean.statusMasterBean.getStatusId() ==null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			
			Messagebox.show("Select To Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
		
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() != null && 
				skilWiseReportBean.statusMasterBean.getStatusId() ==null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			
			
			Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
		
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() == null && 
				skilWiseReportBean.statusMasterBean.getStatusId() !=null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			
			Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
		
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() == null && 
				skilWiseReportBean.statusMasterBean.getStatusId() ==null && skilWiseReportBean.clientInformationBean.getClientId()!= null){
			
			Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
		
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() != null && 
				skilWiseReportBean.statusMasterBean.getStatusId() !=null && skilWiseReportBean.clientInformationBean.getClientId()!= null){
			
			Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
		
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() != null && 
				skilWiseReportBean.statusMasterBean.getStatusId() !=null && skilWiseReportBean.clientInformationBean.getClientId()== null){
			
			Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
		
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() != null && 
				skilWiseReportBean.statusMasterBean.getStatusId() ==null && skilWiseReportBean.clientInformationBean.getClientId()!= null){
			
			
			Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
		
		if(skilWiseReportBean.getFromDate() == null && skilWiseReportBean.getToDate() == null && skilWiseReportBean.skillsetMasterbean.getId() == null && 
				skilWiseReportBean.statusMasterBean.getStatusId() !=null && skilWiseReportBean.clientInformationBean.getClientId()!= null){
			
			Messagebox.show("Select From Date ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION); 
			
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSearch(){
		if(skilWiseReportBean.getSelectedRadioButton().equalsIgnoreCase("detail")){
			System.out.println("SEARCH BUTTON CLICKED FOR DETAIL REPORT");
			generationOfDetailsReport();
			System.out.println("DETAILS REPORT LIST SIZE IS :"+reportBeanList.size());
		}else{
			System.out.println("SEARCH BUTTON CLICKED FOR SUMMARY REPORT");
			generationOfDetailsReport();
			System.out.println("SUMMARY REPORT LIST SIZE IS :"+reportBeanList.size());
			 if(skilWiseReportBean.statusMasterBean.getStatusId()!=null){
				 summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,skilWiseReportBean.statusMasterBean.getStatusId());
			 }else{
				 skilWiseReportBean.statusMasterBean.setStatusId(200);
				 summaryBeanList = IndividualClientReportService.loadRidSummaryListTest(reportBeanList,skilWiseReportBean.statusMasterBean.getStatusId());
			 }
		}	
	}
	
	/*********************************************************************************************************************************************/
	
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
