package org.appsquad.viewmodel;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import org.appsquad.bean.CurrentOpportunitiesReportBean;
import org.appsquad.dao.CurrentOpportunitiesReportDao;
import org.appsquad.service.CurrentOpportunitiesReportService;
import org.appsquad.service.LogAuditServiceClass;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

public class CurrentOpportunitiesReportViewModel {
	private Connection connection = null;
    private Session sessions = null;
    private String userName ;
    private String userId;
    private boolean divVisibility = false;
    private String filePath;
    private boolean fileuploaded = false;
    private String fileName;
    
    public CurrentOpportunitiesReportBean  opportunitiesReportBean = new CurrentOpportunitiesReportBean();
    
    public ArrayList<CurrentOpportunitiesReportBean> reportList = null;
    public ArrayList<String> monthList = null;
    public ArrayList<String> yearList = null;
    
    @AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		System.out.println("USER ID IS :"+userId.toUpperCase());
		reportList = CurrentOpportunitiesReportDao.loadTrackingDetails();
		monthList = CurrentOpportunitiesReportDao.loadAllMonths();
		yearList = CurrentOpportunitiesReportDao.loadAllYears();
	}
    
    @Command
    @NotifyChange("*")
    public void onSelectYear(){
    	System.out.println("CLICKED YEAR IS :"+opportunitiesReportBean.getYear());
    }
    
    @Command
    @NotifyChange("*")
    public void onSelectMonth(){
    	System.out.println("CLICKED Month IS :"+opportunitiesReportBean.getMonth());
    }
    
    @Command
    @NotifyChange("*")
    public void save(@BindingParam("bean") CurrentOpportunitiesReportBean reportBean){
    	boolean flagInsert = false;
    	boolean flagupdate = false;
    	boolean flagLogInsert = false;
    	boolean flagDelete = false;
    	int count = 0;
    	count = CurrentOpportunitiesReportService.fetchCountNumberITrackingDetailsTable(reportBean.getTrackingDetailsId());
    	System.out.println(count);
    	if(reportBean.getPaid()!=null){
    		if(count==0){
    			reportBean.setTimesheetPath(opportunitiesReportBean.getTimesheetPath());
    			reportBean.setInvoiceCopyPath(opportunitiesReportBean.getInvoiceCopyPath());
    			flagInsert = CurrentOpportunitiesReportService.insertTrackingDetails(reportBean);
    			if(flagInsert && reportBean.getPaid().equalsIgnoreCase("Yes")){
    				flagupdate = CurrentOpportunitiesReportService.updateTrackingDetails(reportBean);
    			}
    			System.out.println(flagupdate);
    		}else{
    			flagDelete =CurrentOpportunitiesReportDao.deleteRoleData(reportBean);
    			if(flagDelete){
    				flagInsert = CurrentOpportunitiesReportService.insertTrackingDetails(reportBean);
    				if(flagInsert && reportBean.getPaid().equalsIgnoreCase("Yes")){
        				flagupdate = CurrentOpportunitiesReportService.updateTrackingDetails(reportBean);
        			}
    				System.out.println(flagupdate);
    			}
    		}
    		System.out.println(flagInsert);	
    		if(flagInsert){
    			reportBean.setOperation("INSERT");
    			reportBean.setOperationId(1);
    			reportBean.setSessionUserId(userId);
    			Calendar calendar = Calendar.getInstance();
    		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
    			System.out.println("CREATION DATE :"+currentDate);
    			flagLogInsert = LogAuditServiceClass.insertIntoLogTable(reportBean.getMainScreenName(), reportBean.getChileScreenName(), 
												    					reportBean.getSessionUserId(), reportBean.getOperation(),currentDate,
												    					reportBean.getOperationId());
    			System.out.println("flagLogInsert Is:"+flagLogInsert);
    			reportList = CurrentOpportunitiesReportDao.loadTrackingDetails();
    		}
    	}else{
    		Messagebox.show("Select Paid Status ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
    	}
    }
    
    @Command
	@NotifyChange("*")
	public void onUploadFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext) throws Exception{
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
         filePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
         String yearPath = "PDFs" + "\\" + year + "\\" + month + "\\" + day + "\\";
         filePath = filePath + yearPath;
         File baseDir = new File(filePath);
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
         Files.copy(new File(filePath + finalName), media.getStreamData());
         Messagebox.show("TimeSheet Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
         fileuploaded = true;
         fileName = media.getName();
         filePath = filePath + finalName;
         opportunitiesReportBean.setTimesheetPath(filePath);
	   }
	}
    
    @Command
   	@NotifyChange("*")
   	public void onUploadSecondFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext) throws Exception{
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
            filePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
            String yearPath = "PDFs" + "\\" + year + "\\" + month + "\\" + day + "\\";
            filePath = filePath + yearPath;
            File baseDir = new File(filePath);
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
            Files.copy(new File(filePath + finalName), media.getStreamData());
            Messagebox.show("Invoice Copy Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
            fileuploaded = true;
            fileName = media.getName();
            filePath = filePath + finalName;
            opportunitiesReportBean.setInvoiceCopyPath(filePath);
   	   }
   	}
    
    /************************************************* GETTER AND SETTER METHOD ************************************************************************/
    
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
	public CurrentOpportunitiesReportBean getOpportunitiesReportBean() {
		return opportunitiesReportBean;
	}
	public void setOpportunitiesReportBean(
			CurrentOpportunitiesReportBean opportunitiesReportBean) {
		this.opportunitiesReportBean = opportunitiesReportBean;
	}
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public ArrayList<CurrentOpportunitiesReportBean> getReportList() {
		return reportList;
	}
	public void setReportList(ArrayList<CurrentOpportunitiesReportBean> reportList) {
		this.reportList = reportList;
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
}
