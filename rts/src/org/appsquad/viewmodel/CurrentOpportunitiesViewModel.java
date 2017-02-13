package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.service.CurrentOpportunitiesService;
import org.appsquad.utility.RuntimePopulateRoleBasedOnUserId;
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
import org.zkoss.zul.Window;

public class CurrentOpportunitiesViewModel {
	CurrentOpportunitiesBean currentOpportunitiesBean = new CurrentOpportunitiesBean(); 
	UserprofileBean userprofileBean = new UserprofileBean();
	
	private ArrayList<CurrentOpportunitiesBean> currentOpportunitiesBeanList = null;
	private ArrayList<UserprofileBean> userprofileBeanList = new ArrayList<UserprofileBean>();
	private ArrayList<Integer> idList = null;
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	private String roleName;
	private boolean comboBoxDisable = false;
	private boolean sendToApproverVisibility = false;
	private boolean createOfferVisibility = false;
	private boolean tenureFromDisable = false;
	private boolean tenureToDisable = false;
	private boolean chargeoutRateDisable = false;
	private boolean resourceSalaryDisable = false;
	private boolean marginDisable = false;
	private boolean approverNameDisable = false;
	private boolean createOfferDisable = false;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		currentOpportunitiesBean.setSessionUserId(userId);
		System.out.println("USER ID:"+userId);
		fetchDataWrtDataEntryOrApprover();
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void reLoadPreBillingMainPage(){
		fetchDataWrtDataEntryOrApprover();
	}
	
	public void fetchDataWrtDataEntryOrApprover(){
		roleName = RuntimePopulateRoleBasedOnUserId.populateRoleBasedOnUserId(userId);
		System.out.println("IN MAIN SCREEN ROLE NAME :"+roleName);
		if(roleName.equalsIgnoreCase("DATA ENTRY OPERATOR")){
			setCurrentOpportunitiesBeanList(CurrentOpportunitiesService.loadCurrentOpportunityDetails());
		}else if(roleName.equalsIgnoreCase("APPROVER")){
			idList = CurrentOpportunitiesService.fetchClientIdList(userId);
			System.out.println("ID LIST SIZE IS :"+idList.size()+"------"+Arrays.toString(idList.toArray()));
			if(idList.size()>0){
			   setCurrentOpportunitiesBeanList(CurrentOpportunitiesService.loadCurrentOpportunityDetailsForApprover(idList));	
			}
		}else if(roleName.equalsIgnoreCase("APPROVER AND DATA ENTRY OPERATOR")){
			setCurrentOpportunitiesBeanList(CurrentOpportunitiesService.loadCurrentOpportunityDetails());
		}
	}
	
	@Command
	@NotifyChange("*")
	public void update(@BindingParam("bean") CurrentOpportunitiesBean opportunitiesBean){
		Map<String, Object> parentMap = new HashMap<String, Object>();
		parentMap.put("ParentObject", opportunitiesBean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/currentOppurUpdate.zul", null, parentMap);
		window.doModal();
	}
	
	
    /************************************************* GETTER AND SETTER METHOD ************************************************************************/
	
	public CurrentOpportunitiesBean getCurrentOpportunitiesBean() {
		return currentOpportunitiesBean;
	}
	public void setCurrentOpportunitiesBean(
			CurrentOpportunitiesBean currentOpportunitiesBean) {
		this.currentOpportunitiesBean = currentOpportunitiesBean;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public ArrayList<UserprofileBean> getUserprofileBeanList() {
		return userprofileBeanList;
	}
	public void setUserprofileBeanList(
			ArrayList<UserprofileBean> userprofileBeanList) {
		this.userprofileBeanList = userprofileBeanList;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isComboBoxDisable() {
		return comboBoxDisable;
	}
	public void setComboBoxDisable(boolean comboBoxDisable) {
		this.comboBoxDisable = comboBoxDisable;
	}
	public ArrayList<Integer> getIdList() {
		return idList;
	}
	public void setIdList(ArrayList<Integer> idList) {
		this.idList = idList;
	}
	public boolean isSendToApproverVisibility() {
		return sendToApproverVisibility;
	}
	public void setSendToApproverVisibility(boolean sendToApproverVisibility) {
		this.sendToApproverVisibility = sendToApproverVisibility;
	}
	public boolean isCreateOfferVisibility() {
		return createOfferVisibility;
	}
	public void setCreateOfferVisibility(boolean createOfferVisibility) {
		this.createOfferVisibility = createOfferVisibility;
	}
	public boolean isTenureFromDisable() {
		return tenureFromDisable;
	}
	public void setTenureFromDisable(boolean tenureFromDisable) {
		this.tenureFromDisable = tenureFromDisable;
	}
	public boolean isTenureToDisable() {
		return tenureToDisable;
	}
	public void setTenureToDisable(boolean tenureToDisable) {
		this.tenureToDisable = tenureToDisable;
	}
	public boolean isChargeoutRateDisable() {
		return chargeoutRateDisable;
	}
	public void setChargeoutRateDisable(boolean chargeoutRateDisable) {
		this.chargeoutRateDisable = chargeoutRateDisable;
	}
	public boolean isResourceSalaryDisable() {
		return resourceSalaryDisable;
	}
	public void setResourceSalaryDisable(boolean resourceSalaryDisable) {
		this.resourceSalaryDisable = resourceSalaryDisable;
	}
	public boolean isMarginDisable() {
		return marginDisable;
	}
	public void setMarginDisable(boolean marginDisable) {
		this.marginDisable = marginDisable;
	}
	public boolean isApproverNameDisable() {
		return approverNameDisable;
	}
	public void setApproverNameDisable(boolean approverNameDisable) {
		this.approverNameDisable = approverNameDisable;
	}
	public boolean isCreateOfferDisable() {
		return createOfferDisable;
	}
	public void setCreateOfferDisable(boolean createOfferDisable) {
		this.createOfferDisable = createOfferDisable;
	}
	public ArrayList<CurrentOpportunitiesBean> getCurrentOpportunitiesBeanList() {
		return currentOpportunitiesBeanList;
	}
	public void setCurrentOpportunitiesBeanList(
			ArrayList<CurrentOpportunitiesBean> currentOpportunitiesBeanList) {
		this.currentOpportunitiesBeanList = currentOpportunitiesBeanList;
	}
}
