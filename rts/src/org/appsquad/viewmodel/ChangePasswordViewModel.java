package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.ChangePasswordbean;
import org.appsquad.dao.ChangePasswordDao;
import org.appsquad.service.ChangePasswordService;
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
	private boolean newPasswordFieldDisable = true;
	private boolean reTypeNewPasswordDisable = true;
	private boolean saveButtonDisable = true;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)
					throws Exception {
		Selectors.wireComponents(view, this, false);	
		session = Sessions.getCurrent();
		changePasswordbean.setUserId((String)session.getAttribute("userId"));
	}
	
	@Command
	@NotifyChange("*")
	public void onClickChange(){
		if(changePasswordbean.getPassword()!=null && changePasswordbean.getPassword().trim().length()>0){
			if(changePasswordbean.getNewPassword()!=null && changePasswordbean.getNewPassword().trim().length()>0){
				if(changePasswordbean.getReTypeNewPassword()!=null && changePasswordbean.getReTypeNewPassword().trim().length()>0){
					boolean updateStatus = false;
					updateStatus = ChangePasswordDao.changePassword(changePasswordbean.getUserId(), changePasswordbean.getNewPassword());
					if(updateStatus){
						BindUtils.postGlobalCommand(null, null, "changepassUpdate", null);
						changepasswordWindow.detach();
						Messagebox.show("Password Changed Successfully ", "Information", Messagebox.OK, Messagebox.INFORMATION);
					}	
				}else{
					 Messagebox.show("Re Type Password Can't Be Blank!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}else{
				 Messagebox.show("New Password Can't Be Blank!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else{
			 Messagebox.show("Password Can't Be Blank!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	@Command
	@NotifyChange("*")
	public void onChangePassword(){
		int count = 0;
		count = ChangePasswordService.getCountNumberWrtUserAndPaswword(changePasswordbean);
		System.out.println("COUNT NUMBER WRT USER ID AND PASSWORD IS :"+count);
		if(count>0){
			newPasswordFieldDisable = false;
			reTypeNewPasswordDisable = false;
		}else{
			newPasswordFieldDisable = true;
			reTypeNewPasswordDisable = true;
			changePasswordbean.setPassword(null);
			Messagebox.show("Wrong Password With Respect To This User Id", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeReTypePassword(){
		if(changePasswordbean.getReTypeNewPassword().equalsIgnoreCase(changePasswordbean.getNewPassword())){
			saveButtonDisable = false;
		}else{
			changePasswordbean.setReTypeNewPassword(null);
			saveButtonDisable = true;
			Messagebox.show("New Password And ReType New Password Doesn't Match", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	/*******************************************************************************************************************************************/
	
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
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Window getChangepasswordWindow() {
		return changepasswordWindow;
	}
	public void setChangepasswordWindow(Window changepasswordWindow) {
		this.changepasswordWindow = changepasswordWindow;
	}
	public boolean isNewPasswordFieldDisable() {
		return newPasswordFieldDisable;
	}
	public void setNewPasswordFieldDisable(boolean newPasswordFieldDisable) {
		this.newPasswordFieldDisable = newPasswordFieldDisable;
	}
	public boolean isReTypeNewPasswordDisable() {
		return reTypeNewPasswordDisable;
	}
	public void setReTypeNewPasswordDisable(boolean reTypeNewPasswordDisable) {
		this.reTypeNewPasswordDisable = reTypeNewPasswordDisable;
	}

	public boolean isSaveButtonDisable() {
		return saveButtonDisable;
	}

	public void setSaveButtonDisable(boolean saveButtonDisable) {
		this.saveButtonDisable = saveButtonDisable;
	}
}
