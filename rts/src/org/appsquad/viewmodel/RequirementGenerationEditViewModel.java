package org.appsquad.viewmodel;

import java.util.ArrayList;
import java.util.Calendar;

import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceTypeBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.RequirementGenerationService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class RequirementGenerationEditViewModel {
	RequirementGenerationBean reqEditGenBean = new RequirementGenerationBean();
	StatusMasterBean statusBeanEdit = new StatusMasterBean();
	ResourceTypeBean resourceTypeBean=new ResourceTypeBean();
	public int count;
	private boolean isEqualResource;
	@Wire("#winReqGenEdit")
	private Window winReqGenEdit;
	private String userId;
	private Session session = null;
	
	private ArrayList<StatusMasterBean> statusBeanEditList = new ArrayList<StatusMasterBean>();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("parentBean") RequirementGenerationBean bean)throws Exception
	{
		 Selectors.wireComponents(view, this, false); 
		 session = Sessions.getCurrent();
		 userId =  (String) session.getAttribute("userId");
		 reqEditGenBean = bean;
		 reqEditGenBean.setReqOpenDate(true);
		 reqEditGenBean.setSessionUserId(userId);
		 System.out.println("REQUIREMENTS ID IS:"+reqEditGenBean.getReq_id());
		 if(reqEditGenBean.getResourceTypeBean().getResourceTypeName().equalsIgnoreCase("CONTRACT")){
			 reqEditGenBean.setConFieldvisibility(true);
			 reqEditGenBean.setPerFieldvisibility(false);
			 reqEditGenBean.setOldValue(reqEditGenBean.getNofConResource());
		 }else{
			 reqEditGenBean.setConFieldvisibility(false);
			 reqEditGenBean.setPerFieldvisibility(true);
			 reqEditGenBean.setOldValue(reqEditGenBean.getNofPerResource());
		 }
		 reqEditGenBean.setOldOcStatusId(reqEditGenBean.getOcStatusId());
		 reqEditGenBean.setOldOcStatus(reqEditGenBean.getOcStatus());
		 statusBeanEditList = RequirementGenerationService.fetchStatusList();
		 isEqualResource = RequirementGenerationService.getReqAllEqual(reqEditGenBean.getReq_id());
	}

	@Command
	@NotifyChange("*")
	public void onSelectOcStatusEdit(){
		System.out.println("NEW OC STATUS ID IS :"+statusBeanEdit.getOcstatusId()+"----------"+reqEditGenBean.getOldOcStatusId());
		System.out.println("IS EQUAL RESOURCE IS :"+isEqualResource);
		if(statusBeanEdit.getOcstatusId()>1){
			if(isEqualResource){
				reqEditGenBean.setOcStatusId(statusBeanEdit.getOcstatusId());	
			}else{
				Messagebox.show("This Requirement ID Is In Process,Can't Close!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				reqEditGenBean.setOcStatusId(reqEditGenBean.getOldOcStatusId());
				reqEditGenBean.setOcStatus(reqEditGenBean.getOldOcStatus());
				statusBeanEdit.setOcstatus(reqEditGenBean.getOcStatus());
				statusBeanEditList = RequirementGenerationService.fetchStatusList();
			}
		}
	}
		
	@Command
	@NotifyChange("*")
	public void onChangePer(){
		System.out.println("Req Id :"+reqEditGenBean.getReq_id()+"--Type name -"+reqEditGenBean.getResourceTypeBean().getResourceTypeId());
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeCon(){
		System.out.println("Req Id :"+reqEditGenBean.getReq_id()+"--Type name -"+reqEditGenBean.getResourceTypeBean().getResourceTypeId());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(){
		boolean flag = false;
		boolean flagLogUpdate = false;
		if(RequirementGenerationService.isValidForUpdate(reqEditGenBean)){
			int count = RequirementGenerationService.countWrtReqId(reqEditGenBean);
			System.out.println("COUNT :"+count);
			if(count>0){
				if(reqEditGenBean.getResourceTypeBean().getResourceTypeName().equalsIgnoreCase("CONTRACT")){
					System.out.println("NEW VALUE IS :"+reqEditGenBean.getNofConResource()+"-------------"+reqEditGenBean.getOldValue());
					if(reqEditGenBean.getNofConResource()<reqEditGenBean.getOldValue()){
						 Messagebox.show("You Can't Drecrease The Number Of Resources"+reqEditGenBean.getOldValue()+"!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
						 reqEditGenBean.setNofConResource(reqEditGenBean.getOldValue());
						 flag = false;
					}else{
						flag = true;
					}
				}else{
					System.out.println("NEW VALUE IS :"+reqEditGenBean.getNofPerResource()+"-------------"+reqEditGenBean.getOldValue());
					if(reqEditGenBean.getNofPerResource()<reqEditGenBean.getOldValue()){
						 Messagebox.show("You Can't Drecrease The Number Of Resources!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
						 reqEditGenBean.setNofPerResource(reqEditGenBean.getOldValue());
						 flag = false;
					}else{
						flag = true;
					}
				}
			}else{
				flag = true;
			}
			System.out.println(flag);
			if(flag){
				int i = RequirementGenerationService.updateReqGenMaster(reqEditGenBean);	
				if(i>0){
					Messagebox.show("Updated Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
					reqEditGenBean.setOperation("UPDATE");
					reqEditGenBean.setOperationId(2);
					Calendar calendar = Calendar.getInstance();
				    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
					System.out.println("CREATION DATE :"+currentDate);
					flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(reqEditGenBean.getMainScreenName(), reqEditGenBean.getChileScreenName(), 
																			reqEditGenBean.getSessionUserId(), reqEditGenBean.getOperation(),currentDate,
																			reqEditGenBean.getOperationId());
					System.out.println("Requirement screen flagLogUpdate Is:"+flagLogUpdate);
					winReqGenEdit.detach();
					BindUtils.postGlobalCommand(null, null, "editReqGen", null);
				}	
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
	public ResourceTypeBean getResourceTypeBean() {
		return resourceTypeBean;
	}
	public void setResourceTypeBean(ResourceTypeBean resourceTypeBean) {
		this.resourceTypeBean = resourceTypeBean;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isEqualResource() {
		return isEqualResource;
	}
	public void setEqualResource(boolean isEqualResource) {
		this.isEqualResource = isEqualResource;
	}
	public Window getWinReqGenEdit() {
		return winReqGenEdit;
	}
	public void setWinReqGenEdit(Window winReqGenEdit) {
		this.winReqGenEdit = winReqGenEdit;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
}
