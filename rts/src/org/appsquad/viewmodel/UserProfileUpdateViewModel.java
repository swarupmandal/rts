package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.Calendar;

import org.appsquad.bean.UserprofileBean;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.UserProfileService;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class UserProfileUpdateViewModel {
	UserprofileBean userprofileBean = new UserprofileBean();
	private UserprofileBean bean;
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winUserProfile")
	private Window winUserProfile;
	private boolean flag = false;
	private boolean flagLogUpdate = false;
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("userIdDetails") UserprofileBean userprofileBean)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		bean = userprofileBean;
		userId = (String) sessions.getAttribute("userId");
		sessions = Sessions.getCurrent();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		String emailID = "";
		String address = "";
		boolean flagForUserUpdate = false;
		if(bean.getContactno()!=null && bean.getContactno().length()==10){
			emailID = bean.getEmail().trim();
			bean.setEmail(emailID);
			
			address = bean.getAddress().trim();
			bean.setAddress(address);
			
			flagForUserUpdate = UserProfileService.updateUserMasterData(bean);
			if(flagForUserUpdate){
				userprofileBean.setOperation("UPDATE");
				userprofileBean.setSessionUserId(userId);
				userprofileBean.setOperationId(2);
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
				System.out.println("CREATION DATE :"+currentDate);
				flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(userprofileBean.getMainScreenName(), userprofileBean.getChileScreenName(), 
	                    												userprofileBean.getSessionUserId(), userprofileBean.getOperation(),currentDate,
	                    												userprofileBean.getOperationId());
				System.out.println("flagLogUpdate Is:"+flagLogUpdate);
				winUserProfile.detach();
				BindUtils.postGlobalCommand(null, null, "globalUserDetailsUpdate", null);
			}
		}else{
			bean.setContactno(null);
			Messagebox.show("Enter Proper Contact Number ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseOperation(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		winUserProfile.detach();
		BindUtils.postGlobalCommand(null, null, "globalUserDetailsUpdate", null);
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public UserprofileBean getBean() {
		return bean;
	}
	public void setBean(UserprofileBean bean) {
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
	public Window getWinUserProfile() {
		return winUserProfile;
	}
	public void setWinUserProfile(Window winUserProfile) {
		this.winUserProfile = winUserProfile;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isFlagLogUpdate() {
		return flagLogUpdate;
	}
	public void setFlagLogUpdate(boolean flagLogUpdate) {
		this.flagLogUpdate = flagLogUpdate;
	}
}
