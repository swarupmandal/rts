package org.appsquad.bean;

import java.util.ArrayList;

public class MonthReportBean {

	private int monthId;
	private String monthName;
	
	private CurrentOpportunitiesReportGenerationBean currentOpportunitiesReportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
	private ArrayList<CurrentOpportunitiesReportGenerationBean> currentOpportunitiesReportGenerationBeanList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
	public int getMonthId() {
		return monthId;
	}
	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public CurrentOpportunitiesReportGenerationBean getCurrentOpportunitiesReportGenerationBean() {
		return currentOpportunitiesReportGenerationBean;
	}
	public void setCurrentOpportunitiesReportGenerationBean(
			CurrentOpportunitiesReportGenerationBean currentOpportunitiesReportGenerationBean) {
		this.currentOpportunitiesReportGenerationBean = currentOpportunitiesReportGenerationBean;
	}
	public ArrayList<CurrentOpportunitiesReportGenerationBean> getCurrentOpportunitiesReportGenerationBeanList() {
		return currentOpportunitiesReportGenerationBeanList;
	}
	public void setCurrentOpportunitiesReportGenerationBeanList(
			ArrayList<CurrentOpportunitiesReportGenerationBean> currentOpportunitiesReportGenerationBeanList) {
		this.currentOpportunitiesReportGenerationBeanList = currentOpportunitiesReportGenerationBeanList;
	}
	
}
