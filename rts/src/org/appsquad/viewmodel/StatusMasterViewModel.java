package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.dao.StatusMasterDao;
import org.appsquad.service.StatusMasterService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class StatusMasterViewModel {
	StatusMasterBean statusMasterBean=new StatusMasterBean();
	private ArrayList<StatusMasterBean> statuslist=new ArrayList<StatusMasterBean>();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	private static boolean flag = false;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		statusMasterBean.setUserId(userId);
		statuslist = StatusMasterDao.onLoadStatusDeatils();
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalStatusDetailsUpdate(){
		statuslist = StatusMasterDao.onLoadStatusDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickStatusSave(){
		StatusMasterService.insertClientMasterData(statusMasterBean);
		StatusMasterService.clearAllField(statusMasterBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDelete(@BindingParam("bean") StatusMasterBean masterBean){
		flag = StatusMasterService.deleteStatusMasterData(masterBean);
		if(flag){
			BindUtils.postGlobalCommand(null, null, "globalStatusDetailsUpdate", null);
		}
	}
	
	/**************************************************************************************************************************************/
	
	public StatusMasterBean getStatusMasterBean() {
		return statusMasterBean;
	}
	public void setStatusMasterBean(StatusMasterBean statusMasterBean) {
		this.statusMasterBean = statusMasterBean;
	}
	public ArrayList<StatusMasterBean> getStatuslist() {
		return statuslist;
	}
	public void setStatuslist(ArrayList<StatusMasterBean> statuslist) {
		this.statuslist = statuslist;
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
		StatusMasterViewModel.flag = flag;
	}
}
