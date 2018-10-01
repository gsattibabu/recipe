/**
 * 
 */
package com.myapp.service;

import java.util.List;

import com.myapp.entity.Recipe;
import com.myapp.exception.handler.AppServiceException;

/**
 * 
 * Interface to have all the business methods related to recipe management
 * @author Madhusudan
 * @version : 1.0.0
 *
 */
public interface RecipeManagerService{
	
	/**
	 * This method is used to get all the recipe
	 * @return all the recipe
	 * @throws AppServiceException wrapper exception for service errors
	 */
	public List<Recipe> findAllRecipe() throws AppServiceException;
	
	/**
	 * This method is used to find a recipe by title
	 * @param title to be searched
	 * @return the recipe
	 * @throws AppServiceException wrapper exception for service errors
	 */
	public Recipe getByName(String title) throws AppServiceException;
	
	/**
	 * This method is used to create a new recipe.
	 * @param recipe to be saved
	 * @return the newly created recipe
	 * @throws AppServiceException wrapper exception for service errors
	 */
	public Recipe createNewRecepi(Recipe recipe) throws AppServiceException;
	
}
