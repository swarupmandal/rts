package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.bean.RollDropDownBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.RoleMasterService;
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
import org.zkoss.zul.Window;

public class RoleMasterUpdateViewModel {
	RoleMasterBean roleMasterBean = new RoleMasterBean();
	private RoleMasterBean masterBean;
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winRoleUpdate")
	private Window winRoleUpdate;
	private boolean flag = false;
	private Integer count;
	private Integer uId;

	private ArrayList<RollDropDownBean> roleList = new ArrayList<RollDropDownBean>();

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("roleDetails") RoleMasterBean bean)throws Exception 
	{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		masterBean = bean;
		masterBean.setUserid(userId);
		masterBean.setSessionUserId(userId);
		roleList = RoleMasterDao.onLoadRoleDropDownDeatilsForUpdateScreen(masterBean);
		masterBean.setVisibilityUpdateButton(true);
		uId = masterBean.getUserprofileBean().getId();
		masterBean.setuId(uId);
		System.out.println(masterBean.getuId());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectRollName(){
		System.out.println("ROLE ID IS :"+masterBean.getDownBean().getRollId());
		System.out.println("MAPPER ID IS :"+masterBean.getMapperId());
		masterBean.setVisibilityUpdateButton(false);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		boolean flagLogUpdate = false;
		flag = RoleMasterService.updateAssignRoleData(masterBean);
		if(flag){
			masterBean.setOperation("UPDATE");
			masterBean.setOperationId(2);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			System.out.println("CREATION DATE :"+currentDate);
			flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(masterBean.getMainScreenName(), masterBean.getChileScreenName(), 
																		masterBean.getSessionUserId(), masterBean.getOperation(),currentDate,
																		  masterBean.getOperationId());
			System.out.println("flagLogUpdate Is:"+flagLogUpdate);
			winRoleUpdate.detach();
			BindUtils.postGlobalCommand(null, null, "globalRoleDetailsUpdate", null);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseOperation(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		winRoleUpdate.detach();
		BindUtils.postGlobalCommand(null, null, "globalMapingData", null);
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
	public RoleMasterBean getRoleMasterBean() {
		return roleMasterBean;
	}
	public void setRoleMasterBean(RoleMasterBean roleMasterBean) {
		this.roleMasterBean = roleMasterBean;
	}
	public RoleMasterBean getMasterBean() {
		return masterBean;
	}
	public void setMasterBean(RoleMasterBean masterBean) {
		this.masterBean = masterBean;
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
	public Window getWinRoleUpdate() {
		return winRoleUpdate;
	}
	public void setWinRoleUpdate(Window winRoleUpdate) {
		this.winRoleUpdate = winRoleUpdate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public ArrayList<RollDropDownBean> getRoleList() {
		return roleList;
	}
	public void setRoleList(ArrayList<RollDropDownBean> roleList) {
		this.roleList = roleList;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
}
