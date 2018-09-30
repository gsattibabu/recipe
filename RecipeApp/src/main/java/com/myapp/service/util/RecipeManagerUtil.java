/**
 * 
 */
package com.myapp.service.util;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.dao.CategoryDao;
import com.myapp.entity.Category;
import com.myapp.exception.handler.AppServiceException;

/**
 * Util class to validate the recipe
 * @author Madhusudan
 * @version : 1.0.0
 */
@Component
public class RecipeManagerUtil {

	@Autowired
	private CategoryDao categoryDao;
	
	/**
	 * This method is used to validate the categories and return new or existing category
	 * @param description category description
	 * @return newly created category or existing category
	 * @throws AppServiceException
	 */
	public Category validateCategory(String description) throws AppServiceException {
		Category category = null;
		try {
			category = categoryDao.findByDescription(description);
			if(null == category) {
				Category newCategory = new Category();
				newCategory.setDescription(description);
				return categoryDao.save(newCategory);
			}
			return category;
		} catch (HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while validating category");
		}
		
	}

}
