package com.abc;

import java.util.Date;

public class Transaction {
    public final double amount;
    public final Account account;
    public final TransactionTypeEnum transactionType;
    private final Date transactionDate;

    public Transaction(double amount, Account account) {
        this.amount = amount;
        this.account = account;
        this.transactionDate = DateProvider.getInstance().now();
        this.transactionType = setTransactionType();
       
    }

	private TransactionTypeEnum setTransactionType() {
		return (this.amount < 0) ? TransactionTypeEnum.WITHDRAWAL : TransactionTypeEnum.DEPOSIT; 
	}

	public Date getTransactionDate() {
		return transactionDate;
	} 

	public double getAmount() {
		return amount;
	}

	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}	
	

	public Account getAccount() {
		return account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result
				+ ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}
 
	
	
	

    

 
}
