package org.appsquad.viewmodel;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.dao.TaskNameDao;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

public class TaskListUpdateViewModel {

	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	public String dateString = "";
	private TaskNameBean taskBean = new TaskNameBean();
	private ArrayList<TaskNameBean> taskDetailsList = null;
	private ArrayList<UserprofileBean> userList = new ArrayList<UserprofileBean>();
	
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
	    userList = RoleMasterDao.onLoadUserDeatils();
	    onLoad();
	}

	@GlobalCommand
	@NotifyChange("*")
	public void globalReload(){
		onLoad();
	}
	
	public void onLoad(){
		taskDetailsList = TaskNameDao.fetchAssignedTaskDeatils(userId);
		if(taskDetailsList.size()>0){
			taskBean.setDivVisibility(true);
		}else{
			taskBean.setDivVisibility(true);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") TaskNameBean taskNameBean){
		Map<String, TaskNameBean> modifyEntryDataMap = new HashMap<String, TaskNameBean>();
		modifyEntryDataMap.put("parentData", taskNameBean);
		Window win = (Window) Executions.createComponents("WEB-INF/view/taskmodify.zul", null, modifyEntryDataMap);
		
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
	
	
}
