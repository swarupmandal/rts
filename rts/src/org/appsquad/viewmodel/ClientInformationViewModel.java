package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.service.ClientInformationService;
import org.appsquad.service.LogAuditServiceClass;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class ClientInformationViewModel {
	ClientInformationBean clientInformationBean = new ClientInformationBean();
	UserprofileBean userprofileBean = new UserprofileBean();
	
	private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
	private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
	private ArrayList<ClientInformationBean> clientDetailsList = new ArrayList<ClientInformationBean>();
	private ArrayList<UserprofileBean> userBeanList = new ArrayList<UserprofileBean>();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	private boolean flag = false;
	private boolean flagDelete = false;
	private boolean flagLogInsert = false;
	@Wire("#ad")
	private Bandbox bandBox;
	@Wire("#cd")
	private Bandbox bandBox1;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		clientInformationBean.setUserId(userId);
		clientInformationBean.setSessionUserId(userId);
		countryList = ClientInformationDao.onLoadCountry();
		clientDetailsList = ClientInformationDao.onLoadClientDeatils();
		userBeanList = ClientInformationDao.onLoadUserProfile(connection);
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void globalClientDetailsUpdate(){
		clientDetailsList = ClientInformationDao.onLoadClientDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickExistingData(){
		clientDetailsList = ClientInformationDao.onLoadClientDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void clientOriginalName(){
		if(clientInformationBean.getClientOriginalName()!=null){
			int size = clientInformationBean.getClientOriginalName().length();
			String newName = clientInformationBean.getClientOriginalName().trim();
			int newSize = newName.length();
			clientInformationBean.setClientOriginalName(newName);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void name(){
		if(clientInformationBean.getName()!=null){
			int size1 = clientInformationBean.getName().length();
			String modifyName = clientInformationBean.getName().trim();
			int newSize1 = modifyName.length();
			clientInformationBean.setName(modifyName);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void surName(){
		if(clientInformationBean.getSurName()!=null){
			int size2 = clientInformationBean.getSurName().length();
			String modifySurName = clientInformationBean.getSurName().trim();
			int newSize2 = modifySurName.length();
			clientInformationBean.setSurName(modifySurName);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void address(){
		if(clientInformationBean.getAddress()!=null){
			int size3 = clientInformationBean.getAddress().length();
			String modifyAddress = clientInformationBean.getAddress().trim();
			int newSize3 = modifyAddress.length();
			clientInformationBean.setAddress(modifyAddress);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void country(){
		if(clientInformationBean.getCountry()!=null){
			int size4 = clientInformationBean.getCountry().length();
			String modifyCountry = clientInformationBean.getCountry().trim();
			int newSize4 = modifyCountry.length();
			clientInformationBean.setCountry(modifyCountry);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void state(){
		if(clientInformationBean.getState()!=null){
			int size5 = clientInformationBean.getState().length();
			String modifyState = clientInformationBean.getState().trim();
			int newSize5 = modifyState.length();
			clientInformationBean.setState(modifyState);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void email(){
		if(clientInformationBean.getEmailId()!=null){
			int size6 = clientInformationBean.getEmailId().length();
			String modifyEmail = clientInformationBean.getEmailId().trim();
			int newSize6 = modifyEmail.length();
			clientInformationBean.setEmailId(modifyEmail);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSubmitButton(){
		flag = ClientInformationService.insertClientMasterData(clientInformationBean);
		if(flag){
			clientInformationBean.setOperation("INSERT");
			clientInformationBean.setOperationId(1);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			flagLogInsert = LogAuditServiceClass.insertIntoLogTable(clientInformationBean.getMainScreenName(), clientInformationBean.getChileScreenName(), 
																	clientInformationBean.getSessionUserId(), clientInformationBean.getOperation(),currentDate,
																	clientInformationBean.getOperationId());
			ClientInformationService.clearAllField(clientInformationBean);
			userBeanList.clear();
			userBeanList = ClientInformationService.loadUser(connection);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStateName(){
		bandBox1.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		stateList = ClientInformationDao.onLoadState(clientInformationBean);
		bandBox.close();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdate(@BindingParam("bean") ClientInformationBean bean){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("clientIdDetails", bean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/clientInformationUpdate.zul", null, map);
		window.doModal();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickDelete(@BindingParam("bean") ClientInformationBean clientInformationBean){
		int count = 0;
		boolean flagLogDelete = false;
		count = ClientInformationService.countClientPresentWrtRequirementService(clientInformationBean);
		if(count>0){
			Messagebox.show("Requirement Is Generated With This Client,Can't Delete!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}else{
			flagDelete = ClientInformationService.deleteClientMasterData(clientInformationBean);
			if(flagDelete){
				clientInformationBean.setOperation("DELETE");
				clientInformationBean.setOperationId(3);
				clientInformationBean.setSessionUserId(userId);
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
				flagLogDelete = LogAuditServiceClass.insertIntoLogTable(clientInformationBean.getMainScreenName(), clientInformationBean.getChileScreenName(), 
																		clientInformationBean.getSessionUserId(), clientInformationBean.getOperation(),currentDate,
																		clientInformationBean.getOperationId());
				
				BindUtils.postGlobalCommand(null, null, "globalClientDetailsUpdate", null);
			}	
		}
	}
	
	/***************************************************Getter And Setter Method ****************************************************************/
	
	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
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
	public ArrayList<ClientInformationBean> getClientDetailsList() {
		return clientDetailsList;
	}
	public void setClientDetailsList(
			ArrayList<ClientInformationBean> clientDetailsList) {
		this.clientDetailsList = clientDetailsList;
	}
	public boolean isFlag() {
		return flag;
	}
	public Bandbox getBandBox() {
		return bandBox;
	}
	public void setBandBox(Bandbox bandBox) {
		this.bandBox = bandBox;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isFlagDelete() {
		return flagDelete;
	}
	public void setFlagDelete(boolean flagDelete) {
		this.flagDelete = flagDelete;
	}
	public Bandbox getBandBox1() {
		return bandBox1;
	}
	public void setBandBox1(Bandbox bandBox1) {
		this.bandBox1 = bandBox1;
	}
	public boolean isFlagLogInsert() {
		return flagLogInsert;
	}
	public void setFlagLogInsert(boolean flagLogInsert) {
		this.flagLogInsert = flagLogInsert;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public ArrayList<UserprofileBean> getUserBeanList() {
		return userBeanList;
	}
	public void setUserBeanList(ArrayList<UserprofileBean> userBeanList) {
		this.userBeanList = userBeanList;
	}
}
