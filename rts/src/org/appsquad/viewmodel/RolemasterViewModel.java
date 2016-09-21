package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.dao.RoleMasterDao;
import org.appsquad.service.RoleMasterService;
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

public class RolemasterViewModel {
	RoleMasterBean roleMasterBean= new RoleMasterBean();
	private ArrayList<RoleMasterBean> rolebeanlist= new ArrayList<RoleMasterBean>();
	
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
		roleMasterBean.setUserid(userId);
		rolebeanlist = RoleMasterDao.onLoadRoleDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickRoleSave(){
		RoleMasterService.insertClientMasterData(roleMasterBean);
		RoleMasterService.clearAllField(roleMasterBean);
		rolebeanlist = RoleMasterDao.onLoadRoleDeatils();
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
		RoleMasterDao.updateRoleData(masterBean);
		masterBean.setVisibilityEditButton(false);
		masterBean.setVisibilitySaveButton(false);
		masterBean.setVisibilityDeleteButton(false);
		masterBean.setVisibilityRoleTextBox(true);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDelete(@BindingParam("bean") RoleMasterBean masterBean){
		RoleMasterDao.deleteRoleData(masterBean);
		rolebeanlist = RoleMasterDao.onLoadRoleDeatils();
	}
	
	/*************************************************************************************************************************************************/
	
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
}
