package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.service.DynamicPageCreationService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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

public class DynamicPageCreationViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winDynamicPage")
	private Window winDynamicPage;
	private ArrayList<RoleMasterBean> pagelist = new ArrayList<RoleMasterBean>();
	
	private RoleMasterBean roleMasterBean = new RoleMasterBean();
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("pageDetails") RoleMasterBean bean)
									throws Exception {
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		roleMasterBean = bean;
		userId = (String) sessions.getAttribute("userId");
		roleMasterBean.setRoleAccessUserId(userId);
		allPageNameFetchWithCheckBoxVm(roleMasterBean);
	}
	
	public void allPageNameFetchWithCheckBoxVm(RoleMasterBean masterBean){
		pagelist = DynamicPageCreationService.allPageNameFetchWithCheckBoxSe(masterBean);
		System.out.println(pagelist.size());
	}
	
	@Command
	@NotifyChange("*")
	public void onCheck(@BindingParam("bean") RoleMasterBean masterBean){
	}
	
	@Command
	@NotifyChange("*")
	public void onClickAssign() throws Exception{
		boolean flag = false;
		flag = DynamicPageCreationService.insertMappingPageAndUser(roleMasterBean, pagelist);
		if(flag){
			winDynamicPage.detach();
		}
	}
	
	/******************************************************************************************************************************************/
	
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
	public Window getWinDynamicPage() {
		return winDynamicPage;
	}
	public void setWinDynamicPage(Window winDynamicPage) {
		this.winDynamicPage = winDynamicPage;
	}
	public ArrayList<RoleMasterBean> getPagelist() {
		return pagelist;
	}
	public void setPagelist(ArrayList<RoleMasterBean> pagelist) {
		this.pagelist = pagelist;
	}
	public RoleMasterBean getRoleMasterBean() {
		return roleMasterBean;
	}
	public void setRoleMasterBean(RoleMasterBean roleMasterBean) {
		this.roleMasterBean = roleMasterBean;
	}
}
