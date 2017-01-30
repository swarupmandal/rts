package org.appsquad.viewmodel;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.appsquad.bean.CurrentOpportunitiesReportBean;
import org.appsquad.dao.CurrentOpportunitiesReportDao;
import org.appsquad.service.CurrentOpportunitiesReportService;
import org.appsquad.service.LogAuditServiceClass;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class CurrentOpportunitiesReportViewModel {
	private Connection connection = null;
    private Session sessions = null;
    private String userName ;
    private String userId;
    private boolean divVisibility = false;
    private String filePath;
    private boolean fileuploaded = false;
    private String fileName;
    
    public CurrentOpportunitiesReportBean  opportunitiesReportBean = new CurrentOpportunitiesReportBean();
    public ArrayList<CurrentOpportunitiesReportBean> reportList = null;
    
    @AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		sessions = Sessions.getCurrent();
		userId = (String) sessions.getAttribute("userId");
		System.out.println("USER ID IS :"+userId.toUpperCase());
		reportList = CurrentOpportunitiesReportDao.loadTrackingDetails();
	}
    
    @Command
	@NotifyChange("*")
	public void billingInfoButton(@BindingParam("bean")CurrentOpportunitiesReportBean reportBean)
    {
		Map<String, Object> parentMap = new HashMap<String, Object>();
		parentMap.put("ParentBillingObject", reportBean);
		Window window = (Window) Executions.createComponents("/WEB-INF/view/billingInfo.zul", null, parentMap);
		window.doModal();
	}
    
    /************************************************* GETTER AND SETTER METHOD ************************************************************************/
    
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
	public CurrentOpportunitiesReportBean getOpportunitiesReportBean() {
		return opportunitiesReportBean;
	}
	public void setOpportunitiesReportBean(
			CurrentOpportunitiesReportBean opportunitiesReportBean) {
		this.opportunitiesReportBean = opportunitiesReportBean;
	}
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public ArrayList<CurrentOpportunitiesReportBean> getReportList() {
		return reportList;
	}
	public void setReportList(ArrayList<CurrentOpportunitiesReportBean> reportList) {
		this.reportList = reportList;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public boolean isFileuploaded() {
		return fileuploaded;
	}
	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
