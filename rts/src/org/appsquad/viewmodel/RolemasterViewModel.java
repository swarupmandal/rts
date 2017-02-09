package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.bean.RollDropDownBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.RoleMasterService;
import org.zkoss.bind.BindUtils;
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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class RolemasterViewModel {
	RoleMasterBean roleMasterBean= new RoleMasterBean();
	private ArrayList<RoleMasterBean> rolebeanlist= new ArrayList<RoleMasterBean>();
	private ArrayList<UserprofileBean> userList = null;
	private ArrayList<RollDropDownBean> roleList = null;
	private ArrayList<RoleMasterBean> mappingList = null;
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	private static boolean flag = false;
	private static Integer count ;
	private static Integer countRole ;
	private boolean flagLogDelete = false;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		roleMasterBean.setUserid(userId);
		roleMasterBean.setSessionUserId(userId);
		rolebeanlist = RoleMasterDao.onLoadRoleDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickRoleSave(){
		if(RoleMasterService.isRoleNameExists(roleMasterBean.getRoll(), rolebeanlist)){
			roleMasterBean.setRoll(null);
			Messagebox.show("This role name already exists. Please enter a new role name!","Role Name exixts",Messagebox.OK,Messagebox.EXCLAMATION);
		}else{
			RoleMasterService.insertClientMasterData(roleMasterBean);
			roleMasterBean.setOperation("INSERT");
			roleMasterBean.setOperationId(1);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			LogAuditServiceClass.insertIntoLogTable(roleMasterBean.getMainScreenName(), roleMasterBean.getChileScreenName(), 
																			roleMasterBean.getSessionUserId(), roleMasterBean.getOperation(),currentDate,
																			   roleMasterBean.getOperationId());
			RoleMasterService.clearAllField(roleMasterBean);
			rolebeanlist = RoleMasterDao.onLoadRoleDeatils();	
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onclickedit(@BindingParam("bean") RoleMasterBean masterBean){
		masterBean.setVisibilityRoleTextBox(false);
		masterBean.setVisibilityEditButton(true);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSave(@BindingParam("bean") RoleMasterBean masterBean){
		boolean flagLogUpdate = false;
		
		RoleMasterDao.updateRoleData(masterBean);
		masterBean.setOperation("UPDATE");
		masterBean.setSessionUserId(userId);
		masterBean.setOperationId(2);
		Calendar calendar = Calendar.getInstance();
	    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
		flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(masterBean.getMainScreenName(), masterBean.getChileScreenName(), 
																	masterBean.getSessionUserId(), masterBean.getOperation(),currentDate,
																	masterBean.getOperationId());
		masterBean.setVisibilityEditButton(false);
		masterBean.setVisibilitySaveButton(false);
		masterBean.setVisibilityDeleteButton(false);
		masterBean.setVisibilityRoleTextBox(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange("*")
	public void onClickDelete(@BindingParam("bean") RoleMasterBean masterBean){
		int count = 0;
		count = RoleMasterService.getCountUserPresentWrtRole(masterBean);
		if(count<1){
			Messagebox.show("Are you sure to delete ? ", "Confirm Dialog", Messagebox.OK |  Messagebox.CANCEL, 
					Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener(){
						@Override
						public void onEvent(Event evt) throws Exception {
							if (evt.getName().equals("onOK")) {
								RoleMasterDao.deleteRoleData(masterBean);
								masterBean.setOperation("DELETE");
								masterBean.setSessionUserId(userId);
								masterBean.setOperationId(3);
								Calendar calendar = Calendar.getInstance();
							    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
								flagLogDelete = LogAuditServiceClass.insertIntoLogTable(masterBean.getMainScreenName(), masterBean.getChileScreenName(), 
																							masterBean.getSessionUserId(), masterBean.getOperation(),currentDate,
																							   masterBean.getOperationId());
								BindUtils.postGlobalCommand(null, null, "refresh", null);
							}
					}
			});	
		}else{
			 Messagebox.show("User Assigned Wrt This Role-Name!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickAssignRole(){
		userList = RoleMasterDao.onLoadUserDeatils();
		roleList = RoleMasterDao.onLoadRoleDropDownDeatils();
		mappingList = RoleMasterDao.onLoadMappingDeatils();
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalRoleDetailsUpdate(){
		userList = RoleMasterDao.onLoadUserDeatils();
		roleList = RoleMasterDao.onLoadRoleDropDownDeatils();
		mappingList = RoleMasterDao.onLoadMappingDeatils();
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void refresh(){
		rolebeanlist = RoleMasterDao.onLoadRoleDeatils();	
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalMapingData(){
		mappingList = RoleMasterDao.onLoadMappingDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectUserName(){
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectRollName(){
	}
	
	@Command
	@NotifyChange("*")
	public void onClickAssign(){
		boolean flagAssignLogInsert = false;
		int countNumber = 0;
		countNumber = RoleMasterDao.onLoadCountDeatils(roleMasterBean);
		if(countNumber>0){
			Messagebox.show("This userId and role name combination already exists. Please select a new userId ","User Id & Role Name combination exixts",Messagebox.OK,Messagebox.EXCLAMATION);
		}else {
			RoleMasterService.insertAssignData(roleMasterBean);
			roleMasterBean.setOperation("INSERT");
			roleMasterBean.setSessionUserId(userId);
			roleMasterBean.setOperationId(1);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());			
			flagAssignLogInsert = LogAuditServiceClass.insertIntoLogTable(roleMasterBean.getMainScreenName(), roleMasterBean.getChileScreenName(), 
																			roleMasterBean.getSessionUserId(), roleMasterBean.getOperation(),currentDate,
																			 roleMasterBean.getOperationId());
			RoleMasterService.clearAllFieldAssign(roleMasterBean);
			userList = RoleMasterDao.onLoadUserDeatils();
			roleList = RoleMasterDao.onLoadRoleDropDownDeatils();
			mappingList = RoleMasterDao.onLoadMappingDeatils();	
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") RoleMasterBean roleMasterBean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleDetails", roleMasterBean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/roleInformationUpdate.zul", null, map);
		window.doModal();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickPage(@BindingParam("bean") RoleMasterBean roleMasterBean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageDetails", roleMasterBean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/dynamicPageCreation.zul", null, map);
		window.doModal();
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
	public RoleMasterBean getRoleMasterBean() {
		return roleMasterBean;
	}
	public void setRoleMasterBean(RoleMasterBean roleMasterBean) {
		this.roleMasterBean = roleMasterBean;
	}
	public ArrayList<RoleMasterBean> getRolebeanlist() {
		return rolebeanlist;
	}
	public void setRolebeanlist(ArrayList<RoleMasterBean> rolebeanlist) {
		this.rolebeanlist = rolebeanlist;
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
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		RolemasterViewModel.flag = flag;
	}
	public boolean isFlagLogDelete() {
		return flagLogDelete;
	}
	public void setFlagLogDelete(boolean flagLogDelete) {
		this.flagLogDelete = flagLogDelete;
	}
	public static Integer getCountRole() {
		return countRole;
	}
	public static void setCountRole(Integer countRole) {
		RolemasterViewModel.countRole = countRole;
	}
	public static Integer getCount() {
		return count;
	}
	public static void setCount(Integer count) {
		RolemasterViewModel.count = count;
	}
	public ArrayList<UserprofileBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserprofileBean> userList) {
		this.userList = userList;
	}
	public ArrayList<RollDropDownBean> getRoleList() {
		return roleList;
	}
	public void setRoleList(ArrayList<RollDropDownBean> roleList) {
		this.roleList = roleList;
	}
	public ArrayList<RoleMasterBean> getMappingList() {
		return mappingList;
	}
	public void setMappingList(ArrayList<RoleMasterBean> mappingList) {
		this.mappingList = mappingList;
	}
}
