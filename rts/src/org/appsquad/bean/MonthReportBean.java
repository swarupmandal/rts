package org.appsquad.bean;

import java.util.ArrayList;

public class MonthReportBean {

	private int monthId;
	private String monthName;
	
	private String boldStyle = "font-weight: bold; color: black";
	private String lighterStyle = "font-weight: lighter; color: black";
	private String backGround;
	private String style;
	
	private CurrentOpportunitiesReportGenerationBean currentOpportunitiesReportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
	private ArrayList<CurrentOpportunitiesReportGenerationBean> currentOpportunitiesReportGenerationBeanList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
	
	
	/*****************************************************************************************************************************************************/
	
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
	public String getBoldStyle() {
		return boldStyle;
	}
	public void setBoldStyle(String boldStyle) {
		this.boldStyle = boldStyle;
	}
	public String getLighterStyle() {
		return lighterStyle;
	}
	public void setLighterStyle(String lighterStyle) {
		this.lighterStyle = lighterStyle;
	}
	public String getBackGround() {
		return backGround;
	}
	public void setBackGround(String backGround) {
		this.backGround = backGround;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
