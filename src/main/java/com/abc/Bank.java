package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * usecase: get a report showing the list of customers and number of accounts
     * @return
     */
    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    /**
     * usecase: report showing the total interest paid by the bank on all accounts
     * @return
     * @throws ApplicationException
     */
    public double totalInterestPaid() throws ApplicationException { 
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    public String getFirstCustomer()   {
        try {
            if (customers == null) return "No customers"; 
            return (customers.size()>0 && null != customers.get(0)) ? customers.get(0).getName() : "No customers";
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
}
