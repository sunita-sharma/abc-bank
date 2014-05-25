package com.abc;

import junit.framework.TestCase;

import org.junit.Test;

public class TransactionTest extends TestCase {
	
	
	protected double fValue1;
    protected double fValue2;
    protected double fValue3;

   protected void setUp() {
        fValue1 = 10000.18;
        fValue2 = 1.10;
        fValue3 = -10;
    }

    @Test
    public void testTransaction() {
        Transaction t = new Transaction(5, new Account(AccountTypeEnum.CHECKING));
        assertTrue(t instanceof Transaction);
    }
    
    public void testAdd() { 
    	double expected = 10001.28;
		double actual = fValue1 + fValue2;
        assertEquals(actual, expected);
    }
    
    public void testTransactionType() {
    	 Transaction t = new Transaction(fValue3, new Account(AccountTypeEnum.CHECKING));
    	 assertEquals( t.getTransactionType(), TransactionTypeEnum.WITHDRAWAL);
    	
    }
 
    protected void tearDown() {
    	
    }
}
