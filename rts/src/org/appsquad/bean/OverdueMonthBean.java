package org.appsquad.bean;

import java.util.ArrayList;

public class OverdueMonthBean {
	private String month;
    private Integer monthID;
    private ArrayList<TaskNameBean> yearList = new ArrayList<TaskNameBean>();
    
    
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getMonthID() {
		return monthID;
	}
	public void setMonthID(Integer monthID) {
		this.monthID = monthID;
	}
	public ArrayList<TaskNameBean> getYearList() {
		return yearList;
	}
	public void setYearList(ArrayList<TaskNameBean> yearList) {
		this.yearList = yearList;
	}
}
