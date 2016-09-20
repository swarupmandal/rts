package org.appsquad.bean;

public class SkillsetMasterbean {
	private Integer id;
	private String skillset;
	private String skillsetdetails;
	private String userId;
	
	/*******************************getter ans setter method **************************************************/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSkillset() {
		return skillset;
	}
	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}
	public String getSkillsetdetails() {
		return skillsetdetails;
	}
	public void setSkillsetdetails(String skillsetdetails) {
		this.skillsetdetails = skillsetdetails;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
