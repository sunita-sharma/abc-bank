package com.abc;

import junit.framework.TestCase;

import org.junit.Test;

public class BankTest extends TestCase {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(AccountTypeEnum.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountTypeEnum.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        try {
			assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
			
		} catch (ApplicationException e) {
			 
			e.printStackTrace(); //TODO add test case to test exception 
		}
    }

    @Test
    public void testSavingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountTypeEnum.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        try {
			assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		} catch (ApplicationException e) {
			 
			e.printStackTrace();
		}
    }

    @Test
    public void testMaxiSavingsAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountTypeEnum.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        try {
			assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		} catch (ApplicationException e) {
			 
			e.printStackTrace();
		}
    }
    
    @Test
    public void testGetFirstCustomer() {
    	Bank newBank = new Bank(); 
    	assertEquals( "No customers", newBank.getFirstCustomer());
    	newBank.addCustomer(new Customer("oscar"));
    	assertEquals( "oscar", newBank.getFirstCustomer());
    }

}
