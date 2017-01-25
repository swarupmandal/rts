package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.ClientUserAssignBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ClientUserAssignDao;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.service.ClientUserAssignService;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.ResourceAllocationTrackingService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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

public class ClientUserAssignViewModel {
	 private Connection connection = null;
	 private Session sessions = null;
	 private String userName ;
	 private String userId;
	 @Wire("#clntBb")
	 private Bandbox clnBandBox;
	 @Wire("#userBb")
	 private Bandbox userBandBox;
	 
	 private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
	 private ArrayList<UserprofileBean> userList = new ArrayList<UserprofileBean>();
	 private ArrayList<ClientUserAssignBean> assignList = new ArrayList<ClientUserAssignBean>();
	 
	 private ClientUserAssignBean clientUserAssignBean = new ClientUserAssignBean();
	 
	 @AfterCompose
	 public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
			Selectors.wireComponents(view, this, false);
			sessions = Sessions.getCurrent();
			userId = (String) sessions.getAttribute("userId");
			clientUserAssignBean.setSessionUserId(userId);
			clientList = ResourceAllocationTrackingService.fetchClientDetails();
			userList = RoleMasterDao.onLoadUserDeatilsForApprover();
			assignList = ClientUserAssignDao.onLoadAssignDeatils();
	 }

	 @Command
	 @NotifyChange("*")
	 public void onSelctClientName(){
		clnBandBox.close();
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onSelctUserId(){
		 userBandBox.close();
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onChangeClientName(){
		   if(clientUserAssignBean.getClientNameSearch() != null){
			   clientList = ResourceAllocationTrackingService.fetchClientDetailsSearchClient(clientUserAssignBean.getClientNameSearch());
		   }
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onChangeUserId(){
		 if(clientUserAssignBean.getUserIdSearch()!=null){
			 userList = ClientUserAssignService.fetchUserDetailsSearchUser(clientUserAssignBean.getUserIdSearch());
		 }
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickClientClear(){
			clnBandBox.close();
			clientUserAssignBean.clientInformationBean.setFullName(null);
			clientUserAssignBean.clientInformationBean.setClientId(null);
			clientUserAssignBean.setClientNameSearch(null);
			clientList = ResourceAllocationTrackingService.fetchClientDetails();
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onClearUserId(){
		 userBandBox.close();
		 clientUserAssignBean.userprofileBean.setUserid(null);
		 clientUserAssignBean.setUserIdSearch(null);
		 userList = RoleMasterDao.onLoadUserDeatilsForApprover();
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickSaveButton(){
		 boolean isInsert = false;
		 boolean flagLogInsert = false;
		 int count = 0;
		 if(clientUserAssignBean.clientInformationBean.getFullName()!=null && clientUserAssignBean.clientInformationBean.getFullName().trim().length()>0){
			 if(clientUserAssignBean.userprofileBean.getUserid()!=null && clientUserAssignBean.userprofileBean.getUserid().trim().length()>0){
				 count = ClientUserAssignService.countClientUserAssignData(clientUserAssignBean);
				 if(count==0){
					 isInsert = ClientUserAssignService.insertClientUserAssignData(clientUserAssignBean);
					 if(isInsert){
						 clientUserAssignBean.setOperation("INSERT");
						 clientUserAssignBean.setOperationId(1);
						 Calendar calendar = Calendar.getInstance();
						 java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
						 flagLogInsert = LogAuditServiceClass.insertIntoLogTable(clientUserAssignBean.getMainScreenName(), clientUserAssignBean.getChileScreenName(), 
																				clientUserAssignBean.getSessionUserId(), clientUserAssignBean.getOperation(),currentDate,
																				clientUserAssignBean.getOperationId());
							
							clientUserAssignBean.clientInformationBean.setFullName(null);
							clientUserAssignBean.clientInformationBean.setClientId(null);
							clientUserAssignBean.setClientNameSearch(null);
							clientList = ResourceAllocationTrackingService.fetchClientDetails();
							
							clientUserAssignBean.userprofileBean.setUserid(null);
							clientUserAssignBean.setUserIdSearch(null);
							userList = RoleMasterDao.onLoadUserDeatilsForApprover();
							assignList = ClientUserAssignDao.onLoadAssignDeatils();
					 } 
				 }else{
					 Messagebox.show("Select New Combination ","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
				 }
			 }else{
				 Messagebox.show("Select User ID ","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
			 }
		 }else{
			 Messagebox.show("Select Client Name ","Informtion", Messagebox.OK, Messagebox.EXCLAMATION);
		 }
	 }
	 
	 @Command
	 @NotifyChange("*")
	 public void onClickDelete(@BindingParam("bean") ClientUserAssignBean userAssignBean){
		 boolean isDelete = false;
		 boolean flagLogDelete = false;
		 isDelete = ClientUserAssignService.deleteClientUserAssignData(userAssignBean);
		 if(isDelete){
			 userAssignBean.setOperation("DELETE");
			 userAssignBean.setOperationId(3);
			 userAssignBean.setSessionUserId(userId);
			 Calendar calendar = Calendar.getInstance();
			 java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			 flagLogDelete = LogAuditServiceClass.insertIntoLogTable(userAssignBean.getMainScreenName(), userAssignBean.getChileScreenName(), 
					 												 userAssignBean.getSessionUserId(), userAssignBean.getOperation(),
																	 currentDate,userAssignBean.getOperationId());
			 
			 assignList = ClientUserAssignDao.onLoadAssignDeatils();	
		 }
	 }
	 
	 /***************************************************Getter And Setter Method ****************************************************************/
	 
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
	public Bandbox getClnBandBox() {
		return clnBandBox;
	}
	public void setClnBandBox(Bandbox clnBandBox) {
		this.clnBandBox = clnBandBox;
	}
	public ArrayList<ClientInformationBean> getClientList() {
		return clientList;
	}
	public void setClientList(ArrayList<ClientInformationBean> clientList) {
		this.clientList = clientList;
	}
	public ArrayList<UserprofileBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserprofileBean> userList) {
		this.userList = userList;
	}
	public ClientUserAssignBean getClientUserAssignBean() {
		return clientUserAssignBean;
	}
	public void setClientUserAssignBean(ClientUserAssignBean clientUserAssignBean) {
		this.clientUserAssignBean = clientUserAssignBean;
	}
	public Bandbox getUserBandBox() {
		return userBandBox;
	}
	public void setUserBandBox(Bandbox userBandBox) {
		this.userBandBox = userBandBox;
	}
	public ArrayList<ClientUserAssignBean> getAssignList() {
		return assignList;
	}
	public void setAssignList(ArrayList<ClientUserAssignBean> assignList) {
		this.assignList = assignList;
	} 
}
