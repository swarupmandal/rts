package org.appsquad.viewmodel;

import java.sql.Connection;
import java.text.SimpleDateFormat;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.dao.TaskStatusDao;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class TodayTomorrowModalViewModel 
{
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winModal")
	private Window winModal ;
	private TaskNameBean taskNameBean = null;
	
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("parentModalData")TaskNameBean modifyTaskBean) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		taskNameBean = modifyTaskBean;
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
	public Window getWinModal() {
		return winModal;
	}
	public void setWinModal(Window winModal) {
		this.winModal = winModal;
	}
	public TaskNameBean getTaskNameBean() {
		return taskNameBean;
	}
	public void setTaskNameBean(TaskNameBean taskNameBean) {
		this.taskNameBean = taskNameBean;
	}
}
