/**
 * 
 */
package com.myapp.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.dao.RecipeDao;
import com.myapp.entity.Category;
import com.myapp.entity.IngredientDiv;
import com.myapp.entity.Recipe;
import com.myapp.entity.RecipeStep;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.exception.handler.ResourceException;
import com.myapp.service.RecipeManagerService;
import com.myapp.service.util.RecipeManagerUtil;

/**
 * Implementation class of RecepiManagerService which provides all the business layer implementation
 * 
 * @author Madhusudan
 * @see com.myapp.service.RecipeManagerService
 *
 */
@Service
public class RecepiManagerServiceImpl implements RecipeManagerService{
	
	@Autowired
	private RecipeDao recepiDao;
	
	@Autowired
	private RecipeManagerUtil recipeManagerUtil;
	
	@Override
	public List<Recipe> findAllRecipe() throws AppServiceException, ResourceException {
		try {
			List<Recipe> recipes = (List<Recipe>) recepiDao.findAll();
			if(null == recipes || recipes.isEmpty()) {
				throw new ResourceException("Validation Error","No Recipes found");
			}
			return recipes;
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while fetching all recipes");
		}
	}

	@Override
	public Recipe getByName(String title) throws ResourceException, AppServiceException {

		try {
			Recipe recipe = recepiDao.findByRecipeTitle(title);
			if(null == recipe) {
				throw new ResourceException("Validation Error","Recipe does not exist");
			}
			return recipe;
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while fecthing recipe details");
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = AppServiceException.class)
	public Recipe createNewRecepi(Recipe recipe) throws AppServiceException, ResourceException {
		try {
			
			Recipe newRecipe = saveRecipe(recipe);
			if(null!=newRecipe) {
				Set<Category> categories = recipe.getCatagories();
				for(Category categorie : categories) {
					recipeManagerUtil.validateCategory(categorie.getDescription(), newRecipe);
				}
				Set<RecipeStep> steps = recipe.getDirections();
				for(RecipeStep step : steps) {
					recipeManagerUtil.validateSteps(step, newRecipe);
				}
				Set<IngredientDiv> divs = recipe.getIngrediants();
				for(IngredientDiv div : divs) {
					recipeManagerUtil.validateIngreditants(div, newRecipe);
				}
			}
			return newRecipe;
		}catch(HibernateException ex) {
			throw new AppServiceException("Unexpected Error", ex.getCause().getMessage());
		}catch (ResourceException ex) {
			throw ex;
		}
	}

	private Recipe saveRecipe(Recipe recipe) throws ResourceException {
		try {
			Recipe newRecipe = new Recipe();
			
			newRecipe.setRecipeTitle(recipe.getRecipeTitle());
			newRecipe.setRecipeYield(recipe.getRecipeYield());
			
			return recepiDao.save(newRecipe);
		}catch(HibernateException e) {
			throw new ResourceException("Unexpected Error",e.getCause().getMessage());
		}
	}
}
