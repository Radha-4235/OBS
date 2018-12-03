package com.cg.obs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.obs.exceptions.BankException;

public class DBUtility {

	 private static Connection connection = null;
	  public static Connection getConnection() throws BankException
	  {
		  /*Class.forName("oracle.jdbc.driver.OracleDriver");
		  connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "orcl11g");
		  return connection;*/
		  Properties properties = new Properties();
		  File file = new File("Resources/jdbc.properties");
		  FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new BankException("File not found");
		}
		  try {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new BankException("File not found");
		}
		  
		  String driver= properties.getProperty("db.driver");
		  String url= properties.getProperty("db.url");
		  String username= properties.getProperty("db.username");
		  String password= properties.getProperty("db.password");
		  try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new BankException("Class not found"+e);
		}
		  try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new BankException("Class not found");
		}
		  return connection;
	  }
}
