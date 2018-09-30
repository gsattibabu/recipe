package com.myapp.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.dao.CategoryDao;
import com.myapp.entity.Category;
import com.myapp.entity.Recipe;
import com.myapp.exception.handler.ResourceException;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.service.CatagoryService;


/**
 * Implementation class of CatagoryService which provides all the business layer implementation
 * 
 * @author Madhusudan
 * @see com.myapp.service.CatagoryService
 *
 */
@Service
public class CatagoryServiceImpl implements CatagoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getAllCatogory() throws AppServiceException, ResourceException {
		try {
			List<Category> categories = (List<Category>) categoryDao.findAll();
			if(null == categories || categories.isEmpty()) {
				throw new ResourceException("Validation Error","No Categories found");
			}
			return categories;
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while fetching all categories");
		}
	}

	@Override
	public List<Recipe> getByCatagory(String cat) throws ResourceException, AppServiceException {
		try {
			Category category = categoryDao.findByDescription(cat);
			if(null == category) {
				throw new ResourceException("Validation Error","Category does not exist");
			}
			return category.getRecipes();
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while fecthing recipe details");
		}
	}

}


