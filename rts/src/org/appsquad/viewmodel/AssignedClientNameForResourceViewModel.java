package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.AssignedClientNameForResourceBean;
import org.appsquad.bean.DemoBean;
import org.appsquad.service.AssignedClientNameForResourceService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class AssignedClientNameForResourceViewModel {
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   
   private ArrayList<DemoBean> clientDetailsWrtResource = null;
   
   DemoBean demoBean = null;

   @AfterCompose
   public void initSetUp(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("AssignDetailsWrtResource") DemoBean bean) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		demoBean = bean;
		System.out.println("RESOURCE ID IS:"+demoBean.getId()+"----"+"RESOURCE NAME IS :"+demoBean.getFullName());
		clientDetailsWrtResource = AssignedClientNameForResourceService.fetchResourceDetailsListService(demoBean);
	}
   
    /*********************************************************************************************************************************************/
   
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
	public DemoBean getDemoBean() {
		return demoBean;
	}
	public void setDemoBean(DemoBean demoBean) {
		this.demoBean = demoBean;
	}

	public ArrayList<DemoBean> getClientDetailsWrtResource() {
		return clientDetailsWrtResource;
	}

	public void setClientDetailsWrtResource(
			ArrayList<DemoBean> clientDetailsWrtResource) {
		this.clientDetailsWrtResource = clientDetailsWrtResource;
	}
}
