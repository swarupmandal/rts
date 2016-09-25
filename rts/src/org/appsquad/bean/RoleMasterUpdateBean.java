package org.appsquad.bean;

public class RoleMasterUpdateBean {
	private String rollid;
	private String roll;
	private String user;
	private String userid;
	private int rollId;
	private boolean visibilityRoleTextBox = true;
	private boolean visibilityEditButton = false;
	private boolean visibilitySaveButton = false;
	private boolean visibilityDeleteButton = false;
	private boolean visibilityUpdateButton = true;
	private int mapperId;
	private Integer uId;
	
	private UserprofileBean userprofileBean = new UserprofileBean(); 
	private RollDropDownBean downBean = new RollDropDownBean();
	
	
	/*********************************************************************************************************************************************/
	
	public String getRollid() {
		return rollid;
	}
	public void setRollid(String rollid) {
		this.rollid = rollid;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getRollId() {
		return rollId;
	}
	public void setRollId(int rollId) {
		this.rollId = rollId;
	}
	public boolean isVisibilityRoleTextBox() {
		return visibilityRoleTextBox;
	}
	public void setVisibilityRoleTextBox(boolean visibilityRoleTextBox) {
		this.visibilityRoleTextBox = visibilityRoleTextBox;
	}
	public boolean isVisibilityEditButton() {
		return visibilityEditButton;
	}
	public void setVisibilityEditButton(boolean visibilityEditButton) {
		this.visibilityEditButton = visibilityEditButton;
	}
	public boolean isVisibilitySaveButton() {
		return visibilitySaveButton;
	}
	public void setVisibilitySaveButton(boolean visibilitySaveButton) {
		this.visibilitySaveButton = visibilitySaveButton;
	}
	public boolean isVisibilityDeleteButton() {
		return visibilityDeleteButton;
	}
	public void setVisibilityDeleteButton(boolean visibilityDeleteButton) {
		this.visibilityDeleteButton = visibilityDeleteButton;
	}
	public boolean isVisibilityUpdateButton() {
		return visibilityUpdateButton;
	}
	public void setVisibilityUpdateButton(boolean visibilityUpdateButton) {
		this.visibilityUpdateButton = visibilityUpdateButton;
	}
	public int getMapperId() {
		return mapperId;
	}
	public void setMapperId(int mapperId) {
		this.mapperId = mapperId;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public RollDropDownBean getDownBean() {
		return downBean;
	}
	public void setDownBean(RollDropDownBean downBean) {
		this.downBean = downBean;
	}
}
