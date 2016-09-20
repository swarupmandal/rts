package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.UserprofileBean;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class UserprofileViewModel {
	Session session = null;

	UserprofileBean userprofileBean=new UserprofileBean();
	ArrayList<UserprofileBean> userbeanlList= new ArrayList<UserprofileBean>();
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
	
		session = Sessions.getCurrent();
		
	}

		
	@Command
	@NotifyChange("*")
	public void onSelectusername()
	{
		
	}
	@Command
	@NotifyChange("*")
	public void onSelectuserrole()
	{
		
	}
	@Command
	@NotifyChange("*")
	public void onClickroleExisting(){
		
	}
	@Command
	@NotifyChange("*")
	public void onClickuserSubmit(){
		
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}

	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}

	public ArrayList<UserprofileBean> getUserbeanlList() {
		return userbeanlList;
	}

	public void setUserbeanlList(ArrayList<UserprofileBean> userbeanlList) {
		this.userbeanlList = userbeanlList;
	}

}
