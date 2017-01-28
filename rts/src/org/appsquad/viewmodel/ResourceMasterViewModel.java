package org.appsquad.viewmodel;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.dao.ResourceMasterDao;
import org.appsquad.service.LogAuditServiceClass;
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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ResourceMasterViewModel {
   ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
   
   private ArrayList<StateBean> stateList = null;
   private ArrayList<CountryBean> countryList = null;
   private ArrayList<SkillsetMasterbean> skillList = null;
   private ArrayList<StatusMasterBean> statusList = null;
   private ArrayList<ResourceMasterBean> resourceList = null;
   
   private String fileName;
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   @Wire("#ad")
   private Bandbox bandBox;
   @Wire("#cd")
   private Bandbox bandBox1;
   private String filePath;
   private boolean fileuploaded = false;
   AMedia fileContent;
   
   @AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		resourceMasterBean.setUserId(userId);
		resourceMasterBean.setSessionUserId(userId);
		countryList = ResourceMasterDao.onLoadCountry();
		skillList = ResourceMasterDao.onLoadSkill();
		
	}
   
   public void loadExistingResources(){
	   resourceList = ResourceMasterDao.onLoadResourceDeatils();
   }
   
   @GlobalCommand
   @NotifyChange("*")
   public void globalResourceDetailsUpdate(){
	   loadExistingResources();
	}
   
   @Command
   @NotifyChange("*")
   public void onClickExistingData(){
	   loadExistingResources();
   }
   
   @Command
	@NotifyChange("*")
	public void onSelectStateName(){
	   bandBox1.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		resourceMasterBean.getStateBean().setStateName(null);
		stateList = ClientInformationDao.onLoadStateForResource(resourceMasterBean);
		bandBox.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectSkillName(){
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStatusName(){
	}
	
	@Command
	@NotifyChange("*")
	public void yearOfExperience(){
		if(resourceMasterBean.getYearOfExperience()!=null){
			if(resourceMasterBean.getYearOfExperience()==0){
				resourceMasterBean.setYearOfExperience(null);
				Messagebox.show("Year Of Experience Can't Be Zero ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClickSubmitButton(){
		boolean isInsert = false;
		boolean flagLogInsert = false;
		isInsert = ResourceMasterService.insertClientMasterData(resourceMasterBean);
		if(isInsert){
			resourceMasterBean.setOperation("INSERT");
			resourceMasterBean.setSessionUserId(userId);
			resourceMasterBean.setOperationId(1);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			flagLogInsert = LogAuditServiceClass.insertIntoLogTable(resourceMasterBean.getMainScreenName(), resourceMasterBean.getChileScreenName(), 
																	resourceMasterBean.getSessionUserId(), resourceMasterBean.getOperation(),currentDate,
																	resourceMasterBean.getOperationId());
			ResourceMasterService.clearAllField(resourceMasterBean);
			fileName = null;
			fileContent =null;
			skillList = ResourceMasterDao.onLoadSkill();
			countryList = ResourceMasterDao.onLoadCountry();
			stateList.clear();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") ResourceMasterBean bean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("resourceIdDetails", bean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/resourceInformationUpdate.zul", null, map);
		window.doModal();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDelete(@BindingParam("bean") ResourceMasterBean bean){
		int countDelete = 0;
		boolean flag = false;
		boolean flagLogDelete = false;
		countDelete = ResourceMasterService.countResourceNumberInMapperTable(bean);
		if(countDelete>0){
			Messagebox.show("Resource Is Assigned,Can't Delete!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}else{
			flag = ResourceMasterService.deleteResourceMasterData(bean);
		}	
		
		if(flag){
			bean.setOperation("DELETE");
			bean.setSessionUserId(userId);
			bean.setOperationId(3);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			
			flagLogDelete = LogAuditServiceClass.insertIntoLogTable(bean.getMainScreenName(), bean.getChileScreenName(), 
																	bean.getSessionUserId(), bean.getOperation(),currentDate,bean.getOperationId());
			
			onClickExistingData();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onUploadFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext) throws Exception{
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
         String yearPath = "PDFs" + "\\" + year + "\\" + month + "\\" + day + "\\";
         filePath = filePath + yearPath;
         File baseDir = new File(filePath);
         if (!baseDir.exists()) {
               baseDir.mkdirs();
          }
         
         int number = ResourceMasterDao.countLastNumber();
         String name = media.getName();
         String parts[] = name.split("\\.");
         for(int i=0;i<parts.length;i++){
        	 //System.out.println(parts[i]);
         }
         String n1 = parts[0];
         String n2 = parts[1];
         String n3 = n1+"_"+number;
         String finalName = n3+"."+n2;
         //System.out.println("FINAL NAME:"+finalName);
         Files.copy(new File(filePath + finalName), media.getStreamData());
         Messagebox.show("Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
         fileuploaded = true;
         fileName = media.getName();
         filePath = filePath + finalName;
         resourceMasterBean.setFilePath(filePath);
         resourceMasterBean.setFileName(media.getName());
         //resourceMasterBean.setFileContent(media.getByteData());
         //System.out.println("FILE CONTENT IS :"+resourceMasterBean.getFileContent());
	   }
	}
	
	
	/******************************************************** Getter And Setter Method ****************************************************************/
	
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
	public ArrayList<ResourceMasterBean> getResourceList() {
		return resourceList;
	}
	public void setResourceList(ArrayList<ResourceMasterBean> resourceList) {
		this.resourceList = resourceList;
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
	public Bandbox getBandBox() {
		return bandBox;
	}
	public void setBandBox(Bandbox bandBox) {
		this.bandBox = bandBox;
	}
	public Bandbox getBandBox1() {
		return bandBox1;
	}
	public void setBandBox1(Bandbox bandBox1) {
		this.bandBox1 = bandBox1;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
