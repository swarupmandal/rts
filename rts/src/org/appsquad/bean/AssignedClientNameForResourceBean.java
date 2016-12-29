package org.appsquad.bean;

public class AssignedClientNameForResourceBean {
	private String resourceName;
	private String clientOriginalName;
	private Integer reqId;
    private String reqStatus;
    private String reqSkillName;
    private Integer resourceId;
    
  /***************************************************************************************************************************************************/
  
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getClientOriginalName() {
		return clientOriginalName;
	}
	public void setClientOriginalName(String clientOriginalName) {
		this.clientOriginalName = clientOriginalName;
	}
	public Integer getReqId() {
		return reqId;
	}
	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getReqSkillName() {
		return reqSkillName;
	}
	public void setReqSkillName(String reqSkillName) {
		this.reqSkillName = reqSkillName;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
}
