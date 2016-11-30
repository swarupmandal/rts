package org.appsquad.viewmodel;

import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.service.CurrentOpportunitiesReportGenerationService;
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

public class CurrentOpportunitiesReportGenerationViewModel {
   public CurrentOpportunitiesReportGenerationBean currentOpportunitiesReportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
   
   private Connection connection = null;
   private Session sessions = null;
   private String userName ;
   private String userId;
   private String frmDate = "";
   private String toDate = "";
   private boolean divVisibility = false;
   
   private ArrayList<CurrentOpportunitiesReportGenerationBean> reportList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
   
   @AfterCompose
   public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
 		Selectors.wireComponents(view, this, false);
 		sessions = Sessions.getCurrent();
 		userId = (String) sessions.getAttribute("userId");
 	}
   
   @Command
   @NotifyChange("*")
   public void onChangeToDate(){
	   if(reportList.size()>0){
		   reportList.clear();
		   divVisibility = false;
	   }
 	   if(currentOpportunitiesReportGenerationBean.getFromDate() != null){
 		   if(currentOpportunitiesReportGenerationBean.getToDate().after(currentOpportunitiesReportGenerationBean.getFromDate())){
 		     }else {
 		    	currentOpportunitiesReportGenerationBean.setToDate(null);
 			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
 		   }
 	   }else {
 		   currentOpportunitiesReportGenerationBean.setToDate(null);
 		   Messagebox.show("Select From Date First", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
 	   }
    }
   
   @Command
   @NotifyChange("*")
   public void onChangeFromDate(){
	   if(reportList.size()>0){
		   reportList.clear();
		   divVisibility = false;
	   }
	   if(currentOpportunitiesReportGenerationBean.getToDate()!=null){
		   if(currentOpportunitiesReportGenerationBean.getToDate().after(currentOpportunitiesReportGenerationBean.getFromDate())){
		     }else {
		    	currentOpportunitiesReportGenerationBean.setToDate(null);
			    Messagebox.show("To Date Should be Grater Than From Date", "ALERT", Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	   }else{
		   System.out.println("nothing.");
	   }
   }
   
   @Command
   @NotifyChange("*")
   public void onClickClear(){
	   if(reportList.size()>0){
		   reportList.clear();
		   divVisibility = false;
	   }
	   currentOpportunitiesReportGenerationBean.setFromDate(null);
	   currentOpportunitiesReportGenerationBean.setToDate(null);
   }
   
   @Command
   @NotifyChange("*")
   public void onClickSearchButton(){
	 //when to Date not selected
	 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() == null){
	 	Messagebox.show("Select To Date!!", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	 }
	 		
	 //when no date selected
	 if(currentOpportunitiesReportGenerationBean.getFromDate() == null && currentOpportunitiesReportGenerationBean.getToDate() == null){
	 	Messagebox.show("Select From Date And To Date", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
	 }
	 
	 //when two date are given
	 if(currentOpportunitiesReportGenerationBean.getFromDate() != null && currentOpportunitiesReportGenerationBean.getToDate() != null){
		 reportList = CurrentOpportunitiesReportGenerationService.loadReportDetails(Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getFromDate()), 
				 																	Dateformatter.sqlDate(currentOpportunitiesReportGenerationBean.getToDate()));
	     
		 System.out.println("REPORT LIST SIZE IS :"+reportList.size());
		 if(reportList.size()>0){
			divVisibility = true;
		 }else{
			Messagebox.show("No Data Found For This Date Combination ", "Alert", Messagebox.OK, Messagebox.EXCLAMATION);
			divVisibility = false;
		}
	 }
	 
   }
   
  /****************************************************** GETTER AND SETTER METHOD ***********************************************************/
    
    public CurrentOpportunitiesReportGenerationBean getCurrentOpportunitiesReportGenerationBean() {
		return currentOpportunitiesReportGenerationBean;
	}
	public void setCurrentOpportunitiesReportGenerationBean(
			CurrentOpportunitiesReportGenerationBean currentOpportunitiesReportGenerationBean) {
		this.currentOpportunitiesReportGenerationBean = currentOpportunitiesReportGenerationBean;
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
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public ArrayList<CurrentOpportunitiesReportGenerationBean> getReportList() {
		return reportList;
	}
	public void setReportList(
			ArrayList<CurrentOpportunitiesReportGenerationBean> reportList) {
		this.reportList = reportList;
	}
}
