package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.UserProfileDao;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.UserProfileService;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class UserprofileViewModel {
	UserprofileBean userprofileBean=new UserprofileBean();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	
	private ArrayList<UserprofileBean> userList = new ArrayList<UserprofileBean>();

	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		userprofileBean.setSessionUserId(userId);
		userList = UserProfileDao.onLoadUserDeatils();
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalUserDetailsUpdate(){
		userList = UserProfileDao.onLoadUserDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExisting(){
		userList = UserProfileDao.onLoadUserDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickuserSubmit() throws Exception{
		if(userprofileBean.getContactno()!=null && userprofileBean.getContactno().length()==10){
			boolean flagInsert = false;
			boolean flagLogInsert = false;
			int countNumber = 0;
			countNumber = UserProfileService.countUserIdPresentInTable(userprofileBean);
			System.out.println(countNumber);
			if(countNumber>0){
				Messagebox.show("Please Enter New User ID. ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}else{
				flagInsert = UserProfileService.insertUserMasterData(userprofileBean);
				if(flagInsert){
					userprofileBean.setOperation("INSERT");
					userprofileBean.setOperationId(1);
					Calendar calendar = Calendar.getInstance();
				    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
					System.out.println("CREATION DATE :"+currentDate);
					flagLogInsert = LogAuditServiceClass.insertIntoLogTable(userprofileBean.getMainScreenName(), userprofileBean.getChileScreenName(), 
	                        												userprofileBean.getSessionUserId(), userprofileBean.getOperation(),currentDate,
	                        												userprofileBean.getOperationId());
					System.out.println("flagLogInsert Is:"+flagLogInsert);
					UserProfileService.clearAllField(userprofileBean);	
				}	
			}
		}else{
			userprofileBean.setContactno(null);
			Messagebox.show("Enter Proper Contact Number ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(@BindingParam("bean") UserprofileBean userprofileBean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userIdDetails", userprofileBean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/userInformationUpdate.zul", null, map);
		window.doModal();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDeleteButton(@BindingParam("bean") UserprofileBean userprofileBean){
		boolean flagDelete = false;
		boolean flagLogDelete = false;
		flagDelete = UserProfileDao.deleteUserData(userprofileBean);
		if(flagDelete){
			userprofileBean.setOperation("DELETE");
			userprofileBean.setSessionUserId(userId);
			userprofileBean.setOperationId(3);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			System.out.println("CREATION DATE :"+currentDate);
			flagLogDelete = LogAuditServiceClass.insertIntoLogTable(userprofileBean.getMainScreenName(), userprofileBean.getChileScreenName(), 
                    												userprofileBean.getSessionUserId(), userprofileBean.getOperation(),currentDate,
                    												userprofileBean.getOperationId());
			System.out.println("flagLogDelete Is:"+flagLogDelete);
			BindUtils.postGlobalCommand(null, null, "globalUserDetailsUpdate", null);
		}
	}

	/***************************************************Getter And Setter Method ****************************************************************/
    
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
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
	public ArrayList<UserprofileBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserprofileBean> userList) {
		this.userList = userList;
	}
}
