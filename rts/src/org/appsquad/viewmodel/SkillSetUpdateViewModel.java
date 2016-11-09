package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.Calendar;

import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.SkillSetMasterService;
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

public class SkillSetUpdateViewModel {
	SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	private SkillsetMasterbean bean;
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winSkillSetUpdateScreen")
	private Window winSkillSetUpdateScreen;
	private boolean flag = false;
	private boolean flagLogUpdate = false;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("skillSetDetails") SkillsetMasterbean masterbean)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		bean = masterbean;
		userId = (String) sessions.getAttribute("userId");
		skillsetMasterbean.setUserId(userId);
		skillsetMasterbean.setSessionUserId(userId);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		flag = SkillSetMasterService.updateSkillMasterData(bean);
		if(flag){
			bean.setOperation("UPDATE");
			bean.setOperationId(2);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			System.out.println("CREATION DATE :"+currentDate);
			flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(bean.getMainScreenName(), bean.getChileScreenName(), 
                    												bean.getSessionUserId(), bean.getOperation(),currentDate,bean.getOperationId());
			System.out.println("flagLogUpdate Is:"+flagLogUpdate);
			winSkillSetUpdateScreen.detach();
			BindUtils.postGlobalCommand(null, null, "globalSkillSetDetailsUpdate", null);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseOperation(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		winSkillSetUpdateScreen.detach();
		BindUtils.postGlobalCommand(null, null, "globalSkillSetDetailsUpdate", null);
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}
	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
	}
	public SkillsetMasterbean getBean() {
		return bean;
	}
	public void setBean(SkillsetMasterbean bean) {
		this.bean = bean;
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Window getWinSkillSetUpdateScreen() {
		return winSkillSetUpdateScreen;
	}
	public void setWinSkillSetUpdateScreen(Window winSkillSetUpdateScreen) {
		this.winSkillSetUpdateScreen = winSkillSetUpdateScreen;
	}
	public boolean isFlagLogUpdate() {
		return flagLogUpdate;
	}
	public void setFlagLogUpdate(boolean flagLogUpdate) {
		this.flagLogUpdate = flagLogUpdate;
	}
}
