package com.abc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Account {

	/*
	 * replace with enum types public static final int CHECKING = 0; public
	 * static final int SAVINGS = 1; public static final int MAXI_SAVINGS = 2;
	 */

	private AccountTypeEnum accountType = null;
	protected List<Transaction> transactions = null;

	protected Account(AccountTypeEnum accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * usecase: customer should be able to deposit funds from an account create
	 * a positive transaction activity equal to the amount throw an error
	 * message to customer if the amount is invalid Amount is invalid if lesss
	 * than or equal to zero
	 * 
	 * @param amount
	 */
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount, this));
		}
	}

	/**
	 * usecase: customer should be able to withdraw funds from an account create
	 * a negative transaction activity equal to the amount throw an error
	 * message to customer if the amount is invalid Amount is invalid if less
	 * than or equal to zero or if greater than amount in the account missing
	 * parameter account ? add check for Insufficient funds
	 * 
	 * @param amount
	 */
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount, this));
		}
	}

	/**
	 * @deprecated use calculateInterestEarned
	 * @return
	 */
	public double interestEarned() {
		double amount = sumTransaction();
		switch (accountType) {
		case CHECKING:

		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;
			// case SUPER_SAVINGS:
			// if (amount <= 4000)
			// return 20;
		case MAXI_SAVINGS:
			if (amount <= 1000)
				return amount * 0.02;
			if (amount <= 2000)
				return 20 + (amount - 1000) * 0.05;
			return 70 + (amount - 2000) * 0.1;
		default:
			return amount * 0.001;
		}
	}

	/**
	 * 
	 * @return
	 * @throws InvalidAccountException
	 */
	public double calculateInterestEarned() throws ApplicationException {

		double interest = 0.0;
		switch (accountType) {
		case CHECKING:
			return interest += calculateCheckingInterest(sumTransactionsByAccount(AccountTypeEnum.CHECKING));
		case SAVINGS:
			return interest += calculateSavingsInterest(sumTransactionsByAccount(AccountTypeEnum.SAVINGS));
		case MAXI_SAVINGS:
			return interest += calculateMaxiSavingsInterest(sumTransactionsByAccount(AccountTypeEnum.MAXI_SAVINGS));
		}
		return interest;
	}

	/**
	 * TODO add exception
	 * 
	 * @param amount
	 */
	private double calculateMaxiSavingsInterest(double amount) {
		if (amount <= 1000)
			return amount * getFirstThousandMaxiSavingsInterestRate();
		if (amount <= 2000)
			return 20 + (amount - 1000)
					* getSecondThousandMaxiSavingsInterestRate();
		return 70 + (amount - 2000) * getMaxiSavingsInterestRate();

	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	private double calculateSavingsInterest(double amount) {
		double accruedInterest; // = amount * getCheckingInterestRate();
		if (amount <= 1000) {
			accruedInterest = amount * getFirstThousandSavingsInterestRate();

		} else {
			accruedInterest = (1000 * getFirstThousandSavingsInterestRate())
					+ (amount - 1000) * getSavingsInterestRate();

		}
		return accruedInterest;
	}

	/**
	 * accrual for checkings accounts
	 * double is okay here but just to demonstrate 
	 * BigDecimal should be used for calculations
	 * @param amount
	 * @return
	 */
	private double calculateCheckingInterest(double amount) {
		BigDecimal accruedInterest = new BigDecimal(0.0);
		accruedInterest = new BigDecimal(amount).multiply(new BigDecimal(
				getCheckingInterestRate())); 
		BigDecimal rounded = accruedInterest.round(new MathContext(5, RoundingMode.HALF_UP)); 
		BigDecimal scale = accruedInterest.setScale(2, RoundingMode.HALF_UP); 
		return scale.doubleValue(); //loosing precision here should be BigDecimal
	}

	/**
	 * 0.1 for amount > 2000
	 * 
	 * @return
	 */
	private double getMaxiSavingsInterestRate() {
		return 0.1;
	}

	/**
	 * 0.05 for amount > 1000
	 * 
	 * @return
	 */
	private double getSecondThousandMaxiSavingsInterestRate() {
		return 0.05;
	}

	/**
	 * 0.02 for first 1000 balance
	 * 
	 * @return
	 */
	private double getFirstThousandMaxiSavingsInterestRate() {
		return 0.02;
	}

	/**
	 * 0.002% for all balance > 1000
	 * 
	 * @return
	 */
	private double getSavingsInterestRate() {
		return 0.002;
	}

	/**
	 * 0.1% for first 1000
	 * 
	 * @return
	 */
	private double getFirstThousandSavingsInterestRate() {
		return 0.001;
	}

	/**
	 * get interest rate as this can change 0.1%
	 * 
	 * @return
	 */
	private double getCheckingInterestRate() {
		return 0.001;
	}

	/**
	 * @deprecated use sumTransaction
	 * @return
	 */
	public double sumTransaction() {
		return checkIfTransactionsExist(true);
	}

	/**
	 * added exception as can happen in calculations
	 * @param accountType
	 * @return
	 * @throws ApplicationException
	 */
	public double sumTransactionsByAccount(AccountTypeEnum accountType)
			throws ApplicationException {

		// no transactions
		if (!checkIfTransactionsExist())
			return 0;

		double amount = 0;

		for (Transaction t : transactions) {
			switch (accountType) {
			case CHECKING:
				amount += t.amount;
				break;
			case SAVINGS:
				amount += t.amount;
				break;
			case MAXI_SAVINGS:
				amount += t.amount;
				break;
			}
		}
		return amount;

	}

	/**
	 * get all transactions
	 * 
	 * @return
	 */
	private boolean checkIfTransactionsExist() {
		if (transactions == null)
			return false;
		return (transactions.size() > 0) ? true : false;
	}

	/**
	 * @deprecated use checkIfTransactionsExist()
	 * @param checkAll
	 * @return
	 */
	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	public AccountTypeEnum getAccountType() {
		return accountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result
				+ ((transactions == null) ? 0 : transactions.hashCode());
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
		Account other = (Account) obj;
		if (accountType != other.accountType)
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	/**
	 * sum total of transactions for all account types
	 * 
	 * @return
	 */
	public double sumTransactions() {
		// no transactions
		if (!checkIfTransactionsExist())
			return 0;

		double amount = 0;

		for (Transaction t : transactions) {

			amount += t.amount;
		}

		return amount;

	}

}
