package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.bean.MonthReportBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.CurrentOpportunitiesReportGenerationDao;
import org.appsquad.service.CurrentOpportunitiesReportGenerationService;
import org.appsquad.service.ResourceAllocationTrackingService;
import org.appsquad.utility.CurrentOppurtunitiesReportPdf;
import org.appsquad.utility.CurrentOppurtunitiesReportPdfForOppurtunityWise;
import org.appsquad.utility.MonthShowingUtility;
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

public class CurrentOpportunitiesReportGenerationViewModel {
   public CurrentOpportunitiesReportGenerationBean currentOpportunitiesReportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
   
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   private String frmDate = "";
   private String toDate = "";
   private boolean divVisibility = false;
   private boolean secondDivVisibility = false;
   private boolean pdfDivVisibility = false;
   private boolean buttonVisibility = false;
   private MonthReportBean monthReportBean = new MonthReportBean();
   private ArrayList<MonthReportBean> monthReportBeanList = new ArrayList<MonthReportBean>();
   @Wire("#clntBb")
   private Bandbox clnBandBox;
   @Wire("#resourceBb")
   private Bandbox resourceBandBox;
   
   private ArrayList<CurrentOpportunitiesReportGenerationBean> reportList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
   private ArrayList<ClientInformationBean> clientList = null;
   private ArrayList<ResourceMasterBean> resourceList = null;
   
   private ArrayList<CurrentOpportunitiesReportGenerationBean> finalList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
   private ArrayList<CurrentOpportunitiesReportGenerationBean> resourceFinalList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
   private ArrayList<CurrentOpportunitiesReportGenerationBean> secondTabList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
   private ArrayList<CurrentOpportunitiesReportGenerationBean> finalTabList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
   
