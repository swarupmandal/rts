package org.appsquad.viewmodel;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.dao.TaskNameDao;
import org.appsquad.service.TaskNameService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;

public class TaskNameViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	
	@Wire("#userBb")
	private Bandbox userBandBox;
	
	private TaskNameBean taskBean = new TaskNameBean();
	private ArrayList<UserprofileBean> userList = new ArrayList<UserprofileBean>();
	private ArrayList<TaskNameBean> taskDetailsList = null;
	
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
	    userList = RoleMasterDao.onLoadUserDeatilsForTask(userId);
	    taskDetailsList = TaskNameDao.fetchTaskDeatils();
	    if(taskDetailsList.size()>0){
	    	taskBean.setDivVisibility(true);
	    }else{
	    	taskBean.setDivVisibility(true);
	    }
	}

	 @Command
	 @NotifyChange("*")
	 public void onChangeUserId(){
		 if(taskBean.getUserIdSearch()!=null){
			 userList = RoleMasterDao.onLoadUserDeatilsForTaskScreen(taskBean.getUserIdSearch(),userId);
		 }
	 }
	
	 @Command
	 @NotifyChange("*")
	 public void onSelctUserId(){
		 userBandBox.close();
		 System.out.println(taskBean.userprofileBean.getUserid());
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickSave(){
		 boolean flagInsert = false; 
		 if(taskBean.getTaskName()!=null && taskBean.getTaskName().trim().length()>0){
			 if(taskBean.userprofileBean.getUserid()!=null && taskBean.userprofileBean.getUserid().trim().length()>0){
				 flagInsert = TaskNameService.saveTaskDetails(taskBean);
				 System.out.println(flagInsert);
				 if(flagInsert){
					 taskBean.setTaskName(null);
					 taskBean.userprofileBean.setUserid(null);
					 userList = RoleMasterDao.onLoadUserDeatilsForTask(userId);
					 
					 taskDetailsList = TaskNameDao.fetchTaskDeatils();
					 if(taskDetailsList.size()>0){
					   taskBean.setDivVisibility(true);
					 }else{
					   taskBean.setDivVisibility(true);
					 }
				 }
			 }else{
				 Messagebox.show("Select User ID ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			 }
		 }else{
			 Messagebox.show("Task Name Can't Be Blank ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
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
	public ArrayList<UserprofileBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserprofileBean> userList) {
		this.userList = userList;
	}
	public ArrayList<TaskNameBean> getTaskDetailsList() {
		return taskDetailsList;
	}
	public void setTaskDetailsList(ArrayList<TaskNameBean> taskDetailsList) {
		this.taskDetailsList = taskDetailsList;
	}
}
