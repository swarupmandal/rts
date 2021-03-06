package org.appsquad.viewmodel;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

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
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ResourceInformationUpdateViewModel {
	ResourceMasterBean bean = new ResourceMasterBean();
	private ResourceMasterBean masterBean;
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winResourceUpdate")
	private Window winResourceUpdate;
	private boolean flag = false;
	@Wire("#ad")
	private Bandbox bandBox;
	@Wire("#cd")
	private Bandbox bandBox1;
	
	private String fileName;
	private String filePath;
	private boolean fileuploaded = false;
	AMedia fileContent;
	
	private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
	private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
	private ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
	private ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("resourceIdDetails") ResourceMasterBean bean)throws Exception 
	{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		masterBean = bean;
		masterBean.setUserId(userId);
		masterBean.setSessionUserId(userId);
		countryList = ResourceMasterDao.onLoadCountry();
		statusList = ResourceMasterDao.onLoadStatus();
		skillList = ResourceMasterDao.onLoadSkill();
		stateList = ClientInformationDao.onLoadStateForResource(masterBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStateName(){
		bandBox1.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		stateList = ClientInformationDao.onLoadStateForResource(masterBean);
		masterBean.getStateBean().setStateName(null);
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
		if(masterBean.getYearOfExperience()!=null){
			if(masterBean.getYearOfExperience()==0){
				masterBean.setYearOfExperience(null);
				Messagebox.show("Year Of Experience Can't Be Zero ", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}
	
	
	@Command
	@NotifyChange("*")
	public void onUploadFileUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext) throws Exception{
		
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
         //int number = ResourceMasterDao.countLastNumber();
         int number = masterBean.getResourceId(); 
         String name = media.getName();
         String parts[] = name.split("\\.");
         for(int i=0;i<parts.length;i++){
         }
         String n1 = parts[0];
         String n2 = parts[1];
         String n3 = n1+"_"+number;
         String finalName = n3+"."+n2;
         Files.copy(new File(filePath + finalName), media.getStreamData());
         Messagebox.show("CV Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
         fileuploaded = true;
         fileName = media.getName();
         filePath = filePath + finalName;
         masterBean.setFilePath(filePath);
         masterBean.setFileName(media.getName());
	   }
	}
	
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		boolean flagLogUpdate = false;
		String address = "";
		String state = "";
		String emailID = "";
		String otherInfo = "";
		
		address = masterBean.getAddress().trim();
		masterBean.setAddress(address);
		
		state = masterBean.getState().trim();
		masterBean.setState(state);
		
		emailID = masterBean.getEmailId().trim();
		masterBean.setEmailId(emailID);
		
		otherInfo = masterBean.getOtherInfo().trim();
		masterBean.setOtherInfo(otherInfo);
		
		flag = ResourceMasterService.updateResourceMasterData(masterBean);
		System.out.println(flag);
		if(flag){
			masterBean.setOperation("UPDATE");
			masterBean.setSessionUserId(userId);
			masterBean.setOperationId(2);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			System.out.println("CREATION DATE :"+currentDate);
			flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(masterBean.getMainScreenName(), masterBean.getChileScreenName(), 
																		masterBean.getSessionUserId(), masterBean.getOperation(),currentDate,
																		   masterBean.getOperationId());
			System.out.println("flagLogUpdate Is:"+flagLogUpdate);
			winResourceUpdate.detach();
			BindUtils.postGlobalCommand(null, null, "globalResourceDetailsUpdate", null);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseOperation(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		winResourceUpdate.detach();
		BindUtils.postGlobalCommand(null, null, "globalResourceDetailsUpdate", null);
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
	public ResourceMasterBean getBean() {
		return bean;
	}
	public void setBean(ResourceMasterBean bean) {
		this.bean = bean;
	}
	public ResourceMasterBean getMasterBean() {
		return masterBean;
	}
	public void setMasterBean(ResourceMasterBean masterBean) {
		this.masterBean = masterBean;
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
	public Window getWinResourceUpdate() {
		return winResourceUpdate;
	}
	public void setWinResourceUpdate(Window winResourceUpdate) {
		this.winResourceUpdate = winResourceUpdate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
	public void setCountryList(ArrayList<CountryBean> countryList) {
		this.countryList = countryList;
	}
	public ArrayList<StatusMasterBean> getStatusList() {
		return statusList;
	}
	public void setStatusList(ArrayList<StatusMasterBean> statusList) {
		this.statusList = statusList;
	}
	public ArrayList<SkillsetMasterbean> getSkillList() {
		return skillList;
	}
	public void setSkillList(ArrayList<SkillsetMasterbean> skillList) {
		this.skillList = skillList;
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
