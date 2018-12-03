package com.cg.obs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.obs.beans.Account;
import com.cg.obs.beans.Customer;
import com.cg.obs.beans.Transactions;
import com.cg.obs.dao.DaoImpl;
import com.cg.obs.dao.IDao;
import com.cg.obs.exceptions.BankException;

public class ServiceImpl implements IService{
IDao dao=new DaoImpl();
	@Override
	public boolean verify(long number, String password) throws BankException {
		
		return dao.verify(number,password);
	}
	@Override
	public Customer getAccountDetails(String name) throws BankException {
		return dao.getAccountDetails(name);
	}
	@Override
	public void updateAddress(String add,String name) throws BankException {
		dao.updateAddress(add,name);
	}
	@Override
	public void insertRequest(long accId) {
		dao.insertRequest(accId);
	}
	@Override
	public int checkStatus(long accId) {
		return dao.checkStatus(accId);
		
	}
	@Override
	public void updateStatus(String status,long accId) throws BankException {
		dao.updateStatus(status,accId);
		
	}
	@Override
	public long getServiceId(long accId) throws BankException {
		
		return dao.getServiceId(accId);
	}
	@Override
	public int addAccount(Customer customer) throws BankException {
		
		return dao.addAccount(customer);
	}
	@Override
	public List<Transactions> getAlltransactions() throws BankException {
		
		return dao.getAllTransactions();
	}
	@Override
	public List<Transactions> getDetailedtransactions(long accId,
			Date iDate, Date sDate) throws BankException {
		
		return dao.getDetailedtransaction(accId,iDate,sDate);
	}
	@Override
	public List<Transactions> getMiniStatement(long accId) throws BankException {
		
		return dao.getMiniStatement(accId);
	}
	@Override
	public boolean payeeAccValid(long payeeAccNo) throws BankException {
		
		return dao.payeeAccValid(payeeAccNo);
	}
	@Override
	public boolean transfer(long accId, double transferAmount)
			throws BankException {
		
		 return dao.transfer(accId,transferAmount);
	}
	@Override
	public void getPassword(String newPwd, String confirmPwd, String name) throws BankException {
		dao.getPassword(newPwd,confirmPwd,name);
	}
	@Override
	public long getAccountNo() throws BankException {
		
		return dao.getAccountNo();
	}
	@Override
	public void addAccountMaster(Account account) throws BankException {
		dao.addAccountMaster(account);
		
	}
	@Override
	public boolean validateCustomer(Customer customer) throws BankException {
		Logger logger = Logger.getLogger(ServiceImpl.class);
		logger.info("in validation account method");
		boolean flag = false;
		List<String> list = new ArrayList<>();
		if (!isNameValid(customer.getCustomerName()))
			list.add("customer name should contain minimum 5 letters and maximum 20 letters");
		if (!isBalanceValid(customer.getMinBal()))
			list.add("account balance should be greater than 1000");
		if (!isEmailValid(customer.geteMail()))
			list.add("Email should be in a proper format");
		if (!list.isEmpty())
			throw new BankException(list + " ");
		else
			flag = true;

		return flag;
	}

	private boolean isEmailValid(String mail) {
		String nameRegEx = "^(.*)@(.*)$";
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
		
	}
	public boolean isNameValid(String name) {
		String nameRegEx = "[A-Za-z]{5,20}";
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public boolean isBalanceValid(double amount) {
		boolean balanceFlag = false;
		if (amount >= 1000)
			balanceFlag = true;
		return balanceFlag;

	}
	@Override
	public boolean verifyAdmin(String name, String password)
			throws BankException {
		return dao.verifyAdmin(name,password);
	}

	
	}