   @AfterCompose
   public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
 		Selectors.wireComponents(view, this, false);
 		sessions = Sessions.getCurrent();
 		userId = (String) sessions.getAttribute("userId");
 		clientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
 		resourceList = CurrentOpportunitiesReportGenerationDao.onLoadResourceDetails();
 		reportList = CurrentOpportunitiesReportGenerationService.loadReportDetailsUnpaid();
 		if(reportList.size()>0){
 			divVisibility = true;
 			pdfDivVisibility = true;
 		}
 		/*loadMonths();*/
 	}
   
    public void loadMonths(){
    	String[] months  = {"january","february","march","april","may","june","july","august","september","october","november","december"};
    	for(int i=1 ; i<13; i++){
    		MonthReportBean reportBean = new MonthReportBean();
    		reportBean.setMonthId(i);
    		reportBean.setMonthName(months[i-1]);
    		monthReportBeanList.add(reportBean);
    	}
    }
   
   @Command
   @NotifyChange("*")
   public void onChangeToDate(){
	   if(reportList.size()>0){
		   reportList.clear();
		   divVisibility = false;
		   pdfDivVisibility = false;
	   }
 	   if(currentOpportunitiesReportGenerationBean.getFromDate() != null){
 		   if(currentOpportunitiesReportGenerationBean.getToDate().after(currentOpportunitiesReportGenerationBean.getFromDate())){
 		     }else {
 		    	currentOpportunitiesReportGenerationBean.setToDate(null);
 			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
 		   }
 	   }else {
 		   currentOpportunitiesReportGenerationBean.setToDate(null);
 		   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
 	   }
    }
   
   @Command
   @NotifyChange("*")
   public void onChangeFromDate(){
	   if(reportList.size()>0){
		   reportList.clear();
		   divVisibility = false;
		   pdfDivVisibility = false;
	   }
	   if(currentOpportunitiesReportGenerationBean.getToDate()!=null){
		   if(currentOpportunitiesReportGenerationBean.getToDate().after(currentOpportunitiesReportGenerationBean.getFromDate())){
		     }else {
		    	currentOpportunitiesReportGenerationBean.setToDate(null);
			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }else{
		   System.out.println("nothing.");
	   }
   }
   
   @Command
   @NotifyChange("*")
   public void onClickClear(){
	   if(reportList.size()>0){
		   reportList.clear();
		   divVisibility = false;
		   pdfDivVisibility = false;
	   }
	   currentOpportunitiesReportGenerationBean.setFromDate(null);
	   currentOpportunitiesReportGenerationBean.setToDate(null);
   }
   
   /*@Command
   @NotifyChange("*")
   public void onClickSearchButton(){
	 //when to Date not selected
	 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() == null){
	 	Messagebox.show("Select To Date!!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	 }
	 		
	 //when no date selected
	 if(currentOpportunitiesReportGenerationBean.getFromDate() == null && currentOpportunitiesReportGenerationBean.getToDate() == null){
	 	Messagebox.show("Select From Date And To Date", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	 }
	 
	 //when two date are given
	 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() != null){
		 reportList = CurrentOpportunitiesReportGenerationService.loadReportDetails(Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()), 
				 																	Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate()));
	     
		 System.out.println("REPORT LIST SIZE IS :"+reportList.size());
		 if(reportList.size()>0){
			divVisibility = true;
			pdfDivVisibility = true;
		 }else{
			Messagebox.show("No Data Found For This Date Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			divVisibility = false;
			pdfDivVisibility = false;
		}
	  }
   }*/
   
   @Command
   @NotifyChange("*")
   public void onClickPdf() throws Exception{
	   String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
	   CurrentOppurtunitiesReportPdf pdf = new CurrentOppurtunitiesReportPdf();
	   try {
		    System.out.println(reportList.size());
		   	if(reportList.size()>0){
				pdf.getDetails(pdfPath, currentOpportunitiesReportGenerationBean, reportList, "Current Opportunities Report");
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
   public void onClickPdfForOppurtunity() throws Exception{
	   if(currentOpportunitiesReportGenerationBean.getSelectedRadioButton()!=null){
		   if(currentOpportunitiesReportGenerationBean.getSelectedRadioButton().equalsIgnoreCase("1stReport")){
			  if(currentOpportunitiesReportGenerationBean.getClientInformationBean().getFullName()!=null){
				   String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
				   CurrentOppurtunitiesReportPdfForOppurtunityWise pdf = new CurrentOppurtunitiesReportPdfForOppurtunityWise();
				   try {
					   	if(finalList.size()>0){
							pdf.getDetails(pdfPath, currentOpportunitiesReportGenerationBean, finalList, "Current Opportunities Report");
					   	}else {
							Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
					   	}
				   } catch (FileNotFoundException e) {
					   e.printStackTrace();
				   } catch (DocumentException e) {
					   e.printStackTrace();
				   }   
			  }else{
				  Messagebox.show("Select Client Name ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			  }
		   }else if(currentOpportunitiesReportGenerationBean.getSelectedRadioButton().equalsIgnoreCase("2ndReport")){
			   if(currentOpportunitiesReportGenerationBean.getResourceMasterBean().getFullName()!=null){
				   String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
				   CurrentOppurtunitiesReportPdfForOppurtunityWise pdf = new CurrentOppurtunitiesReportPdfForOppurtunityWise();
				   try {
					   	if(resourceFinalList.size()>0){
							pdf.getDetails(pdfPath, currentOpportunitiesReportGenerationBean, resourceFinalList, "Current Opportunities Report");
					   	}else {
							Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
					   	}
				   } catch (FileNotFoundException e) {
					   e.printStackTrace();
				   } catch (DocumentException e) {
					   e.printStackTrace();
				   }   
			   }else{
				   Messagebox.show("Select Resource Name ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION); 
			   }
		   }
	   }else{
		   Messagebox.show("Select Any Radio Button ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
	   }
	}
   
    @Command
    @NotifyChange("*")
    public void onCheckDetailSummary(){
    	if(currentOpportunitiesReportGenerationBean.getSelectedRadioButton().equalsIgnoreCase("1stReport")){
    		currentOpportunitiesReportGenerationBean.setInnerComboGroup(true);
    		currentOpportunitiesReportGenerationBean.setInnerClientGroup(true);
    		currentOpportunitiesReportGenerationBean.setInnerResourceCombo(false);
    	}else if(currentOpportunitiesReportGenerationBean.getSelectedRadioButton().equalsIgnoreCase("2ndReport")){
    		currentOpportunitiesReportGenerationBean.setInnerComboGroup(true);
    		currentOpportunitiesReportGenerationBean.setInnerClientGroup(false);
    		currentOpportunitiesReportGenerationBean.setInnerResourceCombo(true);
    	}
    }
    
    @Command
	@NotifyChange("*")
	public void onChangeClientName(){
	   if(currentOpportunitiesReportGenerationBean.getClientNameSearch() != null){
		  setClientList(ResourceAllocationTrackingService.fetchClientDetailsSearchClient(currentOpportunitiesReportGenerationBean.getClientNameSearch()));
	   }
	}
    
    @Command
	@NotifyChange("*")
	public void onClickClientClear(){
		clnBandBox.close();
		currentOpportunitiesReportGenerationBean.getClientInformationBean().setFullName(null);
		currentOpportunitiesReportGenerationBean.getClientInformationBean().setClientId(null);
		currentOpportunitiesReportGenerationBean.setClientNameSearch(null);
		clientList = ResourceAllocationTrackingService.fetchClientDetails();
	}
    
    @Command
	@NotifyChange("*")
	public void onSelctClientName(){
		clnBandBox.close();
		if(secondTabList.size()>0){
			secondTabList.clear();	
		}
		
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = null;
		list = CurrentOpportunitiesReportGenerationDao.loadOppurtunityWiseReportForClientDao(currentOpportunitiesReportGenerationBean);
		//System.out.println("IN VIEW MODEL FOR CLIENT :"+list.size());
		
		if(finalList.size()>0){
			finalList.clear();
		}
		
	    for(CurrentOpportunitiesReportGenerationBean bean: list){
	    	//System.out.println(bean.getRtsTrackingDetailsId()+"----"+bean.getCurrentOpportunitiesReportBean().getMonth()+"---"+bean.getCurrentOpportunitiesBean().getTentureFromUtil());
	    	String name = MonthShowingUtility.fetchresourceName(bean);
	    	bean.getResourceMasterBean().setFullName(name);
	    	MonthShowingUtility.fetchdateWrtRtrackingID(bean);
	    	finalList.add(bean);
	    }
	    
	    for(CurrentOpportunitiesReportGenerationBean bean: finalList){
	    	System.out.println(bean.getCurrentOpportunitiesBean().getTentureFromUtil()+"---"+bean.getRtsTrackingDetailsId()+"----"+bean.getCurrentOpportunitiesReportBean().getMonth()+"----"+bean.getResourceMasterBean().getFullName());
	    }
	    
	    secondTabList = finalList;
	    
	    if(secondTabList.size()>0){
	    	secondDivVisibility=true;
		    buttonVisibility = true;	
	    }else{
	    	Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
	    }
	    
	    String[] months  = {"january","february","march","april","may","june","july","august","september","october","november","december"};
    	for(int i=1 ; i<13; i++){
    		MonthReportBean reportBean = new MonthReportBean();
    		reportBean.setMonthId(i);
    		reportBean.setMonthName(months[i-1]);
    	
    		ArrayList<CurrentOpportunitiesReportGenerationBean> monthClienBeanList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
    		for(CurrentOpportunitiesReportGenerationBean gen : secondTabList){
    			//System.out.println("GEN MONTH NAME::::::::: "+gen.getCurrentOpportunitiesReportBean().getMonth()+" Size : "+gen.getCurrentOpportunitiesReportBean().getMonth().length());
    			//System.out.println("REPRT MONTH: : :  "+reportBean.getMonthName()+"  SIZE:::::"+reportBean.getMonthName().length());
    			if(gen.getCurrentOpportunitiesReportBean().getMonth().equalsIgnoreCase(reportBean.getMonthName())){
    
    				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
    				reportGenerationBean.getClientInformationBean().setFullName(gen.getResourceMasterBean().getFullName());
    				monthClienBeanList.add(reportGenerationBean);
    				
    			}
    		}
    		reportBean.setCurrentOpportunitiesReportGenerationBeanList(monthClienBeanList);
    		monthReportBeanList.add(reportBean);
    	}
	    
    } 
    
    @Command
    @NotifyChange("*")
    public void secondTabClick(){
    	divVisibility = false;
    }
    
    @Command
	@NotifyChange("*")
	public void onSelctResourceName(){
		resourceBandBox.close();
		if(secondTabList.size()>0){
			secondTabList.clear();	
		}
		System.out.println("RESOURCE NAME :"+currentOpportunitiesReportGenerationBean.getResourceMasterBean().getFullName());
		
		ArrayList<CurrentOpportunitiesReportGenerationBean> resourceList = null;
		resourceList = CurrentOpportunitiesReportGenerationDao.loadOppurtunityWiseReportForClientDaoForResource(currentOpportunitiesReportGenerationBean);
		System.out.println("IN VIEW MODEL FOR RESOURCE :"+resourceList.size());
		if(resourceFinalList.size()>0){
			resourceFinalList.clear();
		}
		
	    for(CurrentOpportunitiesReportGenerationBean bean1: resourceList){
	    	System.out.println(bean1.getRtsTrackingDetailsId()+"----"+bean1.getCurrentOpportunitiesReportBean().getMonth()+"---"+bean1.getCurrentOpportunitiesBean().getTentureFromUtil());
	    	String name = MonthShowingUtility.fetchresourceName(bean1);
	    	bean1.getResourceMasterBean().setFullName(name);
	    	MonthShowingUtility.fetchdateWrtRtrackingID(bean1);
	    	resourceFinalList.add(bean1);
	    }
	    
	    for(CurrentOpportunitiesReportGenerationBean bean: resourceFinalList){
	    	System.out.println(bean.getCurrentOpportunitiesBean().getTentureFromUtil()+"---"+bean.getRtsTrackingDetailsId()+"----"+bean.getCurrentOpportunitiesReportBean().getMonth()+"----"+bean.getResourceMasterBean().getFullName());
	    }
	    
	    secondTabList = resourceFinalList;
	    if(secondTabList.size()>0){
	    	secondDivVisibility=true;
		    buttonVisibility = true;
	    }else{
	    	Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
	    }
	}
    
    @Command
	@NotifyChange("*")
	public void onClickResourceClear(){
    	resourceBandBox.close();
		currentOpportunitiesReportGenerationBean.getResourceMasterBean().setFullName(null);
		currentOpportunitiesReportGenerationBean.setResourceNameSearch(null);
		resourceList = CurrentOpportunitiesReportGenerationDao.onLoadResourceDetails();;
	}
    
    @Command
	@NotifyChange("*")
	public void onChangeResourceName(){
	   if(currentOpportunitiesReportGenerationBean.getResourceNameSearch()!= null){
		  setResourceList(CurrentOpportunitiesReportGenerationService.loadResourceDetails(currentOpportunitiesReportGenerationBean.getResourceNameSearch()));
	   }
	}
    
    @Command
    @NotifyChange("*")
    public void onCheckClient(){
    	secondDivVisibility= false;
    	buttonVisibility=false;
    	secondTabList.clear();
    }
    
    @Command
    @NotifyChange("*")
    public void onCheckResource(){
    	secondDivVisibility= false;
    	buttonVisibility=false;
    	secondTabList.clear();
    }
    
    /****************************************************** GETTER AND SETTER METHOD *******************************************************************/
    
    public CurrentOpportunitiesReportGenerationBean getCurrentOpportunitiesReportGenerationBean() {
		return currentOpportunitiesReportGenerationBean;
	}
	public void setCurrentOpportunitiesReportGenerationBean(
			CurrentOpportunitiesReportGenerationBean currentOpportunitiesReportGenerationBean) {
		this.currentOpportunitiesReportGenerationBean = currentOpportunitiesReportGenerationBean;
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
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public ArrayList<CurrentOpportunitiesReportGenerationBean> getReportList() {
		return reportList;
	}
	public void setReportList(
	    ArrayList<CurrentOpportunitiesReportGenerationBean> reportList) {
		this.reportList = reportList;
	}
	public boolean isPdfDivVisibility() {
		return pdfDivVisibility;
	}
	public ArrayList<ClientInformationBean> getClientList() {
		return clientList;
	}
	public void setClientList(ArrayList<ClientInformationBean> clientList) {
		this.clientList = clientList;
	}
	public void setPdfDivVisibility(boolean pdfDivVisibility) {
		this.pdfDivVisibility = pdfDivVisibility;
	}
	public Bandbox getClnBandBox() {
		return clnBandBox;
	}
	public void setClnBandBox(Bandbox clnBandBox) {
		this.clnBandBox = clnBandBox;
	}
	public Bandbox getResourceBandBox() {
		return resourceBandBox;
	}
	public void setResourceBandBox(Bandbox resourceBandBox) {
		this.resourceBandBox = resourceBandBox;
	}
	public ArrayList<ResourceMasterBean> getResourceList() {
		return resourceList;
	}
	public void setResourceList(ArrayList<ResourceMasterBean> resourceList) {
		this.resourceList = resourceList;
	}
	public ArrayList<CurrentOpportunitiesReportGenerationBean> getFinalList() {
		return finalList;
	}
	public void setFinalList(
			ArrayList<CurrentOpportunitiesReportGenerationBean> finalList) {
		this.finalList = finalList;
	}
	public ArrayList<CurrentOpportunitiesReportGenerationBean> getResourceFinalList() {
		return resourceFinalList;
	}
	public void setResourceFinalList(ArrayList<CurrentOpportunitiesReportGenerationBean> resourceFinalList) {
		this.resourceFinalList = resourceFinalList;
	}
	public boolean isSecondDivVisibility() {
		return secondDivVisibility;
	}
	public void setSecondDivVisibility(boolean secondDivVisibility) {
		this.secondDivVisibility = secondDivVisibility;
	}
	public ArrayList<CurrentOpportunitiesReportGenerationBean> getSecondTabList() {
		return secondTabList;
	}
	public void setSecondTabList(
			ArrayList<CurrentOpportunitiesReportGenerationBean> secondTabList) {
		this.secondTabList = secondTabList;
	}
	public boolean isButtonVisibility() {
		return buttonVisibility;
	}
	public void setButtonVisibility(boolean buttonVisibility) {
		this.buttonVisibility = buttonVisibility;
	}

	public ArrayList<CurrentOpportunitiesReportGenerationBean> getFinalTabList() {
		return finalTabList;
	}

	public void setFinalTabList(ArrayList<CurrentOpportunitiesReportGenerationBean> finalTabList) {
		this.finalTabList = finalTabList;
	}

	public MonthReportBean getMonthReportBean() {
		return monthReportBean;
	}

	public void setMonthReportBean(MonthReportBean monthReportBean) {
		this.monthReportBean = monthReportBean;
	}

	public ArrayList<MonthReportBean> getMonthReportBeanList() {
		return monthReportBeanList;
	}

	public void setMonthReportBeanList(
			ArrayList<MonthReportBean> monthReportBeanList) {
		this.monthReportBeanList = monthReportBeanList;
	}
}