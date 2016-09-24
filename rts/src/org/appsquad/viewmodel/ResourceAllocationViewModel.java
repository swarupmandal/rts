package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceAllocationBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.ResourceTypeBean;
import org.appsquad.dao.ResourceAllocationDao;
import org.appsquad.dao.SortCriteriaDao;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;

public class ResourceAllocationViewModel {
	    ResourceAllocationBean resourceAllocationBean = new ResourceAllocationBean();
	
	    private Connection connection = null;
	    private Session sessions = null;
	    private String userName ;
	    private String userId;
	    @Wire("#bd")
		private Bandbox bandBox;
	    private int countAllocatedNumber = 0;
	    private int remainingNumber = 0;
	    
	    private ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
	    private ArrayList<RequirementGenerationBean> requirementDetailsList = new ArrayList<RequirementGenerationBean>();
	    private ArrayList<ResourceTypeBean> resourceTypeList = new ArrayList<ResourceTypeBean>();
	    private ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
	    
	    @AfterCompose
	    public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
	  		Selectors.wireComponents(view, this, false);
	  		sessions = Sessions.getCurrent();
	  		userId = (String) sessions.getAttribute("userId");
	  		resourceAllocationBean.setUserId(userId);
	  		clientList = SortCriteriaDao.onLoadClientDeatils();
	  		resourceTypeList = ResourceAllocationDao.onLoadResourceTypeDetails();
	  	}
	    
	    @Command
	    @NotifyChange("*")
	    public void onSelectReqSkill(){
	    	System.out.println("SELECTED REQ-SKILL ID IS :"+resourceAllocationBean.getRequirementGenerationBean().getRequirementId());
	    	bandBox.close();
	    	resourceTypeList = ResourceAllocationDao.onLoadResourceTypeDetails();
	    	resourceAllocationBean.getMasterbean().setSkillset(ResourceAllocationDao.fetchSkillDetails(resourceAllocationBean.getClientInformationBean().getClientId(), resourceAllocationBean.getRequirementGenerationBean().getRequirementId()));
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onSelectClientName(){
	    	if(requirementDetailsList.size()>0){
	    		requirementDetailsList.clear();
	    	}
	    	resourceAllocationBean.getRequirementGenerationBean().setRequirementId(null);
	    	resourceAllocationBean.getMasterbean().setSkillset(null);
	    	resourceAllocationBean.getResourceTypeBean().setResourceTypeName(null);
	    	resourceAllocationBean.setRequiredResourcenumber(null);
	    	resourceAllocationBean.setAllocatedResourceNumber(null);
	    	System.out.println(resourceAllocationBean.getClientInformationBean().getClientId());
	    	resourceTypeList = ResourceAllocationDao.onLoadResourceTypeDetails();
	    	requirementDetailsList = ResourceAllocationDao.onLoadRequirementSkillDetails(resourceAllocationBean.getClientInformationBean().getClientId());
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onSelectResourcetypeName(){
	    	System.out.println(resourceAllocationBean.getResourceTypeBean().getResourceTypeId());
	    	resourceTypeList = ResourceAllocationDao.onLoadResourceTypeDetails();
	    	resourceAllocationBean.setRequiredResourcenumber(ResourceAllocationDao.fetchRequiredResourceNumber(resourceAllocationBean.getClientInformationBean().getClientId(), resourceAllocationBean.getRequirementGenerationBean().getRequirementId(),resourceAllocationBean.getResourceTypeBean().getResourceTypeName()));
	        resourceAllocationBean.setAllocatedResourceNumber(ResourceAllocationDao.fetchRequiredResourceNumberAllocated(resourceAllocationBean.getClientInformationBean().getClientId(), resourceAllocationBean.getRequirementGenerationBean().getRequirementId(),resourceAllocationBean.getResourceTypeBean().getResourceTypeName()));
	    	System.out.println(resourceAllocationBean.getRequiredResourcenumber());
	    	System.out.println(resourceAllocationBean.getAllocatedResourceNumber());
	        resourceAllocationBean.setDivVisibility(true);
	    	resourceList = ResourceAllocationDao.onLoadResourceDetails();
	    }
	    
	    @Command
	    @NotifyChange("*")
	    public void onCheck(@BindingParam("bean") ResourceMasterBean masterBean){
	    	if(masterBean.isChkSelect()){
                resourceAllocationBean.setAllocatedResourceNumber(resourceAllocationBean.getAllocatedResourceNumber()+1);
	    	}else{
	    		resourceAllocationBean.setAllocatedResourceNumber(resourceAllocationBean.getAllocatedResourceNumber()-1);
	    	}
	    	remainingNumber = (resourceAllocationBean.getRequiredResourcenumber()-resourceAllocationBean.getAllocatedResourceNumber());
	    	System.out.println("COUNT ALLOCATED NUMBER IS :"+countAllocatedNumber);
	    	System.out.println("Remaining Number is :"+remainingNumber);
	    	System.out.println("--------------------------------------");
	    	System.out.println("getAllocated Number is :"+resourceAllocationBean.getRequiredResourcenumber());
	    	if(resourceAllocationBean.getAllocatedResourceNumber()>resourceAllocationBean.getRequiredResourcenumber()){
	    		Messagebox.show("Can't");
	    		masterBean.setChkSelect(false);
	    		resourceAllocationBean.setAllocatedResourceNumber(resourceAllocationBean.getAllocatedResourceNumber()-1);
	    	}
	    	System.out.println(countAllocatedNumber);
	    }
	    
	    
	    /**************************************************************************************************************************************/
	    
		public ResourceAllocationBean getResourceAllocationBean() {
			return resourceAllocationBean;
		}
		public void setResourceAllocationBean(
				ResourceAllocationBean resourceAllocationBean) {
			this.resourceAllocationBean = resourceAllocationBean;
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
		public ArrayList<ClientInformationBean> getClientList() {
			return clientList;
		}
		public void setClientList(ArrayList<ClientInformationBean> clientList) {
			this.clientList = clientList;
		}
		public ArrayList<RequirementGenerationBean> getRequirementDetailsList() {
			return requirementDetailsList;
		}
		public void setRequirementDetailsList(
				ArrayList<RequirementGenerationBean> requirementDetailsList) {
			this.requirementDetailsList = requirementDetailsList;
		}
		public ArrayList<ResourceTypeBean> getResourceTypeList() {
			return resourceTypeList;
		}
		public void setResourceTypeList(ArrayList<ResourceTypeBean> resourceTypeList) {
			this.resourceTypeList = resourceTypeList;
		}
		public Bandbox getBandBox() {
			return bandBox;
		}
		public void setBandBox(Bandbox bandBox) {
			this.bandBox = bandBox;
		}
		public ArrayList<ResourceMasterBean> getResourceList() {
			return resourceList;
		}
		public void setResourceList(ArrayList<ResourceMasterBean> resourceList) {
			this.resourceList = resourceList;
		}
		public int getRemainingNumber() {
			return remainingNumber;
		}
		public void setRemainingNumber(int remainingNumber) {
			this.remainingNumber = remainingNumber;
		}
		public int getCountAllocatedNumber() {
			return countAllocatedNumber;
		}
		public void setCountAllocatedNumber(int countAllocatedNumber) {
			this.countAllocatedNumber = countAllocatedNumber;
		}	  
}
