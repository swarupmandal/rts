package org.appsquad.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.RequirementGenerationDao;
import org.appsquad.service.RequirementGenerationService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class RequirementGenerationViewModel {
	private Session session = null;
	private String userName;
	
	RequirementGenerationBean reqGenBean = new RequirementGenerationBean();
	ClientInformationBean clientInfoBean = new ClientInformationBean();
	SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	StatusMasterBean statusBean = new StatusMasterBean(); 	
	
	private ArrayList<ClientInformationBean> clientNameBeanList = new ArrayList<ClientInformationBean>();
	private ArrayList<SkillsetMasterbean> skillSetBeanList = new ArrayList<SkillsetMasterbean>(); 
	private ArrayList<StatusMasterBean> statusBeanList = new ArrayList<StatusMasterBean>();
	private ArrayList<RequirementGenerationBean> reqGenBeanList = new ArrayList<RequirementGenerationBean>();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)throws Exception{
		Selectors.wireComponents(view, this, false);	
		session = Sessions.getCurrent();
		userName =  (String) session.getAttribute("userId");
		reqGenBean.setUserName(userName);
		clientNameBeanList = RequirementGenerationService.fetchClientNameList();
		skillSetBeanList = RequirementGenerationService.fetchSkillSetList();
		statusBeanList = RequirementGenerationService.fetchStatusList();
		reqGenBeanList = RequirementGenerationService.loadReqGenMasterData();
		reqGenBean.setReqStatusId(RequirementGenerationDao.fetchOverallStatusId());
		System.out.println(reqGenBean.getReqStatusId());
	}

	@Command
	@NotifyChange("*")
	public void onSelectName(){		
		if(clientInfoBean.getClientId()>0){
			reqGenBean.setClientId(clientInfoBean.getClientId());
			reqGenBean.setClientName(clientInfoBean.getName());
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectSkill(){
		if(skillsetMasterbean.getId()>0){
			reqGenBean.setReqSkillId(skillsetMasterbean.getId());
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectOcStatus(){
		if(statusBean.getOcstatusId()>0){
			reqGenBean.setOcStatusId(statusBean.getOcstatusId());
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeCloseDate(){
		int comparision = 0;
		comparision = reqGenBean.getCloseDatesql().compareTo(reqGenBean.getRaiseDatesql());
		if(comparision<0){
			Messagebox.show("Your Close Date Can't Be Less Than Raise Date!", "Information", Messagebox.OK, Messagebox.INFORMATION);
			reqGenBean.setCloseDatesql(null);
		}
	}
	
	
	@Command
	@NotifyChange("*")
	public void onclickSubmit(){
		if(RequirementGenerationService.isValid(reqGenBean)){
			int i = RequirementGenerationService.isertDet(reqGenBean);
			if(i>0){
				Messagebox.show("Saved Successfully ", "Information", Messagebox.OK, Messagebox.INFORMATION);
				clear();
				reqGenBeanList = RequirementGenerationService.loadReqGenMasterData();
			}
		}
	}

    @Command
    @NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") RequirementGenerationBean bean){
    	bean.setUserName(userName);
    	Map<String, Object> parentMap = new HashMap<String, Object>();
    	parentMap.put("parentBean", bean);
    	Window window = (Window) Executions.createComponents("/WEB-INF/view/requrementGenerationEdit.zul", null, parentMap);
    	window.doModal();
    	
    }
	
    @GlobalCommand
    @NotifyChange("*")
	public void editReqGen(){
    	reqGenBeanList = RequirementGenerationService.loadReqGenMasterData();
    }
    
	public void clear(){
		reqGenBean.setClientId(0);
		clientInfoBean.setFullName(null);
		clientNameBeanList = RequirementGenerationService.fetchClientNameList();
		reqGenBean.setReqSkillId(0);
		skillsetMasterbean.setSkillset(null);
		skillSetBeanList = RequirementGenerationService.fetchSkillSetList();
		reqGenBean.setJobType(null);
		reqGenBean.setDetailedJob(null);
		reqGenBean.setNofPerResource(null);
		reqGenBean.setNofConResource(null);
		reqGenBean.setRaiseDatesql(null);
		reqGenBean.setCloseDatesql(null);
		reqGenBean.setContactNo(null);
		reqGenBean.setEmail(null);
		reqGenBean.setOcStatusId(0);
		statusBean.setOcstatus(null);
		statusBeanList = RequirementGenerationService.fetchStatusList();
		reqGenBean.setClosureReason(null);
	}
	
	/***********************************************************************************************************************************************/
	
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
	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}
	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
	}
	public ArrayList<SkillsetMasterbean> getSkillSetBeanList() {
		return skillSetBeanList;
	}
	public void setSkillSetBeanList(ArrayList<SkillsetMasterbean> skillSetBeanList) {
		this.skillSetBeanList = skillSetBeanList;
	}
	public StatusMasterBean getStatusBean() {
		return statusBean;
	}
	public void setStatusBean(StatusMasterBean statusBean) {
		this.statusBean = statusBean;
	}
	public ArrayList<StatusMasterBean> getStatusBeanList() {
		return statusBeanList;
	}
	public void setStatusBeanList(ArrayList<StatusMasterBean> statusBeanList) {
		this.statusBeanList = statusBeanList;
	}
	public ArrayList<RequirementGenerationBean> getReqGenBeanList() {
		return reqGenBeanList;
	}
	public void setReqGenBeanList(
			ArrayList<RequirementGenerationBean> reqGenBeanList) {
		this.reqGenBeanList = reqGenBeanList;
	}
}
