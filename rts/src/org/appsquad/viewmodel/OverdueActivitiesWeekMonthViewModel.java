package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.dao.TaskNameDao;
import org.appsquad.utility.TaskDescriptionReportPdf;
import org.zkoss.bind.annotation.AfterCompose;
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

import com.itextpdf.text.DocumentException;

public class OverdueActivitiesWeekMonthViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	private boolean monthColumnVisibility = false;
	private boolean weekColumnVisibility = false;
	
	private boolean monthGridVisibility = false;
	private boolean weekGridVisibility = false;
	
	private boolean divVisibility = false;
	private boolean buttonVisibility = false;
	
	private ArrayList<TaskNameBean> weekMonthReportList = null;
	
	private TaskNameBean taskBean = new TaskNameBean();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
	}

	@Command
	@NotifyChange("*")
	 public void onCheckInnerRadio(){
		 if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("weekReport")){
			 weekMonthReportList = TaskNameDao.fetchTaskDeatilsForWeeklyReport();
			 if(weekMonthReportList.size()>0){
				 weekColumnVisibility = true;
				 weekGridVisibility = true;
				 buttonVisibility = true;
				 divVisibility = true;
				 monthColumnVisibility = false;
				 monthGridVisibility = false;
			 }else{
				 
			 }
		 }else if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("monthReport")){
			 weekMonthReportList = TaskNameDao.fetchTaskDeatilsForMonthlyReport();
			 if(weekMonthReportList.size()>0){
				 weekColumnVisibility = false;
				 weekGridVisibility = false;
				 buttonVisibility = true;
				 divVisibility = true;
				 monthColumnVisibility = true;
				 monthGridVisibility = true;
			 }else{
				 
			 }
		 }
	 }
	
	 @Command
	 @NotifyChange("*")
	 public void onClickPdf() throws Exception{
			String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
			TaskDescriptionReportPdf pdf = new TaskDescriptionReportPdf();
			try {
					if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("weekReport")){
						if(weekMonthReportList.size()>0){
							   pdf.getDetailsForWeekReport(pdfPath, taskBean, weekMonthReportList, "Overdue Activities - Weekly Report");
						}else {
							Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
						}
					}else if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("monthReport")){
						if(weekMonthReportList.size()>0){
							   pdf.getDetailsForMonthReport(pdfPath, taskBean, weekMonthReportList, "Overdue Activities - Monthly Report");
						}else {
							Messagebox.show("No Data Found ","Alert",Messagebox.OK,Messagebox.EXCLAMATION);
						}
					}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
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
	public ArrayList<TaskNameBean> getWeekMonthReportList() {
		return weekMonthReportList;
	}
	public void setWeekMonthReportList(ArrayList<TaskNameBean> weekMonthReportList) {
		this.weekMonthReportList = weekMonthReportList;
	}
	public TaskNameBean getTaskBean() {
		return taskBean;
	}
	public void setTaskBean(TaskNameBean taskBean) {
		this.taskBean = taskBean;
	}
	public boolean isMonthColumnVisibility() {
		return monthColumnVisibility;
	}
	public void setMonthColumnVisibility(boolean monthColumnVisibility) {
		this.monthColumnVisibility = monthColumnVisibility;
	}
	public boolean isWeekColumnVisibility() {
		return weekColumnVisibility;
	}
	public void setWeekColumnVisibility(boolean weekColumnVisibility) {
		this.weekColumnVisibility = weekColumnVisibility;
	}
	public boolean isMonthGridVisibility() {
		return monthGridVisibility;
	}
	public void setMonthGridVisibility(boolean monthGridVisibility) {
		this.monthGridVisibility = monthGridVisibility;
	}
	public boolean isWeekGridVisibility() {
		return weekGridVisibility;
	}
	public void setWeekGridVisibility(boolean weekGridVisibility) {
		this.weekGridVisibility = weekGridVisibility;
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
