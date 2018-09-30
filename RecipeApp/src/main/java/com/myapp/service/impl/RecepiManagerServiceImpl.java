/**
 * 
 */
package com.myapp.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.dao.IngredientDao;
import com.myapp.dao.IngredientDivDao;
import com.myapp.dao.RecipeDao;
import com.myapp.dao.RecipeStepDao;
import com.myapp.entity.Category;
import com.myapp.entity.Ingredient;
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
	private IngredientDao ingredientDao;
	
	@Autowired
	private IngredientDivDao ingredientDivDao;
	
	@Autowired
	private RecipeStepDao recipeStepDao;
	
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
	public Recipe createNewRecepi(Recipe recipe) throws AppServiceException {
		try {
			Set<Category> validatedCategories = new HashSet<>();
			Set<Category> categories = recipe.getCatagories();
			for(Category categorie : categories) {
				validatedCategories.add(recipeManagerUtil.validateCategory(categorie.getDescription()));
			}
			/*recipe.getCatagories().clear();
			recipe.setCatagories(validatedCategories);*/
			Set<RecipeStep> steps = recipe.getDirections();
			for(RecipeStep step : steps) {
				step.setRecipeTitle(recipe.getRecipeTitle());
			}
			Set<IngredientDiv> divs = recipe.getIngrediants();
			for(IngredientDiv div : divs) {
				div.setRecipeTitle(recipe.getRecipeTitle());
			}
			return recepiDao.save(recipe);
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while saving recipe");
		}
	}
}
