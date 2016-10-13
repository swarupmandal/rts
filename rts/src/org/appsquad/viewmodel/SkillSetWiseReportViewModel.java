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
		statusList = ResourceMasterDao.onLoadStatus();
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
		
		skilWiseReportBean.setDetailsDivVis(true);
		skilWiseReportBean.setSelectedRadioButton("detail");
		
	}

	
	@Command
	   @NotifyChange("*")
	   public void onChangeFromDate(){
		   
		   reportBeanList.clear();
		   summaryBeanList.clear();
		   
		   skilWiseReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   skilWiseReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   skilWiseReportBean.clientInformationBean.setFullName(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   
		   if(skilWiseReportBean.getFromDate() == null){
			   skilWiseReportBean.setToDate(null);
		   }
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onChangeToDate(){
		   summaryBeanList.clear();
		   skilWiseReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   skilWiseReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   skilWiseReportBean.clientInformationBean.setFullName(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   
		   if(skilWiseReportBean.getFromDate() != null){
			   
			   if(skilWiseReportBean.getToDate().after(skilWiseReportBean.getFromDate())){
				    
				    //if(skilWiseReportBean.clientInformationBean.getClientId() != null){
				    	
				    	if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null){
				    	reportBeanList = IndividualClientReportService.loadRidListwithDateRange(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()));
				    	skilWiseReportBean.setSelectedRadioButton("detail");
				    	}
				    //}else {}
				   
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
		   skilWiseReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
			
			   if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null){
			    
				   reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkillReport(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId());
				   skilWiseReportBean.setSelectedRadioButton("detail");
			    }
			   
	}
	
	
	@Command
	@NotifyChange("*")
	public void onSelectStatusName(){
		
		summaryBeanList.clear();
		reportBeanList.clear();
		
		skilWiseReportBean.clientInformationBean.setFullName(null);
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
		
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null){
			
			if(skilWiseReportBean.skillsetMasterbean.getId() != null){
				
				 reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDateWiseReport(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId(), skilWiseReportBean.statusMasterBean.getStatusId());
				 skilWiseReportBean.setSelectedRadioButton("detail");
			   
			}else {
				skilWiseReportBean.statusMasterBean.setStatus(null);
				statusList = ResourceMasterDao.onLoadStatus();
				Messagebox.show("Select Skill Set", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);   
			}
			
		}else {
			skilWiseReportBean.statusMasterBean.setStatus(null);
			statusList = ResourceMasterDao.onLoadStatus();
			Messagebox.show("Select From Date and To Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	
	@Command
	@NotifyChange("*")
	public void onSelctClientName(){
		
		summaryBeanList.clear();
		reportBeanList.clear();
		
		clientBandBox.close();
		
		if(skilWiseReportBean.getFromDate() != null && skilWiseReportBean.getToDate() != null && skilWiseReportBean.skillsetMasterbean.getId() != null){
		
			if(skilWiseReportBean.statusMasterBean.getStatusId() != null){
			   
			   reportBeanList = IndividualClientReportService.loadRidListWithStatusSkillDate(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId(), skilWiseReportBean.statusMasterBean.getStatusId(), skilWiseReportBean.clientInformationBean.getClientId());
			   skilWiseReportBean.setSelectedRadioButton("detail");
			}else{
		    
			   reportBeanList = IndividualClientReportService.loadRidListwithDateRangeWithSkill(Dateformatter.sqlDate(skilWiseReportBean.getFromDate()), Dateformatter.sqlDate(skilWiseReportBean.getToDate()), skilWiseReportBean.skillsetMasterbean.getId(), skilWiseReportBean.clientInformationBean.getClientId());
			   skilWiseReportBean.setSelectedRadioButton("detail");
		 }
		}else {
			skilWiseReportBean.clientInformationBean.setFullName(null);
			clientList = ResourceAllocationTrackingService.fetchClientDetails();
			Messagebox.show("Select From Date To Date And Skill Set ", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onCheckDetailSummary(){

		   if(skilWiseReportBean.getSelectedRadioButton().equals("detail")){
			  skilWiseReportBean.setDetailsDivVis(true);
			  skilWiseReportBean.setSummaryDivVis(false);
			   
		   }else {
			   
			   skilWiseReportBean.setDetailsDivVis(false);
			   summaryBeanList = IndividualClientReportService.loadRidSummaryList(reportBeanList);
			   skilWiseReportBean.setSummaryDivVis(true);
		}
	}
	
	
	
	@Command
	@NotifyChange("*")
	public void onClickClear(){

		   
		   summaryBeanList.clear();
		   reportBeanList.clear();
		   skilWiseReportBean.clientInformationBean.setFullName(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
		   skilWiseReportBean.setFromDate(null);
		   skilWiseReportBean.setToDate(null);
		   
		   skilWiseReportBean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   skilWiseReportBean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   skilWiseReportBean.setSelectedRadioButton(null);
		   
	   
	}
	
	@Command
	@NotifyChange("*")
	public void onClickPdf(){
		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		//String totalPdfPath = pdfPath + "report.pdf";
		String totalPdfPath = "C:\\pdf test\\Report_Pdf.pdf";
		
		IndividualClientReportPdf pdf = new IndividualClientReportPdf();
		
		try{
			
		if(skilWiseReportBean.getSelectedRadioButton().equals("detail")){
		
			pdf.getDetails(totalPdfPath, skilWiseReportBean, reportBeanList);
			/*if(reportBeanList.size()>0);	
		  ArrayList<IndividualClientReportBean> detailList = new ArrayList<IndividualClientReportBean>();
			for(IndividualClientReportBean bean : reportBeanList){
				if(bean.isDetailChecked()){
					detailList.add(bean);
				}
			}
			if(detailList.size()>0){
				pdf.getDetails(totalPdfPath, skilWiseReportBean, detailList);
			}else {
				Messagebox.show("NO DATA SELECTED ", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION );
			}*/
			
		}else {
			pdf.getSummary(totalPdfPath, skilWiseReportBean, summaryBeanList);
			/*if(summaryBeanList.size()>0);
			ArrayList<IndividualClientReportBean> summList = new ArrayList<IndividualClientReportBean>();
			for(IndividualClientReportBean bean : summaryBeanList){
				if(bean.isSummaryChecked()){
					summList.add(bean);
				}
			}
			if(summList.size()>0){
				pdf.getSummary(totalPdfPath, skilWiseReportBean, summList);
			}else {
				Messagebox.show("NO DATA SELECTED ", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION );
			}*/
			
		}
	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		/*try {
			
			if(individualClientReportBean.getSelectedRadioButton().equals("detail")){
			   pdf.getDetails(totalPdfPath, individualClientReportBean, reportBeanList);
			   
			}else {
				pdf.getSummary(totalPdfPath, individualClientReportBean, summaryBeanList);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}*/
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExcel(){
		if(skilWiseReportBean.getSelectedRadioButton().equals("detail")){
		
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
