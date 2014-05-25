package com.abc;

public enum TransactionTypeEnum {
	
	WITHDRAWAL("Withdrawal"), 	
	DEPOSIT("Deposit"), 	
	TRANSFER("Transfer");
	
	private final String name;
 

	public String getName() {
		return name;
	}
	 
	
	private TransactionTypeEnum(String name) {
		 this.name = name;
	}
	 
	

}
