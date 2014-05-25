package com.abc;

import org.junit.Test;

import junit.framework.TestCase;

public class AccountTest extends TestCase {

	protected double fValue1;
    protected double fValue2;
    protected double fValue3;

   protected void setUp() {
        fValue1 = 10000.18;
        fValue2 = 1.10;
        fValue3 = -10;
    }
	
	@Test
	public void testAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeposit() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithdraw() {
		fail("Not yet implemented");
	}

    @Test (expected = ApplicationException.class)
    public final void testTotalInterestEarned0() throws ApplicationException {
    	 Customer oscar = new Customer("Oscar")
         .openAccount(new Account(AccountTypeEnum.SAVINGS));
    	 oscar.openAccount(new Account(AccountTypeEnum.CHECKING));
    	 assertEquals(0.0, oscar.totalInterestEarned()); 
    	
    }
    
    @Test (expected = ApplicationException.class)
    public final void testTotalInterestEarned() throws ApplicationException {
    	 Customer oscar = new Customer("Oscar")
         .openAccount(new Account(AccountTypeEnum.SAVINGS));
    	 oscar.openAccount(new Account(AccountTypeEnum.CHECKING));
    
    	// System.out.println( oscar.getAccounts());
    	 for (Account s : oscar.getAccounts()) {
    		 switch(s.getAccountType())  {
    			case CHECKING:
    		       s.deposit(100000.15); 
    		       break;
    			case SAVINGS: 
     		       break;
    			case MAXI_SAVINGS: 
      		       break;
    		 }
    		       
    	 }
    	 
    	 double expected = 100.0;
    	 //System.out.println(oscar.totalInterestEarned()); 
         assertEquals(expected, oscar.totalInterestEarned());
    	
    }
 
	@Test
	public void testSumTransactions() throws ApplicationException {
		 Transaction t1 = new Transaction(fValue1, new Account(AccountTypeEnum.CHECKING));
		 Transaction t2 = new Transaction(fValue2, new Account(AccountTypeEnum.SAVINGS));
		 Transaction t3 = new Transaction(fValue3, new Account(AccountTypeEnum.MAXI_SAVINGS));
		 Account checkingAccount = new Account(AccountTypeEnum.CHECKING);
	     Account savingsAccount = new Account(AccountTypeEnum.SAVINGS);
	     Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
	     checkingAccount.deposit(fValue1);
	     savingsAccount.deposit(fValue2); 
		 
		 assertEquals(fValue1, checkingAccount.sumTransactionsByAccount(AccountTypeEnum.CHECKING)); 
		 assertEquals(fValue2, savingsAccount.sumTransactionsByAccount(AccountTypeEnum.SAVINGS)); 
	}

	@Test
	public void testGetAccountType() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

}
