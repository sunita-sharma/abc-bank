package com.abc;

public enum AccountTypeEnum {
	
	CHECKING("Checking Account"), 	
	SAVINGS("Saving Account"), 	
	MAXI_SAVINGS("Maxi Savings Account");
//	/SUPER_SAVINGS("Super Savings Account");
	
	private final String name;
 

	public String getName() {
		return name;
	}
	 
	
	private AccountTypeEnum(String name) {
		 this.name = name;
	}
	 
	

}
