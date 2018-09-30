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

import com.myapp.dao.RecipeDao;
import com.myapp.entity.Category;
import com.myapp.entity.Recipe;
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
	public Recipe createNewRecepi(Recipe recipe) throws AppServiceException {
		try {
			Set<Category> validatedCategories = new HashSet<>();
			Set<Category> categories = recipe.getCatagories();
			for(Category categorie : categories) {
				validatedCategories.add(recipeManagerUtil.validateCategory(categorie.getDescription()));
			}
			
			recipe.getCatagories().clear();
			recipe.setCatagories(validatedCategories);
			return recepiDao.save(recipe);
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while saving recipe");
		}
	}
}
