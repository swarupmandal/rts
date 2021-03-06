package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.CreationDateWiseReportDao;
import org.appsquad.dao.ScheduleDateWiseDetailsDao;
import org.appsquad.dao.TaskNameDao;
import org.appsquad.utility.Dateformatter;
import org.appsquad.utility.TaskDescriptionReportPdf;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import org.zkoss.zul.Window;

import com.itextpdf.text.DocumentException;

public class CreationDateWiseReportViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#userB")
	private Bandbox userBox;
	
	@Wire("#userC")
	private Bandbox anotherBox;
	
	private ArrayList<UserprofileBean> assignByUserList = null;
	private ArrayList<UserprofileBean> assignToUserList = null;
	private ArrayList<TaskNameBean> scheduleDateWiseReportList = null;
	private boolean divVisibility = false;
	private boolean buttonVisibility = false;
	
	private UserprofileBean bean = new UserprofileBean();
	private TaskNameBean taskBean = new TaskNameBean();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		assignByUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatils();
		assignToUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsForAssignTo();
		scheduleDateWiseReportList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReport();
		if(scheduleDateWiseReportList.size()>0){
			divVisibility = true;
			buttonVisibility = true;
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeFromDate(){
		   if(taskBean.getToDate()!=null){
			   if(taskBean.getToDate().after(taskBean.getFromDate())){
			     }else {
			    	 taskBean.setToDate(null);
				    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
			   }
		   }else{
			   System.out.println("nothing.");
		   }
	}
	
	 @Command
	 @NotifyChange("*")
	 public void onChangeToDate(){
	 	   if(taskBean.getFromDate() != null){
	 		   if(taskBean.getToDate().after(taskBean.getFromDate()) ||
	 				  taskBean.getToDate().compareTo(taskBean.getFromDate()) ==0){
	 		     }else {
	 		    	taskBean.setToDate(null);
	 			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
	 		   }
	 	   }else {
	 		  taskBean.setToDate(null);
	 		   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
	 	   }
	    }
	
	@Command
	@NotifyChange("*")
	public void onClickSearchButton(){
		scheduleDateWiseReportList.clear();
		divVisibility = false;
		buttonVisibility = false;
		if(taskBean.getFromDate()==null && taskBean.getToDate()==null){
			divVisibility = true;
			buttonVisibility = true;
			Messagebox.show("Select Creation From Date & To Date", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}else if(taskBean.getFromDate()!=null && taskBean.getToDate()==null){
			divVisibility = true;
			buttonVisibility = true;
			Messagebox.show("Select Creation To Date", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}else if(taskBean.getFromDate()==null && taskBean.getToDate()!=null){
			divVisibility = true;
			buttonVisibility = true;
			Messagebox.show("Select Creation From Date", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}else if(taskBean.getFromDate()!=null && taskBean.getToDate()!=null && 
				taskBean.userprofileBean2.getAnotherUserId()==null && taskBean.userprofileBean.getUserid()==null){
			scheduleDateWiseReportList = CreationDateWiseReportDao.fetchTaskDeatilsForCreationDateWiseReportFordateRange(Dateformatter.sqlDate(taskBean.getFromDate()), 
					   																					   Dateformatter.sqlDate(taskBean.getToDate()));
				if(scheduleDateWiseReportList.size()>0){
					divVisibility = true;
					buttonVisibility = true;
				}else{
					Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				}
		}else if(taskBean.getFromDate()!=null && taskBean.getToDate()!=null && taskBean.getUserprofileBean().getUserid()!=null
				 && taskBean.getUserprofileBean2().getAnotherUserId()!=null){
			scheduleDateWiseReportList = CreationDateWiseReportDao.fetchTaskDeatilsForCreationDateWiseReportFordateRangeWithByTo(Dateformatter.sqlDate(taskBean.getFromDate()), Dateformatter.sqlDate(taskBean.getToDate()), taskBean.getUserprofileBean().getUserid(), taskBean.getUserprofileBean2().getAnotherUserId());
		    if(scheduleDateWiseReportList.size()>0){
		    	divVisibility = true;
		    	buttonVisibility = true;
		    }else{
		    	Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		    }
		}else if(taskBean.userprofileBean.getUserid()!=null && taskBean.userprofileBean2.getAnotherUserId()==null
				&& taskBean.getFromDate()!=null && taskBean.getToDate()!=null){
			System.out.println("1st method");
			scheduleDateWiseReportList = CreationDateWiseReportDao.fetchTaskDeatilsForCreationDateWiseReportForAssignerBy(taskBean.userprofileBean.getUserid(),Dateformatter.sqlDate(taskBean.getFromDate()),Dateformatter.sqlDate(taskBean.getToDate()));
			if(scheduleDateWiseReportList.size()>0){
				divVisibility = true;
				buttonVisibility = true;
			}else{
				Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else if(taskBean.userprofileBean2.getAnotherUserId()!=null && taskBean.userprofileBean.getUserid()==null
				&& taskBean.getFromDate()!=null && taskBean.getToDate()!=null){
			System.out.println("2nd method");
			scheduleDateWiseReportList = CreationDateWiseReportDao.fetchTaskDeatilsForCreationDateWiseReportForAssignerTo(taskBean.userprofileBean2.getAnotherUserId(),Dateformatter.sqlDate(taskBean.getFromDate()),Dateformatter.sqlDate(taskBean.getToDate()));
			if(scheduleDateWiseReportList.size()>0){
				divVisibility = true;
				buttonVisibility = true;
			}else{
				Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickPdf() throws Exception{
			String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
			TaskDescriptionReportPdf pdf = new TaskDescriptionReportPdf();
			try {
				if(scheduleDateWiseReportList.size()>0){
				   pdf.getDetails(pdfPath, taskBean, scheduleDateWiseReportList, "Creation Datewise Detail Report");
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
	public void onClickClear(){
		scheduleDateWiseReportList.clear();
		taskBean.userprofileBean.setUserid(null);
		taskBean.userprofileBean2.setAnotherUserId(null);
		taskBean.setFromDate(null);
		taskBean.setToDate(null);
		assignByUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatils();
		assignToUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsForAssignTo();
		scheduleDateWiseReportList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReport();
		if(scheduleDateWiseReportList.size()>0){
			divVisibility = true;
			buttonVisibility = true;
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeUserId(){
		 if(taskBean.getUserIdSearch()!=null){
			 assignByUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsBy(taskBean.getUserIdSearch());
		 }
	 }
	
	@Command
	@NotifyChange("*")
	public void onSelctAssignBy(){
		 userBox.close();
		 System.out.println(taskBean.userprofileBean.getUserid());
	 }
	
	@Command
	@NotifyChange("*")
	public void AnotherUserId(){
		 anotherBox.close();
		 System.out.println(taskBean.userprofileBean2.getAnotherUserId());
	 }
	
	@Command
	@NotifyChange("*")
	public void onChangeAnotherUserId(){
		 if(taskBean.getAnotherUserIdSearch()!=null){
			 assignToUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsForAssignToSearch(taskBean.getAnotherUserIdSearch());
		 }
	 }
	
	@Command
	@NotifyChange("*")
	public void onClickToShowDescription(@BindingParam("bean") TaskNameBean nameBean){
		Map<String, TaskNameBean> entryDataMap = new HashMap<String, TaskNameBean>();
	    entryDataMap.put("parentModalData", nameBean);
		Window win = (Window) Executions.createComponents("WEB-INF/view/activityScheduledForTodayTomorrowModal.zul", null, entryDataMap);	
		win.doModal();
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
	public ArrayList<UserprofileBean> getAssignByUserList() {
		return assignByUserList;
	}
	public void setAssignByUserList(ArrayList<UserprofileBean> assignByUserList) {
		this.assignByUserList = assignByUserList;
	}
	public ArrayList<UserprofileBean> getAssignToUserList() {
		return assignToUserList;
	}
	public void setAssignToUserList(ArrayList<UserprofileBean> assignToUserList) {
		this.assignToUserList = assignToUserList;
	}
	public ArrayList<TaskNameBean> getScheduleDateWiseReportList() {
		return scheduleDateWiseReportList;
	}
	public void setScheduleDateWiseReportList(
			ArrayList<TaskNameBean> scheduleDateWiseReportList) {
		this.scheduleDateWiseReportList = scheduleDateWiseReportList;
	}
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public boolean isButtonVisibility() {
		return buttonVisibility;
	}
	public void setButtonVisibility(boolean buttonVisibility) {
		this.buttonVisibility = buttonVisibility;
	}
	public UserprofileBean getBean() {
		return bean;
	}
	public void setBean(UserprofileBean bean) {
		this.bean = bean;
	}
	public TaskNameBean getTaskBean() {
		return taskBean;
	}
	public Bandbox getUserBox() {
		return userBox;
	}
	public void setUserBox(Bandbox userBox) {
		this.userBox = userBox;
	}
	public Bandbox getAnotherBox() {
		return anotherBox;
	}
	public void setAnotherBox(Bandbox anotherBox) {
		this.anotherBox = anotherBox;
	}
	public void setTaskBean(TaskNameBean taskBean) {
		this.taskBean = taskBean;
	}
}
