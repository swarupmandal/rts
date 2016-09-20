package org.appsquad.bean;

import java.util.ArrayList;

public class UserprofileBean {

	private String username;
	private String role;
	private Integer userid;
	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public Integer getRollid() {
		return rollid;
	}


	public void setRollid(Integer rollid) {
		this.rollid = rollid;
	}


	private Integer rollid;
	
	


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public ArrayList<UserprofileBean> getUserList() {
		return userList;
	}


	public void setUserList(ArrayList<UserprofileBean> userList) {
		this.userList = userList;
	}
}
