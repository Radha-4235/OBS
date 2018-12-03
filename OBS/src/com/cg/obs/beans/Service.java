package com.cg.obs.beans;

import java.util.Date;

public class Service {

	private long serviceId;
	private String serviceDescription;
	private long accountId;
	private Date raisedDate;
	private String serviceStatus;
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public Date getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(Date raisedDate) {
		this.raisedDate = raisedDate;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public Service(long serviceId, String serviceDescription, long accountId,
			Date raisedDate, String serviceStatus) {
		super();
		this.serviceId = serviceId;
		this.serviceDescription = serviceDescription;
		this.accountId = accountId;
		this.raisedDate = raisedDate;
		this.serviceStatus = serviceStatus;
	}
	public Service(String serviceDescription, String serviceStatus) {
		super();
		this.serviceDescription = serviceDescription;
		this.serviceStatus = serviceStatus;
	}
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceDescription="
				+ serviceDescription + ", accountId=" + accountId
				+ ", raisedDate=" + raisedDate + ", serviceStatus="
				+ serviceStatus + "]";
	}
	
	
}
