package com.cg.obs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.obs.beans.Account;
import com.cg.obs.beans.Customer;
import com.cg.obs.beans.Transactions;
import com.cg.obs.exceptions.BankException;
import com.cg.obs.utility.DBUtility;

public class DaoImpl implements IDao {
	static Logger logger = Logger.getLogger(DaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	Date date = new Date(0);

	@Override
	public boolean verify(long number, String password) throws BankException {
		boolean flag = false;
		try {

			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			String query = QueryConstants.login;
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				long id = result.getLong(1);
				String pwd = result.getString(2);
				if (id == number && password.equals(pwd)) {
					flag = true;
					break;
				}
			}
		} catch (BankException e) {
			logger.error("Not created");
			System.out.println("could not establish connection" + e);
		} catch (SQLException e) {
			logger.error("DB error");
			throw new BankException("Sql Error" + e);
		}
		return flag;
	}

	@Override
	public Customer getAccountDetails(String name) throws BankException {
		String query = QueryConstants.view;
		Customer customer = null;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			result = statement.executeQuery();

			while (result.next()) {
				logger.debug("Account details fetched");
				int accId = result.getInt(1);
				String cusName = result.getString(2);
				String email = result.getString(3);
				String address = result.getString(4);
				String pancard = result.getString(5);
				double min_bal = result.getDouble(6);
				String type = result.getString(7);
				customer = new Customer(accId, cusName, email, address,
						pancard, min_bal, type);
			}

		} catch (SQLException e) {
			logger.error("DB error");
			throw new BankException("Db error" + e);
		}

