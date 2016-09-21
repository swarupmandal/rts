package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.RoleMasterBean;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class RolemasterViewModel {
	
	RoleMasterBean roleMasterBean= new RoleMasterBean();
	ArrayList<RoleMasterBean> rolebeanlist= new ArrayList<RoleMasterBean>();

	
		@Command
		@NotifyChange("*")
		public void onClickroleSave(){
			
		}
		
		@Command
		@NotifyChange("*")
		public void onclickedit(){
			
		}
		
		@Command
		@NotifyChange("*")
		public void onClickSave(){
			
		}
		
		@Command
		@NotifyChange("*")
		public void onClickDelete(){
			
		}
	
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
	

}
