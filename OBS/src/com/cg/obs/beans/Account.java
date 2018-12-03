package com.cg.obs.beans;

public class Account {
	private long account_id;
	private String name;
	private String email;
	private String adress;
	private String pancard;
	private Double account_balance;
	private String accountType;

	public Account(long account_id, String name, String email, String adress,
			String pancard, Double account_balance, String accountType) {
		super();
		this.account_id = account_id;
		this.name = name;
		this.email = email;
		this.adress = adress;
		this.pancard = pancard;
		this.account_balance = account_balance;
		this.accountType = accountType;
	}
	

	public Account(String name, String email, String adress, String pancard,
			Double account_balance, String accountType) {
		super();
		this.name = name;
		this.email = email;
		this.adress = adress;
		this.pancard = pancard;
		this.account_balance = account_balance;
		this.accountType = accountType;
	}


	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public Double getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(Double account_balance) {
		this.account_balance = account_balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
