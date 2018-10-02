package com.myapp.controller.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.myapp.controller.RecepiManagerController;
import com.myapp.exception.handler.AppExceptionHandler;
import com.myapp.test.config.TestConfig;

/**
 * Test case to test recipe manager controller
 * @author Madhusudan
 *
 */
@RunWith(SpringRunner.class)
public class TestRecipeManagerController extends TestConfig {

	private MockMvc mvc;

	@Autowired
	private RecepiManagerController recepiManagerController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(recepiManagerController).setControllerAdvice(new AppExceptionHandler())
				.build();
	}

	/**
	 * Test to check success response if recipe exists
	 * 
	 * @throws Exception
	 */
	@Test
	public void getRecipes_success() throws Exception {

		persistRecipe("Test Recipe", "2", "Test Category");
		mvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/**
	 * Test to check success response if recipe with a title
	 * 
	 * @throws Exception
	 */
	@Test
	public void getRecipesByTitle_success() throws Exception {

		persistRecipe("Test Recipe", "2", "Test Category");
		mvc.perform(get("/recipes/Test Recipe").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/**
	 * Test to check success response if recipe is created
	 * 
	 * @throws Exception
	 */
	@Test
	public void createRecipes_success() throws Exception {

		String createRecipeRequest = "{\"recipeTitle\": \"Test Recipe\",\"recipeYield\": \"2\",\"cookingTime\": \"test\",\"catagories\": [{\"catogoryId\": 1,\"description\": \"Main dish\"}],\"ingrediants\": [{\"title\": \"\",\"ingrediant\": [{\"item\": \"Ground chuck or lean ground; beef\",\"quantity\": \"1\",\"unit\": \"pound\"}]}],\"directions\": [{\"stepDetails\": \"test description\"}]}";

		mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(createRecipeRequest))
				.andExpect(status().isOk());
	}

	/**
	 * Test to check error response if recipe with no category is requested
	 * 
	 * @throws Exception
	 */
	@Test
	public void createRecipesNoCategory_failure() throws Exception {

		String createRecipeRequest = "{\"recipeTitle\": \"Test Recipe\",\"recipeYield\": \"2\",\"cookingTime\": \"test\",\"ingrediants\": [{\"title\": \"\",\"ingrediant\": [{\"item\": \"Ground chuck or lean ground; beef\",\"quantity\": \"1\",\"unit\": \"pound\"}]}],\"directions\": [{\"stepDetails\": \"test description\"}]}";

		mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(createRecipeRequest))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("message", is("Input recipe validation failed")));
	}

	/**
	 * Test to check error response if recipe with no ingredients is requested
	 * 
	 * @throws Exception
	 */
	@Test
	public void createRecipesNoIngediant_failure() throws Exception {

		String createRecipeRequest = "{\"recipeTitle\": \"Test Recipe\",\"recipeYield\": \"2\",\"cookingTime\": \"test\",\"catagories\": [{\"catogoryId\": 1,\"description\": \"Main dish\"}],\"directions\": [{\"stepDetails\": \"test description\"}]}";

		mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(createRecipeRequest))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("message", is("Input recipe validation failed")));
	}

	/**
	 * Test to check error response if recipe with no steps is requested
	 * 
	 * @throws Exception
	 */
	@Test
	public void createRecipesNoSteps_failure() throws Exception {

		String createRecipeRequest = "{\"recipeTitle\": \"Test Recipe\",\"recipeYield\": \"2\",\"cookingTime\": \"test\",\"ingrediants\": [{\"title\": \"\",\"ingrediant\": [{\"item\": \"Ground chuck or lean ground; beef\",\"quantity\": \"1\",\"unit\": \"pound\"}]}]}";

		mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(createRecipeRequest))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("message", is("Input recipe validation failed")));
	}
	
	/**
	 * Test to check error response if recipe title is not present in requested
	 * 
	 * @throws Exception
	 */
	@Test
	public void createRecipes_failure() throws Exception {

		String createRecipeRequest = "{\"recipeYield\": \"2\",\"cookingTime\": \"test\",\"catagories\": [{\"catogoryId\": 1,\"description\": \"Main dish\"}],\"ingrediants\": [{\"title\": \"\",\"ingrediant\": [{\"item\": \"Ground chuck or lean ground; beef\",\"quantity\": \"1\",\"unit\": \"pound\"}]}],\"directions\": [{\"stepDetails\": \"test description\"}]}";

		mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(createRecipeRequest))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("details", is("[recipeTitle: must not be null]")));
	}
	
	/**
	 * Test to check error response if recipe yield is empty in request
	 * 
	 * @throws Exception
	 */
	@Test
	public void createRecipesWithoutYield_failure() throws Exception {

		String createRecipeRequest = "{\"recipeTitle\": \"Test Recipe\",\"recipeYield\": \"\",\"cookingTime\": \"test\",\"catagories\": [{\"catogoryId\": 1,\"description\": \"Main dish\"}],\"ingrediants\": [{\"title\": \"\",\"ingrediant\": [{\"item\": \"Ground chuck or lean ground; beef\",\"quantity\": \"1\",\"unit\": \"pound\"}]}],\"directions\": [{\"stepDetails\": \"test description\"}]}";

		mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(createRecipeRequest))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("details", is("[recipeYield: Recipe yield cannot be empty]")));
	}

}