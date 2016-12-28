package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.dao.SkillSetMasterDao;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.service.SkillSetMasterService;
import org.zkoss.bind.BindUtils;
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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class SkillsetmasterViewModel {
	SkillsetMasterbean skillsetMasterbean=new SkillsetMasterbean();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	
	private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		skillsetMasterbean.setUserId(userId);
		skillsetMasterbean.setSessionUserId(userId);
		skillList = SkillSetMasterDao.onLoadSetDeatils();
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalSkillSetDetailsUpdate(){
		skillList = SkillSetMasterDao.onLoadSetDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSetskillsubmit(){
		int countNumber = 0;
		boolean flagInsert = false;
		boolean flagLogInsert = false;
		countNumber = SkillSetMasterService.countNumberPresentSkillName(skillsetMasterbean);
		if(countNumber>0){
			 Messagebox.show("Please Enter New Skill Name!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}else{
			flagInsert = SkillSetMasterService.insertClientMasterData(skillsetMasterbean);
			if(flagInsert){
				skillsetMasterbean.setOperation("INSERT");
				skillsetMasterbean.setOperationId(1);
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
				System.out.println("CREATION DATE :"+currentDate);
				flagLogInsert = LogAuditServiceClass.insertIntoLogTable(skillsetMasterbean.getMainScreenName(), skillsetMasterbean.getChileScreenName(), 
						skillsetMasterbean.getSessionUserId(), skillsetMasterbean.getOperation(),currentDate,skillsetMasterbean.getOperationId());
				System.out.println("flagLogInsert Is:"+flagLogInsert);	
			   SkillSetMasterService.clearAllField(skillsetMasterbean);  
			}	
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(@BindingParam("bean") SkillsetMasterbean masterbean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("skillSetDetails", masterbean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/skillSetUpdate.zul", null, map);
		window.doModal();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExistingSkill(){
		skillList = SkillSetMasterDao.onLoadSetDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void skillSet(){
		if(skillsetMasterbean.getSkillset()!=null){
			int size = skillsetMasterbean.getSkillset().length();
			System.out.println(size);
			String newName = skillsetMasterbean.getSkillset().trim();
			System.out.println(newName);
			int newSize = newName.length();
			System.out.println(newSize);
			skillsetMasterbean.setSkillset(newName);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void skillsetdetails(){
		if(skillsetMasterbean.getSkillsetdetails()!=null){
			int size1 = skillsetMasterbean.getSkillsetdetails().length();
			System.out.println(size1);
			String newSkill = skillsetMasterbean.getSkillsetdetails().trim();
			System.out.println(newSkill);
			int newSize1 = newSkill.length();
			System.out.println(newSize1);
			skillsetMasterbean.setSkillsetdetails(newSkill);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDeleteButton(@BindingParam("bean") SkillsetMasterbean masterbean){
		Messagebox.show("Are you sure to delete?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
		    public void onEvent(Event evt) throws InterruptedException {
		        if (evt.getName().equals("onOK")) {
		        	boolean flagDelete = false;
		        	boolean flagLogDelete = false;
		        	flagDelete = SkillSetMasterService.deleteSkillDetails(masterbean);
		    		if(flagDelete){
		    			skillsetMasterbean.setOperation("DELETE");
		    			skillsetMasterbean.setSessionUserId(userId);
		    			skillsetMasterbean.setOperationId(3);
						Calendar calendar = Calendar.getInstance();
					    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
						System.out.println("CREATION DATE :"+currentDate);
						flagLogDelete = LogAuditServiceClass.insertIntoLogTable(skillsetMasterbean.getMainScreenName(), skillsetMasterbean.getChileScreenName(), 
								skillsetMasterbean.getSessionUserId(), skillsetMasterbean.getOperation(),currentDate,skillsetMasterbean.getOperationId());
						System.out.println("flagLogDelete Is:"+flagLogDelete);	
		    			BindUtils.postGlobalCommand(null, null, "globalSkillSetDetailsUpdate", null);
		    		}
		        } else {
		           
		        }
		    }
		});
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}
	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
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
}
