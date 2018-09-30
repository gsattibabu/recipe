package com.myapp.exception.handler;

/**
 * This class represents the resource not found exception
 * 
 * @author Anshu Kumar
 *
 */
public class ResourceException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3147373530789701970L;

	protected final String errorType;
	
	/**
	 * default constructor
	 */
	public ResourceException() {
		super();
		this.errorType = "Resource Exception";
	}
	
	/**
	 * parametrized constructor with string type and error message
	 * @param errorType
	 * @param messages
	 */
	public ResourceException(final String errorType, final String messages) {
		super(messages);
		this.errorType = errorType;
	}

	public String getErrorType() {
		return errorType;
	}
	
}
