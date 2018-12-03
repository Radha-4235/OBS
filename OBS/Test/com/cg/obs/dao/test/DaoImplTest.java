package com.cg.obs.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.obs.beans.Transactions;
import com.cg.obs.dao.DaoImpl;
import com.cg.obs.dao.IDao;
import com.cg.obs.exceptions.BankException;

public class DaoImplTest {

	static IDao dao=null;
	@BeforeClass
	public static  void setUp() throws Exception {
	dao=new DaoImpl();
	}

	@AfterClass
	public static void tearDown() throws Exception {
	dao=null;
	}

	@Test
	public void testVerify() {
		try {
			boolean result=dao.verify(100000, "123");
			
			assertEquals(true, result);
			
		} catch (BankException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	@Test
	public void testUpdateAddress() {
		try {
			 int result=dao.updateAddress("davanagere", "ramesh");
			 assertEquals(1,result);
		} catch (BankException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetMiniStatement() {
		try {
			List<Transactions> result=dao.getMiniStatement(100000);
			boolean flag = result.isEmpty();
			assertFalse(flag);
			
		} catch (BankException e) {
			System.out.println(e.getMessage());
			
		}
	}

}
