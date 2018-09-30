/**
 * 
 */
package com.myapp.service.util;

import org.springframework.stereotype.Component;

/**
 * String utility class which contains all the generic methods related to String datatype
 * @author Madhusudan
 * @version : 1.0.0
 *
 */
@Component
public class StringUtils {
	
	
	/**
	 * This method checks if the string value is not null and not empty and returns a boolean expression 
	 * @param inputString the string object which needs to be validated
	 * @return true if any string is present or else returns false if null or empty string is passed to this method
	 */
	public static boolean isValidString(String inputString){
		boolean isValidString=true;
		if(inputString==null || inputString.trim().length()==0){
			isValidString = false;
		}
		return isValidString;
	}

}

