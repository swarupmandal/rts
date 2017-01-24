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
	private Date tenureFrom;
	private java.sql.Date tenureFromsql;
	private String tenureFromStr;
	private String tenureFromValue;
	private Date tenureTo;
	private java.sql.Date tenureTosql;
	private java.util.Date tentureToUtil;
	private java.util.Date tentureFromUtil;
	private String tenureToStr;
	private String tenureToValue;
	private Double chargeoutRate;
	private Double resourceSalary;
	private Double margin;
	private String marginString;
	private long marginTotal;
	private String approval;
	private Integer userId;
	private String userName;
	private boolean createOfferDisable = false;
	private String onClickButtonValue;
	private String loginID;
	private String trackLogingID;
	
	private UserClientMappingBean bean = new UserClientMappingBean();
	private ArrayList<UserClientMappingBean> userClBeanList = new ArrayList<UserClientMappingBean>();

	/*********Log-Audit Purpose ************/
	private String mainScreenName = "TRANSACTION";
	private String chileScreenName = "PRE BILLING CONFIRMATION";
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	/*************************************************************************************************************************************************/
	
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
	public java.util.Date getTentureToUtil() {
		return tentureToUtil;
	}
	public void setTentureToUtil(java.util.Date tentureToUtil) {
		this.tentureToUtil = tentureToUtil;
	}
	public java.util.Date getTentureFromUtil() {
		return tentureFromUtil;
	}
	public void setTentureFromUtil(java.util.Date tentureFromUtil) {
		this.tentureFromUtil = tentureFromUtil;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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
	public Date getTenureFrom() {
		return tenureFrom;
	}
	public void setTenureFrom(Date tenureFrom) {
		this.tenureFrom = tenureFrom;
	}
	public java.sql.Date getTenureFromsql() {
		return tenureFromsql;
	}
	public void setTenureFromsql(java.sql.Date tenureFromsql) {
		this.tenureFromsql = tenureFromsql;
	}
	public String getTenureFromStr() {
		return tenureFromStr;
	}
	public void setTenureFromStr(String tenureFromStr) {
		this.tenureFromStr = tenureFromStr;
	}
	public String getTenureFromValue() {
		return tenureFromValue;
	}
	public void setTenureFromValue(String tenureFromValue) {
		this.tenureFromValue = tenureFromValue;
	}
	public Date getTenureTo() {
		return tenureTo;
	}
	public void setTenureTo(Date tenureTo) {
		this.tenureTo = tenureTo;
	}
	public java.sql.Date getTenureTosql() {
		return tenureTosql;
	}
	public void setTenureTosql(java.sql.Date tenureTosql) {
		this.tenureTosql = tenureTosql;
	}
	public String getTenureToStr() {
		return tenureToStr;
	}
	public void setTenureToStr(String tenureToStr) {
		this.tenureToStr = tenureToStr;
	}
	public String getTenureToValue() {
		return tenureToValue;
	}
	public void setTenureToValue(String tenureToValue) {
		this.tenureToValue = tenureToValue;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	public String getMainScreenName() {
		return mainScreenName;
	}
	public void setMainScreenName(String mainScreenName) {
		this.mainScreenName = mainScreenName;
	}
	public String getChileScreenName() {
		return chileScreenName;
	}
	public void setChileScreenName(String chileScreenName) {
		this.chileScreenName = chileScreenName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getSessionUserId() {
		return sessionUserId;
	}
	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}
	public Integer getOperationId() {
		return operationId;
	}
	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}
	public boolean isCreateOfferDisable() {
		return createOfferDisable;
	}
	public void setCreateOfferDisable(boolean createOfferDisable) {
		this.createOfferDisable = createOfferDisable;
	}
	public String getOnClickButtonValue() {
		return onClickButtonValue;
	}
	public void setOnClickButtonValue(String onClickButtonValue) {
		this.onClickButtonValue = onClickButtonValue;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getTrackLogingID() {
		return trackLogingID;
	}
	public void setTrackLogingID(String trackLogingID) {
		this.trackLogingID = trackLogingID;
	}
	public String getMarginString() {
		return marginString;
	}
	public void setMarginString(String marginString) {
		this.marginString = marginString;
	}
	public long getMarginTotal() {
		return marginTotal;
	}
	public void setMarginTotal(long marginTotal) {
		this.marginTotal = marginTotal;
	}
}
