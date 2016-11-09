package org.appsquad.bean;

import java.sql.Date;
import java.util.ArrayList;

public class CurrentOpportunitiesBean {
	
	private Integer reqResStatusTrackingId;
	
	private Integer clientId;
	private String clientName;
	
	private Integer rid;
	
	private Integer skillsetId;
	private String skillset;
	
	private Integer resourceId;
	private String resourceName;
	
	private Integer resourceTypeId;
	private String resourceType;
	
	private Date tenureFromSql;
	private java.util.Date tenureFromUtil;
	private String tenureFromString;
	
	private Date tenureToSql;
	private java.util.Date tenureToUtil;
	private String tenureToString;
	
	private Double chargeoutRate;
	private Double resourceSalary;
	private Double margin;
	
	private String approval;
	
	private Integer userId;
	private String userName;
	
	private UserClientMappingBean bean = new UserClientMappingBean();
	private ArrayList<UserClientMappingBean> userClBeanList = new ArrayList<UserClientMappingBean>();

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getSkillsetId() {
		return skillsetId;
	}

	public void setSkillsetId(Integer skillsetId) {
		this.skillsetId = skillsetId;
	}

	public String getSkillset() {
		return skillset;
	}

	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}

	public Integer getResourceTypeId() {
		return resourceTypeId;
	}

	public void setResourceTypeId(Integer resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Date getTenureFromSql() {
		return tenureFromSql;
	}

	public void setTenureFromSql(Date tenureFromSql) {
		this.tenureFromSql = tenureFromSql;
	}

	public java.util.Date getTenureFromUtil() {
		return tenureFromUtil;
	}

	public void setTenureFromUtil(java.util.Date tenureFromUtil) {
		this.tenureFromUtil = tenureFromUtil;
	}

	public String getTenureFromString() {
		return tenureFromString;
	}

	public void setTenureFromString(String tenureFromString) {
		this.tenureFromString = tenureFromString;
	}

	public Date getTenureToSql() {
		return tenureToSql;
	}

	public void setTenureToSql(Date tenureToSql) {
		this.tenureToSql = tenureToSql;
	}

	public java.util.Date getTenureToUtil() {
		return tenureToUtil;
	}

	public void setTenureToUtil(java.util.Date tenureToUtil) {
		this.tenureToUtil = tenureToUtil;
	}

	public String getTenureToString() {
		return tenureToString;
	}

	public void setTenureToString(String tenureToString) {
		this.tenureToString = tenureToString;
	}

	public Double getChargeoutRate() {
		return chargeoutRate;
	}

	public void setChargeoutRate(Double chargeoutRate) {
		this.chargeoutRate = chargeoutRate;
	}

	public Double getResourceSalary() {
		return resourceSalary;
	}

	public void setResourceSalary(Double resourceSalary) {
		this.resourceSalary = resourceSalary;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getReqResStatusTrackingId() {
		return reqResStatusTrackingId;
	}

	public void setReqResStatusTrackingId(Integer reqResStatusTrackingId) {
		this.reqResStatusTrackingId = reqResStatusTrackingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserClientMappingBean getBean() {
		return bean;
	}

	public void setBean(UserClientMappingBean bean) {
		this.bean = bean;
	}

	public ArrayList<UserClientMappingBean> getUserClBeanList() {
		return userClBeanList;
	}

	public void setUserClBeanList(ArrayList<UserClientMappingBean> userClBeanList) {
		this.userClBeanList = userClBeanList;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}
	
	
	
	
	
}
