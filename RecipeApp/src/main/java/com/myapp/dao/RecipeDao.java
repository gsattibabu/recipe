package com.myapp.dao;

import org.hibernate.HibernateException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.entity.Recipe;
/**
 * 
 * This class provide default implementation of the basic CURD operations provided by CrudRepository. 
 * Also it contains all the blue print of business methods for Recipe entity
 * @author Madhusudan
 *
 */

@Repository
public interface RecipeDao extends CrudRepository<Recipe, Long>  {

	/**
	 * This dao method is used find recipe by title
	 * 
	 * @param recipeTitle
	 * @return the recipe matching the title
	 * @throws HibernateException data access layer exception
	 * 
	 */
	public Recipe findByRecipeTitle(String recipeTitle) throws HibernateException;
}
