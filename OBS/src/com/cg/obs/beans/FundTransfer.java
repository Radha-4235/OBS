package com.cg.obs.beans;
import java.util.Date;

public class FundTransfer {

	 private long fundTransferID ;
	 private long accountId;
	 private long payeeAccountId;
	 private Date dateOfTransfer;
	 private double transferAmount;
	 
	   public long getFundTransferID() {
		return fundTransferID;
	}

	public void setFundTransferID(long fundTransferID) {
		this.fundTransferID = fundTransferID;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(long payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public Date getDateOfTransfer() {
		return dateOfTransfer;
	}

	public void setDateOfTransfer(Date dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	 
	   public FundTransfer(long fundTransferID, long accountId,
			long payeeAccountId, Date dateOfTransfer, double transferAmount) {
		super();
		this.fundTransferID = fundTransferID;
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.dateOfTransfer = dateOfTransfer;
		this.transferAmount = transferAmount;
	}

	   
	public FundTransfer(long accountId, long payeeAccountId,
			double transferAmount) {
		super();
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.transferAmount = transferAmount;
	}
	

	public FundTransfer(long accountId, long payeeAccountId,
			Date dateOfTransfer, double transferAmount) {
		super();
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.dateOfTransfer = dateOfTransfer;
		this.transferAmount = transferAmount;
	}

	public FundTransfer() {
		super();
		
	}

	@Override
	public String toString() {
		return "FundTransfer [fundTransferID=" + fundTransferID
				+ ", accountId=" + accountId + ", payeeAccountId="
				+ payeeAccountId + ", dateOfTransfer=" + dateOfTransfer
				+ ", transferAmount=" + transferAmount + "]";
	 
	}



}
