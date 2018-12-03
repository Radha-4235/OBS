package com.cg.obs.beans;

public class Customer {

	private long accountId;
	private String customerName;
	private String eMail;
	private String address;
	private String panCard;
	private double minBal;
	private String type;

	public Customer(long accountId, String customerName, String eMail,
			String address, String panCard, double minBal, String type) {
		super();
		this.accountId = accountId;
		this.customerName = customerName;
		this.eMail = eMail;
		this.address = address;
		this.panCard = panCard;
		this.minBal = minBal;
		this.type = type;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public double getMinBal() {
		return minBal;
	}

	public void setMinBal(double minBal) {
		this.minBal = minBal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Customer(String customerName, String eMail, String address,
			String panCard, double minBal, String type) {
		super();
		this.customerName = customerName;
		this.eMail = eMail;
		this.address = address;
		this.panCard = panCard;
		this.minBal = minBal;
		this.type = type;
	}

	@Override
	public String toString() {
		return "accountId=" + accountId + "\n customerName="
				+ customerName + "\n eMail=" + eMail + "\n address=" + address
				+ "\n panCard=" + panCard + "\n minBal=" + minBal + "\n type="
				+ type ;
	}
	

}
