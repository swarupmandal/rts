package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.ClientInformationService;
import org.appsquad.service.ResourceMasterService;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class ResourceInformationUpdateViewModel {
	ResourceMasterBean bean = new ResourceMasterBean();
	private ResourceMasterBean masterBean;
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winResourceUpdate")
	private Window winResourceUpdate;
	private boolean flag = false;
	
	private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
	private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
	private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
	private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("resourceIdDetails") ResourceMasterBean bean)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		masterBean = bean;
		sessions = Sessions.getCurrent();
		stateList = ResourceMasterDao.onLoadState();
		countryList = ResourceMasterDao.onLoadCountry();
		statusList = ResourceMasterDao.onLoadStatus();
		skillList = ResourceMasterDao.onLoadSkill();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStateName(){
		System.out.println("STATE ID IS :"+masterBean.getStateBean().getStateId());
		System.out.println("STATE NAME IS :"+masterBean.getStateBean().getStateName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		System.out.println("COUNTRY ID IS :"+masterBean.getCountryBean().getCountryId());
		System.out.println("COUNTRY NAME IS :"+masterBean.getCountryBean().getCountryName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectSkillName(){
		System.out.println("COUNTRY ID IS :"+masterBean.getSkillsetMasterbean().getId());
		System.out.println("COUNTRY NAME IS :"+masterBean.getSkillsetMasterbean().getSkillset());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStatusName(){
		System.out.println("COUNTRY ID IS :"+masterBean.getStatusMasterBean().getStatusId());
		System.out.println("COUNTRY NAME IS :"+masterBean.getStatusMasterBean().getStatus());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		flag = ResourceMasterService.updateResourceMasterData(masterBean);
		if(flag){
			winResourceUpdate.detach();
			BindUtils.postGlobalCommand(null, null, "globalUserDetailsUpdate", null);
		}
	}
	
	/*************************************************************************************************************************************************/
	
	public ResourceMasterBean getBean() {
		return bean;
	}
	public void setBean(ResourceMasterBean bean) {
		this.bean = bean;
	}
	public ResourceMasterBean getMasterBean() {
		return masterBean;
	}
	public void setMasterBean(ResourceMasterBean masterBean) {
		this.masterBean = masterBean;
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
	public Window getWinResourceUpdate() {
		return winResourceUpdate;
	}
	public void setWinResourceUpdate(Window winResourceUpdate) {
		this.winResourceUpdate = winResourceUpdate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
	public ArrayList<SkillsetMasterbean> getSkillList() {
		return skillList;
	}
	public void setSkillList(ArrayList<SkillsetMasterbean> skillList) {
		this.skillList = skillList;
	}	
}