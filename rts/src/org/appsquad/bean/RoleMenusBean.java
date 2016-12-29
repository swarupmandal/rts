package org.appsquad.bean;

public class RoleMenusBean {
    private Integer rtsRolePriviledgeId;
    private String userId;
    private Integer menusId;
    private String menusName;
    private String masterLink = "N";
    private String transactionLink = "N";
    private String reportLink = "N";
    private Integer userSerialId;
    
    /*************************************************************************************************************************************************/
	public Integer getRtsRolePriviledgeId() {
		return rtsRolePriviledgeId;
	}
	public void setRtsRolePriviledgeId(Integer rtsRolePriviledgeId) {
		this.rtsRolePriviledgeId = rtsRolePriviledgeId;
	}
	public Integer getMenusId() {
		return menusId;
	}
	public void setMenusId(Integer menusId) {
		this.menusId = menusId;
	}
	public String getMenusName() {
		return menusName;
	}
	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMasterLink() {
		return masterLink;
	}
	public void setMasterLink(String masterLink) {
		this.masterLink = masterLink;
	}
	public String getTransactionLink() {
		return transactionLink;
	}
	public void setTransactionLink(String transactionLink) {
		this.transactionLink = transactionLink;
	}
	public String getReportLink() {
		return reportLink;
	}
	public void setReportLink(String reportLink) {
		this.reportLink = reportLink;
	}
	public Integer getUserSerialId() {
		return userSerialId;
	}
	public void setUserSerialId(Integer userSerialId) {
		this.userSerialId = userSerialId;
	}
}
