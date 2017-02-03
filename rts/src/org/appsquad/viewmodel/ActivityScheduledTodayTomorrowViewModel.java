package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.dao.ActivityScheduledTodayTomorrowdao;
import org.appsquad.dao.TaskNameDao;
import org.appsquad.utility.TaskDescriptionReportPdf;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.itextpdf.text.DocumentException;

public class ActivityScheduledTodayTomorrowViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	public String dateString = "";
	private boolean divVisibility = false;
	private boolean buttonVisibility = false;
	
	private ArrayList<TaskNameBean> todayTomorrowList = null;
	
	private TaskNameBean taskBean = new TaskNameBean();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		dateString = TaskNameDao.createdDateString();
		todayTomorrowList = ActivityScheduledTodayTomorrowdao.fetchTaskDeatilsForTodayAndTomorrow(dateString);
		if(todayTomorrowList.size()>0){
			divVisibility = true;
			buttonVisibility = true;
		}else{
			Messagebox.show("Sorry no data found!","Alert Information",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}

	 @Command
	 @NotifyChange("*")
	 public void onClickPdf() throws Exception{
			String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
			TaskDescriptionReportPdf pdf = new TaskDescriptionReportPdf();
			try {
				if(todayTomorrowList.size()>0){
					   pdf.getDetails(pdfPath, taskBean, todayTomorrowList, "Activity Scheduled For Today And Tomorrow Report");
				}else {
					Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
	} 
	 
	@Command
	@NotifyChange("*")
	public void onClickImage(@BindingParam("bean") TaskNameBean nameBean){
		Map<String, TaskNameBean> entryDataMap = new HashMap<String, TaskNameBean>();
	    entryDataMap.put("parentModalData", nameBean);
		Window win = (Window) Executions.createComponents("WEB-INF/view/activityScheduledForTodayTomorrowModal.zul", null, entryDataMap);	
		win.doModal();
	}
	
	
	/***************************************************************************************************************************************************/
	
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
	public ArrayList<TaskNameBean> getTodayTomorrowList() {
		return todayTomorrowList;
	}
	public void setTodayTomorrowList(ArrayList<TaskNameBean> todayTomorrowList) {
		this.todayTomorrowList = todayTomorrowList;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public TaskNameBean getTaskBean() {
		return taskBean;
	}
	public void setTaskBean(TaskNameBean taskBean) {
		this.taskBean = taskBean;
	}
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public boolean isButtonVisibility() {
		return buttonVisibility;
	}
	public void setButtonVisibility(boolean buttonVisibility) {
		this.buttonVisibility = buttonVisibility;
	}
}
