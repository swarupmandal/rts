package org.appsquad.viewmodel;

import java.util.ArrayList;

import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.service.RequirementGenerationService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;


public class RequirementGenerationEditViewModel {
	RequirementGenerationBean reqEditGenBean = new RequirementGenerationBean();
	StatusMasterBean statusBeanEdit = new StatusMasterBean();
	
	private ArrayList<StatusMasterBean> statusBeanEditList = new ArrayList<StatusMasterBean>();
	
	@Wire("#winReqGenEdit")
	private Window winReqGenEdit;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view,
						  @ExecutionArgParam("parentBean") RequirementGenerationBean bean) throws Exception{
		 Selectors.wireComponents(view, this, false); 
		 reqEditGenBean = bean;
		 reqEditGenBean.setReqOpenDate(true);
		 statusBeanEditList = RequirementGenerationService.fetchStatusList();
	}

	@Command
	@NotifyChange("*")
	public void onSelectOcStatusEdit(){
		if(statusBeanEdit.getOcstatusId()>0){
			reqEditGenBean.setOcStatusId(statusBeanEdit.getOcstatusId());	
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(){
		if(RequirementGenerationService.isValidForUpdate(reqEditGenBean)){
			int i = RequirementGenerationService.updateReqGenMaster(reqEditGenBean);
			if(i>0){
				Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
				winReqGenEdit.detach();
				BindUtils.postGlobalCommand(null, null, "editReqGen", null);
			}	
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeCloseDate(){
		int comparision = 0;
		comparision = reqEditGenBean.getCloseDate().compareTo(reqEditGenBean.getRaiseDate());
		if(comparision<0){
			Messagebox.show("Your Close Date Can't Be Less Than Raise Date!", "Information", Messagebox.OK, Messagebox.INFORMATION);
			reqEditGenBean.setCloseDate(null);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseOperation(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		winReqGenEdit.detach();
		BindUtils.postGlobalCommand(null, null, "editReqGen", null);
	}
	
	/******************************************************************************************************************************************/
	
	public RequirementGenerationBean getReqEditGenBean() {
		return reqEditGenBean;
	}
	public void setReqEditGenBean(RequirementGenerationBean reqEditGenBean) {
		this.reqEditGenBean = reqEditGenBean;
	}
	public StatusMasterBean getStatusBeanEdit() {
		return statusBeanEdit;
	}
	public void setStatusBeanEdit(StatusMasterBean statusBeanEdit) {
		this.statusBeanEdit = statusBeanEdit;
	}
	public ArrayList<StatusMasterBean> getStatusBeanEditList() {
		return statusBeanEditList;
	}
	public void setStatusBeanEditList(ArrayList<StatusMasterBean> statusBeanEditList) {
		this.statusBeanEditList = statusBeanEditList;
	}
}
