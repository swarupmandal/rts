package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.StateBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.service.ClientInformationService;
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
	
	private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
	private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
	private ArrayList<ClientInformationBean> clientDetailsList = new ArrayList<ClientInformationBean>();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	private boolean flag = false;
	private boolean flagDelete = false;
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
		countryList = ClientInformationDao.onLoadCountry();
		clientDetailsList = ClientInformationDao.onLoadClientDeatils();
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
	public void onClickSubmitButton(){
		flag = ClientInformationService.insertClientMasterData(clientInformationBean);
		if(flag){
			ClientInformationService.clearAllField(clientInformationBean);
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
		count = ClientInformationService.countClientPresentWrtRequirementService(clientInformationBean);
		if(count>0){
			Messagebox.show("Requirement Is Generated With This Client,Can't Delete!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}else{
			flagDelete = ClientInformationService.deleteClientMasterData(clientInformationBean);
			if(flagDelete){
				BindUtils.postGlobalCommand(null, null, "globalClientDetailsUpdate", null);
			}	
		}
	}
	
	/*********************************************Getter and Setter Method ****************************************************/
	
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
}
