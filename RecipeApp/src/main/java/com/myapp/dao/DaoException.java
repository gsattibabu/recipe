package com.myapp.dao;

/**
 * 
 * This is wrapper exception class to throw all the DAO exceptions 
 * 
 * @author Madhusudan
 *
 */
public class DaoException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8091510671214920462L;
	
	/**
	 * Associaction to the messages component that will handle error, warning and
	 * info messages.
	 */
	private String message;

	/**
	 * msg
	 * 
	 * @param msg
	 *            msg
	 */
	public DaoException(String msg) {
		super(msg);
	}

	/**
	 * Default constructor
	 */
	public DaoException() {
		super();
	}

	/**
	 * @return messages messages
	 */
	public String getMessages() {
		return message;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(String message) {
		this.message = message;
	}

}
