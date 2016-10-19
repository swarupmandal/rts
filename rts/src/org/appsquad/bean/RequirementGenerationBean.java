package org.appsquad.bean;

import java.util.Date;

public class RequirementGenerationBean {
  private String clientName;
  private String clientfName;
  private String clientsName;
  private int clientId;
  private String clientOriginalName;
  
  private String reqSkill;
  private int reqSkillId;
  
  private String jobType;
  private String detailedJob;  
  private Integer nofPerResource;
  private Integer nofConResource;
  private Integer oldValue;
  private Date raiseDate;
  private java.sql.Date	raiseDatesql;
  private String raiseDateStr;
  private String raiseDateValue;
  
  private Date closeDate;
  private java.sql.Date closeDatesql;
  private String closeDateStr;
  private String closeDateValue;
  
  private Date createdDate;
  private java.sql.Date createdDatesql;
  private String createdDateStr;
  private String CreatedDateValue;
  
  private String rIdType;
  
  private String reqStatus;
  private int reqStatusId;
  private String ocStatus;
  private int ocStatusId;
  private Integer oldOcStatusId;
  private String oldOcStatus;
  
  private String closureReason;
  private String userName;
  private String contactNo;
  private String email;
  
  private Integer req_id;
  private Integer requirementId;
  private boolean reqOpenDate = false;
  
  ResourceTypeBean resourceTypeBean = new ResourceTypeBean();
  private boolean perFieldvisibility = true;
  private boolean conFieldvisibility = false;
  private Integer type_id;
  private String type_name;
  
  StatusMasterBean statusMasterBean = new StatusMasterBean();
  
  /*************************************************************************************************************************************************/
  
    public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientfName() {
		return clientfName;
	}
	public void setClientfName(String clientfName) {
		this.clientfName = clientfName;
	}
	public String getClientsName() {
		return clientsName;
	}
	public void setClientsName(String clientsName) {
		this.clientsName = clientsName;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getReqSkill() {
		return reqSkill;
	}
	public void setReqSkill(String reqSkill) {
		this.reqSkill = reqSkill;
	}
	public int getReqSkillId() {
		return reqSkillId;
	}
	public void setReqSkillId(int reqSkillId) {
		this.reqSkillId = reqSkillId;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getDetailedJob() {
		return detailedJob;
	}
	public void setDetailedJob(String detailedJob) {
		this.detailedJob = detailedJob;
	}
	public Date getRaiseDate() {
		return raiseDate;
	}
	public void setRaiseDate(Date raiseDate) {
		this.raiseDate = raiseDate;
	}
	public String getRaiseDateStr() {
		return raiseDateStr;
	}
	public void setRaiseDateStr(String raiseDateStr) {
		this.raiseDateStr = raiseDateStr;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public String getCloseDateStr() {
		return closeDateStr;
	}
	public void setCloseDateStr(String closeDateStr) {
		this.closeDateStr = closeDateStr;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public int getReqStatusId() {
		return reqStatusId;
	}
	public void setReqStatusId(int reqStatusId) {
		this.reqStatusId = reqStatusId;
	}
	public String getClosureReason() {
		return closureReason;
	}
	public Integer getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(Integer requirementId) {
		this.requirementId = requirementId;
	}
	public void setClosureReason(String closureReason) {
		this.closureReason = closureReason;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getNofPerResource() {
		return nofPerResource;
	}
	public void setNofPerResource(Integer nofPerResource) {
		this.nofPerResource = nofPerResource;
	}
	public Integer getNofConResource() {
		return nofConResource;
	}
	public void setNofConResource(Integer nofConResource) {
		this.nofConResource = nofConResource;
	}
	public String getOcStatus() {
		return ocStatus;
	}
	public void setOcStatus(String ocStatus) {
		this.ocStatus = ocStatus;
	}
	public int getOcStatusId() {
		return ocStatusId;
	}
	public void setOcStatusId(int ocStatusId) {
		this.ocStatusId = ocStatusId;
	}
	public java.sql.Date getRaiseDatesql() {
		return raiseDatesql;
	}
	public void setRaiseDatesql(java.sql.Date raiseDatesql) {
		this.raiseDatesql = raiseDatesql;
	}
	public java.sql.Date getCloseDatesql() {
		return closeDatesql;
	}
	public void setCloseDatesql(java.sql.Date closeDatesql) {
		this.closeDatesql = closeDatesql;
	}
	public String getRaiseDateValue() {
		return raiseDateValue;
	}
	public void setRaiseDateValue(String raiseDateValue) {
		this.raiseDateValue = raiseDateValue;
	}
	public String getCloseDateValue() {
		return closeDateValue;
	}
	public void setCloseDateValue(String closeDateValue) {
		this.closeDateValue = closeDateValue;
	}
	public Integer getReq_id() {
		return req_id;
	}
	public boolean isReqOpenDate() {
		return reqOpenDate;
	}
	public void setReqOpenDate(boolean reqOpenDate) {
		this.reqOpenDate = reqOpenDate;
	}
	public void setReq_id(Integer req_id) {
		this.req_id = req_id;
	}
	public ResourceTypeBean getResourceTypeBean() {
		return resourceTypeBean;
	}
	public void setResourceTypeBean(ResourceTypeBean resourceTypeBean) {
		this.resourceTypeBean = resourceTypeBean;
	}
	public boolean isPerFieldvisibility() {
		return perFieldvisibility;
	}
	public void setPerFieldvisibility(boolean perFieldvisibility) {
		this.perFieldvisibility = perFieldvisibility;
	}
	public boolean isConFieldvisibility() {
		return conFieldvisibility;
	}
	public void setConFieldvisibility(boolean conFieldvisibility) {
		this.conFieldvisibility = conFieldvisibility;
	}
	public Integer getOldValue() {
		return oldValue;
	}
	public void setOldValue(Integer oldValue) {
		this.oldValue = oldValue;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public java.sql.Date getCreatedDatesql() {
		return createdDatesql;
	}
	public void setCreatedDatesql(java.sql.Date createdDatesql) {
		this.createdDatesql = createdDatesql;
	}
	public String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}
	public String getCreatedDateValue() {
		return CreatedDateValue;
	}
	public void setCreatedDateValue(String createdDateValue) {
		CreatedDateValue = createdDateValue;
	}
	public String getrIdType() {
		return rIdType;
	}
	public void setrIdType(String rIdType) {
		this.rIdType = rIdType;
	}
	public Integer getOldOcStatusId() {
		return oldOcStatusId;
	}
	public void setOldOcStatusId(Integer oldOcStatusId) {
		this.oldOcStatusId = oldOcStatusId;
	}
	public StatusMasterBean getStatusMasterBean() {
		return statusMasterBean;
	}
	public void setStatusMasterBean(StatusMasterBean statusMasterBean) {
		this.statusMasterBean = statusMasterBean;
	}
	public String getOldOcStatus() {
		return oldOcStatus;
	}
	public void setOldOcStatus(String oldOcStatus) {
		this.oldOcStatus = oldOcStatus;
	}
	public String getClientOriginalName() {
		return clientOriginalName;
	}
	public void setClientOriginalName(String clientOriginalName) {
		this.clientOriginalName = clientOriginalName;
	}
}
