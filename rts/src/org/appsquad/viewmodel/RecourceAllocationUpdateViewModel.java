package org.appsquad.viewmodel;


import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class RecourceAllocationUpdateViewModel {
	
	  private Session sessions = null;
	  private String userName ;
	  private String userId;
	
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)throws Exception {

		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userName = (String) sessions.getAttribute("login");
	}

}
