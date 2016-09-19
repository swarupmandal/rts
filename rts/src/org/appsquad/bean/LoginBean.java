package org.appsquad.bean;

public class LoginBean {

	private int id;
	private String userName;
	private String userId;
	private String password;
	private int companyId;
	private int unitId;
	
	private Boolean userNameFocus = false;
	private Boolean passwordFocus = false;
	
	public String userNameStyle = "";
	public String passwordStyle = "";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public Boolean getUserNameFocus() {
		return userNameFocus;
	}
	public void setUserNameFocus(Boolean userNameFocus) {
		this.userNameFocus = userNameFocus;
	}
	public Boolean getPasswordFocus() {
		return passwordFocus;
	}
	public void setPasswordFocus(Boolean passwordFocus) {
		this.passwordFocus = passwordFocus;
	}
	public String getUserNameStyle() {
		return userNameStyle;
	}
	public void setUserNameStyle(String userNameStyle) {
		this.userNameStyle = userNameStyle;
	}
	public String getPasswordStyle() {
		return passwordStyle;
	}
	public void setPasswordStyle(String passwordStyle) {
		this.passwordStyle = passwordStyle;
	}
	
	
	
}
