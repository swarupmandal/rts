package org.appsquad.viewmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.appsquad.bean.RoleMenusBean;
import org.appsquad.database.DbConnection;
import org.zkoss.bind.annotation.AfterCompose;
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

public class HomePageViewModel {
	final static Logger logger = Logger.getLogger(HomePageViewModel.class);
	Session session = null;
	private String userId;
	
	private boolean masterLinkVisibility = false;
	private boolean userRoleLinkVisibility = false;
	private boolean clientLinkVisibility = false;
	private boolean skillSetLinkVisibility = false;
	private boolean statusLinkVisibility = false;
	private boolean registeredResourceLinkVisibility = false;
	private boolean userrofileLinkVisibility = false;
	
	private boolean tranactionLinkVisibility = false;
	private boolean requirementGenerationLinkVisibility = false;
	private boolean assignResourceToRIdLinkVisibility = false;
	private boolean resourceAllocationTrackingLinkVisibility = false;
	
	private boolean reportLinkVisibility = false;
	private boolean requirementWiseStatusReport = false;
	private boolean individualClientReport = false;
	private boolean individualRequirementRIDReport = false;
	private boolean skillSetWiseRequirementList = false;
	private boolean resourceDetailPerRequirementCV = false;
	
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		userId = (String) session.getAttribute("userId");
		if(userId==null){
			Executions.sendRedirect("/welcome1.zul");
		}else{
			userId = "Welcome :"+ userId;
			setRoleAccessLink(userId);
		}
	}
	
	public void setRoleAccessLink(String userId){
		try {
			Connection connection = DbConnection.createConnection();
			sql_connection:{
				try {
					//1st SQL block
					sql_1:{
					    PreparedStatement preparedStatement = null;
					    try {
							String sql = "select * from vw_role_priviledge_details where user_id = ? ";
							preparedStatement = connection.prepareStatement(sql);
							preparedStatement.setString(1, userId);
							System.out.println("HOME PAGE VIEW MODEL SQL QUERY :"+preparedStatement);
							ResultSet resultSet = preparedStatement.executeQuery();
							while(resultSet.next()){
								RoleMenusBean bean = new RoleMenusBean();
								bean.setMenusId(resultSet.getInt("menus_id"));
								bean.setUserId(resultSet.getString("user_id"));
								bean.setUserSerialId(resultSet.getInt("user_serial_id"));
								
								if(bean.getMenusId()>=5 && bean.getMenusId()<=10){
									bean.setMasterLink("Y");
									masterLinkVisibility = true;
									if(bean.getMenusId()==5){
										userRoleLinkVisibility = true;
									}else if(bean.getMenusId()==6){
										clientLinkVisibility = true;
									}else if(bean.getMenusId()==7){
										skillSetLinkVisibility = true;
									}else if(bean.getMenusId()==8){
										statusLinkVisibility = true;
									}else if(bean.getMenusId()==9){
										registeredResourceLinkVisibility = true;
									}else if(bean.getMenusId()==10){
										userrofileLinkVisibility = true;
									}
								}else if(bean.getMenusId()>=11 && bean.getMenusId()<=13){
									tranactionLinkVisibility = true;
									if(bean.getMenusId()==11){
										requirementGenerationLinkVisibility = true;
									}else if(bean.getMenusId()==12){
										assignResourceToRIdLinkVisibility = true;
									}else if(bean.getMenusId()==13){
										resourceAllocationTrackingLinkVisibility = true;
									}
								}else if(bean.getMenusId()>=14 && bean.getMenusId()<=18){
									reportLinkVisibility = true;
									if(bean.getMenusId()==14){
										requirementWiseStatusReport = true;
									}else if(bean.getMenusId()==15){
										individualClientReport = true;
									}else if(bean.getMenusId()==16){
										individualRequirementRIDReport = true;
									}else if(bean.getMenusId()==17){
										skillSetWiseRequirementList = true;
									}else if(bean.getMenusId()==18){
										resourceDetailPerRequirementCV = true;
									}
								}
							}
						} finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					logger.fatal(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSignOut(){
		System.out.println("Signiing out...");
		if(session!=null){
			session.removeAttribute("userId");
			session=null;
			Executions.sendRedirect("/welcome1.zul");
			System.out.println("--- -- - >>> >> >");
		}
	}

	@Command
	@NotifyChange("*")
	public void onClickChangePassword(){
		Window window = (Window) Executions.createComponents("/WEB-INF/view/changepassword.zul", null, null);
		window.doModal();
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void changepassUpdate(){
		onClickSignOut();	
	}
	
	/**********************************************************************************************************************************/
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isMasterLinkVisibility() {
		return masterLinkVisibility;
	}
	public void setMasterLinkVisibility(boolean masterLinkVisibility) {
		this.masterLinkVisibility = masterLinkVisibility;
	}
	public boolean isUserRoleLinkVisibility() {
		return userRoleLinkVisibility;
	}
	public void setUserRoleLinkVisibility(boolean userRoleLinkVisibility) {
		this.userRoleLinkVisibility = userRoleLinkVisibility;
	}
	public boolean isClientLinkVisibility() {
		return clientLinkVisibility;
	}
	public void setClientLinkVisibility(boolean clientLinkVisibility) {
		this.clientLinkVisibility = clientLinkVisibility;
	}
	public boolean isSkillSetLinkVisibility() {
		return skillSetLinkVisibility;
	}
	public void setSkillSetLinkVisibility(boolean skillSetLinkVisibility) {
		this.skillSetLinkVisibility = skillSetLinkVisibility;
	}
	public boolean isStatusLinkVisibility() {
		return statusLinkVisibility;
	}
	public void setStatusLinkVisibility(boolean statusLinkVisibility) {
		this.statusLinkVisibility = statusLinkVisibility;
	}
	public boolean isRegisteredResourceLinkVisibility() {
		return registeredResourceLinkVisibility;
	}
	public void setRegisteredResourceLinkVisibility(
			boolean registeredResourceLinkVisibility) {
		this.registeredResourceLinkVisibility = registeredResourceLinkVisibility;
	}
	public boolean isUserrofileLinkVisibility() {
		return userrofileLinkVisibility;
	}
	public void setUserrofileLinkVisibility(boolean userrofileLinkVisibility) {
		this.userrofileLinkVisibility = userrofileLinkVisibility;
	}
	public static Logger getLogger() {
		return logger;
	}
	public boolean isTranactionLinkVisibility() {
		return tranactionLinkVisibility;
	}
	public void setTranactionLinkVisibility(boolean tranactionLinkVisibility) {
		this.tranactionLinkVisibility = tranactionLinkVisibility;
	}
	public boolean isRequirementGenerationLinkVisibility() {
		return requirementGenerationLinkVisibility;
	}
	public void setRequirementGenerationLinkVisibility(
			boolean requirementGenerationLinkVisibility) {
		this.requirementGenerationLinkVisibility = requirementGenerationLinkVisibility;
	}
	public boolean isAssignResourceToRIdLinkVisibility() {
		return assignResourceToRIdLinkVisibility;
	}
	public void setAssignResourceToRIdLinkVisibility(
			boolean assignResourceToRIdLinkVisibility) {
		this.assignResourceToRIdLinkVisibility = assignResourceToRIdLinkVisibility;
	}
	public boolean isResourceAllocationTrackingLinkVisibility() {
		return resourceAllocationTrackingLinkVisibility;
	}
	public void setResourceAllocationTrackingLinkVisibility(
			boolean resourceAllocationTrackingLinkVisibility) {
		this.resourceAllocationTrackingLinkVisibility = resourceAllocationTrackingLinkVisibility;
	}
	public boolean isReportLinkVisibility() {
		return reportLinkVisibility;
	}
	public void setReportLinkVisibility(boolean reportLinkVisibility) {
		this.reportLinkVisibility = reportLinkVisibility;
	}
	public boolean isRequirementWiseStatusReport() {
		return requirementWiseStatusReport;
	}
	public void setRequirementWiseStatusReport(boolean requirementWiseStatusReport) {
		this.requirementWiseStatusReport = requirementWiseStatusReport;
	}
	public boolean isIndividualClientReport() {
		return individualClientReport;
	}
	public void setIndividualClientReport(boolean individualClientReport) {
		this.individualClientReport = individualClientReport;
	}
	public boolean isIndividualRequirementRIDReport() {
		return individualRequirementRIDReport;
	}
	public void setIndividualRequirementRIDReport(
			boolean individualRequirementRIDReport) {
		this.individualRequirementRIDReport = individualRequirementRIDReport;
	}
	public boolean isSkillSetWiseRequirementList() {
		return skillSetWiseRequirementList;
	}
	public void setSkillSetWiseRequirementList(boolean skillSetWiseRequirementList) {
		this.skillSetWiseRequirementList = skillSetWiseRequirementList;
	}
	public boolean isResourceDetailPerRequirementCV() {
		return resourceDetailPerRequirementCV;
	}
	public void setResourceDetailPerRequirementCV(
			boolean resourceDetailPerRequirementCV) {
		this.resourceDetailPerRequirementCV = resourceDetailPerRequirementCV;
	}
}
