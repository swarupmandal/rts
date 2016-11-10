package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.service.CurrentOpportunitiesService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class CurrentOpportunitiesViewModel {
	CurrentOpportunitiesBean currentOpportunitiesBean = new CurrentOpportunitiesBean(); 
	UserprofileBean userprofileBean = new UserprofileBean();
	
	private ArrayList<CurrentOpportunitiesBean> currentOpportunitiesBeanList = new ArrayList<CurrentOpportunitiesBean>();
	private ArrayList<UserprofileBean> userprofileBeanList = new ArrayList<UserprofileBean>();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		System.out.println("USER ID:"+userId);
		currentOpportunitiesBeanList = CurrentOpportunitiesService.loadCurrentOpportunityDetails();
	}
	
	
	/*********************************************************************************************************************************************/
	
	public CurrentOpportunitiesBean getCurrentOpportunitiesBean() {
		return currentOpportunitiesBean;
	}
	public void setCurrentOpportunitiesBean(
			CurrentOpportunitiesBean currentOpportunitiesBean) {
		this.currentOpportunitiesBean = currentOpportunitiesBean;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public ArrayList<CurrentOpportunitiesBean> getCurrentOpportunitiesBeanList() {
		return currentOpportunitiesBeanList;
	}
	public void setCurrentOpportunitiesBeanList(
			ArrayList<CurrentOpportunitiesBean> currentOpportunitiesBeanList) {
		this.currentOpportunitiesBeanList = currentOpportunitiesBeanList;
	}
	public ArrayList<UserprofileBean> getUserprofileBeanList() {
		return userprofileBeanList;
	}
	public void setUserprofileBeanList(
			ArrayList<UserprofileBean> userprofileBeanList) {
		this.userprofileBeanList = userprofileBeanList;
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
}
