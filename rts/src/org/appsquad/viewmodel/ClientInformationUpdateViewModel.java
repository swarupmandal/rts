package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.StateBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.service.ClientInformationService;
import org.appsquad.service.LogAuditServiceClass;
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
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Window;

public class ClientInformationUpdateViewModel {
	ClientInformationBean clientInformationBean = new ClientInformationBean();
	private ClientInformationBean informationBean;
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#winClientDeatilsUpdate")
	private Window winClientDeatilsUpdate;
	private boolean flag = false;
	private boolean flagLogUpdate = false;
	@Wire("#ad")
	private Bandbox bandBox;
	@Wire("#cd")
	private Bandbox bandBox1;
	
	private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
	private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("clientIdDetails") ClientInformationBean bean)throws Exception 
	{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		informationBean = bean;
		userId = (String) sessions.getAttribute("userId");
		informationBean.setUserId(userId);
		informationBean.setSessionUserId(userId);
		countryList = ClientInformationDao.onLoadCountry();
		informationBean.setCountryDropdownDisable(true);
		informationBean.setStateDropdownDisable(false);
		stateList = ClientInformationDao.onLoadState(informationBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		boolean flagForUpdate = false;
		String modifyAddress = "";
		String modifyState = "";
		String modifyEmail = "";
		
		modifyAddress = informationBean.getAddress().trim();
		informationBean.setAddress(modifyAddress);
		
		modifyState = informationBean.getState().trim();
		informationBean.setState(modifyState);
		
		modifyEmail = informationBean.getEmailId().trim();
		informationBean.setEmailId(modifyEmail);
		
		flagForUpdate = ClientInformationService.updateClientMasterData(informationBean);
		
		if(flagForUpdate){
			informationBean.setOperation("UPDATE");
			informationBean.setSessionUserId(userId);
			informationBean.setOperationId(2);
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			flagLogUpdate = LogAuditServiceClass.insertIntoLogTable(informationBean.getMainScreenName(), informationBean.getChileScreenName(), 
																		informationBean.getSessionUserId(), informationBean.getOperation(),currentDate,
																		   informationBean.getOperationId());
			
			winClientDeatilsUpdate.detach();
			BindUtils.postGlobalCommand(null, null, "globalClientDetailsUpdate", null);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseOperation(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		winClientDeatilsUpdate.detach();
		BindUtils.postGlobalCommand(null, null, "globalClientDetailsUpdate", null);
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
	public ClientInformationBean getInformationBean() {
		return informationBean;
	}
	public void setInformationBean(ClientInformationBean informationBean) {
		this.informationBean = informationBean;
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
	public Window getWinClientDeatilsUpdate() {
		return winClientDeatilsUpdate;
	}
	public void setWinClientDeatilsUpdate(Window winClientDeatilsUpdate) {
		this.winClientDeatilsUpdate = winClientDeatilsUpdate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
	public boolean isFlagLogUpdate() {
		return flagLogUpdate;
	}
	public void setFlagLogUpdate(boolean flagLogUpdate) {
		this.flagLogUpdate = flagLogUpdate;
	}
}
