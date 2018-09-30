package com.myapp.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.myapp.config.ApplicationConfig;
import com.myapp.controller.CategoryManagerController;
import com.myapp.controller.RecepiManagerController;
import com.myapp.entity.Category;
import com.myapp.entity.CategoryMap;
import com.myapp.entity.Recipe;
import com.myapp.exception.handler.AppExceptionHandler;

/**
 * @author Madhusudan
 *
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase
public class TestCategoryManagerController {

	private MockMvc mvc;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategoryManagerController categoryManagerController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(categoryManagerController).setControllerAdvice(new AppExceptionHandler())
				.build();
	}

	@Test
	public void getCategory_success() throws Exception {

		Category category = new Category();
		category.setDescription("test description");
		entityManager.persist(category);
		entityManager.flush();
		mvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getRecipesByTitle_failure() throws Exception {

		Category category = new Category();
		category.setDescription("test description");
		entityManager.persistAndFlush(category);
		
		Recipe recipe = new Recipe();
		recipe.setRecipeTitle("Test Recipe");
		recipe.setRecipeYield("2");
		entityManager.persistAndFlush(recipe);
		
		CategoryMap categoryMap = new CategoryMap();
		categoryMap.setCatagories(category);
		categoryMap.setRecipes(recipe);
		entityManager.persistAndFlush(categoryMap);
		
		mvc.perform(get("/categories/test description").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
}