		return customer;
	}

	@SuppressWarnings("unused")
	@Override
	public int updateAddress(String add, String name) throws BankException {
		String query = QueryConstants.update;

		int result=0;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			statement = connection.prepareStatement(query);
			statement.setString(1, add);
			statement.setString(2, name);

			
			 result = statement.executeUpdate();
			logger.debug("Updated adress successfully");

		} catch (SQLException e) {
			logger.error("DB error");
			throw new BankException("db error" + e);
		}
	return result;
	}

	@Override
	public void insertRequest(long accId) {
		String query = QueryConstants.insertRequest;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection.prepareStatement(query);
				statement.setLong(1, accId);
				int result = statement.executeUpdate();
				if (result > 0) {
					logger.debug("inserted checkbook request");
				}

			} catch (SQLException e) {
				logger.error("DB error");
				e.printStackTrace();
			}

		} catch (BankException e) {

			logger.error("Not created");
		}

	}

	@Override
	public int checkStatus(long accId) {
		String query = QueryConstants.checkStatus;
		int days = 0;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection.prepareStatement(query);
				statement.setLong(1, accId);
				result = statement.executeQuery();

				if (result.next()) {
					Date date = result.getDate(1);
					LocalDate sql = date.toLocalDate();
					LocalDate now = LocalDate.now();
					days = now.compareTo(sql);

				}
			} catch (SQLException e) {
				logger.error("DB error");
				e.printStackTrace();
			}

		} catch (BankException e) {
			logger.error("Not created");
			e.printStackTrace();
		}
		return days;
	}

	@Override
	public void updateStatus(String status, long accId) throws BankException {
		String query = QueryConstants.updateStatus;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection.prepareStatement(query);
				statement.setString(1, status);
				statement.setLong(2, accId);
				int res = statement.executeUpdate();
				if (res > 0) {
					logger.debug("Checkbook status updated");
				}
			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("DB error");
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("connection not established");
		}

	}

	@Override
	public long getServiceId(long accId) throws BankException {
		String query = QueryConstants.getServiceId;
		long serviceId = 0;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection.prepareStatement(query);
				statement.setLong(1, accId);
				result = statement.executeQuery();
				if (result.next())
					serviceId = result.getLong(1);

			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("DB error" + e);
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("Connection not established" + e);
		}

		return serviceId;
	}

	@Override
	public int addAccount(Customer customer) throws BankException {
		int result = 0;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection
						.prepareStatement(QueryConstants.insertQuery);
				statement.setString(1, customer.getCustomerName());
				statement.setString(2, customer.geteMail());
				statement.setString(3, customer.getAddress());
				statement.setString(4, customer.getPanCard());
				statement.setDouble(5, customer.getMinBal());
				statement.setString(6, customer.getType());
				result = statement.executeUpdate();
				if (result > 0) {
					logger.debug("Account Created in customer table");
				}
			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("DB error" + e);
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("Connection not established" + e);
		}

		return result;
	}

	@Override
	public List<Transactions> getAllTransactions() throws BankException {
		List<Transactions> list = new ArrayList<>();
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection
						.prepareStatement(QueryConstants.selectQuery);
				result = statement.executeQuery();

				while (result.next()) {

					int id = result.getInt(1);
					String tran_description = result.getString(2);
					Date dateofTransaction = result.getDate(3);
					String transactionType = result.getString(4);
					double tranAmount = result.getDouble(5);
					int account_id = result.getInt(6);

					Transactions transactions = new Transactions(id,
							tran_description, dateofTransaction,
							transactionType, tranAmount, account_id);
					list.add(transactions);

				}
			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("DB error");
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("Connection not established");
		}

		return list;
	}

	@Override
	public List<Transactions> getDetailedtransaction(long accId,
			java.util.Date iDate, java.util.Date sDate) throws BankException {

		long ims = iDate.getTime();
		Date iDates = new Date(ims);
		long sms = sDate.getTime();
		Date sDates = new Date(sms);
		List<Transactions> list = new ArrayList<>();
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection
						.prepareStatement(QueryConstants.detailedQuery);
				statement.setLong(1, accId);
				statement.setDate(2, iDates);
				statement.setDate(3, sDates);

				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next()) {

					int id = resultSet.getInt(1);
					String tran_description = resultSet.getString(2);
					Date dateofTransaction = resultSet.getDate(3);
					String transactionType = resultSet.getString(4);
					double tranAmount = resultSet.getDouble(5);
					int account_id = resultSet.getInt(6);

					Transactions transactions = new Transactions(id,
							tran_description, dateofTransaction,
							transactionType, tranAmount, account_id);
					list.add(transactions);

				}
			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("DB error");
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("Could not establish connection");
		}

		return list;
	}

	@Override
	public List<Transactions> getMiniStatement(long accId) throws BankException {
		List<Transactions> list = new ArrayList<>();
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection
						.prepareStatement(QueryConstants.miniStatement);
				statement.setLong(1, accId);
				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next()) {

					int id = resultSet.getInt(1);
					String tran_description = resultSet.getString(2);
					Date dateofTransaction = resultSet.getDate(3);
					String transactionType = resultSet.getString(4);
					double tranAmount = resultSet.getDouble(5);
					int account_id = resultSet.getInt(6);

					Transactions transactions = new Transactions(id,
							tran_description, dateofTransaction,
							transactionType, tranAmount, account_id);
					list.add(transactions);

				}
			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("DB error");
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("Could not establish connection");
		}

		return list;
	}

	@Override
	public boolean payeeAccValid(long payeeAccNo) throws BankException {

		ResultSet resultSet = null;
		boolean res = false;
		connection = DBUtility.getConnection();
		logger.info("Connection Established");
		try {
			statement = connection
					.prepareStatement(QueryConstants.getpayeeAccId);
			statement.setLong(1, payeeAccNo);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				res = true;
			}
		} catch (SQLException e) {
			logger.error("DB error");
			e.printStackTrace();
		}

		return res;

	}

	@SuppressWarnings("unused")
	@Override
	public boolean transfer(long accId, double transferAmount)
			throws BankException {

		boolean res = false;
		boolean flag = false;
		connection = DBUtility.getConnection();
		logger.info("Connection Established");
		double accountBalance = 0;
		System.out.println("hi5436");
		try {
			statement = connection.prepareStatement(QueryConstants.getBalance);
			statement.setLong(1, accId);
			result = statement.executeQuery();
			if (result.next()) {
				accountBalance = result.getDouble(1);
			}
			if (accountBalance > 500 && transferAmount < accountBalance) {
				accountBalance = accountBalance - transferAmount;
				System.out.println("Your account Balance is:" + accountBalance);
				statement = connection
						.prepareStatement(QueryConstants.updateBal);
				statement.setDouble(1, accountBalance);
				statement.setLong(2, accId);
				result = statement.executeQuery();
				if (result.next()) {
					res = true;
					statement = connection
							.prepareStatement(QueryConstants.getAccId);
					statement.setLong(1, accId);
					result = statement.executeQuery();
					if (result.next()) {
						long accId1 = result.getLong(1);
						String accType = result.getString(2);

						statement = connection
								.prepareStatement(QueryConstants.insertDetails);
						String s = "Credit";
						statement.setString(1, s);
						statement.setString(2, "C");
						statement.setDouble(3, transferAmount);
						statement.setLong(4, accId);
						int result = statement.executeUpdate();
						if (result > 0) {
							flag = true;
						}
					}
				} else {
					System.out.println("Insufficient Balance");
				}
			}
		} catch (SQLException e) {
			logger.error("DB error");
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (SQLException e) {
				throw new BankException("Problem while closing resultset");
			}
			try {
				statement.close();
			} catch (SQLException e) {
				throw new BankException("Problem while closing statement");
			}

		}

		return res;
	}

	@SuppressWarnings("unused")
	@Override
	public void getPassword(String newPwd, String confirmPwd, String name)
			throws BankException {

		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
		} catch (BankException e1) {
			e1.printStackTrace();
		}

		try {
			statement = connection.prepareStatement(QueryConstants.updatePwd);
			if (newPwd.equals(confirmPwd)) {
				statement.setString(1, newPwd);
				statement.setString(2, name);
				int value = statement.executeUpdate();
				System.out.println("Password updated successfully..!");
			} else {
				System.out.println("Please enter again");
			}

		} catch (SQLException e) {
			logger.error("DB error");
			throw new BankException("Re-Enter password " + e);
		}

	}

	@Override
	public long getAccountNo() throws BankException {
		long id = 0;
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection
						.prepareStatement(QueryConstants.selectMaxId);
				result = statement.executeQuery();
				result.next();
				id = result.getInt(1);
			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("SQL error");
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("Connection not established");
		}

		return id;
	}

	@Override
	public void addAccountMaster(Account account) throws BankException {
		try {
			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			try {
				statement = connection
						.prepareStatement(QueryConstants.insertAccount);
				statement.setLong(1, account.getAccount_id());
				statement.setString(2, account.getName());
				statement.setString(3, account.getEmail());
				statement.setString(4, account.getAdress());
				statement.setString(5, account.getPancard());
				statement.setDouble(6, account.getAccount_balance());
				statement.setString(7, account.getAccountType());
				statement.executeUpdate();
			} catch (SQLException e) {
				logger.error("DB error");
				throw new BankException("DB error" + e);
			}

		} catch (BankException e) {
			logger.error("Not created");
			throw new BankException("Connection not established" + e);
		}

	}

	@Override
	public boolean verifyAdmin(String name, String password)
			throws BankException {
		boolean flag = false;
		try {

			connection = DBUtility.getConnection();
			logger.info("Connection Established");
			String query = QueryConstants.loginAdmin;
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			while (result.next()) {
				String name1 = result.getString(1);
				String pwd = result.getString(2);
				if (name.equals(name1) && password.equals(pwd)) {
					flag = true;
					break;
				}
			}
		} catch (BankException e) {
			logger.error("Not created");
			System.out.println("could not establish connection" + e);
		} catch (SQLException e) {
			logger.error("DB error");
			throw new BankException("Sql Error" + e);
		}
		return flag;
	
		
	}
}
