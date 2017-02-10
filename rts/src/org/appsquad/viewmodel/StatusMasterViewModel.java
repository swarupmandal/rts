package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.StatusMasterDao;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.StatusMasterService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

public class StatusMasterViewModel {
	StatusMasterBean statusMasterBean=new StatusMasterBean();
	private ArrayList<StatusMasterBean> statuslist=new ArrayList<StatusMasterBean>();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	private static boolean flag = false;
	private static boolean flagLogDelete = false;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		statusMasterBean.setUserId(userId);
		statusMasterBean.setSessionUserId(userId);
		statuslist = StatusMasterDao.onLoadStatusDeatils();
		statusMasterBean.setChkbxDisable(StatusMasterService.setForPreBill(statuslist)); 
		statusMasterBean.setInitChkbxDisable(StatusMasterService.setForInitialStage(statuslist));
		statusMasterBean.setFinalChkbxDisable(StatusMasterService.setForFinalStage(statuslist));
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalStatusDetailsUpdate(){
		statuslist = StatusMasterDao.onLoadStatusDeatils();
		statusMasterBean.setInitChkbxDisable(StatusMasterService.setForInitialStage(statuslist));
		statusMasterBean.setChkbxDisable(StatusMasterService.setForPreBill(statuslist));
		statusMasterBean.setFinalChkbxDisable(StatusMasterService.setForFinalStage(statuslist));
	}
	
