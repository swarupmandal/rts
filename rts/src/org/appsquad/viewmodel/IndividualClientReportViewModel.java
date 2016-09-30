package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.dao.SortCriteriaDao;
import org.appsquad.service.IndividualClientReportService;
import org.appsquad.service.RequirementGenerationService;
import org.appsquad.service.ResourceAllocationTrackingService;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;

public class IndividualClientReportViewModel {
	
	  IndividualClientReportBean individualClientReportBean = new IndividualClientReportBean();
	
	  
	  private ArrayList<IndividualClientReportBean> reportBeanList = new ArrayList<IndividualClientReportBean>();
	  private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
	  private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
	  private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
	  
	  private Connection connection = null;
	  private Session sessions = null;
	  private String userName ;
	  private String userId;
	  
	  @Wire("#clname")
	  private Bandbox clnBandBox;
		
	  @Wire("#skSt")
	  private Bandbox skStBandBox;
	  
	  @AfterCompose
	  public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
			Selectors.wireComponents(view, this, false);
			sessions = Sessions.getCurrent();
			userId = (String) sessions.getAttribute("userId");
			individualClientReportBean.setUserId(userId);
			skillList = RequirementGenerationService.fetchSkillSetList();
			statusList = ResourceMasterDao.onLoadStatus();
			clientList = ResourceAllocationTrackingService.fetchClientDetails();
		}  
	  
	   @Command
	   @NotifyChange("*")
	   public void onChangeClientName(){
		   System.out.println("--");
	   }
	    
	   
	   @Command
	   @NotifyChange("*")
	   public void onSelctClientName(){
		   clnBandBox.close();
	   }
	    
	   
	   @Command
	   @NotifyChange("*")
	   public void onCheckRepairRedo(){
		   
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onSelectStatusName(){
		   
	   }
	
	   @Command
	   @NotifyChange("*")
	   public void onChangeSkillName(){
		   
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onSelctSkillName(){
		   skStBandBox.close();
	   }
	   
	   @Command
	   @NotifyChange("*")
	   public void onClickSearch(){
		   reportBeanList = IndividualClientReportService.loadRidList(individualClientReportBean.clientInformationBean.getClientId());
		   
		   System.out.println("List Size >>> >> > " +reportBeanList.size());
		   for(IndividualClientReportBean rrb : reportBeanList){
			   
			   //System.out.print(">> " + rrb.getrIdDateLabel() +" - " + rrb.getReqId() + rrb.getrIdDateLabel() + " : " + rrb.getCreatedDateValue() + " - " + rrb.getSkillSetLabel() +" - " + rrb.getSkillSet() + " >> ");
			   //System.out.println("Test 1 "+rrb.getTestId1() + " - Test 2 " + rrb.getTestId2());
			   //System.out.println("________________________________________________________________________________________");
			   
		   }
		   
		   
		   
		   
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

	public IndividualClientReportBean getIndividualClientReportBean() {
		return individualClientReportBean;
	}

	public void setIndividualClientReportBean(
			IndividualClientReportBean individualClientReportBean) {
		this.individualClientReportBean = individualClientReportBean;
	}

	public ArrayList<IndividualClientReportBean> getReportBeanList() {
		return reportBeanList;
	}

	public void setReportBeanList(
			ArrayList<IndividualClientReportBean> reportBeanList) {
		this.reportBeanList = reportBeanList;
	}
}
