package com.myapp.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.config.ApplicationConfig;
import com.myapp.entity.Category;
import com.myapp.entity.CategoryMap;
import com.myapp.entity.Recipe;

/**
 * Test case common configuration
 * @author Madhsudan
 *
 */
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase
public class TestConfig {

	@Autowired
	private TestEntityManager entityManager;

	/*
	 * converts a Java object into JSON representation
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Test case util method to persist recipe, category
	 * 
	 * @param title
	 * @param yield
	 * @param categoryDescrip
	 * @return created recipe
	 */
	public Recipe persistRecipe(String title, String yield, String categoryDescrip) {

		Category category = new Category();
		category.setDescription(categoryDescrip);
		entityManager.persistAndFlush(category);

		Recipe recipe = new Recipe();
		recipe.setRecipeTitle(title);
		recipe.setRecipeYield(yield);
		recipe.setCookingTime("test");
		entityManager.persistAndFlush(recipe);

		CategoryMap categoryMap = new CategoryMap();
		categoryMap.setCatagories(category);
		categoryMap.setRecipes(recipe);
		entityManager.persistAndFlush(categoryMap);

		return recipe;
	}
}
