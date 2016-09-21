package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.StatusMasterBean;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class StatusMasterViewModel {

	StatusMasterBean statusMasterBean=new StatusMasterBean();
	ArrayList<StatusMasterBean> statuslist=new ArrayList<StatusMasterBean>();

	
	
	@Command
	@NotifyChange("*")
	public void onClickStatussave(){
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickedit(){
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSave(){
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDelete(){
		
	}
	
	
	public StatusMasterBean getStatusMasterBean() {
		return statusMasterBean;
	}
	public void setStatusMasterBean(StatusMasterBean statusMasterBean) {
		this.statusMasterBean = statusMasterBean;
	}
	public ArrayList<StatusMasterBean> getStatuslist() {
		return statuslist;
	}
	public void setStatuslist(ArrayList<StatusMasterBean> statuslist) {
		this.statuslist = statuslist;
	}
	
}
