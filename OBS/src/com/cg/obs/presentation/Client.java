package com.cg.obs.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.obs.beans.Account;
import com.cg.obs.beans.Customer;
import com.cg.obs.beans.Transactions;
import com.cg.obs.exceptions.BankException;
import com.cg.obs.service.IService;
import com.cg.obs.service.ServiceImpl;

public class Client {
	static Logger logger = Logger.getLogger(Client.class);
	public static void main(String[] args) {
		PropertyConfigurator.configure("Resources/log4j.properties");
		logger.info("log4j configured");
		Scanner scanner = new Scanner(System.in);
		IService service = new ServiceImpl();
		long accId;
		int option =0;
		System.out.println("Welcome to Online Banking System");
		System.out.println("1.Admin");
		System.out.println("2.AccountHolder");
		try{
		option = scanner.nextInt();
		logger.info("selected option is :" + option);}
		catch (InputMismatchException e) {
			System.out.println("Enter digits only(1-2)");
			logger.error(" User entered Invalid option");
			System.exit(0);
		}
		switch (option) {
		case 1:
			scanner.nextLine();
			System.out.println("Enter Admin Name");
			String name1=scanner.nextLine();

			System.out.println("Enter the password");
			String password1 = scanner.nextLine();

			try {
				if (service.verifyAdmin(name1, password1)) {
					System.out.println("Success");
					
			int input =0;
			System.out.println("1.Create account");
			System.out.println("2.view  all transactions");

			System.out.println("select your choice");
			try{
			 input = scanner.nextInt();
			logger.info("selected option is :" + input);}
			catch (InputMismatchException e) {
				System.out.println("Enter digits only(1-2");
				logger.error(" User entered Invalid option");
				System.exit(0);
			}
			switch (input) {
			case 1:
				scanner.nextLine();
				System.out.println("Enter name of the customer: ");
				String name = scanner.next();
				System.out.println("Enter email: ");
				String email = scanner.next();
				System.out.println("Enter adress: ");
				String adress = scanner.next();
				System.out.println("Enter pancard: ");
				String pancard = scanner.next();
				System.out.println("enter account_balance:");
				double account_balance = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("enter accountType:");
				String accountType = scanner.nextLine();
				Customer customer= new Customer(name, email, adress, pancard, account_balance, accountType);
				try {
					logger.debug("Data in the customer class is" + customer);
					if(service.validateCustomer(customer))
					{

					try {
						service.addAccount(customer);
						long accno=service.getAccountNo();
						Account account=new Account(accno, name, email, adress, pancard, account_balance, accountType);
						service.addAccountMaster(account);
						
						System.out.println( "inserted..");

					} catch (BankException e) {

						System.out.println(e.getMessage());
					}
					}
				} catch (BankException e1) {
					System.out.println(e1.getMessage());
				}
					

				break;
			case 2:
				try {
					List<Transactions> list = service.getAlltransactions();
					for (Transactions transactions : list) {

						System.out.println(transactions.getTransaction_id()
								+ ":" + transactions.getTran_description()
								+ ":" + transactions.getTranAmount() + ":"
								+ transactions.getTransactionType() + ":"
								+ transactions.getAccount_id() + ":"
								+ transactions.getDateofTransaction());
					}

				} catch (BankException e) {
					System.out.println(e.getMessage());
				}

				break;
			
			default:
				break;

			}
					
			break;
				}
				else{
					System.out.println("Wrong username and password");
				}
			}
				catch (BankException e) {
				System.out.println(e.getMessage());
				}
			
		// --------------------------------------------------
		// accountholder part
		case 2:
			scanner.nextLine();
			System.out.println("Enter Account number");
			long number = scanner.nextLong();
			scanner.nextLine();
			System.out.println("Enter name of Account holder");
			String name=scanner.nextLine();

			System.out.println("Enter the password");
			String password = scanner.nextLine();

			try {
				if (service.verify(number, password)) {
					System.out.println("Success");

					Customer customer = service.getAccountDetails(name);
					if (customer == null) {
						System.out.println("unable to connect, try later");
					} else {
						System.out.println(customer);
						accId = customer.getAccountId();
						System.out.println("1.Request for address change");
						System.out.println("2.Request for checkbook");
						System.out.println("3.view detailed statement");
						System.out.println("4.mini statement");
						System.out.println("5.Fund Transfer");
						System.out.println("6.Change Password");

						int option1=0;
						System.out.println();
						try{
						 option1 = scanner.nextInt();
						logger.info("selected option is :" + option1);}
						catch (InputMismatchException e) {
							System.out.println("Enter digits only(1-6");
							logger.error(" User entered Invalid option");
							System.exit(0);
						}
						switch (option1) {
						case 1:
							scanner.nextLine();
							System.out
									.println("Enter the new address to be updated");
							String add = scanner.nextLine();
							service.updateAddress(add, name);
							System.out.println("Address updated successfully");

							break;

						case 2:
							System.out.println("Welcome to query section");
							System.out.println("1.insert query");
							System.out.println("2.status");
							int choice = scanner.nextInt();
							logger.info("selected option is :" + choice);
							switch (choice) {
							case 1:
								service.insertRequest(accId);
								System.out
										.println("Your request for checkbook is successfull");
								long serviceId = service.getServiceId(accId);
								System.out.println("Your service id is "
										+ serviceId);

								break;

							case 2:
								int day = service.checkStatus(accId);
								String status = "";

								if (day < 7) {
									status = "Issued";
								} else if (day > 7 && day < 14) {
									status = "Dispatched";
								} else {
									status = "Returned";
								}
								System.out.println(status);
								service.updateStatus(status, accId);
								/*
								 * System.out
								 * .println(" Status Updated successfully");
								 */
								break;
							default:
								break;
							}
							break;
						case 3:
							/*
							 * System.out.println("enter account number:"); int
							 * accountno = scanner.nextInt();
							 */
							scanner.nextLine();
							System.out.println("enter inital date:");
							String date1 = scanner.nextLine();
							System.out.println("enter last date:");
							String date2 = scanner.nextLine();

							SimpleDateFormat format = new SimpleDateFormat(
									"dd/MM/yyyy");
							java.util.Date iDate = format.parse(date1);
							java.util.Date sDate = format.parse(date2);

							List<Transactions> list = service
									.getDetailedtransactions(accId, iDate,
											sDate);
							for (Transactions transactions : list) {

								System.out.println(transactions
										.getTransaction_id()
										+ ":"
										+ transactions.getTran_description()
										+ ":"
										+ transactions.getTranAmount()
										+ ":"
										+ transactions.getTransactionType()
										+ ":"
										+ transactions.getAccount_id()
										+ ":"
										+ transactions.getDateofTransaction());
							}

							break;
						case 4:
							List<Transactions> list1 = service
									.getMiniStatement(accId);
							for (Transactions transactions : list1) {

								System.out.println(transactions
										.getTransaction_id()
										+ ":"
										+ transactions.getTran_description()
										+ ":"
										+ transactions.getTranAmount()
										+ ":"
										+ transactions.getTransactionType()
										+ ":"
										+ transactions.getAccount_id()
										+ ":"
										+ transactions.getDateofTransaction());
							}

							break;
						case 5:

							System.out.println("Enter payees account number:");
							long payeeAccNo = scanner.nextLong();
							boolean valid = service.payeeAccValid(payeeAccNo);
							if (valid) {
								System.out.println("Enter Transfer Amount:");
								double transferAmount = scanner.nextDouble();
								boolean success = service.transfer(accId,
										transferAmount);
								if (success) {
									System.out
											.println("Money transferred successfully");
								}
							} else {
								System.out
										.println("Enter valid payee Account number");
							}

							break;
						case 6:
							scanner.nextLine();
							System.out.println("Type new password: ");
							String newPwd = scanner.nextLine();

							System.out.println("Re-type to confirm password: ");

							String confirmPwd = scanner.nextLine();
							service.getPassword(newPwd, confirmPwd, name);

						}
					}

				} else {
					System.out.println("Enter correct details");
				}
			} catch (BankException e) {
				System.out.println(e.getMessage());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		default:
			break;
		}
		scanner.close();

	}

}
