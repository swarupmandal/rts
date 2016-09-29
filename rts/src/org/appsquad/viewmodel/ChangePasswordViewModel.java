package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.ChangePasswordbean;
import org.appsquad.dao.ChangePasswordDao;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class ChangePasswordViewModel {

	Session session = null;
	
	ChangePasswordbean changePasswordbean=new ChangePasswordbean();
	ArrayList<String> alist= new ArrayList<String>();
	
	
	@Wire("#changePasswordWindow")
	private Window changepasswordWindow ;
	
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {

		Selectors.wireComponents(view, this, false);
	
		session = Sessions.getCurrent();
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClickChange(){
	
		boolean updateStatus = false;
		
		updateStatus = ChangePasswordDao.changePassword(changePasswordbean.getUserId(), changePasswordbean.getPassword());
		
		if(updateStatus){
			BindUtils.postGlobalCommand(null, null, "changepassUpdate", null);
			changepasswordWindow.detach();
			Messagebox.show("Password Changed Successfully ", "Information", Messagebox.OK, Messagebox.INFORMATION);
			
			
		}else {
			//Messagebox.show()
		}
		
		
	}

	public ArrayList<String> getAlist() {
		return alist;
	}

	public void setAlist(ArrayList<String> alist) {
		this.alist = alist;
	}

	public ChangePasswordbean getChangePasswordbean() {
		return changePasswordbean;
	}

	public void setChangePasswordbean(ChangePasswordbean changePasswordbean) {
		this.changePasswordbean = changePasswordbean;
	}
	
}
