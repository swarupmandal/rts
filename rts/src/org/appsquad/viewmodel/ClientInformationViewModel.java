package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.StateBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.service.ClientInformationService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class ClientInformationViewModel {
	ClientInformationBean clientInformationBean = new ClientInformationBean();
	
	private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
	private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
	private ArrayList<ClientInformationBean> clientDetailsList = new ArrayList<ClientInformationBean>();
	
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		clientInformationBean.setUserId(userId);
		stateList = ClientInformationDao.onLoadState();
		countryList = ClientInformationDao.onLoadCountry();
		clientDetailsList = ClientInformationDao.onLoadClientDeatils();
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSubmitButton(){
		ClientInformationService.insertClientMasterData(clientInformationBean);
		ClientInformationService.clearAllField(clientInformationBean);
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStateName(){
		System.out.println("STATE ID IS :"+clientInformationBean.getStateBean().getStateId());
		System.out.println("STATE NAME IS :"+clientInformationBean.getStateBean().getStateName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		System.out.println("COUNTRY ID IS :"+clientInformationBean.getCountryBean().getCountryId());
		System.out.println("COUNTRY NAME IS :"+clientInformationBean.getCountryBean().getCountryName());
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
}
