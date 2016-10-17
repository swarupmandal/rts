package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.DemoBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.DemoDao;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.DemoService;
import org.appsquad.service.RequirementGenerationService;
import org.appsquad.service.ResourceAllocationTrackingService;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class demoViewModel {
	    private Double count;
	    private Connection connection = null;
	    private Session sessions = null;
	    private String userName ;
	    private String userId;
	    @Wire("#clntBb")
		private Bandbox clientBandBox;
	    private boolean saveButtonVisibility = false;
	    
		@Wire("#skSt")
		private Bandbox skillBandBox;
		private boolean resourceDivVisibility = true;
	    
	    private DemoBean bean = new DemoBean();
	    
	    private ArrayList<DemoBean> list = new ArrayList<DemoBean>();
	    private ArrayList<DemoBean> idList = new ArrayList<DemoBean>();
	    private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
		private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
		private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
		
	    @AfterCompose
		public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
			Selectors.wireComponents(view, this, false);
			sessions = Sessions.getCurrent();
			userId = (String) sessions.getAttribute("userId");
			skillList = RequirementGenerationService.fetchSkillSetList();
			statusList = ResourceMasterDao.onLoadStatus();
			clientList = ResourceAllocationTrackingService.fetchClientDetails();
			resourceDivVisibility = false;
		}
	    
	    @GlobalCommand
	    @NotifyChange("*")
	    public void refreshDownloadingScreen(){
	    	list = DemoDao.getDetailsForSkill(bean);
	    	for(DemoBean bean: list){
	    		bean.setChkSelect(false);
	    	}
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onCheckBox(@BindingParam("bean") DemoBean demoBean){
	    	String cvPath = "";
	    	if(demoBean.isChkSelect()){
	    		cvPath = DemoService.getCvPathService(demoBean);
	    		System.out.println("CV PATH IS :"+cvPath);
	    		if(!cvPath.equalsIgnoreCase("A")){
	    			idList.add(demoBean);	
	    		}else{
	    			demoBean.setChkSelect(false);
	    			Messagebox.show("This Resource Doesn't Have CV!","Excalamation",Messagebox.OK,Messagebox.EXCLAMATION);	
	    		}
	    	}else{
	    		idList.remove(demoBean);
	    	}
	    }
	  
	    @Command
	    @NotifyChange("*")
	    public void onClickDownload(){
	    	if(idList.size()>0){
	    		Map<String, Object> parentMap = new HashMap<String, Object>();
		    	parentMap.put("fetchID", idList);
		    	Window window = (Window) Executions.createComponents("/WEB-INF/view/testing.zul", null, parentMap);
				window.doModal();	
	    	}else{
	    		Messagebox.show(" You Didn't Check Any Check-Box!","Excalamation",Messagebox.OK,Messagebox.EXCLAMATION);
	    	}
	    }
	    
	    @Command
		@NotifyChange("*")
		public void onSelctSkillName(){
			skillBandBox.close();
		}
		
		@Command
		@NotifyChange("*")
		public void onSelctClientName(){
			if(bean.skillsetMasterbean.getSkillset()!=null){
				if(bean.getFromDate()!=null && bean.getToDate()!=null){
					clientBandBox.close();		
				}else{
					clientBandBox.close();	
				}
			}else{
				Messagebox.show("First Select Skill Name!","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		}
		
		@Command
		@NotifyChange("*")
		public void onChangeFromDate(){
			System.out.println("INSIDE FORM DATE METHOD");
		}
		
		@Command
		@NotifyChange("*")
		public void onChangeToDate(){
			   if(bean.getFromDate() != null){
				   if(bean.getToDate().after(bean.getFromDate())){
					    	if(bean.getFromDate() != null && bean.getToDate() != null){
					    		System.out.println("INSIDE METHOD");
					    	 }
				     }else {
				    	bean.setToDate(null);
					    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
				   }
			   }else {
				   bean.setToDate(null);
				   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
			}
	   }
		
	   @Command
	   @NotifyChange("*")
	   public void onClickClear(){
		   list.clear();
		   resourceDivVisibility = false;
		   saveButtonVisibility = false;
		   
		   bean.skillsetMasterbean.setSkillset(null);
		   skillList = RequirementGenerationService.fetchSkillSetList();
		   
		   bean.setFromDate(null);
		   bean.setToDate(null);
		   
		   bean.statusMasterBean.setStatus(null);
		   statusList = ResourceMasterDao.onLoadStatus();
		   
		   bean.clientInformationBean.setFullName(null);
		   clientList = ResourceAllocationTrackingService.fetchClientDetails();
	   }
		
	   @Command
	   @NotifyChange("*")
	   public void onClickSearch(){
		   if(bean.skillsetMasterbean.getSkillset()!=null && bean.skillsetMasterbean.getSkillset().trim().length()>0 && 
				         bean.getFromDate()==null && bean.getToDate()==null &&
				            bean.clientInformationBean.getFullName()==null){
				   list = DemoService.getDetailsForSkillService(bean);
			    	System.out.println("SKILL NAME CORRESPONDING LIST SIZE IS :"+list.size());
			    	if(list.size()>0){
			    		resourceDivVisibility = true;
			    		saveButtonVisibility = true;
			    	}else{
			    		resourceDivVisibility = false;
			    		saveButtonVisibility = false;
			    		Messagebox.show("No Data Found Wrt This Skill Name!","Excalamation",Messagebox.OK,Messagebox.EXCLAMATION);
			    	}
		   }else if(bean.skillsetMasterbean.getSkillset()!=null && bean.skillsetMasterbean.getSkillset().trim().length()>0 && 
				              bean.getFromDate()!=null && bean.getToDate()!=null){
			    list = DemoService.getDetailsForSkillAndDateService(bean);
	    		System.out.println("SKILL NAME and DATE CORRESPONDING LIST SIZE IS :"+list.size());
				if(list.size()>0){
		    		resourceDivVisibility = true;
		    		saveButtonVisibility = true;
		    	}else{
		    		resourceDivVisibility = false;
		    		saveButtonVisibility = false;
		    		Messagebox.show("No Data Found Wrt This Combination!","Excalamation",Messagebox.OK,Messagebox.EXCLAMATION);
		    	}
		   }else if(bean.skillsetMasterbean.getSkillset()!=null && bean.skillsetMasterbean.getSkillset().trim().length()>0 && 
				           bean.clientInformationBean.getFullName()!=null && bean.clientInformationBean.getFullName().trim().length()>0){
			    list = DemoService.getDetailsForSkillAndDateAndClientService(bean);
				System.out.println("SKILL NAME and CLIENT NAME CORRESPONDING LIST SIZE IS :"+list.size());
				if(list.size()>0){
		    		resourceDivVisibility = true;
		    		saveButtonVisibility = true;
		    	}else{
		    		resourceDivVisibility = false;
		    		saveButtonVisibility = false;
		    		Messagebox.show("No Data Found Wrt This Combination!","Excalamation",Messagebox.OK,Messagebox.EXCLAMATION);
		    	}
		   }
		   
		   if(!(bean.skillsetMasterbean.getSkillset()!=null && bean.skillsetMasterbean.getSkillset().trim().length()>0)){
			   Messagebox.show("Select Skill Name First!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }
	   
	  /*************************************************************************************************************************************************/
		public Double getCount() {
			return count;
		}
		public void setCount(Double count) {
			this.count = count;
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
		public Bandbox getClientBandBox() {
			return clientBandBox;
		}
		public void setClientBandBox(Bandbox clientBandBox) {
			this.clientBandBox = clientBandBox;
		}
		public Bandbox getSkillBandBox() {
			return skillBandBox;
		}
		public void setSkillBandBox(Bandbox skillBandBox) {
			this.skillBandBox = skillBandBox;
		}
		public boolean isResourceDivVisibility() {
			return resourceDivVisibility;
		}
		public void setResourceDivVisibility(boolean resourceDivVisibility) {
			this.resourceDivVisibility = resourceDivVisibility;
		}
		public DemoBean getBean() {
			return bean;
		}
		public void setBean(DemoBean bean) {
			this.bean = bean;
		}
		public ArrayList<DemoBean> getList() {
			return list;
		}
		public void setList(ArrayList<DemoBean> list) {
			this.list = list;
		}
		public ArrayList<DemoBean> getIdList() {
			return idList;
		}
		public void setIdList(ArrayList<DemoBean> idList) {
			this.idList = idList;
		}
		public ArrayList<SkillsetMasterbean> getSkillList() {
			return skillList;
		}
		public void setSkillList(ArrayList<SkillsetMasterbean> skillList) {
			this.skillList = skillList;
		}
		public ArrayList<StatusMasterBean> getStatusList() {
			return statusList;
		}
		public void setStatusList(ArrayList<StatusMasterBean> statusList) {
			this.statusList = statusList;
		}
		public ArrayList<ClientInformationBean> getClientList() {
			return clientList;
		}
		public void setClientList(ArrayList<ClientInformationBean> clientList) {
			this.clientList = clientList;
		}
		public boolean isSaveButtonVisibility() {
			return saveButtonVisibility;
		}
		public void setSaveButtonVisibility(boolean saveButtonVisibility) {
			this.saveButtonVisibility = saveButtonVisibility;
		} 
}
