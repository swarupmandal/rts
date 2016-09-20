package org.appsquad.viewmodel;

import java.sql.Connection;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.service.ClientInformationService;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
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
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("userIdDetails") UserprofileBean userprofileBean)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		bean = userprofileBean;
		sessions = Sessions.getCurrent();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		flag = UserProfileService.updateUserMasterData(bean);
		if(flag){
			winUserProfile.detach();
			BindUtils.postGlobalCommand(null, null, "globalUserDetailsUpdate", null);
		}
	}
	
	/**************************************************************************************************************************************/
	
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
}
