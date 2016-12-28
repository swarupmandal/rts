package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.service.CurrentOpportunitiesService;
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

public class CurrentOpportunitiesViewModel {
	CurrentOpportunitiesBean currentOpportunitiesBean = new CurrentOpportunitiesBean(); 
	UserprofileBean userprofileBean = new UserprofileBean();
	
	private ArrayList<CurrentOpportunitiesBean> currentOpportunitiesBeanList = new ArrayList<CurrentOpportunitiesBean>();
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
	
	public void fetchDataWrtDataEntryOrApprover(){
		roleName = CurrentOpportunitiesService.fetchRoleNameWrtUserId(userId);
		System.out.println("ROLE NAME :"+roleName);
		if(roleName.equalsIgnoreCase("DATA ENTRY OPERATOR")){
			comboBoxDisable = true;
			sendToApproverVisibility = true;
			approverNameDisable = false;
			tenureFromDisable = false;
			tenureToDisable = false;
			chargeoutRateDisable = false;
			resourceSalaryDisable = false;
			marginDisable = false;
			currentOpportunitiesBeanList = CurrentOpportunitiesService.loadCurrentOpportunityDetails();
		}else if(roleName.equalsIgnoreCase("APPROVER")){
			comboBoxDisable = false;
			createOfferVisibility = true;
			tenureFromDisable = true;
			approverNameDisable = true;
			tenureToDisable = true;
			chargeoutRateDisable = true;
			resourceSalaryDisable = true;
			marginDisable = true;
			idList = CurrentOpportunitiesService.fetchClientIdList(userId);
			System.out.println("ID LIST SIZE IS :"+idList.size()+"------"+Arrays.toString(idList.toArray()));
			currentOpportunitiesBeanList = CurrentOpportunitiesService.loadCurrentOpportunityDetailsForApprover(idList);
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
	
	/*@Command
	@NotifyChange("*")
	public void onSend(@BindingParam("bean") CurrentOpportunitiesBean currentOpportunitiesBean){
		currentOpportunitiesBean.setOnClickButtonValue("onSend");
		boolean flagInsert = false;
		boolean flagDelete = false;
		boolean flagLogInsert = false;
		boolean flagEmailSend = false;
		int count = 0;
		count = CurrentOpportunitiesService.fetchCountNumberITrackingDetailsTable(currentOpportunitiesBean.getReqResStatusTrackingId());
		System.out.println("COUNT IS :"+count);
		if(count==0){
			if(CurrentOpportunitiesService.validateForDataEntryOperator(currentOpportunitiesBean)){
				flagInsert = CurrentOpportunitiesService.insertTrackingDetails(currentOpportunitiesBean);	
			}
		}else{
			if(CurrentOpportunitiesService.validateForDataEntryOperator(currentOpportunitiesBean)){
				flagDelete = CurrentOpportunitiesDao.deleteRoleData(currentOpportunitiesBean);
				System.out.println(flagDelete);
				if(flagDelete){
					flagInsert = CurrentOpportunitiesService.insertTrackingDetails(currentOpportunitiesBean);
				}	
			}
		}
		System.out.println("FLAG INSERT FOR DATA ENTRY OPERATOR IS :"+flagInsert);
		if(flagInsert){
			System.out.println("APPROVER USER ID IS :"+currentOpportunitiesBean.getBean().getUserID());
			String emailId = CurrentOpportunitiesDao.fetchEmailId(currentOpportunitiesBean.getBean().getUserID());
			System.out.println(emailId);
			flagEmailSend = SendEmail.validator(emailId);
			System.out.println("flag email send is :"+flagEmailSend);
			if(flagEmailSend){
				SendEmail.generateAndSendEmail(emailId);	
			}else{
				System.out.println("APPROVER'S EMAIL ID IS NOT CORRECT. ");
			}
			currentOpportunitiesBean.setOperation("INSERT");
			currentOpportunitiesBean.setOperationId(1);
			currentOpportunitiesBean.setSessionUserId(userId);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			System.out.println("CREATION DATE :"+currentDate);
			flagLogInsert = LogAuditServiceClass.insertIntoLogTable(currentOpportunitiesBean.getMainScreenName(), currentOpportunitiesBean.getChileScreenName(), 
																	currentOpportunitiesBean.getSessionUserId(), currentOpportunitiesBean.getOperation(),currentDate,
																	currentOpportunitiesBean.getOperationId());
			System.out.println("flagLogInsert Is:"+flagLogInsert);
			fetchDataWrtDataEntryOrApprover();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCreate(@BindingParam("bean") CurrentOpportunitiesBean opportunitiesBean){
		opportunitiesBean.setOnClickButtonValue("onCreate");
		boolean flagCreateInsert = false;
		boolean flagCreateLogInsert = false;
		boolean flagCreateUpdate = false;
		boolean flag1stInsert = false;
		boolean flag1stDelete = false;
		
		String approverName= ""; 
		if(opportunitiesBean.getBean().getUserID()!=null){
			System.out.println("TRACKING ID IS :"+opportunitiesBean.getReqResStatusTrackingId());
			approverName = CurrentOpportunitiesDao.fetchApproverNameDao(opportunitiesBean.getReqResStatusTrackingId());
			System.out.println(approverName);
			if(approverName.equalsIgnoreCase(userId)){
				if(CurrentOpportunitiesService.validateForApprover(opportunitiesBean)){
					flag1stDelete = CurrentOpportunitiesDao.deleteRoleData(opportunitiesBean);
					if(flag1stDelete){
						flag1stInsert = CurrentOpportunitiesService.insertTrackingDetails(opportunitiesBean);
					}
					
					System.out.println("FLAG INSERT FOR APPROVER IS :"+flag1stInsert);
					
					if(flag1stInsert){
						flagCreateInsert = CurrentOpportunitiesService.updateTrackingDetailsService(opportunitiesBean);	
					}
					System.out.println(flagCreateInsert);
					if(flagCreateInsert){
						flagCreateUpdate = CurrentOpportunitiesService.updateTrackingService(opportunitiesBean);
						System.out.println(flagCreateUpdate);
						if(flagCreateUpdate){
							Messagebox.show(" Approval Request Saved Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
							currentOpportunitiesBean.setOperation("UPDATE");
							currentOpportunitiesBean.setOperationId(2);
							currentOpportunitiesBean.setSessionUserId(userId);
							Calendar calendar = Calendar.getInstance();
						    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
							System.out.println("CREATION DATE :"+currentDate);
							flagCreateLogInsert = LogAuditServiceClass.insertIntoLogTable(currentOpportunitiesBean.getMainScreenName(), currentOpportunitiesBean.getChileScreenName(), 
																						  currentOpportunitiesBean.getSessionUserId(), currentOpportunitiesBean.getOperation(),currentDate,
																					      currentOpportunitiesBean.getOperationId());
							System.out.println("flagLogInsert Is:"+flagCreateLogInsert);
							
							fetchDataWrtDataEntryOrApprover();
						}
					}	
				}
			}else{
				Messagebox.show(" You Are Not The Assigned Approver To Approve This Request. ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else{
			Messagebox.show(" There Is No Assigned Approver For This Row ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
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
	public ArrayList<CurrentOpportunitiesBean> getCurrentOpportunitiesBeanList() {
		return currentOpportunitiesBeanList;
	}
	public void setCurrentOpportunitiesBeanList(
			ArrayList<CurrentOpportunitiesBean> currentOpportunitiesBeanList) {
		this.currentOpportunitiesBeanList = currentOpportunitiesBeanList;
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
}
