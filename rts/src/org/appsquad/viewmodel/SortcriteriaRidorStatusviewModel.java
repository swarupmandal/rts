package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.SortCriteriaRidorStatusBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.dao.SortCriteriaDao;
import org.appsquad.utility.Dateformatter;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class SortcriteriaRidorStatusviewModel {
  SortCriteriaRidorStatusBean criteriaRidorStatusBean = new SortCriteriaRidorStatusBean();
  
  private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
  private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
  private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
  
  private Connection connection = null;
  private Session sessions = null;
  private String userName ;
  private String userId;

  @AfterCompose
  public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		criteriaRidorStatusBean.setUserId(userId);
		skillList = SortCriteriaDao.onLoadSetDeatils();
		statusList = SortCriteriaDao.onLoadStatus();
		clientList = SortCriteriaDao.onLoadClientDeatils();
	}
  
    @Command
    @NotifyChange("*")
    public void onChangeFromDate(){
    	System.out.println("FROM DATE IS :"+Dateformatter.formatdate(criteriaRidorStatusBean.getFromDate()));
    }
    
    @Command
    @NotifyChange("*")
    public void onChangeToDate(){
    	System.out.println("TO DATE IS :"+Dateformatter.formatdate(criteriaRidorStatusBean.getToDate()));
    }
  
    @Command
    @NotifyChange("*")
    public void onSelectSkillName(){
    	System.out.println("SKILL ID IS :"+criteriaRidorStatusBean.getSkillsetMasterbean().getId());
    }
    
    @Command
    @NotifyChange("*")
    public void onSelectStatusName(){
    	System.out.println("Status ID IS :"+criteriaRidorStatusBean.getStatusMasterBean().getStatusId());
    }
    
    @Command
    @NotifyChange("*")
    public void onSelectClientName(){
    	System.out.println("Client ID IS :"+criteriaRidorStatusBean.getClientInformationBean().getClientId());
    }
    
    @Command
    @NotifyChange("*")
    public void onCheckRepairRedo(){
    	System.out.println("SELECTEDRADIOBUTTON DATA IS :"+criteriaRidorStatusBean.getSelectedRadioButton());
    }
    
    /************************************************************************************************************************************************/
  
	public SortCriteriaRidorStatusBean getCriteriaRidorStatusBean() {
		return criteriaRidorStatusBean;
	}
	
	public void setCriteriaRidorStatusBean(
			SortCriteriaRidorStatusBean criteriaRidorStatusBean) {
		this.criteriaRidorStatusBean = criteriaRidorStatusBean;
	}

	public ArrayList<SkillsetMasterbean> getSkillList() {
		return skillList;
	}

	public void setSkillList(ArrayList<SkillsetMasterbean> skillList) {
		this.skillList = skillList;
	}

	public ArrayList<StatusMasterBean> getStatusList() {
		return statusList;
	}

	public void setStatusList(ArrayList<StatusMasterBean> statusList) {
		this.statusList = statusList;
	}

	public ArrayList<ClientInformationBean> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<ClientInformationBean> clientList) {
		this.clientList = clientList;
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
