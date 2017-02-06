package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.CurrentOpportunitiesDao;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.dao.TaskNameDao;
import org.appsquad.service.TaskNameService;
import org.appsquad.utility.TaskDescriptionReportPdf;
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

public class TaskNameViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	public String dateString = "";
	
	@Wire("#userBb")
	private Bandbox userBandBox;
	
	private TaskNameBean taskBean = new TaskNameBean();
	private ArrayList<UserprofileBean> userList = null;
	private ArrayList<TaskNameBean> taskDetailsList = null;
	private ArrayList<TaskNameBean> newlyCreatedTaskDetailsList = null;
	private ArrayList<TaskNameBean> reportDetailsList = null;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		taskBean.setAssignedByUserId(userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis);
	    taskBean.setCreatedDateStr(sdf.format(date));
	    taskBean.setCreatedDateSql(new java.sql.Date(new java.util.Date().getTime()));
	    loadUserIdList();
	    onLoad();
	}

	public void onLoad(){
		newlyCreatedTaskDetailsList = TaskNameDao.fetchNewlyCreatedTaskDeatils(userId);
		if(newlyCreatedTaskDetailsList.size()>0){
			taskBean.setDivVisibility(true);
		}else{
			taskBean.setDivVisibility(false);
		}
	}
	
	public void loadUserIdList(){
		userList = RoleMasterDao.onLoadUserDeatils();
	}
	
	 @Command
	 @NotifyChange("*")
	 public void onChangeUserId(){
		 if(taskBean.getUserIdSearch()!=null){
			userList = TaskNameService.loadSearchedUserId(taskBean.getUserIdSearch(), userList);
		 }
	 }
	
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickClear(){
		 taskBean.setUserIdSearch(null);
		 loadUserIdList();
		 taskBean.userprofileBean.setUserid(null);
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onSelctUserId(){
		 userBandBox.close();
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickSave(){
		 boolean flagInsert = false; 
		 boolean flagEmailSend = false;
		 if(taskBean.getTaskDescription()!=null && taskBean.getTaskDescription().trim().length()>0){
			 if(taskBean.getTaskName()!=null && taskBean.getTaskName().trim().length()>0){
				 if(taskBean.userprofileBean.getUserid()!=null && taskBean.userprofileBean.getUserid().trim().length()>0){
				    if(taskBean.getScheduledDateSql()!=null){
				    				flagInsert = TaskNameService.saveTaskDetails(taskBean);
				   				 	System.out.println(flagInsert);
				   				 	if(flagInsert){
				   				 	 onLoad();
				   					 String emailId = CurrentOpportunitiesDao.fetchEmailId(taskBean.userprofileBean.getUserid());
				   					 flagEmailSend = SendEmail.validator(emailId);
				   					
				   					 if(flagEmailSend){
				   						SendEmail.generateAndSendEmailForTaskCreation(emailId,taskBean.getTaskName(),taskBean);	
				   					 }else{
				   						System.out.println("APPROVER'S EMAIL ID IS NOT CORRECT. ");
				   					 }
				   					 
				   					 if(flagEmailSend){
				   						 taskBean.setTaskDescription(null);
				   						 taskBean.setTaskName(null);
				   						 taskBean.userprofileBean.setUserid(null);
				   						 taskBean.setVenue(null);
				   						 taskBean.setScheduledDateSql(null);
				   						 taskBean.setStatus(null);
				   						 taskBean.setActualCompletionDateSql(null);
				   						 taskBean.setRemarksOrResults(null);
				   						 userList = RoleMasterDao.onLoadUserDeatils();
				   						 
				   						 taskDetailsList = TaskNameDao.fetchTaskDeatils();
				   						 if(taskDetailsList.size()>0){
				   						   taskBean.setDivVisibility(true);
				   						 }else{
				   						   taskBean.setDivVisibility(true);
				   						 }
				   					  }
				   				   }
				    }else{
				    	Messagebox.show("Schedule Date Can't Be Blank ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				    }
			 }else{
				 Messagebox.show("Select User ID ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			 }
			 }else{
				 Messagebox.show("Task Description Can't Be Blank ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			 } 
		 }else{
			 Messagebox.show("Task Name Can't Be Blank ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		 }
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickTaskDescriptionReport(){
		dateString = TaskNameDao.createdDateString();
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onCheckDetailSummary(){
		 if(taskBean.getSelectedRadioButton().equalsIgnoreCase("1stReport")){
			 taskBean.setInnerRadioGroup(false);
			 reportDetailsList = TaskNameDao.fetchTaskDeatilsForTodayAndTomorrow(dateString);
		 }else if(taskBean.getSelectedRadioButton().equalsIgnoreCase("2ndReport")){
			 taskBean.setInnerRadioGroup(true);
		 }else if(taskBean.getSelectedRadioButton().equalsIgnoreCase("3rdReport")){
			 taskBean.setInnerRadioGroup(false);
			 reportDetailsList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReport();
		 }
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onCheckInnerRadio(){
		 if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("weekReport")){
			 reportDetailsList = TaskNameDao.fetchTaskDeatilsForWeeklyReport();
		 }else if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("monthReport")){
			 reportDetailsList = TaskNameDao.fetchTaskDeatilsForMonthlyReport();
		 }
	 }
	 
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickPdf() throws Exception{
			String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
			TaskDescriptionReportPdf pdf = new TaskDescriptionReportPdf();
			try {
				if(taskBean.getSelectedRadioButton().equalsIgnoreCase("1stReport")){
					if(reportDetailsList.size()>0){
						   pdf.getDetails(pdfPath, taskBean, reportDetailsList, "Activity Scheduled For Today And Tomorrow Report");
					}else {
						Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
					}	
				}else if(taskBean.getSelectedRadioButton().equalsIgnoreCase("3rdReport")){
					if(reportDetailsList.size()>0){
						   pdf.getDetails(pdfPath, taskBean, reportDetailsList, "Schedule Datewise Detail Report");
					}else {
						Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
					}	
				}else if(taskBean.getSelectedRadioButton().equalsIgnoreCase("2ndReport")){
					if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("weekReport")){
						if(reportDetailsList.size()>0){
							   pdf.getDetailsForWeekReport(pdfPath, taskBean, reportDetailsList, "Overdue Activities - Weekly Report");
						}else {
							Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
						}
					}else if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("monthReport")){
						if(reportDetailsList.size()>0){
							   pdf.getDetailsForMonthReport(pdfPath, taskBean, reportDetailsList, "Overdue Activities - Monthly Report");
						}else {
							Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
	} 
	 
    /************************************************************** GETTER AND SETTER METHOD ************************************************************/
	
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
	public TaskNameBean getTaskBean() {
		return taskBean;
	}
	public void setTaskBean(TaskNameBean taskBean) {
		this.taskBean = taskBean;
	}
	public Bandbox getUserBandBox() {
		return userBandBox;
	}
	public void setUserBandBox(Bandbox userBandBox) {
		this.userBandBox = userBandBox;
	}
	public ArrayList<TaskNameBean> getTaskDetailsList() {
		return taskDetailsList;
	}
	public void setTaskDetailsList(ArrayList<TaskNameBean> taskDetailsList) {
		this.taskDetailsList = taskDetailsList;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public ArrayList<TaskNameBean> getReportDetailsList() {
		return reportDetailsList;
	}
	public void setReportDetailsList(ArrayList<TaskNameBean> reportDetailsList) {
		this.reportDetailsList = reportDetailsList;
	}
	public ArrayList<TaskNameBean> getNewlyCreatedTaskDetailsList() {
		return newlyCreatedTaskDetailsList;
	}
	public void setNewlyCreatedTaskDetailsList(
			ArrayList<TaskNameBean> newlyCreatedTaskDetailsList) {
		this.newlyCreatedTaskDetailsList = newlyCreatedTaskDetailsList;
	}
	public ArrayList<UserprofileBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserprofileBean> userList) {
		this.userList = userList;
	}
}
