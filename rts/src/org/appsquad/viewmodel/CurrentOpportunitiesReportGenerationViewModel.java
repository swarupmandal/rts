package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.bean.MonthReportBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.BillingReportDashBoardDao;
import org.appsquad.dao.CurrentOpportunitiesReportGenerationDao;
import org.appsquad.service.CurrentOpportunitiesReportGenerationService;
import org.appsquad.service.ResourceAllocationTrackingService;
import org.appsquad.utility.CurrentOppurtunitiesReportPdf;
import org.appsquad.utility.Dateformatter;
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
   private boolean thirdDivVisibility = false;
   private boolean pdfDivVisibility = false;
   private boolean buttonVisibility = false;
   
   private MonthReportBean monthReportBean = new MonthReportBean();
   private ArrayList<MonthReportBean> monthReportBeanList = new ArrayList<MonthReportBean>();
   private ArrayList<String> billingStatusList = new ArrayList<String>();
   LinkedHashSet<MonthReportBean> monthSetList = new LinkedHashSet<MonthReportBean>();
   @Wire("#clntBb")
   private Bandbox clnBandBox;
   @Wire("#frstCln")
   private Bandbox firstBandBox;
   @Wire("#resourceBb")
   private Bandbox resourceBandBox;
   
   private ArrayList<CurrentOpportunitiesReportGenerationBean> reportList = null;
   private ArrayList<ClientInformationBean> clientList = null;
   private ArrayList<ClientInformationBean> firstTabClientList = null;
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
 		firstTabClientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
 		divVisibility = false;
		pdfDivVisibility = false;
		billingStatusList.add("Yes");
		billingStatusList.add("No");
		billingStatusList.add("All");
 	}
   
   
   @Command
   @NotifyChange("*")
   public void firstTab(){
	   
	   divVisibility = false;
	   pdfDivVisibility = false;
	   if(reportList !=null & reportList.size()>0){
		   reportList.clear();
	   }
	   
	   currentOpportunitiesReportGenerationBean.setFromDate(null);
	   currentOpportunitiesReportGenerationBean.setToDate(null);
	   currentOpportunitiesReportGenerationBean.getClientBean().setFullName(null);
	   currentOpportunitiesReportGenerationBean.setBillingStatus(null);
	   
	   firstTabClientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
	   
	   /************************ THIS IS USED FOR CLEARANCE PURPOSE OF ANOTHER TAB DATA******************************************/
	   secondDivVisibility= false;
   	   buttonVisibility=false;
   	   secondTabList.clear();
   	   currentOpportunitiesReportGenerationBean.getResourceMasterBean().setFullName(null);
	   clientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
		
	   thirdDivVisibility= false;
   	   buttonVisibility=false;
   	   secondTabList.clear();
   	   currentOpportunitiesReportGenerationBean.getClientInformationBean().setFullName(null);
	   resourceList = CurrentOpportunitiesReportGenerationDao.onLoadResourceDetails();
   }
   
   @Command
   @NotifyChange("*")
   public void secondTabClick(){
   	 divVisibility = false;
   	 clientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
	 resourceList = CurrentOpportunitiesReportGenerationDao.onLoadResourceDetails();
   }
    
   @Command
   @NotifyChange("*")
   public void onChangeToDate(){
	   divVisibility = false;
	   pdfDivVisibility = false;
	  
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
		divVisibility = false;
		pdfDivVisibility = false;
	
	   if(currentOpportunitiesReportGenerationBean.getToDate()!=null){
		   if(currentOpportunitiesReportGenerationBean.getToDate().after(currentOpportunitiesReportGenerationBean.getFromDate())){
		     }else {
		    	currentOpportunitiesReportGenerationBean.setToDate(null);
			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }else{
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
   
   @Command
   @NotifyChange("*")
   public void onClickSearchButton(){
	   
	 System.out.println(currentOpportunitiesReportGenerationBean.getBillingStatus());
	   
	 //when to Date not selected
	 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() == null){
	 	Messagebox.show("Select To Date!!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	 }
	 		
	 //case 1
	 if(currentOpportunitiesReportGenerationBean.getFromDate() == null && currentOpportunitiesReportGenerationBean.getToDate() == null
			 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()==null
			   && currentOpportunitiesReportGenerationBean.getBillingStatus()==null){
		 
		 reportList = BillingReportDashBoardDao.loadReportAllData();
		 if(reportList.size()>0){
				divVisibility = true;
				pdfDivVisibility = true;
		 }else{
				Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				divVisibility = false;
				pdfDivVisibility = false;
		 }
	 }
	 
	 //case 2
	 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() != null
			 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()==null
			   && currentOpportunitiesReportGenerationBean.getBillingStatus()==null){
		 
		 reportList = BillingReportDashBoardDao.loadReportBillDateData(Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()),
				 													   Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate()));
		 if(reportList.size()>0){
				divVisibility = true;
				pdfDivVisibility = true;
		 }else{
				Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				divVisibility = false;
				pdfDivVisibility = false;
		 }
	 }
	 
	 //case 3
	 if(currentOpportunitiesReportGenerationBean.getFromDate() == null && currentOpportunitiesReportGenerationBean.getToDate() == null
			 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()!=null
			   && currentOpportunitiesReportGenerationBean.getBillingStatus()==null){
		 
		 reportList = BillingReportDashBoardDao.loadReportDataForClient(currentOpportunitiesReportGenerationBean.getClientBean().getClientId());
		 if(reportList.size()>0){
				divVisibility = true;
				pdfDivVisibility = true;
		 }else{
				Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				divVisibility = false;
				pdfDivVisibility = false;
		 }
	 }
	 
	 //case 4
	 if(currentOpportunitiesReportGenerationBean.getFromDate() == null && currentOpportunitiesReportGenerationBean.getToDate() == null
			 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()==null
			   && currentOpportunitiesReportGenerationBean.getBillingStatus()!=null){
		 
		 if(currentOpportunitiesReportGenerationBean.getBillingStatus().equalsIgnoreCase("All")){
			 reportList = BillingReportDashBoardDao.loadReportAllData(); 
		 }else{
			 reportList = BillingReportDashBoardDao.loadReportBillSelectionData(currentOpportunitiesReportGenerationBean.getBillingStatus()); 
		 }
		 
		 if(reportList.size()>0){
				divVisibility = true;
				pdfDivVisibility = true;
		 }else{
				Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
				divVisibility = false;
				pdfDivVisibility = false;
		 }
	 }
	 
	     //case 5
		 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() != null
				 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()!=null
				   && currentOpportunitiesReportGenerationBean.getBillingStatus()==null){
			 
			 reportList = BillingReportDashBoardDao.loadReportWrtDateAndClient(Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()),
					   													       Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate()),
					   													       currentOpportunitiesReportGenerationBean.getClientBean().getClientId());
			 
			 if(reportList.size()>0){
					divVisibility = true;
					pdfDivVisibility = true;
			 }else{
					Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
					divVisibility = false;
					pdfDivVisibility = false;
			 }
		 }
	 
		 
		//case 6
		 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() != null
				 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()==null
				   && currentOpportunitiesReportGenerationBean.getBillingStatus()!=null){
			 
			 if(currentOpportunitiesReportGenerationBean.getBillingStatus().equalsIgnoreCase("All")){
				reportList = BillingReportDashBoardDao.loadReportBillDateData(Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()),
					   													       Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate())); 
			 }else{
				reportList = BillingReportDashBoardDao.loadReportWrtBillAndDateTotal(Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()),
						       														 Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate()),
						       														 currentOpportunitiesReportGenerationBean.getBillingStatus()); 
			 }
			 
			 if(reportList.size()>0){
					divVisibility = true;
					pdfDivVisibility = true;
			 }else{
					Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
					divVisibility = false;
					pdfDivVisibility = false;
			 }
		 }
		 
		//case 7
		 if(currentOpportunitiesReportGenerationBean.getFromDate() == null && currentOpportunitiesReportGenerationBean.getToDate() == null
				 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()!=null
				   && currentOpportunitiesReportGenerationBean.getBillingStatus()!=null){
			 
			 if(currentOpportunitiesReportGenerationBean.getBillingStatus().equalsIgnoreCase("All")){
				 reportList = BillingReportDashBoardDao.loadReportDataForClient(currentOpportunitiesReportGenerationBean.getClientBean().getClientId());
			 }else{
				reportList = BillingReportDashBoardDao.loadReportDataForClientAndStatusCombination(currentOpportunitiesReportGenerationBean.getClientBean().getClientId(),
						                                                                           currentOpportunitiesReportGenerationBean.getBillingStatus()); 
			 }
			 
			 if(reportList.size()>0){
					divVisibility = true;
					pdfDivVisibility = true;
			 }else{
					Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
					divVisibility = false;
					pdfDivVisibility = false;
			 }
		 }

		 
		 //case 8
		 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() != null
				 && currentOpportunitiesReportGenerationBean.getClientBean().getFullName()!=null
				   && currentOpportunitiesReportGenerationBean.getBillingStatus()!=null){
			 
			 if(currentOpportunitiesReportGenerationBean.getBillingStatus().equalsIgnoreCase("All")){
				 reportList = BillingReportDashBoardDao.loadReportWrtDateAndClient(Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()),
						       													   Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate()),
						       													   currentOpportunitiesReportGenerationBean.getClientBean().getClientId());
			 }else{
				 reportList = BillingReportDashBoardDao.loadReportDataForAllCombination(currentOpportunitiesReportGenerationBean.getClientBean().getClientId(), currentOpportunitiesReportGenerationBean.getBillingStatus(), 
																						Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()),
																						Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate()));
 
			 }
			 
			 if(reportList.size()>0){
					divVisibility = true;
					pdfDivVisibility = true;
			 }else{
					Messagebox.show("No Data Found ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
					divVisibility = false;
					pdfDivVisibility = false;
			 }
		 }
   }
   
   @Command
   @NotifyChange("*")
   public void onClickPdf() throws Exception{
	   String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
	   CurrentOppurtunitiesReportPdf pdf = new CurrentOppurtunitiesReportPdf();
	   try {
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
    	String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
    	PdfTestingViewModel pdf = new PdfTestingViewModel();
 	   try {
 		    if(currentOpportunitiesReportGenerationBean.getResourceFlag().contentEquals("Y")){
 		    	System.out.println("1st method");
 		    	pdf.getDetailsForResource(pdfPath,"ResourceWise A/R Visibility Report",monthSetList);
 		    }else if(currentOpportunitiesReportGenerationBean.getClientFlag().contentEquals("Y")){
 		    	System.out.println("2nd method");
 		    	pdf.getDetails(pdfPath,"ClientWise A/R Visibility Report",monthSetList);
 		    }
 	   } catch (FileNotFoundException e) {
 		   e.printStackTrace();
 	   } catch (DocumentException e) {
 		   e.printStackTrace();
 	   }
    }
   
    
    @Command
    @NotifyChange("*")
    public void onCheckDetailSummary(){
    	secondDivVisibility = false;
    	thirdDivVisibility = false;
    	pdfDivVisibility = false;
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
	public void onChangeFirstClientName(){
	   if(currentOpportunitiesReportGenerationBean.getFirstClientNameSearch() != null){
		  setFirstTabClientList(ResourceAllocationTrackingService.fetchClientDetailsSearchClient(currentOpportunitiesReportGenerationBean.getFirstClientNameSearch()));
	   }
	}
    
    @Command
	@NotifyChange("*")
	public void onClickClientClear(){
		clnBandBox.close();
		currentOpportunitiesReportGenerationBean.getClientInformationBean().setFullName(null);
		currentOpportunitiesReportGenerationBean.getClientInformationBean().setClientId(null);
		currentOpportunitiesReportGenerationBean.setClientNameSearch(null);
		clientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
	}
    
    @Command
   	@NotifyChange("*")
   	public void onClickFirstClientClear(){
   		firstBandBox.close();
   		currentOpportunitiesReportGenerationBean.getClientBean().setFullName(null);
   		currentOpportunitiesReportGenerationBean.getClientBean().setClientId(null);
   		currentOpportunitiesReportGenerationBean.setFirstClientNameSearch(null);
   		firstTabClientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
   	}
    
    @Command
    @NotifyChange("*")
    public void onSelctFirstClientName(){
    	System.out.println(currentOpportunitiesReportGenerationBean.getClientBean().getFullName()+"----"+currentOpportunitiesReportGenerationBean.getClientBean().getClientId());
    }
    
    @Command
	@NotifyChange("*")
	public void onSelctClientName(){
    	
    	currentOpportunitiesReportGenerationBean.setClientFlag("Y");
    	currentOpportunitiesReportGenerationBean.setResourceFlag("N");
    	long grandTotalClient = 0;
    	ArrayList<CurrentOpportunitiesReportGenerationBean> list = null;
    	
    	if(currentOpportunitiesReportGenerationBean.getClientInformationBean().getFullName().equalsIgnoreCase("-ALL-")){
    		list = CurrentOpportunitiesReportGenerationDao.loadOppurtunityWiseReportForClientDaoForAll(currentOpportunitiesReportGenerationBean);
    	}else{
    		list = CurrentOpportunitiesReportGenerationDao.loadOppurtunityWiseReportForClientDao(currentOpportunitiesReportGenerationBean);
    	}
    	
		clnBandBox.close();
		if(secondTabList.size()>0){
			secondTabList.clear();	
		}
		
		if(monthReportBeanList.size()>0){
			monthReportBeanList.clear();
		}
		
		if(monthSetList.size()>0){
			monthSetList.clear();
		}
		
		if(monthSetList.size()>0){
			monthSetList.clear();
		}
		
		//ArrayList<CurrentOpportunitiesReportGenerationBean> list = null;
		//list = CurrentOpportunitiesReportGenerationDao.loadOppurtunityWiseReportForClientDao(currentOpportunitiesReportGenerationBean);
		
		if(finalList.size()>0){
			finalList.clear();
		}
		
	    for(CurrentOpportunitiesReportGenerationBean bean: list){
	    	String name = MonthShowingUtility.fetchresourceName(bean);
	    	bean.getResourceMasterBean().setFullName(name);
	    	MonthShowingUtility.fetchdateWrtRtrackingID(bean);
	    	finalList.add(bean);
	    }
	    
	   /* for(CurrentOpportunitiesReportGenerationBean bean: finalList){
	    	System.out.println(bean.getCurrentOpportunitiesBean().getTentureFromUtil()+"---"+bean.getRtsTrackingDetailsId()+"----"+bean.getCurrentOpportunitiesReportBean().getMonth()+"----"+bean.getResourceMasterBean().getFullName());
	    }*/
	    
	    secondTabList = finalList;
	    
	    if(secondTabList.size()>0){
	    	secondDivVisibility=true;
	    	thirdDivVisibility = false;
		    buttonVisibility = true;	
	    }else{
	    	secondDivVisibility=false;
	    	thirdDivVisibility = false;
		    buttonVisibility = false;
	    	Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
	    }
	    
	    String[] months  = {"january","february","march","april","may","june","july","august","september","october","november","december"};
    	for(int i=1 ; i<13; i++){
    		MonthReportBean reportBean = new MonthReportBean();
    		reportBean.setMonthId(i);
    		reportBean.setMonthName(months[i-1].toUpperCase());
    		reportBean.setStyle(reportBean.getBoldStyle());
    		
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setResourcenameString("Resource Name");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setClientNameString("Client Name");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setChargeOutString("Charge Out Rate(Monthly)");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setMarginString("Margin");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setPercentage("Percentage(%)");
    		
    		int counter = 0;
    		int finalCounter = 0;
    		finalCounter = MonthShowingUtility.countNumberOfMonthPresentInLoop(secondTabList, months[i-1]);
    	
    		ArrayList<CurrentOpportunitiesReportGenerationBean> monthClienBeanList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
    		for(CurrentOpportunitiesReportGenerationBean gen : secondTabList){
    			if(gen.getCurrentOpportunitiesReportBean().getMonth().equalsIgnoreCase(reportBean.getMonthName())){
    				++counter;
    				
    				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
    				reportGenerationBean.getClientInformationBean().setFullName(gen.getClientInformationBean().getFullName());
    				reportGenerationBean.getResourceMasterBean().setFullName(gen.getResourceMasterBean().getFullName());
    				reportGenerationBean.setRtsTrackingDetailsId(gen.getRtsTrackingDetailsId());
    				MonthShowingUtility.MarginSetForTrackingDeatilsID(reportGenerationBean);
    				reportGenerationBean.setBackGround(reportGenerationBean.getBackGroundpaParent());
    				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
    				
    				monthClienBeanList.add(reportGenerationBean);
    				
    			}
    		}
    		
    		reportBean.setCurrentOpportunitiesReportGenerationBeanList(monthClienBeanList);
    		if(reportBean.getCurrentOpportunitiesReportGenerationBeanList().size()>0){
    			monthReportBeanList.add(reportBean);	
    		}
    		if(counter>0){
    			if(counter+1==finalCounter){
        			CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
        			bean.getClientInformationBean().setFullName(null);
        			bean.getResourceMasterBean().setFullName(null);
        			bean.setPercentage(null);
        			bean.getCurrentOpportunitiesBean().setMarginString("TOTAL: "+MonthShowingUtility.calculateTotalMonthWise(monthClienBeanList));
        			//bean.getCurrentOpportunitiesBean().setMargin(Double.longBitsToDouble(MonthShowingUtility.calculateTotalMonthWise(monthClienBeanList)));
        			bean.getCurrentOpportunitiesBean().setMarginTotal(MonthShowingUtility.calculateTotalMonthWise(monthClienBeanList));
        			bean.setStyle(bean.getLighterStyle());
        			monthClienBeanList.add(bean);
        			reportBean.setCurrentOpportunitiesReportGenerationBeanList(monthClienBeanList);
        			monthReportBeanList.add(reportBean);
        		}	
    		}
    	  LinkedHashSet<MonthReportBean> set1 = new LinkedHashSet<MonthReportBean>(monthReportBeanList);
    	  monthSetList = set1;
    	}
    	
    	for(int i =0;i<1;i++){
    		MonthReportBean monthReportBean = new MonthReportBean();
    		monthReportBean.setMonthName("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setResourcenameString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setClientNameString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setChargeOutString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setMarginString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setPercentage("");
    		
    		grandTotalClient = CurrentOpportunitiesReportGenerationDao.calculateGrandTotal(monthSetList);
    		
    		
    		ArrayList<CurrentOpportunitiesReportGenerationBean> list2 = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
    		CurrentOpportunitiesReportGenerationBean opportunitiesReportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
    		opportunitiesReportGenerationBean.getCurrentOpportunitiesBean().setMarginString("GRAND TOTAL: "+grandTotalClient);
    		opportunitiesReportGenerationBean.setStyle(opportunitiesReportGenerationBean.getBoldStyle());
    		list2.add(opportunitiesReportGenerationBean);
    		
    		monthReportBean.setCurrentOpportunitiesReportGenerationBeanList(list2);
    		monthSetList.add(monthReportBean);
    	}
    } 
    
    
    @Command
	@NotifyChange("*")
	public void onSelctResourceName(){
    	
    	currentOpportunitiesReportGenerationBean.setClientFlag("N");
    	currentOpportunitiesReportGenerationBean.setResourceFlag("Y");
    	
    	long grandTotal = 0;
    	
		resourceBandBox.close();
		if(secondTabList.size()>0){
			secondTabList.clear();	
		}
		
		if(monthReportBeanList.size()>0){
			monthReportBeanList.clear();
		}
		
		if(monthSetList.size()>0){
			monthSetList.clear();
		}
		
		
		ArrayList<CurrentOpportunitiesReportGenerationBean> resourceList = null;
		if(currentOpportunitiesReportGenerationBean.getResourceMasterBean().getFullName().equalsIgnoreCase("-ALL-")){
			resourceList = CurrentOpportunitiesReportGenerationDao.loadOppurtunityWiseReportForClientDaoForResourceAll(currentOpportunitiesReportGenerationBean);
		}else{
			resourceList = CurrentOpportunitiesReportGenerationDao.loadOppurtunityWiseReportForClientDaoForResource(currentOpportunitiesReportGenerationBean);
		}
	
		
		if(resourceFinalList.size()>0){
			resourceFinalList.clear();
		}
		
	    for(CurrentOpportunitiesReportGenerationBean bean1: resourceList){
	    	String name = MonthShowingUtility.fetchresourceName(bean1);
	    	bean1.getResourceMasterBean().setFullName(name);
	    	MonthShowingUtility.fetchdateWrtRtrackingID(bean1);
	    	resourceFinalList.add(bean1);
	    }
	    
	    secondTabList = resourceFinalList;
	    if(secondTabList.size()>0){
	    	thirdDivVisibility=true;
	    	secondDivVisibility = false;
		    buttonVisibility = true;
	    }else{
	    	thirdDivVisibility=false;
	    	secondDivVisibility = false;
		    buttonVisibility = false;
	    	Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
	    }
	    
	    String[] months  = {"january","february","march","april","may","june","july","august","september","october","november","december"};
    	for(int i=1 ; i<13; i++){
    		MonthReportBean reportBean = new MonthReportBean();
    		reportBean.setMonthId(i);
    		reportBean.setMonthName(months[i-1].toUpperCase());
    		reportBean.setStyle(reportBean.getBoldStyle());
    		
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setResourcenameString("Resource Name");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setClientNameString("Client Name");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setChargeOutString("Charge Out Rate(Monthly)");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setMarginString("Margin");
    		reportBean.getCurrentOpportunitiesReportGenerationBean().setPercentage("Percentage(%)");
    	
    		int resourceCounter = 0;
    		int resourceFinalCounter = 0;
    		resourceFinalCounter = MonthShowingUtility.countNumberOfMonthPresentInLoop(secondTabList, months[i-1]);
    		
    		ArrayList<CurrentOpportunitiesReportGenerationBean> monthClienBeanList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
    		for(CurrentOpportunitiesReportGenerationBean gen : secondTabList){
    			if(gen.getCurrentOpportunitiesReportBean().getMonth().equalsIgnoreCase(reportBean.getMonthName())){
    
    				++resourceCounter;
    				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
    				
    				reportGenerationBean.getClientInformationBean().setFullName(gen.getClientInformationBean().getFullName());
    				reportGenerationBean.getResourceMasterBean().setFullName(gen.getResourceMasterBean().getFullName());
    				reportGenerationBean.setRtsTrackingDetailsId(gen.getRtsTrackingDetailsId());
    				MonthShowingUtility.MarginSetForTrackingDeatilsID(reportGenerationBean);
    				reportGenerationBean.setBackGround(reportGenerationBean.getBackGroundpaParent());
    				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
    				monthClienBeanList.add(reportGenerationBean);
    				
    			}
    		}
    		reportBean.setCurrentOpportunitiesReportGenerationBeanList(monthClienBeanList);
    		if(reportBean.getCurrentOpportunitiesReportGenerationBeanList().size()>0){
    			monthReportBeanList.add(reportBean);	
    		}
    		
    		if(resourceCounter>0){
    			if(resourceCounter+1==resourceFinalCounter){
        			CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
        			bean.getClientInformationBean().setFullName(null);
        			bean.getResourceMasterBean().setFullName(null);
        			bean.setPercentage(null);
        			bean.getCurrentOpportunitiesBean().setMarginString("TOTAL: "+MonthShowingUtility.calculateTotalMonthWise(monthClienBeanList));
        			bean.getCurrentOpportunitiesBean().setMarginTotal(MonthShowingUtility.calculateTotalMonthWise(monthClienBeanList));
        			bean.setStyle(bean.getLighterStyle());
        			monthClienBeanList.add(bean);
        			reportBean.setCurrentOpportunitiesReportGenerationBeanList(monthClienBeanList);
        			monthReportBeanList.add(reportBean);
        		}	
    		}
    	  LinkedHashSet<MonthReportBean> set2 = new LinkedHashSet<MonthReportBean>(monthReportBeanList);
    	  monthSetList = set2;
    	}
    	
    	for(int i =0;i<1;i++){
    		MonthReportBean monthReportBean = new MonthReportBean();
    		monthReportBean.setMonthName("");
		
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setResourcenameString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setClientNameString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setChargeOutString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setMarginString("");
    		monthReportBean.getCurrentOpportunitiesReportGenerationBean().setPercentage("");
		
    		ArrayList<CurrentOpportunitiesReportGenerationBean> list2 = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
    		CurrentOpportunitiesReportGenerationBean opportunitiesReportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
		
    		grandTotal = CurrentOpportunitiesReportGenerationDao.calculateGrandTotal(monthSetList);
    		
    		opportunitiesReportGenerationBean.getCurrentOpportunitiesBean().setMarginString("GRAND TOTAL: "+grandTotal);
    		opportunitiesReportGenerationBean.setStyle(opportunitiesReportGenerationBean.getBoldStyle());
    		list2.add(opportunitiesReportGenerationBean);
		
    		monthReportBean.setCurrentOpportunitiesReportGenerationBeanList(list2);
    		monthSetList.add(monthReportBean);
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
    	currentOpportunitiesReportGenerationBean.getResourceMasterBean().setFullName(null);
 		clientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
    }
    
    @Command
    @NotifyChange("*")
    public void onCheckResource(){
    	secondDivVisibility= false;
    	buttonVisibility=false;
    	secondTabList.clear();
    	currentOpportunitiesReportGenerationBean.getClientInformationBean().setFullName(null);
 		resourceList = CurrentOpportunitiesReportGenerationDao.onLoadResourceDetails();
    }
    
    @Command
    @NotifyChange("*")
    public void onClickFirstTabClear(){
    	currentOpportunitiesReportGenerationBean.setFromDate(null);
    	currentOpportunitiesReportGenerationBean.setToDate(null);
    	currentOpportunitiesReportGenerationBean.getClientBean().setFullName(null);
    	currentOpportunitiesReportGenerationBean.setBillingStatus(null);
    	
    	firstTabClientList = ResourceAllocationTrackingService.fetchClientDetailsForReport();
    	
    	if(reportList !=null && reportList.size()>0){
    		reportList.clear();
    	}
    	
    	divVisibility = false;
    	pdfDivVisibility = false;
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
	public boolean isThirdDivVisibility() {
		return thirdDivVisibility;
	}
	public void setThirdDivVisibility(boolean thirdDivVisibility) {
		this.thirdDivVisibility = thirdDivVisibility;
	}
	public LinkedHashSet<MonthReportBean> getMonthSetList() {
		return monthSetList;
	}
	public ArrayList<CurrentOpportunitiesReportGenerationBean> getReportList() {
		return reportList;
	}
	public void setReportList(
			ArrayList<CurrentOpportunitiesReportGenerationBean> reportList) {
		this.reportList = reportList;
	}
	public void setMonthSetList(LinkedHashSet<MonthReportBean> monthSetList) {
		this.monthSetList = monthSetList;
	}
	public ArrayList<ClientInformationBean> getFirstTabClientList() {
		return firstTabClientList;
	}
	public void setFirstTabClientList(ArrayList<ClientInformationBean> firstTabClientList) {
		this.firstTabClientList = firstTabClientList;
	}
	public Bandbox getFirstBandBox() {
		return firstBandBox;
	}
	public void setFirstBandBox(Bandbox firstBandBox) {
		this.firstBandBox = firstBandBox;
	}
	public ArrayList<String> getBillingStatusList() {
		return billingStatusList;
    }
    public void setBillingStatusList(ArrayList<String> billingStatusList) {
		this.billingStatusList = billingStatusList;
	}
}