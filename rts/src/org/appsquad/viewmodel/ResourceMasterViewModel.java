package org.appsquad.viewmodel;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.research.FileUploadDownload;
import org.appsquad.service.ResourceMasterService;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Fileupload;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ResourceMasterViewModel {
   ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
   
   private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
   private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
   private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
   private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
   private ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
   
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   
   @AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		resourceMasterBean.setUserId(userId);
		stateList = ResourceMasterDao.onLoadState();
		countryList = ResourceMasterDao.onLoadCountry();
		skillList = ResourceMasterDao.onLoadSkill();
		statusList = ResourceMasterDao.onLoadStatus();
		resourceList = ResourceMasterDao.onLoadResourceDeatils();
	}
   
   @GlobalCommand
   @NotifyChange("*")
   public void globalResourceDetailsUpdate(){
	   resourceList = ResourceMasterDao.onLoadResourceDeatils();
	}
   
   @Command
   @NotifyChange("*")
   public void onClickExistingData(){
	   resourceList = ResourceMasterDao.onLoadResourceDeatils();
   }
   
   @Command
	@NotifyChange("*")
	public void onSelectStateName(){
		//System.out.println("STATE ID IS :"+resourceMasterBean.getStateBean().getStateId());
		//System.out.println("STATE NAME IS :"+resourceMasterBean.getStateBean().getStateName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		//System.out.println("COUNTRY ID IS :"+resourceMasterBean.getCountryBean().getCountryId());
		//System.out.println("COUNTRY NAME IS :"+resourceMasterBean.getCountryBean().getCountryName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectSkillName(){
		//System.out.println("COUNTRY ID IS :"+resourceMasterBean.getSkillsetMasterbean().getId());
		//System.out.println("COUNTRY NAME IS :"+resourceMasterBean.getSkillsetMasterbean().getSkillset());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStatusName(){
		//System.out.println("COUNTRY ID IS :"+resourceMasterBean.getStatusMasterBean().getStatusId());
		//System.out.println("COUNTRY NAME IS :"+resourceMasterBean.getStatusMasterBean().getStatus());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSubmitButton(){
		ResourceMasterService.insertClientMasterData(resourceMasterBean);
		ResourceMasterService.clearAllField(resourceMasterBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") ResourceMasterBean bean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("resourceIdDetails", bean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/resourceInformationUpdate.zul", null, map);
		window.doModal();
	}
	

	
	private String filePath;
    private boolean fileuploaded = false;
    AMedia fileContent;
	@Command
	@NotifyChange("*")
	public void onClickFileUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext) throws Exception{
		
		UploadEvent uploadEvent = null;
		Object objUpEvent = bindContext.getTriggerEvent();
		
		if (objUpEvent != null && (objUpEvent instanceof UploadEvent)) {
			 uploadEvent = (UploadEvent) objUpEvent;
			 
		 }
		if(uploadEvent != null){
			Media media = uploadEvent.getMedia();
		
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH); // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
		
        filePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
        
        String yearPath = "\\" + "PDFs" + "\\" + year + "\\" + month + "\\" + day + "\\";
        filePath = filePath + yearPath;
        
        File baseDir = new File(filePath);
        
        if (!baseDir.exists()) {
               baseDir.mkdirs();
          }
        
        Files.copy(new File(filePath + media.getName()), media.getStreamData());
        Messagebox.show("Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
        fileuploaded = true;
        filePath = filePath + media.getName();
		}
		
	
	}
	
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isFileuploaded() {
		return fileuploaded;
	}

	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}

	public AMedia getFileContent() {
		return fileContent;
	}

	public void setFileContent(AMedia fileContent) {
		this.fileContent = fileContent;
	}
	
	class Test extends Fileupload{
		
	}

	
	
	
   /************************************************************************************************************************************************/
   
	public ResourceMasterBean getResourceMasterBean() {
		return resourceMasterBean;
	}
	public void setResourceMasterBean(ResourceMasterBean resourceMasterBean) {
		this.resourceMasterBean = resourceMasterBean;
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

	public ArrayList<ResourceMasterBean> getResourceList() {
		return resourceList;
	}

	public void setResourceList(ArrayList<ResourceMasterBean> resourceList) {
		this.resourceList = resourceList;
	}
}
