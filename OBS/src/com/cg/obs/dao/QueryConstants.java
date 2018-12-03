package com.cg.obs.dao;

public interface QueryConstants {
	public static String login = "select account_id,password from login";
	public static String view = "select * from customer where customer_name=? ";
	public static String update = "update customer set address =? where customer_name=?";
	public static String insertRequest = "update service_tracker set service_description='checkbook',SERVICE_RAISED_DATE=sysdate where  ACCOUNT_ID=?";
	public static String checkStatus = "select SERVICE_RAISED_DATE from service_tracker where account_id=?";
	public static String updateStatus="update service_tracker set SERVICE_STATUS=? where ACCOUNT_ID=? ";
	public static String getServiceId="select SERVICE_ID from service_tracker where ACCOUNT_ID=? ";
	public static String insertQuery = "insert into customer values(account_id.nextval,?,?,?,?,?,?)";
	public static String selectQuery = "select * from transactions";
	public static String detailedQuery = " select * from transactions where account_no=? and dateoftransaction between ? and ?";
	public static String miniStatement = "select * from transactions where  account_no=? and rownum<=10 order by transaction_id desc";
	public static String getpayeeAccId = "select * from Payeetable where Payee_Account_Id=?";
	public static String getBalance = "SELECT Balance FROM Account_Master WHERE Account_NO=?";
	public static String updateBal = "update Account_Master set Balance =? where Account_NO=?";
	public static String getAccId = "select * from Account_Master where Account_NO=?";
	public static String insertDetails = "insert into transactions values(trans_id.nextval,?,sysdate,?,?,?)";
	public static String updatePwd = "update login set password = ? where customername = ?";
	public static String selectMaxId = "select max(account_id) from customer";
	public static String insertAccount = "insert into accountmaster values(?,?,?,?,?,?,?)";
	public static String loginAdmin = "select * from Admin";

	
}
