package org.appsquad.viewmodel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ScheduleDateWiseDetailsDao;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Messagebox;

import com.itextpdf.text.DocumentException;

public class ScheduleDateWiseReportViewModel {
	private Connection connection = null;
	private Session sessions = null;
	private String userName ;
	private String userId;
	@Wire("#userBb")
	private Bandbox userBandBox;
	
	@Wire("#userBc")
	private Bandbox anotherBandBox;
	
	private ArrayList<UserprofileBean> assignByUserList = null;
	private ArrayList<UserprofileBean> assignToUserList = null;
	private ArrayList<TaskNameBean> scheduleDateWiseReportList = null;
	private boolean divVisibility = false;
	private boolean buttonVisibility = false;
	
	private UserprofileBean bean = new UserprofileBean();
	private TaskNameBean taskBean = new TaskNameBean();
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		assignByUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatils();
		assignToUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsForAssignTo();
		scheduleDateWiseReportList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReport();
		if(scheduleDateWiseReportList.size()>0){
			divVisibility = true;
			buttonVisibility = true;
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickSearchButton(){
		scheduleDateWiseReportList.clear();
		divVisibility = false;
		buttonVisibility = false;
		if(taskBean.userprofileBean.getUserid()!=null && taskBean.userprofileBean2.getAnotherUserId()==null){
			System.out.println("1st method");
			scheduleDateWiseReportList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReportForAssignerBy(taskBean.userprofileBean.getUserid());
			if(scheduleDateWiseReportList.size()>0){
				divVisibility = true;
				buttonVisibility = true;
			}else{
				Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else if(taskBean.userprofileBean2.getAnotherUserId()!=null && taskBean.userprofileBean.getUserid()==null){
			System.out.println("2nd method");
			scheduleDateWiseReportList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReportForAssignerTo(taskBean.userprofileBean2.getAnotherUserId());
			if(scheduleDateWiseReportList.size()>0){
				divVisibility = true;
				buttonVisibility = true;
			}else{
				Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else if(taskBean.userprofileBean2.getAnotherUserId()!=null && taskBean.userprofileBean.getUserid()!=null){
			System.out.println("3rd method");
			scheduleDateWiseReportList = TaskNameDao.fetchDetailsForByTo(taskBean.userprofileBean2.getAnotherUserId(),taskBean.userprofileBean.getUserid());
			if(scheduleDateWiseReportList.size()>0){
				divVisibility = true;
				buttonVisibility = true;
			}else{
				Messagebox.show("No Data Found", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}else if(taskBean.userprofileBean2.getAnotherUserId()==null && taskBean.userprofileBean.getUserid()==null){
			divVisibility = true;
			buttonVisibility = true;
			scheduleDateWiseReportList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReport();
			Messagebox.show("Please Select DropDown", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickPdf() throws Exception{
			String pdfPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
			TaskDescriptionReportPdf pdf = new TaskDescriptionReportPdf();
			try {
				if(scheduleDateWiseReportList.size()>0){
				   pdf.getDetails(pdfPath, taskBean, scheduleDateWiseReportList, "Schedule Datewise Detail Report");
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
	public void onClickClear(){
		scheduleDateWiseReportList.clear();
		taskBean.userprofileBean.setUserid(null);
		taskBean.userprofileBean2.setAnotherUserId(null);
		assignByUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatils();
		assignToUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsForAssignTo();
		scheduleDateWiseReportList = TaskNameDao.fetchTaskDeatilsForScheduleDateWiseReport();
		if(scheduleDateWiseReportList.size()>0){
			divVisibility = true;
			buttonVisibility = true;
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeUserId(){
		 if(taskBean.getUserIdSearch()!=null){
			 assignByUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsBy(taskBean.getUserIdSearch());
		 }
	 }
	
	@Command
	@NotifyChange("*")
	public void onSelctAssignBy(){
		 userBandBox.close();
		 System.out.println(taskBean.userprofileBean.getUserid());
	 }
	
	@Command
	@NotifyChange("*")
	public void AnotherUserId(){
		 anotherBandBox.close();
		 System.out.println(taskBean.userprofileBean2.getAnotherUserId());
	 }
	
	@Command
	@NotifyChange("*")
	public void onChangeAnotherUserId(){
		 if(taskBean.getAnotherUserIdSearch()!=null){
			 assignToUserList = ScheduleDateWiseDetailsDao.onLoadUserDeatilsForAssignToSearch(taskBean.getAnotherUserIdSearch());
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
	public ArrayList<TaskNameBean> getScheduleDateWiseReportList() {
		return scheduleDateWiseReportList;
	}
	public void setScheduleDateWiseReportList(
			ArrayList<TaskNameBean> scheduleDateWiseReportList) {
		this.scheduleDateWiseReportList = scheduleDateWiseReportList;
	}
	public TaskNameBean getTaskBean() {
		return taskBean;
	}
	public void setTaskBean(TaskNameBean taskBean) {
		this.taskBean = taskBean;
	}
	public Bandbox getUserBandBox() {
		return userBandBox;
	}
	public void setUserBandBox(Bandbox userBandBox) {
		this.userBandBox = userBandBox;
	}
	public Bandbox getAnotherBandBox() {
		return anotherBandBox;
	}
	public void setAnotherBandBox(Bandbox anotherBandBox) {
		this.anotherBandBox = anotherBandBox;
	}
	public ArrayList<UserprofileBean> getAssignByUserList() {
		return assignByUserList;
	}
	public void setAssignByUserList(ArrayList<UserprofileBean> assignByUserList) {
		this.assignByUserList = assignByUserList;
	}
	public ArrayList<UserprofileBean> getAssignToUserList() {
		return assignToUserList;
	}
	public void setAssignToUserList(ArrayList<UserprofileBean> assignToUserList) {
		this.assignToUserList = assignToUserList;
	}
	public UserprofileBean getBean() {
		return bean;
	}
	public void setBean(UserprofileBean bean) {
		this.bean = bean;
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
