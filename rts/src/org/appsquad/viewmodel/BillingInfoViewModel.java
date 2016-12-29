package org.appsquad.viewmodel;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.appsquad.bean.CurrentOpportunitiesReportBean;
import org.appsquad.dao.CurrentOpportunitiesReportDao;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class BillingInfoViewModel {
	private Connection connection = null;
    private Session sessions = null;
    private String userName ;
    private String userId;
    private boolean divVisibility = false;
    private String filePath;
    private boolean fileuploaded = false;
    private String fileName;
    private String secondFileName;
    @Wire("#winBillingModal")
	private Window winBillingModalPage;
    private String checkFileName = null;
    
    public ArrayList<String> monthList = null;
    public ArrayList<String> yearList = null;
    
    public CurrentOpportunitiesReportBean  opportunitiesReportBean = null;
    private ArrayList<CurrentOpportunitiesReportBean> reportGridList = new ArrayList<CurrentOpportunitiesReportBean>();
    private ArrayList<CurrentOpportunitiesReportBean> finalReportGridList = new ArrayList<CurrentOpportunitiesReportBean>();
    private ArrayList<Object> fileNameList = new ArrayList<Object>();
    
    private HashSet<CurrentOpportunitiesReportBean> reportUniqueSet = new HashSet<CurrentOpportunitiesReportBean>();

    @AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("ParentBillingObject")CurrentOpportunitiesReportBean bean)throws Exception
    {
		Selectors.wireComponents(view, this, false);	
		sessions = Sessions.getCurrent();
		opportunitiesReportBean = bean;
		reportGridList = CurrentOpportunitiesReportDao.fetchBillingDetailsWrtTrackingDetailsId(opportunitiesReportBean.getTrackingDetailsId());
		if(reportGridList.size()>0){
			System.out.println("fetched");
		}else{
			populateGrid();
		}
		
		monthList = CurrentOpportunitiesReportDao.loadAllMonths();
		yearList = CurrentOpportunitiesReportDao.loadAllYears();
	}
    
    public void populateGrid(){
    	for(int i=1;i<2;i++){
    		reportGridList.add(new CurrentOpportunitiesReportBean());
    	}
    }
    
    @Command
   	@NotifyChange("*")
   	public void onUploadSecondFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext,
   			 @BindingParam("bean") CurrentOpportunitiesReportBean reportBean) throws Exception{
   		UploadEvent uploadEvent = null;
   		Object objUpEvent = bindContext.getTriggerEvent();
   		if (objUpEvent != null && (objUpEvent instanceof UploadEvent)) {
   			 uploadEvent = (UploadEvent) objUpEvent;
   		 }
   		if(uploadEvent != null){
   			Media media = uploadEvent.getMedia();
   			Calendar now = Calendar.getInstance();
   			int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH); // Note: zero based!
            int day = now.get(Calendar.DAY_OF_MONTH);
            //filePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
            reportBean.setSecondFilePath(Executions.getCurrent().getDesktop().getWebApp().getRealPath("/"));
            String yearPath = "Invoice" + "\\" + year + "\\" + month + "\\" + day + "\\";
            //filePath = filePath + yearPath;
            reportBean.setSecondFilePath(reportBean.getSecondFilePath()+yearPath);
            File baseDir = new File(reportBean.getSecondFilePath());
            if (!baseDir.exists()) {
                  baseDir.mkdirs();
             }
            int number = CurrentOpportunitiesReportDao.countLastNumber();
            String name = media.getName();
            String parts[] = name.split("\\.");
            String n1 = parts[0];
            String n2 = parts[1];
            String n3 = n1+"_"+number;
            String finalName = n3+"."+n2;
            Files.copy(new File(reportBean.getSecondFilePath() + finalName), media.getStreamData());
            Messagebox.show("Invoice Copy Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
            reportBean.setSecondFileuploaded(true);
            //filePath = filePath + finalName;
            reportBean.setSecondFilePath(reportBean.getSecondFilePath()+finalName);
            reportBean.setSecondFileName(media.getName());
   	   }
   	}
    
    
    @Command
   	@NotifyChange("*")
   	public void onUploadFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext,
   							@BindingParam("bean")CurrentOpportunitiesReportBean cBean) throws Exception{
    	System.out.println("c: "+cBean.getYear());
    	cBean.setSecondButtonDisable(false);
   		UploadEvent uploadEvent = null;
   		Object objUpEvent = bindContext.getTriggerEvent();
   		if (objUpEvent != null && (objUpEvent instanceof UploadEvent)) {
   			 uploadEvent = (UploadEvent) objUpEvent;
   		 }
   		if(uploadEvent != null){
   		    Media media = uploadEvent.getMedia();
   		    Calendar now = Calendar.getInstance();
   		    int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH); // Note: zero based!
            int day = now.get(Calendar.DAY_OF_MONTH);
            cBean.setFilePath(Executions.getCurrent().getDesktop().getWebApp().getRealPath("/"));;
            String yearPath = "Timesheet" + "\\" + year + "\\" + month + "\\" + day + "\\";
            //filePath = filePath + yearPath;
            cBean.setFilePath(cBean.getFilePath()+yearPath);
            File baseDir = new File(cBean.getFilePath());
            if (!baseDir.exists()) {
                  baseDir.mkdirs();
             }
            int number = CurrentOpportunitiesReportDao.countLastNumber();
            String name = media.getName();
            String parts[] = name.split("\\.");
            String n1 = parts[0];
            String n2 = parts[1];
            String n3 = n1+"_"+number;
            String finalName = n3+"."+n2;
            Files.copy(new File(cBean.getFilePath() + finalName), media.getStreamData());
            Messagebox.show("TimeSheet Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
            //fileuploaded = true;
            cBean.setFileuploaded(true);
            //filePath = filePath + finalName;
            cBean.setFilePath(cBean.getFilePath()+finalName);
            cBean.setFileName(media.getName());
            System.out.println(cBean.getFilePath());
            
   	   }
   	}
    
    @Command
    @NotifyChange("*")
    public void addRow(@BindingParam("bean") CurrentOpportunitiesReportBean reportBean){
    	if(reportBean.getYear()!=null){
    		if(reportBean.getMonth()!=null){
    			if(reportBean.getFilePath()!=null){
    					for(int i =0;i<1;i++){
    			    		reportGridList.add(new CurrentOpportunitiesReportBean());
    			    	}
    			}else{
					Messagebox.show("Upload Time Sheet First", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				}
    		}else{
				Messagebox.show("Select Month", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
    	}else{
			Messagebox.show("Select Year", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
    }
    
    public boolean validateDuplicateRow(){
		int sizeList = 0;
		int sizeSet = 0;
		reportUniqueSet.addAll(reportGridList);
		sizeList = reportGridList.size();
		sizeSet = reportUniqueSet.size();
		System.out.println("List Size: "+ sizeList+"\tSet Size: "+sizeSet);
		if(sizeList == sizeSet){
			return true;
		}else{
			Messagebox.show("Two Rows Having Same Year And Month.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			return false;
		}
	}
    
    @Command
    @NotifyChange("*")
    public void saveBillingDetails(){
    	if(validateDuplicateRow()){
    		int size = 0;
        	int counter = 0;
        	boolean isDelete = false;
        	int checkingCounter = 0;
        	finalReportGridList.clear();
        	checkingCounter = CurrentOpportunitiesReportDao.fetchCountTrackingIdService(opportunitiesReportBean.getTrackingDetailsId());
        	System.out.println("IN BILLING VIEW MODEL COUNTER NUMBER :"+checkingCounter);
        	if(checkingCounter>0){
        		isDelete =CurrentOpportunitiesReportDao.deleteBillingWrtTrackingID(opportunitiesReportBean);
        	}
        	for(CurrentOpportunitiesReportBean bean: reportGridList){
        		if(bean.getYear()!=null && bean.getMonth()!=null && bean.getFilePath()!=null){
        			finalReportGridList.add(bean);
        		}
        	}
        	size = finalReportGridList.size();
        	counter = CurrentOpportunitiesReportDao.insertBillingDetails(finalReportGridList,opportunitiesReportBean.getTrackingDetailsId());
        	if(counter==size){
        		Messagebox.show(" Billing Details Saved Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
        		winBillingModalPage.detach();
        	}else{
        		System.out.println("not inserted all");
        	}		
    	}
    }
    
    @Command
    @NotifyChange("*")
    public void downloadTimesheet(@BindingParam("bean") CurrentOpportunitiesReportBean reportBean) throws Exception{
    	String newPath = "";
    	if(reportBean.getFilePath()!=null){
    		System.out.println(reportBean.getFilePath());
    		newPath=reportBean.getFilePath().replace('\\','/');
    		System.out.println(newPath);
    		/*TimeSheetDownloadUtility.download(newPath);*/
    		Map<String, Object> parentMap = new HashMap<String, Object>();
	    	parentMap.put("fetchPath", newPath);
	    	Window window = (Window) Executions.createComponents("/WEB-INF/view/testingTimesheet.zul", null, parentMap);
			window.doModal();
    	}else{
    		Messagebox.show("No Uploaded File Found.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
    	}
    }
    
    @Command
    @NotifyChange("*")
    public void downloadInvoice(@BindingParam("bean") CurrentOpportunitiesReportBean currentOpportunitiesReportBean) throws Exception{
    	String invoicePath = "";
    	if(currentOpportunitiesReportBean.getSecondFilePath()!=null){
    		System.out.println(currentOpportunitiesReportBean.getSecondFilePath());
    		invoicePath=currentOpportunitiesReportBean.getSecondFilePath().replace('\\','/');
    		System.out.println(invoicePath);
    		Map<String, Object> invoiceMap = new HashMap<String, Object>();
    		invoiceMap.put("fetchOnvoice", invoicePath);
	    	Window window = (Window) Executions.createComponents("/WEB-INF/view/testingInvoice.zul", null, invoiceMap);
			window.doModal();
    	}else{
    		Messagebox.show("No Uploaded File Found.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
    	}
    }
    
    /************************************************************ GETTER AND SETTER METHOD *************************************************************/
    
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
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public boolean isFileuploaded() {
		return fileuploaded;
	}
	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ArrayList<String> getMonthList() {
		return monthList;
	}
	public void setMonthList(ArrayList<String> monthList) {
		this.monthList = monthList;
	}
	public ArrayList<String> getYearList() {
		return yearList;
	}
	public void setYearList(ArrayList<String> yearList) {
		this.yearList = yearList;
	}
	public CurrentOpportunitiesReportBean getOpportunitiesReportBean() {
		return opportunitiesReportBean;
	}
	public void setOpportunitiesReportBean(
			CurrentOpportunitiesReportBean opportunitiesReportBean) {
		this.opportunitiesReportBean = opportunitiesReportBean;
	}
	public ArrayList<CurrentOpportunitiesReportBean> getReportGridList() {
		return reportGridList;
	}
	public void setReportGridList(ArrayList<CurrentOpportunitiesReportBean> reportGridList) {
		this.reportGridList = reportGridList;
	}
	public Window getWinBillingModalPage() {
		return winBillingModalPage;
	}
	public void setWinBillingModalPage(Window winBillingModalPage) {
		this.winBillingModalPage = winBillingModalPage;
	}
	public String getSecondFileName() {
		return secondFileName;
	}
	public void setSecondFileName(String secondFileName) {
		this.secondFileName = secondFileName;
	}
	public ArrayList<Object> getFileNameList() {
		return fileNameList;
	}
	public void setFileNameList(ArrayList<Object> fileNameList) {
		this.fileNameList = fileNameList;
	}
	public String getCheckFileName() {
		return checkFileName;
	}
	public void setCheckFileName(String checkFileName) {
		this.checkFileName = checkFileName;
	}
	public HashSet<CurrentOpportunitiesReportBean> getReportUniqueSet() {
		return reportUniqueSet;
	}
	public void setReportUniqueSet(
			HashSet<CurrentOpportunitiesReportBean> reportUniqueSet) {
		this.reportUniqueSet = reportUniqueSet;
	}
	public ArrayList<CurrentOpportunitiesReportBean> getFinalReportGridList() {
		return finalReportGridList;
	}
	public void setFinalReportGridList(ArrayList<CurrentOpportunitiesReportBean> finalReportGridList) {
		this.finalReportGridList = finalReportGridList;
	}
}
