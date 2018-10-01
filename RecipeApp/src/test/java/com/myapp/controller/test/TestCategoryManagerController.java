package com.myapp.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.myapp.controller.CategoryManagerController;
import com.myapp.exception.handler.AppExceptionHandler;
import com.myapp.test.config.TestConfig;

/**
 * @author Madhusudan
 *
 */
@RunWith(SpringRunner.class)
public class TestCategoryManagerController extends TestConfig{

	private MockMvc mvc;

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

		persistRecipe("Test Recipe","2","Test Category");
		mvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getRecipesByTitle_failure() throws Exception {

		persistRecipe("Test Recipe","2","Test Category");
		
		mvc.perform(get("/categories/Test Category").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
}
