package com.myapp.exception.handler;

/**
 * This class represents the exception class which needs to be thrown from  Service layer
 * 
 * @author Madhusudan
 *
 */
public class AppServiceException extends Exception{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 701688450575548978L;

	protected final String errorType;
	
	/**
	 * default constructor
	 */
	public AppServiceException() {
		super();
		this.errorType = "Service Exception";
	}
	
	/**
	 * parametrized constructor with string error as input
	 * @param messages the error message
	 */
	public AppServiceException(final String errorType, final String messages) {
		super(messages);
		this.errorType = errorType;
	}
	
	public String getErrorType() {
		return errorType;
	}
	
}
