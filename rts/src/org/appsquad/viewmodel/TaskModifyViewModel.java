package org.appsquad.viewmodel;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.TaskStatusBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.dao.TaskModifyDao;
import org.appsquad.dao.TaskStatusDao;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class TaskModifyViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	public String dateString = "";
	@Wire("#win")
	private Window win ;
	private TaskNameBean taskBean = new TaskNameBean();
	private ArrayList<TaskNameBean> taskDetailsList = null;
	private ArrayList<UserprofileBean> userList = new ArrayList<UserprofileBean>();
	private TaskStatusBean taskStatusBean = new TaskStatusBean();
	private ArrayList<TaskStatusBean> taskStatusBeanList = new ArrayList<TaskStatusBean>();
	private boolean updateButtonDisability = false;
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("parentData")TaskNameBean modifyTaskBean) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		//taskBean.setAssignedByUserId(userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis);
	    //taskBean.setCreatedDateStr(sdf.format(date));
	    userList = RoleMasterDao.onLoadUserDeatils();
	    taskBean = modifyTaskBean;
	   //taskBean.setAssignedByUserId(userId);
	    taskBean.setTaskStatusBeanList(TaskStatusDao.loadAllTaskStatus());
	   if(taskBean.getTaskStatusBean().getTaskStatusId() == 3){
		   updateButtonDisability = true;
	   }
	}

	@Command
	@NotifyChange("*")
	public void onClickUpdate(){
		System.out.println("task status id:"+taskBean.getTaskStatusBean().getTaskStatusId());
		System.out.println("completion date:: "+taskBean.getActualCompletionDate());
		System.out.println(taskBean.getRemarksOrResults());
		System.out.println("c"+taskBean.getAssignedTo());
		System.out.println("s"+taskBean.getRtsTaskId());
		if(taskBean.getTaskStatusBean().getTaskStatusId() == 3){
		   if(	isValidCompletionData() ){
			   TaskModifyDao.updateTask(taskBean);
			   Messagebox.show("Task successfully updated!","Successful information",Messagebox.OK,Messagebox.INFORMATION);
			   win.detach();
			   BindUtils.postGlobalCommand(null, null, "globalReload", null);
		   }
		}else{
			TaskModifyDao.updateTask(taskBean);
			Messagebox.show("Task successfully updated!","Successful information",Messagebox.OK,Messagebox.INFORMATION);
			win.detach();
			BindUtils.postGlobalCommand(null, null, "globalReload", null);
		}
	}
	
	public boolean isValidCompletionData(){
		if(taskBean.getActualCompletionDate()!=null){
			if(taskBean.getRemarksOrResults()!=null){
				return true;
			}else{
				Messagebox.show("Remarks/Results required!","Required information",Messagebox.OK,Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("Completion date required!","Required information",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseModify(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		win.detach();
		BindUtils.postGlobalCommand(null, null, "globalReload", null);
	}
	
	
	/***************************************************************** GETTER AND SETTER ****************************************************************/
	
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
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public TaskNameBean getTaskBean() {
		return taskBean;
	}
	public void setTaskBean(TaskNameBean taskBean) {
		this.taskBean = taskBean;
	}
	public ArrayList<TaskNameBean> getTaskDetailsList() {
		return taskDetailsList;
	}
	public void setTaskDetailsList(ArrayList<TaskNameBean> taskDetailsList) {
		this.taskDetailsList = taskDetailsList;
	}
	public ArrayList<UserprofileBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserprofileBean> userList) {
		this.userList = userList;
	}
	public TaskStatusBean getTaskStatusBean() {
		return taskStatusBean;
	}
	public void setTaskStatusBean(TaskStatusBean taskStatusBean) {
		this.taskStatusBean = taskStatusBean;
	}
	public ArrayList<TaskStatusBean> getTaskStatusBeanList() {
		return taskStatusBeanList;
	}
	public void setTaskStatusBeanList(ArrayList<TaskStatusBean> taskStatusBeanList) {
		this.taskStatusBeanList = taskStatusBeanList;
	}
	public Window getWin() {
		return win;
	}
	public void setWin(Window win) {
		this.win = win;
	}
	public boolean isUpdateButtonDisability() {
		return updateButtonDisability;
	}
	public void setUpdateButtonDisability(boolean updateButtonDisability) {
		this.updateButtonDisability = updateButtonDisability;
	}
}
