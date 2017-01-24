package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import org.appsquad.bean.OverdueMonthBean;
import org.appsquad.bean.OverdueWeeklyBean;
import org.appsquad.bean.TaskNameBean;
import org.appsquad.dao.TaskNameDao;
import org.appsquad.utility.OverdueMonthPdf;
import org.appsquad.utility.OverdueMonthUtility;
import org.appsquad.utility.OverdueWeekPdf;
import org.appsquad.utility.OverdueWeekUtility;
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
	
	private boolean secondDivVisibility = false;
	private boolean secondButtonVisibility = false;
	
	private ArrayList<TaskNameBean> weekMonthReportList = null;
	private ArrayList<TaskNameBean> monthWeekReportList = null;
	private ArrayList<OverdueWeeklyBean> weekFinalList = new ArrayList<OverdueWeeklyBean>();
	private ArrayList<OverdueMonthBean> monthFinalList = new ArrayList<OverdueMonthBean>();
	
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
				 divVisibility = true;
				 buttonVisibility = true;
				 secondButtonVisibility = false;
				 secondDivVisibility = false;
				 if(weekFinalList.size()>0){
					 weekFinalList.clear();
				 }
				 
				 for(int i =1;i<54;i++){
					 OverdueWeeklyBean overdueWeeklyBean = new OverdueWeeklyBean();
					 overdueWeeklyBean.setWeekID(i);
					 OverdueWeekUtility.populateWeekNumber(overdueWeeklyBean.getWeekID(), overdueWeeklyBean);
					 
					 ArrayList<TaskNameBean> weekInnerList = new ArrayList<TaskNameBean>();
					 
					 for(TaskNameBean bean: weekMonthReportList){
						 if(bean.getWeek()==i){
							 TaskNameBean taskNameBean = new TaskNameBean();
							 taskNameBean.setTaskName(bean.getTaskName());
							 taskNameBean.setAssignedByUserId(bean.getAssignedByUserId());
							 taskNameBean.getUserprofileBean().setUserid(bean.getUserprofileBean().getUserid());
							 taskNameBean.setCreatedDateStr(bean.getCreatedDateStr());
							 taskNameBean.setScheduledDateStr(bean.getScheduledDateStr());
							 taskNameBean.setStatus(bean.getStatus());
							 taskNameBean.setActualCompletionDateStr(bean.getActualCompletionDateStr());
							 taskNameBean.setTaskDescription(bean.getTaskDescription());
							
							 weekInnerList.add(taskNameBean);
						 }
					 }
					 overdueWeeklyBean.setWeekList(weekInnerList);
					 if(overdueWeeklyBean.getWeekList().size()>0){
						 weekFinalList.add(overdueWeeklyBean);
					 }
				 }
			 }else{
				 Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			 }
		 }else if(taskBean.getSelectedInnerRadioButton().equalsIgnoreCase("monthReport")){
			 monthWeekReportList = TaskNameDao.fetchTaskDeatilsForMonthlyReport();
			 if(monthWeekReportList.size()>0){
				 divVisibility = false;
				 buttonVisibility = false;
				 secondButtonVisibility = true;
				 secondDivVisibility = true;
				 if(monthFinalList.size()>0){
					 monthFinalList.clear();
				 }
				 
				 for(int i =1;i<54;i++){
					 OverdueMonthBean overdueMonthBean = new OverdueMonthBean();
					 overdueMonthBean.setMonthID(i);
					 OverdueMonthUtility.populateMonthNumber(overdueMonthBean.getMonthID(), overdueMonthBean); 
					 ArrayList<TaskNameBean> monthInnerList = new ArrayList<TaskNameBean>();
					 
					 for(TaskNameBean bean: monthWeekReportList){
						 if(bean.getMonth()==i){
							 TaskNameBean taskNameBean = new TaskNameBean();
							 taskNameBean.setTaskName(bean.getTaskName());
							 taskNameBean.setAssignedByUserId(bean.getAssignedByUserId());
							 taskNameBean.getUserprofileBean().setUserid(bean.getUserprofileBean().getUserid());
							 taskNameBean.setCreatedDateStr(bean.getCreatedDateStr());
							 taskNameBean.setScheduledDateStr(bean.getScheduledDateStr());
							 taskNameBean.setStatus(bean.getStatus());
							 taskNameBean.setTaskDescription(bean.getTaskDescription());
							 taskNameBean.setActualCompletionDateStr(bean.getActualCompletionDateStr());
							
							 monthInnerList.add(taskNameBean);
						 }
					 }
					 overdueMonthBean.setYearList(monthInnerList);
					 if(overdueMonthBean.getYearList().size()>0){
						 monthFinalList.add(overdueMonthBean);
					 }
				 }
			 }else{
				 Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			 }
		 }
	 }
	
	@Command
	@NotifyChange("*")
	public void onClickPdf() throws Exception{
		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		OverdueWeekPdf overdueWeekPdf = new OverdueWeekPdf();
		try {
			overdueWeekPdf.getDetails(pdfPath, "Overdue Weekly Report", weekFinalList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSecondPdf() throws Exception{
		String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		OverdueMonthPdf overdueMonthPdf = new OverdueMonthPdf();
		try {
			overdueMonthPdf.getDetails(pdfPath, "Overdue Monthly Report", monthFinalList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
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
	public ArrayList<OverdueWeeklyBean> getWeekFinalList() {
		return weekFinalList;
	}
	public void setWeekFinalList(ArrayList<OverdueWeeklyBean> weekFinalList) {
		this.weekFinalList = weekFinalList;
	}
	public ArrayList<TaskNameBean> getMonthWeekReportList() {
		return monthWeekReportList;
	}
	public void setMonthWeekReportList(ArrayList<TaskNameBean> monthWeekReportList) {
		this.monthWeekReportList = monthWeekReportList;
	}
	public boolean isSecondDivVisibility() {
		return secondDivVisibility;
	}
	public void setSecondDivVisibility(boolean secondDivVisibility) {
		this.secondDivVisibility = secondDivVisibility;
	}
	public boolean isSecondButtonVisibility() {
		return secondButtonVisibility;
	}
	public void setSecondButtonVisibility(boolean secondButtonVisibility) {
		this.secondButtonVisibility = secondButtonVisibility;
	}
	public ArrayList<OverdueMonthBean> getMonthFinalList() {
		return monthFinalList;
	}
	public void setMonthFinalList(ArrayList<OverdueMonthBean> monthFinalList) {
		this.monthFinalList = monthFinalList;
	}
}