	@Command
	@NotifyChange("*")
	public void status(){
		if(statusMasterBean.getStatus()!=null){
			int size = statusMasterBean.getStatus().length();
			String newName = statusMasterBean.getStatus().trim();
			int newSize = newName.length();
			statusMasterBean.setStatus(newName);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCheckInitial(){
		if(statusMasterBean.isInitial()){
			statusMasterBean.setPreBilled(false);
			statusMasterBean.setFinalStage(false);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCheckPreBill(){
		if(statusMasterBean.isPreBilled()){
			statusMasterBean.setInitial(false);
			statusMasterBean.setFinalStage(false);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCheckFinal(){
		if(statusMasterBean.isFinalStage()){
			statusMasterBean.setPreBilled(false);
			statusMasterBean.setInitial(false);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickStatusSave(){
		boolean flagInsert = false;
		boolean flagLogInsert = false;
		int count = 0;
		count = StatusMasterDao.countStatusNumber(statusMasterBean);
		if(count>0){
			Messagebox.show("This status already exists. Please enter a new status. ","Status exixts",Messagebox.OK,Messagebox.EXCLAMATION);
			statusMasterBean.setStatus(null);
		}else{
			flagInsert = StatusMasterService.insertClientMasterData(statusMasterBean);
			if(flagInsert){
				statusMasterBean.setOperation("INSERT");
				statusMasterBean.setOperationId(1);
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
				flagLogInsert = LogAuditServiceClass.insertIntoLogTable(statusMasterBean.getMainScreenName(), statusMasterBean.getChileScreenName(), 
																		statusMasterBean.getSessionUserId(), statusMasterBean.getOperation(),currentDate,
																		statusMasterBean.getOperationId());
				clearScreen();
				//System.out.println("flagLogInsert Is:"+flagLogInsert);
				StatusMasterService.clearAllField(statusMasterBean);
				statuslist = StatusMasterDao.onLoadStatusDeatils();
				statusMasterBean.setPreBilled(false);
				statusMasterBean.setInitial(false);
				statusMasterBean.setFinalStage(false);
				statusMasterBean.setChkbxDisable(StatusMasterService.setForPreBill(statuslist)); 
				statusMasterBean.setInitChkbxDisable(StatusMasterService.setForInitialStage(statuslist));
				statusMasterBean.setFinalChkbxDisable(StatusMasterService.setForFinalStage(statuslist));
			}	
		}
	}
	
	public void clearScreen(){
		StatusMasterService.clearAllField(statusMasterBean);
		statuslist = StatusMasterDao.onLoadStatusDeatils();
		statusMasterBean.setPreBilled(false);
		statusMasterBean.setInitial(false);
		statusMasterBean.setFinalStage(false);
		statusMasterBean.setChkbxDisable(StatusMasterService.setForPreBill(statuslist)); 
		statusMasterBean.setInitChkbxDisable(StatusMasterService.setForInitialStage(statuslist));
		statusMasterBean.setFinalChkbxDisable(StatusMasterService.setForFinalStage(statuslist));
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDelete(@BindingParam("bean") StatusMasterBean masterBean){
		Messagebox.show("Are you sure to delete?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		        	flag = StatusMasterService.deleteStatusMasterData(masterBean);
		    		if(flag){
		    			masterBean.setOperation("DELETE");
		    			masterBean.setSessionUserId(userId);
		    			masterBean.setOperationId(3);
						Calendar calendar = Calendar.getInstance();
					    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
						//System.out.println("CREATION DATE :"+currentDate);
						flagLogDelete = LogAuditServiceClass.insertIntoLogTable(masterBean.getMainScreenName(), masterBean.getChileScreenName(), 
																					masterBean.getSessionUserId(), masterBean.getOperation(),currentDate,
																					   masterBean.getOperationId());
						//System.out.println("flagLogDelete Is:"+flagLogDelete);
		    			BindUtils.postGlobalCommand(null, null, "globalStatusDetailsUpdate", null);
		    		}
		        } else {
		           
		        }
		    }
		});
	}
	
	@Command
	@NotifyChange("*")
	public void onClickEditButton(@BindingParam("bean") StatusMasterBean masterBean){
		masterBean.setStatusDisabled(false);
		masterBean.setEditButtonDisable(true);
		masterBean.setSaveButtonDisable(false);
		masterBean.setChkbxDisable(false);
		masterBean.setInitChkbxDisable(false);
		masterBean.setFinalChkbxDisable(false);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSaveButton(@BindingParam("bean") StatusMasterBean masterBean){
		boolean flagUpdate = false;
		boolean flagLogUpdate = false;
		String newStatus = "";
		
		flagUpdate = StatusMasterService.updateClientMasterData(masterBean);
		if(flagUpdate){
			if(masterBean.isInitial()){
				StatusMasterService.updateOthersInitialStatus(masterBean);
			}
			if(masterBean.isPreBilled()){
				StatusMasterService.updateOtherStatus(masterBean);
			}
			if(masterBean.isFinalStage()){
				StatusMasterService.updateOthersFinalStatus(masterBean);
			}
			
			masterBean.setOperation("UPDATE");
			masterBean.setSessionUserId(userId);
			masterBean.setOperationId(2);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			//System.out.println("CREATION DATE :"+currentDate);
			flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(masterBean.getMainScreenName(), masterBean.getChileScreenName(), 
																		masterBean.getSessionUserId(), masterBean.getOperation(),currentDate,
																		   masterBean.getOperationId());
			//System.out.println("flagLogUpdate Is:"+flagLogUpdate);
			masterBean.setEditButtonDisable(false);
			masterBean.setSaveButtonDisable(true);
			masterBean.setStatusDisabled(true);
			//System.out.println(masterBean.isStatusDisabled());
			statuslist = StatusMasterDao.onLoadStatusDeatils();
			statusMasterBean.setChkbxDisable(StatusMasterService.setForPreBill(statuslist)); 
			statusMasterBean.setInitChkbxDisable(StatusMasterService.setForInitialStage(statuslist));
			statusMasterBean.setFinalChkbxDisable(StatusMasterService.setForFinalStage(statuslist));
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExistingStatus(){
		statuslist = StatusMasterDao.onLoadStatusDeatils();
		statusMasterBean.setChkbxDisable(StatusMasterService.setForPreBill(statuslist)); 
	}
	
	@Command
	@NotifyChange("*")
	public void onChkInit(@BindingParam("bean")StatusMasterBean statusbean){
		if(statusbean.isInitial()){
			statusbean.setChkbxDisable(true);
			statusbean.setFinalChkbxDisable(true);
		}else{
			statusbean.setChkbxDisable(false);
			statusbean.setFinalChkbxDisable(false);
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onChkPreBill(@BindingParam("bean")StatusMasterBean statusbean){
		if(statusbean.isPreBilled()){
			statusbean.setInitChkbxDisable(true);
			statusbean.setFinalChkbxDisable(true);
		}else{
			statusbean.setInitChkbxDisable(false);
			statusbean.setFinalChkbxDisable(false);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChkFinal(@BindingParam("bean")StatusMasterBean statusbean){
		if(statusbean.isFinalStage()){
			statusbean.setChkbxDisable(true);
			statusbean.setInitChkbxDisable(true);
		}else{
			statusbean.setChkbxDisable(false);
			statusbean.setInitChkbxDisable(false);
		}
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
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
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		StatusMasterViewModel.flag = flag;
	}
	public static boolean isFlagLogDelete() {
		return flagLogDelete;
	}
	public static void setFlagLogDelete(boolean flagLogDelete) {
		StatusMasterViewModel.flagLogDelete = flagLogDelete;
	}
}
