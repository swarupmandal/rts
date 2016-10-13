package org.appsquad.bean;

public class ChangePasswordbean {
	
	private String userId;
	private String password;
	private String newPassword;
	private String reTypeNewPassword;
	
	
	/*************************************************************************************************************************************************/
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getReTypeNewPassword() {
		return reTypeNewPassword;
	}
	public void setReTypeNewPassword(String reTypeNewPassword) {
		this.reTypeNewPassword = reTypeNewPassword;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
