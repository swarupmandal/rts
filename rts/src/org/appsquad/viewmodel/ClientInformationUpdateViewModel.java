package org.appsquad.viewmodel;

import java.security.AllPermission;
import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.StateBean;
import org.appsquad.dao.ClientInformationDao;
import org.appsquad.service.ClientInformationService;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
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
	
	private ArrayList<StateBean> stateList = new ArrayList<StateBean>();
	private ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,@ExecutionArgParam("clientIdDetails") ClientInformationBean bean)
			throws Exception {
		Selectors.wireComponents(view, this, false);
		informationBean = bean;
		sessions = Sessions.getCurrent();
		stateList = ClientInformationDao.onLoadState();
		countryList = ClientInformationDao.onLoadCountry();
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectStateName(){
		System.out.println("STATE ID IS :"+informationBean.getStateBean().getStateId());
		System.out.println("STATE NAME IS :"+informationBean.getStateBean().getStateName());
	}
	
	@Command
	@NotifyChange("*")
	public void onSelectCountryName(){
		System.out.println("COUNTRY ID IS :"+informationBean.getCountryBean().getCountryId());
		System.out.println("COUNTRY NAME IS :"+informationBean.getCountryBean().getCountryName());
	}
	
	@Command
	@NotifyChange("*")
	public void onClickUpdateButton(){
		flag = ClientInformationService.updateClientMasterData(informationBean);
		if(flag){
			winClientDeatilsUpdate.detach();
			BindUtils.postGlobalCommand(null, null, "globalClientDetailsUpdate", null);
		}
	}
	
	/********************************Getter and Setter Method ************************************************************/
	
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
}
