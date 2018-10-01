/**
 * 
 */
package com.myapp.service.util;

import java.util.Optional;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.dao.CategoryDao;
import com.myapp.dao.CategoryMapDao;
import com.myapp.dao.IngredientDao;
import com.myapp.dao.IngredientDivDao;
import com.myapp.dao.RecipeStepDao;
import com.myapp.entity.Category;
import com.myapp.entity.CategoryMap;
import com.myapp.entity.Ingredient;
import com.myapp.entity.IngredientDiv;
import com.myapp.entity.Recipe;
import com.myapp.entity.RecipeStep;
import com.myapp.exception.handler.AppServiceException;

/**
 * Util class to validate and add necessary entity while new recipe creation
 * @author Madhusudan
 * @version : 1.0.0
 */
@Component
public class RecipeManagerUtil {

	@Autowired
	private IngredientDao ingredientDao;
	
	@Autowired
	private IngredientDivDao ingredientDivDao;
	
	@Autowired
	private RecipeStepDao recipeStepDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CategoryMapDao categoryMapDao;
	
	/**
	 * This method is used to validate the categories and return new or existing category
	 * @param categorie category description
	 * @param newRecipe 
	 * @param string 
	 * @return newly created category or existing category
	 * @throws AppServiceException
	 */
	public void validateCategory(Category categorie, Recipe newRecipe) throws AppServiceException {
		
		try {
			Optional<Category> category = categoryDao.findById(categorie.getCatogoryId());
			if(!category.isPresent()) {
				Category newCategory = new Category();
				newCategory.setDescription(categorie.getDescription());
				categoryDao.save(newCategory);
				CategoryMap categoryMap = new CategoryMap();
				categoryMap.setCatagories(newCategory);
				categoryMap.setRecipes(newRecipe);
				categoryMapDao.save(categoryMap);
			}else {
				CategoryMap categoryMap = new CategoryMap();
				categoryMap.setCatagories(categorie);
				categoryMap.setRecipes(newRecipe);
				categoryMapDao.save(categoryMap);
			}
			
			
		} catch (HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while validating category");
		}
		
	}
	
	/**
	 * This method is used to validate the categories and return new or existing category
	 * @param description category description
	 * @return newly created category or existing category
	 * @throws AppServiceException
	 * @throws ResourceException 
	 */
	public void validateIngreditants(IngredientDiv ingrediantsDiv, Recipe newRecipe) throws AppServiceException {
		try {
			IngredientDiv newIngredientDiv = new IngredientDiv();
			newIngredientDiv.setRecipe(newRecipe);
			newIngredientDiv.setTitle(null!=ingrediantsDiv.getTitle()?ingrediantsDiv.getTitle():"");
			ingredientDivDao.save(newIngredientDiv);
			Set<Ingredient> ingrediants = ingrediantsDiv.getIngrediant();
			if(null!=ingrediants) {
				for(Ingredient ingredient:ingrediants) {
					ingredient.setIngredientDiv(newIngredientDiv);
					ingredientDao.save(ingredient);
				}
			}else {
				throw new AppServiceException("Input recipe validation failed","Recipe Ingreditants cannot be empty");
			}
			
		} catch (HibernateException e) {
			throw new AppServiceException("Unexpected Error",e.getMessage());
		}
		
	}
	
	/**
	 * This method is used to validate the categories and return new or existing category
	 * @param description category description
	 * @return newly created category or existing category
	 * @throws AppServiceException
	 */
	public void validateSteps(RecipeStep steps, Recipe newRecipe) throws AppServiceException {
		try {
			RecipeStep newSteps = new RecipeStep();
			newSteps.setStepDetails(null!=steps.getStepDetails()?steps.getStepDetails():"");
			newSteps.setRecipe(newRecipe);
			recipeStepDao.save(newSteps);
		} catch (HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while validating steps");
		}
		
	}

}
