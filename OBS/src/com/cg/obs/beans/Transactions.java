package com.cg.obs.beans;

import java.util.Date;

public class Transactions {
private	int transaction_id;                            
	private String tran_description;                                   
private Date	dateofTransaction;                                  
private String transactionType;                                   
private double tranAmount;                                         
private int account_id;
public int getTransaction_id() {
	return transaction_id;
}
public void setTransaction_id(int transaction_id) {
	this.transaction_id = transaction_id;
}
public String getTran_description() {
	return tran_description;
}
public void setTran_description(String tran_description) {
	this.tran_description = tran_description;
}
public Date getDateofTransaction() {
	return dateofTransaction;
}
public void setDateofTransaction(Date dateofTransaction) {
	this.dateofTransaction = dateofTransaction;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public double getTranAmount() {
	return tranAmount;
}
public void setTranAmount(double tranAmount) {
	this.tranAmount = tranAmount;
}
public int getAccount_id() {
	return account_id;
}
public void setAccount_id(int account_id) {
	this.account_id = account_id;
}
public Transactions() {
	super();
	// TODO Auto-generated constructor stub
}
public Transactions(int transaction_id, String tran_description,
		Date dateofTransaction, String transactionType, double tranAmount,
		int account_id) {
	super();
	this.transaction_id = transaction_id;
	this.tran_description = tran_description;
	this.dateofTransaction = dateofTransaction;
	this.transactionType = transactionType;
	this.tranAmount = tranAmount;
	this.account_id = account_id;
}   


}
