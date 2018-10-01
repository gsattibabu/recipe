package com.myapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.entity.RecipeStep;

/**
 * 
 * This class provide default implementation of the basic CURD operations provided by CrudRepository. 
 * Also it contains all the blue print of business methods for RecipeStep entity
 * @author Madhusudan
 *
 */
@Repository
public interface RecipeStepDao extends CrudRepository<RecipeStep, Long>  {

}
