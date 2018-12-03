package com.cg.obs.service;

import java.util.Date;
import java.util.List;

import com.cg.obs.beans.Account;
import com.cg.obs.beans.Customer;
import com.cg.obs.beans.Transactions;
import com.cg.obs.exceptions.BankException;

public interface IService {

	boolean verify(long number, String password) throws BankException;

	Customer getAccountDetails(String name) throws BankException;

	void updateAddress(String add, String name) throws BankException;

	void insertRequest(long accId);

	int checkStatus(long accId);

	void updateStatus(String status,long accId) throws BankException;

	long getServiceId(long accId) throws BankException;

	int addAccount(Customer customer) throws BankException;

	List<Transactions> getAlltransactions() throws BankException;

	List<Transactions> getDetailedtransactions(long accId, Date iDate,
			Date sDate) throws BankException;

	List<Transactions> getMiniStatement(long accId) throws BankException;

	boolean payeeAccValid(long payeeAccNo)throws BankException;

	boolean transfer(long accId, double transferAmount)throws BankException;

	void getPassword(String newPwd, String confirmPwd, String name) throws BankException;

	long getAccountNo() throws BankException;

	void addAccountMaster(Account account) throws BankException;

	boolean validateCustomer(Customer customer) throws BankException;

	boolean verifyAdmin(String name, String password)throws BankException;


}
