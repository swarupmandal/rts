package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualRequirementBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.IndividualRequirementDao;
import org.appsquad.dao.SortCriteriaDao;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class IndividualRequirementViewModel {
   IndividualRequirementBean individualRequirementBean = new IndividualRequirementBean();
   
   private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
   private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
   private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
   private ArrayList<RequirementGenerationBean> reqIdList = new ArrayList<RequirementGenerationBean>();
   
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   
   @AfterCompose
   public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
 		Selectors.wireComponents(view, this, false);
 		sessions = Sessions.getCurrent();
 		userId = (String) sessions.getAttribute("userId");
 		individualRequirementBean.setUserId(userId);
 		statusList = SortCriteriaDao.onLoadStatus();
 		reqIdList = IndividualRequirementDao.onLoadReqList();
 	}
   
   @Command
   @NotifyChange("*")
   public void onSelectStatusName(){
   	System.out.println("Status ID IS :"+individualRequirementBean.getStatusMasterBean().getStatusId());
   }
   
   @Command
   @NotifyChange("*")
   public void onCheckRepairRedo(){
   	System.out.println("SELECTEDRADIOBUTTON DATA IS :"+individualRequirementBean.getSelectedRadioButton());
   }
   
   @Command
   @NotifyChange("*")
   public void onSelectReqId(){
   	System.out.println("SELECTEDRADIOBUTTON DATA IS :"+individualRequirementBean.getRequirementGenerationBean().getReq_id());
   }
   
    /********************************************************************************************************************************************/
   
	public IndividualRequirementBean getIndividualRequirementBean() {
		return individualRequirementBean;
	}
	public void setIndividualRequirementBean(
			IndividualRequirementBean individualRequirementBean) {
		this.individualRequirementBean = individualRequirementBean;
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
	public ArrayList<RequirementGenerationBean> getReqIdList() {
		return reqIdList;
	}
	public void setReqIdList(ArrayList<RequirementGenerationBean> reqIdList) {
		this.reqIdList = reqIdList;
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
