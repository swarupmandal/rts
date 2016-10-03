package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.DemoBean;
import org.appsquad.dao.DemoDao;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

public class demoViewModel {
	    private Double count;
	    private Connection connection = null;
	    private Session sessions = null;
	    private String userName ;
	    private String userId;
	    
	    private DemoBean bean = new DemoBean();
	    
	    private ArrayList<DemoBean> list = new ArrayList<DemoBean>();
	    private ArrayList<DemoBean> idList = new ArrayList<DemoBean>();
	    
	 
	    @AfterCompose
		public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
			Selectors.wireComponents(view, this, false);
			sessions = Sessions.getCurrent();
			userId = (String) sessions.getAttribute("userId");
            list = DemoDao.getDetails();
		}
	    
	    @Command
	    @NotifyChange("*")
	    public void onCheckBox(@BindingParam("bean") DemoBean demoBean){
	    	if(demoBean.isChkSelect()){
	    		idList.add(demoBean);
	    	}else{
	    		idList.remove(demoBean);
	    	}
	    	System.out.println("checked->"+idList.size());
	    }
	  
	    @Command
	    @NotifyChange("*")
	    public void onClickDownload(){
	    	Map<String, Object> parentMap = new HashMap<String, Object>();
	    	parentMap.put("fetchID", idList);
	    	Window window = (Window) Executions.createComponents("/WEB-INF/view/testing.zul", null, parentMap);
			window.doModal();
	    }
	    
	/*****************************************************************************************************/
	    
    public void getDetails(){
		 
    }
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
	public ArrayList<DemoBean> getList() {
		return list;
	}
	public void setList(ArrayList<DemoBean> list) {
		this.list = list;
	}
	public DemoBean getBean() {
		return bean;
	}
	public void setBean(DemoBean bean) {
		this.bean = bean;
	}

	public ArrayList<DemoBean> getIdList() {
		return idList;
	}

	public void setIdList(ArrayList<DemoBean> idList) {
		this.idList = idList;
	}
}
