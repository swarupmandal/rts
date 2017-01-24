package org.appsquad.bean;

import java.util.ArrayList;

public class OverdueWeeklyBean {
    private String week;
    private Integer weekID;
    private TaskNameBean taskNameBean = new TaskNameBean();
    private ArrayList<TaskNameBean> weekList = new ArrayList<TaskNameBean>();
    private ArrayList<TaskNameBean> yearList = new ArrayList<TaskNameBean>();
    
    
    /*****************************************************************GETTER AND SETTER***************************************************************/
    
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public Integer getWeekID() {
		return weekID;
	}
	public void setWeekID(Integer weekID) {
		this.weekID = weekID;
	}
	public TaskNameBean getTaskNameBean() {
		return taskNameBean;
	}
	public void setTaskNameBean(TaskNameBean taskNameBean) {
		this.taskNameBean = taskNameBean;
	}
	public ArrayList<TaskNameBean> getWeekList() {
		return weekList;
	}
	public void setWeekList(ArrayList<TaskNameBean> weekList) {
		this.weekList = weekList;
	}
	public ArrayList<TaskNameBean> getYearList() {
		return yearList;
	}
	public void setYearList(ArrayList<TaskNameBean> yearList) {
		this.yearList = yearList;
	}
}
