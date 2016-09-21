package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.ResourceMasterService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

public class ResourceMasterViewModel {
   ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
   
   private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
   private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
   private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
   private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
   private ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
   
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   
   @AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		resourceMasterBean.setUserId(userId);
		stateList = ResourceMasterDao.onLoadState();
		countryList = ResourceMasterDao.onLoadCountry();
		skillList = ResourceMasterDao.onLoadSkill();
		statusList = ResourceMasterDao.onLoadStatus();
		resourceList = ResourceMasterDao.onLoadResourceDeatils();
	}
   
   @Command
	@NotifyChange("*")
	public void onSelectStateName(){
		System.out.println("STATE ID IS :"+resourceMasterBean.getStateBean().getStateId());
		System.out.println("STATE NAME IS :"+resourceMasterBean.getStateBean().getStateName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		System.out.println("COUNTRY ID IS :"+resourceMasterBean.getCountryBean().getCountryId());
		System.out.println("COUNTRY NAME IS :"+resourceMasterBean.getCountryBean().getCountryName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectSkillName(){
		System.out.println("COUNTRY ID IS :"+resourceMasterBean.getSkillsetMasterbean().getId());
		System.out.println("COUNTRY NAME IS :"+resourceMasterBean.getSkillsetMasterbean().getSkillset());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStatusName(){
		System.out.println("COUNTRY ID IS :"+resourceMasterBean.getStatusMasterBean().getStatusId());
		System.out.println("COUNTRY NAME IS :"+resourceMasterBean.getStatusMasterBean().getStatus());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSubmitButton(){
		ResourceMasterService.insertClientMasterData(resourceMasterBean);
		ResourceMasterService.clearAllField(resourceMasterBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") ResourceMasterBean bean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("resourceIdDetails", bean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/resourceInformationUpdate.zul", null, map);
		window.doModal();
	}
	
   /************************************************************************************************************************************************/
   
	public ResourceMasterBean getResourceMasterBean() {
		return resourceMasterBean;
	}
	public void setResourceMasterBean(ResourceMasterBean resourceMasterBean) {
		this.resourceMasterBean = resourceMasterBean;
	}
	public ArrayList<StateBean> getStateList() {
		return stateList;
	}
	public void setStateList(ArrayList<StateBean> stateList) {
		this.stateList = stateList;
	}
	public ArrayList<CountryBean> getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList<CountryBean> countryList) {
		this.countryList = countryList;
	}
	public ArrayList<StatusMasterBean> getStatusList() {
		return statusList;
	}
	public void setStatusList(ArrayList<StatusMasterBean> statusList) {
		this.statusList = statusList;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Session getSessions() {
		return sessions;
	}
	public void setSessions(Session sessions) {
		this.sessions = sessions;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<SkillsetMasterbean> getSkillList() {
		return skillList;
	}

	public void setSkillList(ArrayList<SkillsetMasterbean> skillList) {
		this.skillList = skillList;
	}

	public ArrayList<ResourceMasterBean> getResourceList() {
		return resourceList;
	}

	public void setResourceList(ArrayList<ResourceMasterBean> resourceList) {
		this.resourceList = resourceList;
	}
}
