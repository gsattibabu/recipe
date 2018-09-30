package com.myapp.dao;

import org.hibernate.HibernateException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.entity.Category;

/**
 * 
 * This class provide default implementation of the basic CURD operations provided by CrudRepository. 
 * Also it contains all the blue print of business methods for Category entity
 * @author Madhusudan
 *
 */
@Repository
public interface CategoryDao extends CrudRepository<Category, Long>   {

	/**
	 * This dao method is used find category by description
	 * 
	 * @param description
	 * @return the category matching the description
	 * @throws HibernateException data access layer exception
	 * 
	 */
	public Category findByDescription(String description)throws HibernateException;
	
}
