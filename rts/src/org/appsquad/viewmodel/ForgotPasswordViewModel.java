package org.appsquad.viewmodel;

import org.appsquad.bean.UserprofileBean;
import org.appsquad.service.ForgotPasswordService;
import org.appsquad.utility.PasswordGenerator;
import org.zkoss.bind.annotation.AfterCompose;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ForgotPasswordViewModel {

	private Session sessions = null;
	private UserprofileBean user = new UserprofileBean();
	private boolean btnDisabled = false;
	@Wire("#winForgetPassword")
	Window winForgetPassword ;
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("userDetails") UserprofileBean userbean)throws Exception 
	{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		System.out.println("Login details: "+userbean.getUserid()+" And email : "+userbean.getEmail());
		user = userbean;
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSend(){
		btnDisabled = true;
		String systemPassword = PasswordGenerator.generate();
		String emailBody = "Please login with this password and set password as per your choice.\n"
				+ " Your temporary password is: "+systemPassword;
		if(ForgotPasswordService.isMailSend(user.getEmail(), emailBody)){
			ForgotPasswordService.upDatePassword(user.getUserid(), systemPassword);
			Messagebox.show("A dummy password is sent to your mail,kindly check your mail.","Information",
					Messagebox.OK,Messagebox.INFORMATION);
			winForgetPassword.detach();
			btnDisabled = false;
		}
	}
	
	public Session getSessions() {
		return sessions;
	}
	public void setSessions(Session sessions) {
		this.sessions = sessions;
	}
	public UserprofileBean getUser() {
		return user;
	}
	public void setUser(UserprofileBean user) {
		this.user = user;
	}

	public boolean isBtnDisabled() {
		return btnDisabled;
	}

	public void setBtnDisabled(boolean btnDisabled) {
		this.btnDisabled = btnDisabled;
	}

	public Window getWinForgetPassword() {
		return winForgetPassword;
	}

	public void setWinForgetPassword(Window winForgetPassword) {
		this.winForgetPassword = winForgetPassword;
	}
}
