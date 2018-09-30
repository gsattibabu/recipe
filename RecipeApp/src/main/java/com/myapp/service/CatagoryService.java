/**
 * 
 */
package com.myapp.service;

import java.util.List;

import com.myapp.entity.Category;
import com.myapp.entity.Recipe;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.exception.handler.ResourceException;

/**
 * 
 * Interface to have all the business methods related to categories
 * @author Madhusudan
 * @version : 1.0.0
 *
 */
public interface CatagoryService {

	/**
	 * This method is used to get all the categories
	 * @return all the categories
	 * @throws AppServiceException wrapper exception for service errors
	 * @throws ResourceException wrapper exception for resource errors
	 */
	public  List<Category> getAllCatogory() throws AppServiceException, ResourceException;
	
	/**
	 * This method is used to get all the recipe belong to category inpuut
	 * @param category to be searched
	 * @return list of recipe belong to the category
	 * @throws AppServiceException wrapper exception for service errors
	 * @throws ResourceException wrapper exception for resource errors
	 */
	public List<Recipe> getByCatagory(Long categoryId) throws ResourceException, AppServiceException;
	
}
