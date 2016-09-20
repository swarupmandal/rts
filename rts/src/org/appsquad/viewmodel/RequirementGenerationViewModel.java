package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class RequirementGenerationViewModel {
	
	private Session session = null;
	private String userName;
	
	RequirementGenerationBean reqGenBean = new RequirementGenerationBean();
	ClientInformationBean clientInfoBean = new ClientInformationBean();
	
	private ArrayList<ClientInformationBean> clientNameBeanList = new ArrayList<ClientInformationBean>();
	
	
	
	
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)throws Exception{
		Selectors.wireComponents(view, this, false);
		
		session = Sessions.getCurrent();
		userName =  (String) session.getAttribute("userId");
		System.out.println("user name " + userName);
		
		
		
	}






	public RequirementGenerationBean getReqGenBean() {
		return reqGenBean;
	}






	public void setReqGenBean(RequirementGenerationBean reqGenBean) {
		this.reqGenBean = reqGenBean;
	}






	public Session getSession() {
		return session;
	}






	public void setSession(Session session) {
		this.session = session;
	}






	public String getUserName() {
		return userName;
	}






	public void setUserName(String userName) {
		this.userName = userName;
	}






	public ClientInformationBean getClientInfoBean() {
		return clientInfoBean;
	}






	public void setClientInfoBean(ClientInformationBean clientInfoBean) {
		this.clientInfoBean = clientInfoBean;
	}






	public ArrayList<ClientInformationBean> getClientNameBeanList() {
		return clientNameBeanList;
	}






	public void setClientNameBeanList(
			ArrayList<ClientInformationBean> clientNameBeanList) {
		this.clientNameBeanList = clientNameBeanList;
	}


}
