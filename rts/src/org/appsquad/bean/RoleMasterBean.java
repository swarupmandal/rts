package org.appsquad.bean;

public class RoleMasterBean {
	
	private String rollid;
	private String roll;
	private String user;
	private String userid;
	private int rollId;
	private boolean visibilityRoleTextBox = true;
	private boolean visibilityEditButton = false;
	private boolean visibilitySaveButton = false;
	private boolean visibilityDeleteButton = false;
	
	
	/*********************************************************************************************************************************************/
	
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
}
