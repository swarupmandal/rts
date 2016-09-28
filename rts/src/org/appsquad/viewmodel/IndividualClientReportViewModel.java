package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
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

public class IndividualClientReportViewModel {
	IndividualClientReportBean individualClientReportBean = new IndividualClientReportBean();
	
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
			individualClientReportBean.setUserId(userId);
			skillList = SortCriteriaDao.onLoadSetDeatils();
			statusList = SortCriteriaDao.onLoadStatus();
			clientList = SortCriteriaDao.onLoadClientDeatils();
		}  
	  
	    @Command
	    @NotifyChange("*")
	    public void onChangeFromDate(){
	    	System.out.println("FROM DATE IS :"+Dateformatter.formatdate(individualClientReportBean.getFromDate()));
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onChangeToDate(){
	    	System.out.println("TO DATE IS :"+Dateformatter.formatdate(individualClientReportBean.getToDate()));
	    }
	  
	    @Command
	    @NotifyChange("*")
	    public void onSelectSkillName(){
	    	System.out.println("SKILL ID IS :"+individualClientReportBean.getSkillsetMasterbean().getId());
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onSelectStatusName(){
	    	System.out.println("Status ID IS :"+individualClientReportBean.getStatusMasterBean().getStatusId());
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onSelectClientName(){
	    	System.out.println("Client ID IS :"+individualClientReportBean.getClientInformationBean().getClientId());
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onCheckRepairRedo(){
	    	System.out.println("SELECTEDRADIOBUTTON DATA IS :"+individualClientReportBean.getSelectedRadioButton());
	    }
	  
	/***************************************************************************************************************************************************/
	  
	public IndividualClientReportBean getIndividualClientBean() {
		return individualClientReportBean;
	}
	public void setIndividualClientBean(IndividualClientReportBean individualClientReportBean) {
		this.individualClientReportBean = individualClientReportBean;
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
