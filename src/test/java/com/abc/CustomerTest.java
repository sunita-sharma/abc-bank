package com.abc;

import junit.framework.TestCase;

import org.junit.Test;

public class CustomerTest  extends TestCase {

    @Test //Test customer statement generation
    public void testStatement(){

        Account checkingAccount = new Account(AccountTypeEnum.CHECKING);
        Account savingsAccount = new Account(AccountTypeEnum.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
       
       
        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        

        try {
        	
        	 
			assertEquals("Statement for Henry\n" +
			        "\n" +
			        "Checking Account\n" +
			        "  Deposit  $100.00\n" +
			        "Total $100.00\n" +
			        "\n" +
			        "Saving Account\n" +
			        "  Deposit  $4,000.00\n" +
			        "  Withdrawal  $200.00\n" +
			        "Total $3,800.00\n" +
			        "\n" +
			        "Total In All Accounts $3,900.00", henry.getStatement());
			
		} catch (ApplicationException e) {
		 
			e.printStackTrace();
		}
    }

    @Test
    public void testGetOneOfAccounts(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(AccountTypeEnum.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testGetTwoOfAccounts(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountTypeEnum.SAVINGS));
        oscar.openAccount(new Account(AccountTypeEnum.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testGetThreeOfAccounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountTypeEnum.SAVINGS));
        oscar.openAccount(new Account(AccountTypeEnum.CHECKING));
        oscar.openAccount(new Account(AccountTypeEnum.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
 
   
   @Test
   public void testFormatToDollars() {
	   Account checkingAccount = new Account(AccountTypeEnum.CHECKING);
       Account savingsAccount = new Account(AccountTypeEnum.SAVINGS);

       Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount); 
       String expected = "$200.15";
       assertEquals(expected, henry.formatToDollars(200.1500));
       assertEquals("$200.15", henry.formatToDollars(200.1500)); 
       expected = "$20,000.15";
       assertEquals(expected , henry.formatToDollars(20000.15));  
       expected = "$200,004,578.15";
       assertEquals(expected , henry.formatToDollars(200004578.1500012));  
    }
    
    
    
}
