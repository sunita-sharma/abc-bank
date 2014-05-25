package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }
    
        public List<Account> getAccounts() {
		return accounts;
	}

        /**
         * get total interest paid by the bank on all accounts
         * @return
         * @throws ApplicationException
         */
	public double totalInterestEarned() throws ApplicationException  { 
        double total = 0;
        for (Account a : accounts) {
        	 total += a.calculateInterestEarned();
        }
        return total;
    }
/**
 * usecase: customer request a statement that shows transaction
 * totals for each of their accounts
 */
    public String getStatement() throws ApplicationException {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + formatToDollars(total);
        return statement;
    }

    /**
     * TODO add null check
     * TODO any exceptions?
     * @param a
     * @return
     */
    private String statementForAccount(Account a) {
         
        String NEWLINE = "\n";
        String SPACE = "  ";
        String TOTAL = "Total ";
        StringBuffer sb =  new StringBuffer(a.getAccountType().getName()).append(NEWLINE); 
       /* remove this by using enum
       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break; 
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }
        */ 
        

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            sb.append(SPACE).append(t.getTransactionType().getName()).append(SPACE);
            sb.append(formatToDollars(t.amount)).append(NEWLINE);
            total += t.amount;
        }
        sb.append(TOTAL).append(formatToDollars(total));
        return sb.toString();
    }

    /**
     * changing visibility for unit test
     * @param d
     * @return
     */
    String formatToDollars(double d){
        return String.format("$%,.2f", Math.abs(d));
    }
}
