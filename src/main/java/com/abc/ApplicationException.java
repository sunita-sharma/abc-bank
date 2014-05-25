/**
 * 
 */
package com.abc;

/**
 * @author Sharma
 *
 */
public class ApplicationException extends Exception {

 
	private static final long serialVersionUID = 5564069032744227015L;
	private String message; 
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ApplicationException(String message) { 
		 this.message = message;
	} 
	

}
