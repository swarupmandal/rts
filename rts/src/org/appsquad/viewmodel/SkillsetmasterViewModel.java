package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.dao.SkillSetMasterDao;
import org.appsquad.service.SkillSetMasterService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
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
		SkillSetMasterService.insertClientMasterData(skillsetMasterbean);
		SkillSetMasterService.clearAllField(skillsetMasterbean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(@BindingParam("bean") SkillsetMasterbean masterbean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("skillSetDetails", masterbean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/skillSetUpdate.zul", null, map);
		window.doModal();
	}
	
	/************************** Getter And Setter Method ************************************/
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
