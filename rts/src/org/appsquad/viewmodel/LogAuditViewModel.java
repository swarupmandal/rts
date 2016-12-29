package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.LogAuditBean;
import org.appsquad.service.LogAuditServiceClass;
import org.appsquad.utility.Dateformatter;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

public class LogAuditViewModel {
   public LogAuditBean logAuditBean = new LogAuditBean();
   
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   private String frmDate = "";
   private String toDate = "";
   
   private ArrayList<LogAuditBean> logAuditlist = new ArrayList<LogAuditBean>();
   
   @AfterCompose
   public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
 		Selectors.wireComponents(view, this, false);
 		sessions = Sessions.getCurrent();
 		userId = (String) sessions.getAttribute("userId");
 	}
   
   @Command
   @NotifyChange("*")
   public void onChangeToDate(){
	   if(logAuditlist.size()>0){
		   logAuditlist.clear();
		   logAuditBean.setLogAuditDivVisibility(false);   
	   }
 	   if(logAuditBean.getFromDate() != null){
 		   if(logAuditBean.getToDate().after(logAuditBean.getFromDate())){
 		     }else {
 		    	logAuditBean.setToDate(null);
 			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
 		   }
 	   }else {
 		  logAuditBean.setToDate(null);
 		   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
 	   }
    }
   
   @Command
   @NotifyChange("*")
   public void onChangeFromDate(){
	   if(logAuditlist.size()>0){
		   logAuditlist.clear();
		   logAuditBean.setLogAuditDivVisibility(false);   
	   }
	   if(logAuditBean.getToDate()!=null){
		   if(logAuditBean.getToDate().after(logAuditBean.getFromDate())){
		     }else {
		    	logAuditBean.setToDate(null);
			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }else{
		   System.out.println("nothing.");
	   }
   }
   
   @Command
   @NotifyChange("*")
   public void onClickClear(){
	   logAuditBean.setLogAuditDivVisibility(false);
	   logAuditBean.setFromDate(null);
	   logAuditBean.setToDate(null);
   }
   
   @Command
   @NotifyChange("*")
   public void onClickSearchButton(){
	    //when to Date not selected
		if(logAuditBean.getFromDate() != null && logAuditBean.getToDate() == null){
			Messagebox.show("Select To Date!!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		//when no date selected
		if(logAuditBean.getFromDate() == null && logAuditBean.getToDate() == null){
			Messagebox.show("Select From Date And To Date", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		
		//when all date given 
		if(logAuditBean.getFromDate()!=null && logAuditBean.getToDate()!=null){
			logAuditlist = LogAuditServiceClass.fetchLogDetails(Dateformatter.sqlDate(logAuditBean.getFromDate()), 
																Dateformatter.sqlDate(logAuditBean.getToDate()));
			
			System.out.println("LOG AUDIT LIST SIZE IS :"+logAuditlist.size());
			if(logAuditlist.size()>0){
				logAuditBean.setLogAuditDivVisibility(true);
			}else{
				Messagebox.show("No Data Found For This Date Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
   }
   
   /****************************************************** GETTER AND SETTER METHOD ***********************************************************/
   
    public LogAuditBean getLogAuditBean() {
		return logAuditBean;
	}
	public void setLogAuditBean(LogAuditBean logAuditBean) {
		this.logAuditBean = logAuditBean;
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
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public ArrayList<LogAuditBean> getLogAuditlist() {
		return logAuditlist;
	}
	public void setLogAuditlist(ArrayList<LogAuditBean> logAuditlist) {
		this.logAuditlist = logAuditlist;
	}
}
