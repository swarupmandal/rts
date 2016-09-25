package org.appsquad.bean;

public class ResourceAllocationTrackingBean {

	private String clientName;
	private Integer clientId;
	private String clientNameSearch;
	
	private Integer reqId;
	private Integer r_idSearch;
	
	private String skillSet;
   
	
	
	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public Integer getClientId() {
		return clientId;
	}


	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}


	public Integer getReqId() {
		return reqId;
	}


	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}


	public String getClientNameSearch() {
		return clientNameSearch;
	}


	public void setClientNameSearch(String clientNameSearch) {
		this.clientNameSearch = clientNameSearch;
	}


	public Integer getR_idSearch() {
		return r_idSearch;
	}


	public void setR_idSearch(Integer r_idSearch) {
		this.r_idSearch = r_idSearch;
	}


	public String getSkillSet() {
		return skillSet;
	}


	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	
}
