package org.appsquad.viewmodel;

import java.sql.Connection;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

public class HomePageViewModel {
	

	Session session = null;

	private String userId;


	private Connection connection = null;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
	
		session = Sessions.getCurrent();
		
		userId = (String) session.getAttribute("userId");
		
		if(userId==null){
			Executions.sendRedirect("/welcome1.zul");
		}else{
			userId = userId;
			
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSignOut(){
		System.out.println("Signiing out...");
		if(session!=null){
			session.removeAttribute("userId");
			session=null;
			Executions.sendRedirect("/welcome1.zul");
			System.out.println("--- -- - >>> >> >");
		}
	}

	@Command
	@NotifyChange("*")
	public void onClickChangePassword(){
		Window window = (Window) Executions.createComponents("/WEB-INF/view/changepassword.zul", null, null);
		window.doModal();
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	



}
