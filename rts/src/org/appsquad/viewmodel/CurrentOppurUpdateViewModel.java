package org.appsquad.viewmodel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.UserClientMappingBean;
import org.appsquad.dao.CurrentOpportunitiesDao;
import org.appsquad.database.DbConnection;
import org.appsquad.service.CurrentOpportunitiesService;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.utility.RuntimePopulateRoleBasedOnUserId;
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

public class CurrentOppurUpdateViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winCurrOppurUpdate")
	private Window winCurrOppurUpdatePage;
	private boolean comboBoxDisable = false;
	private boolean approverNameDisable = false;
	private String roleName;
	private boolean sendToApproverVisibility = false;
	private boolean createOfferVisibility = false;
	private boolean saveButtonVisibility = false;
	private String previousApproverName = "";
	
	private CurrentOpportunitiesBean currentOpportunitiesBean = new CurrentOpportunitiesBean();
	
	private ArrayList<UserClientMappingBean> userprofileBeanList = null;
	private ArrayList<UserClientMappingBean> userClBeanList = null;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("ParentObject")CurrentOpportunitiesBean bean)throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		currentOpportunitiesBean = bean;
		userId = (String) sessions.getAttribute("userId");
		currentOpportunitiesBean.setLoginID(userId);
		previousApproverName = currentOpportunitiesBean.getBean().getUserID();
		currentOpportunitiesBean.setModalTitle("Pre Billing Confirmation Update - ( RID: "+currentOpportunitiesBean.getRid()+" - "+"Resource Name: "+currentOpportunitiesBean.getResourceName()+")");
		loadApproverName();
		fetchDataWrtDataEntryOrApprover();
	}
	
	
	public void fetchDataWrtDataEntryOrApprover() throws Exception{
		int number = 0;
		int count = 0;
		roleName = RuntimePopulateRoleBasedOnUserId.populateRoleBasedOnUserId(userId);
		System.out.println("IN UPDATE SCREEN ROLE NAME :"+roleName);
		if(roleName.equalsIgnoreCase("DATA ENTRY OPERATOR")){
			comboBoxDisable = true;
			sendToApproverVisibility = true;
			approverNameDisable = false;
		}else if(roleName.equalsIgnoreCase("APPROVER")){
			comboBoxDisable = false;
			createOfferVisibility = true;
			approverNameDisable = true;
		}else if(roleName.equalsIgnoreCase("APPROVER AND DATA ENTRY OPERATOR")){
			
			number = CurrentOpportunitiesDao.coutrowForDataEntryAndApproverBoth(currentOpportunitiesBean);
			System.out.println("IN INIT METHOD NUMBER FOR DATA ENTRY AND APPROVER BOTH PERSON::::"+number);
			
			if(number==0){
				count = CurrentOpportunitiesDao.checjCountWhetherTraetDataEntryOrApprover(userId, currentOpportunitiesBean.getClientName());
				if(count==0){
					comboBoxDisable = true;
					approverNameDisable = false;
					sendToApproverVisibility = true;
				}else{
					comboBoxDisable = false;
					saveButtonVisibility = true;
					approverNameDisable = false;
				}
			}else if(number>0){
				count = CurrentOpportunitiesDao.checjCountWhetherTraetDataEntryOrApprover(userId, currentOpportunitiesBean.getClientName());
				
				if(count==0){
					comboBoxDisable = true;
					approverNameDisable = false;
					sendToApproverVisibility = true;
				}else{
					comboBoxDisable = false;
					approverNameDisable = false;
					saveButtonVisibility = true;
				}
			}
		}
	}
	
	
	@Command
	@NotifyChange("*")
	public void onCloseOperationPreBilling(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		winCurrOppurUpdatePage.detach();
		BindUtils.postGlobalCommand(null, null, "reLoadPreBillingMainPage", null);
	}
	
	@Command
	@NotifyChange("*")
	public void approverPerson(){
		String currentApproverName = "";
		int num = 0;
		int countNum = 0;
		
		num = CurrentOpportunitiesDao.coutrowForDataEntryAndApproverBoth(currentOpportunitiesBean);
		if(num==0){
			countNum = CurrentOpportunitiesDao.checjCountWhetherTraetDataEntryOrApprover(userId, currentOpportunitiesBean.getClientName());
			if(countNum>0){
				System.out.println("nothing");
			}
		}else{
			roleName = RuntimePopulateRoleBasedOnUserId.populateRoleBasedOnUserId(userId);
			System.out.println("IN UPDATE SCREEN ROLE NAME :"+roleName);
			
			currentApproverName = currentOpportunitiesBean.getBean().getUserID();
			
			if(roleName.equalsIgnoreCase("APPROVER AND DATA ENTRY OPERATOR")){
				if(previousApproverName.equalsIgnoreCase(currentApproverName)){
					
				}else{
					sendToApproverVisibility = true;
					saveButtonVisibility = false;
					currentOpportunitiesBean.setApproval(null);
					comboBoxDisable = true;
				}
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeFromDate(){
		if(currentOpportunitiesBean.getTenureTosql()!=null){
			   if(currentOpportunitiesBean.getTenureTosql().after(currentOpportunitiesBean.getTenureFromsql())){
			     }else {
			    	 currentOpportunitiesBean.setTenureTosql(null);
				    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
			   }
		   }else{
			   System.out.println("nothing.");
		   }
	   }
	
	@Command
	@NotifyChange("*")
	public void onChangeToDate(){
	 	   if(currentOpportunitiesBean.getTenureFromsql()!= null){
	 		   if(currentOpportunitiesBean.getTenureTosql().after(currentOpportunitiesBean.getTenureFromsql())){
	 		     }else {
	 		    	currentOpportunitiesBean.setTenureTosql(null);
	 			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
	 		   }
	 	   }else {
	 		   currentOpportunitiesBean.setTenureTosql(null);
	 		   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
	 	   }
	    }
	
	public void loadApproverName() throws Exception{
		try {
			connection = DbConnection.createConnection();
			currentOpportunitiesBean.setUserClBeanList(CurrentOpportunitiesDao.userBeanList(connection, currentOpportunitiesBean.getClientId()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(connection!=null){
				connection.close();
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void chargeOut(){
		if(currentOpportunitiesBean.getChargeoutRate()!=null && currentOpportunitiesBean.getChargeoutRate()>0){
			currentOpportunitiesBean.setMargin(null);
		}else{
			Messagebox.show(" Please Enter Charge Out Rate ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void resourceSalary(){
		if(currentOpportunitiesBean.getResourceSalary()!=null && currentOpportunitiesBean.getResourceSalary()>0){
			currentOpportunitiesBean.setMargin(null);
		}else{
			Messagebox.show(" Please Enter Resource Salary ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void createMargin(){
		if(currentOpportunitiesBean.getChargeoutRate()!=null && currentOpportunitiesBean.getChargeoutRate()>0){
			if(currentOpportunitiesBean.getResourceSalary()!=null && currentOpportunitiesBean.getResourceSalary()>0){
				if(currentOpportunitiesBean.getChargeoutRate()<currentOpportunitiesBean.getResourceSalary()){
					currentOpportunitiesBean.setResourceSalary(null);
					currentOpportunitiesBean.setMargin(null);
					currentOpportunitiesBean.setPercentage(null);
					Messagebox.show(" Resource Sallary Can't Be Greater Than The Charge Out Rate ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				}else{
					if(currentOpportunitiesBean.getChargeoutRate()!=null && currentOpportunitiesBean.getChargeoutRate()>0){
						if(currentOpportunitiesBean.getResourceSalary()!=null && currentOpportunitiesBean.getResourceSalary()>0){
							currentOpportunitiesBean.setMargin((currentOpportunitiesBean.getChargeoutRate()-currentOpportunitiesBean.getResourceSalary()));
						    System.out.println(((currentOpportunitiesBean.getChargeoutRate()-currentOpportunitiesBean.getResourceSalary())/(currentOpportunitiesBean.getResourceSalary()*100)));
							currentOpportunitiesBean.setPercentage((float)((currentOpportunitiesBean.getChargeoutRate()-currentOpportunitiesBean.getResourceSalary())/(currentOpportunitiesBean.getResourceSalary()*100)));
						}else{
							Messagebox.show(" Please Enter Resource Salary ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
						}
					}else{
						Messagebox.show(" Please Enter Charge Out Rate ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
					}	
				}		
			}else{
				Messagebox.show(" Resource Salary Can't Be Blank ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else{
			Messagebox.show(" Charge Out Rate Can't Be Blank ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onSend(){
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
			//fetchDataWrtDataEntryOrApprover();
			winCurrOppurUpdatePage.detach();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCreate() throws Exception{
		currentOpportunitiesBean.setOnClickButtonValue("onCreate");
		boolean flagCreateInsert = false;
		boolean flagCreateLogInsert = false;
		boolean flagCreateUpdate = false;
		boolean flag1stInsert = false;
		boolean flag1stDelete = false;
		boolean emailSend = false;
		String name = "";
		String email = "";
		String approverName= ""; 
		if(currentOpportunitiesBean.getBean().getUserID()!=null){
			System.out.println("TRACKING ID IS :"+currentOpportunitiesBean.getReqResStatusTrackingId());
			approverName = CurrentOpportunitiesDao.fetchApproverNameDao(currentOpportunitiesBean.getReqResStatusTrackingId());
			System.out.println(approverName);
			if(approverName.equalsIgnoreCase(userId)){
				if(CurrentOpportunitiesService.validateForApprover(currentOpportunitiesBean)){
					System.out.println("before name :"+currentOpportunitiesBean.getReqResStatusTrackingId());
					name = CurrentOpportunitiesDao.getDataEntryOperatorName(currentOpportunitiesBean.getReqResStatusTrackingId());
					System.out.println("IN ON CREATE METHOD NAME :"+name);
					flag1stDelete = CurrentOpportunitiesDao.deleteRoleData(currentOpportunitiesBean);
					if(flag1stDelete){
						currentOpportunitiesBean.setLoginID(name);
						System.out.println("*****"+currentOpportunitiesBean.getLoginID());
						flag1stInsert = CurrentOpportunitiesService.insertTrackingDetails(currentOpportunitiesBean);
					}
					
					System.out.println("FLAG INSERT FOR APPROVER IS :"+flag1stInsert);
					
					if(flag1stInsert){
						flagCreateInsert = CurrentOpportunitiesService.updateTrackingDetailsService(currentOpportunitiesBean);	
					}
					System.out.println(flagCreateInsert);
					if(flagCreateInsert){
						flagCreateUpdate = CurrentOpportunitiesService.updateTrackingService(currentOpportunitiesBean);
						System.out.println(flagCreateUpdate);
						if(flagCreateUpdate){
							
							String emailId = CurrentOpportunitiesDao.fetchEmailId(name);
							System.out.println(emailId);
							emailSend = SendEmail.validator(emailId);
							System.out.println("flag email send is :"+emailSend);
							if(emailSend){
								SendEmail.generateAndSendEmailForApproveOrReject(emailId, currentOpportunitiesBean.getApproval(), currentOpportunitiesBean.getReqResStatusTrackingId())	;
							}else{
								System.out.println("APPROVER'S EMAIL ID IS NOT CORRECT. ");
							}
							
							System.out.println(currentOpportunitiesBean.getApproval());
							if(currentOpportunitiesBean.getApproval().equalsIgnoreCase("Approve")){
								Messagebox.show(" Approved Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
							}else{
								Messagebox.show(" Rejected Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
							}
							
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
							winCurrOppurUpdatePage.detach();
							BindUtils.postGlobalCommand(null, null, "reLoadPreBillingMainPage", null);
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
	
	
	@Command
	@NotifyChange("*")
	public void onSave() throws Exception{
		currentOpportunitiesBean.setOnClickButtonValue("onSave");
		boolean flagDelete = false;
		boolean flagInsert = false;
		boolean email = false;
		boolean emailSend = false;
		boolean flagCreateUpdate = false;
		boolean flagCreateLogInsert = false;
		int count = 0;
		String approverName= "";
		
		count = CurrentOpportunitiesDao.coutrowForDataEntryAndApproverBoth(currentOpportunitiesBean);
		System.out.println(" SUPER ADMIN -> COUNT :"+count);
		
		if(count>0){
			System.out.println("METHOD 1 FOR BOTH");
			approverName = CurrentOpportunitiesDao.fetchApproverNameDao(currentOpportunitiesBean.getReqResStatusTrackingId());
			System.out.println(approverName);
			
			if(approverName.equalsIgnoreCase(userId)){
				if(CurrentOpportunitiesService.validateForApprover(currentOpportunitiesBean)){
					flagDelete = CurrentOpportunitiesDao.deleteRoleData(currentOpportunitiesBean);
					if(flagDelete){
						flagInsert = CurrentOpportunitiesService.insertTrackingDetails(currentOpportunitiesBean);
					}
					if(flagInsert){
							flagCreateUpdate = CurrentOpportunitiesService.updateTrackingService(currentOpportunitiesBean);
							System.out.println(flagCreateUpdate);
							if(flagCreateUpdate){
								
								String emailId = CurrentOpportunitiesDao.fetchEmailId(userId);
								System.out.println(emailId);
								emailSend = SendEmail.validator(emailId);
								System.out.println("flag email send is :"+email);
								if(emailSend){
									SendEmail.generateAndSendEmailForApproveOrReject(emailId, currentOpportunitiesBean.getApproval(), currentOpportunitiesBean.getReqResStatusTrackingId())	;
								}else{
									System.out.println("APPROVER'S EMAIL ID IS NOT CORRECT. ");
								}
								
								System.out.println(currentOpportunitiesBean.getApproval());
								if(currentOpportunitiesBean.getApproval().equalsIgnoreCase("Approve")){
									Messagebox.show(" Approved Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
								}else{
									Messagebox.show(" Rejected Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
								}
								
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
								winCurrOppurUpdatePage.detach();
								BindUtils.postGlobalCommand(null, null, "reLoadPreBillingMainPage", null);
							}
					  }
				}
			}else{
				Messagebox.show(" You Are Not The Assigned Approver To Approve This Request. ","Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
			
		}else{
			System.out.println("METHOD 2 FOR BOTH");
			if(CurrentOpportunitiesService.validateForApprover(currentOpportunitiesBean)){
				flagInsert = CurrentOpportunitiesService.insertTrackingDetails(currentOpportunitiesBean);	
			}
			if(flagInsert){
				flagCreateUpdate = CurrentOpportunitiesService.updateTrackingService(currentOpportunitiesBean);
				System.out.println(flagCreateUpdate);
				if(flagCreateUpdate){
					
					String emailId = CurrentOpportunitiesDao.fetchEmailId(userId);
					System.out.println(emailId);
					email = SendEmail.validator(emailId);
					System.out.println("flag email send is :"+email);
					if(emailSend){
						SendEmail.generateAndSendEmailForApproveOrReject(emailId, currentOpportunitiesBean.getApproval(), currentOpportunitiesBean.getReqResStatusTrackingId())	;
					}else{
						System.out.println("APPROVER'S EMAIL ID IS NOT CORRECT. ");
					}
					
					System.out.println(currentOpportunitiesBean.getApproval());
					if(currentOpportunitiesBean.getApproval().equalsIgnoreCase("Approve")){
						Messagebox.show(" Approved Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Rejected Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
					}
					
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
					winCurrOppurUpdatePage.detach();
					BindUtils.postGlobalCommand(null, null, "reLoadPreBillingMainPage", null);
				}
			 }
		 }
	}
	
	/**************************************************************************************************************************************************/
	
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
	public Window getWinCurrOppurUpdatePage() {
		return winCurrOppurUpdatePage;
	}
	public void setWinCurrOppurUpdatePage(Window winCurrOppurUpdatePage) {
		this.winCurrOppurUpdatePage = winCurrOppurUpdatePage;
	}
	public CurrentOpportunitiesBean getCurrentOpportunitiesBean() {
		return currentOpportunitiesBean;
	}
	public void setCurrentOpportunitiesBean(
			CurrentOpportunitiesBean currentOpportunitiesBean) {
		this.currentOpportunitiesBean = currentOpportunitiesBean;
	}
	public boolean isComboBoxDisable() {
		return comboBoxDisable;
	}
	public void setComboBoxDisable(boolean comboBoxDisable) {
		this.comboBoxDisable = comboBoxDisable;
	}
	public boolean isApproverNameDisable() {
		return approverNameDisable;
	}
	public void setApproverNameDisable(boolean approverNameDisable) {
		this.approverNameDisable = approverNameDisable;
	}
	public ArrayList<UserClientMappingBean> getUserprofileBeanList() {
		return userprofileBeanList;
	}
	public void setUserprofileBeanList(ArrayList<UserClientMappingBean> userprofileBeanList) {
		this.userprofileBeanList = userprofileBeanList;
	}
	public ArrayList<UserClientMappingBean> getUserClBeanList() {
		return userClBeanList;
	}
	public void setUserClBeanList(ArrayList<UserClientMappingBean> userClBeanList) {
		this.userClBeanList = userClBeanList;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public boolean isSaveButtonVisibility() {
		return saveButtonVisibility;
	}
	public void setSaveButtonVisibility(boolean saveButtonVisibility) {
		this.saveButtonVisibility = saveButtonVisibility;
	}
	public String getPreviousApproverName() {
		return previousApproverName;
	}
	public void setPreviousApproverName(String previousApproverName) {
		this.previousApproverName = previousApproverName;
	}
}
