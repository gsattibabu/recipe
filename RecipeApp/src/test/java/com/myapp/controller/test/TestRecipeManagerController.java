package com.myapp.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.myapp.entity.Category;
import com.myapp.entity.CategoryMap;
import com.myapp.entity.Recipe;
import com.myapp.exception.handler.AppExceptionHandler;
import com.myapp.test.config.TestConfig;

/**
 * @author Madhusudan
 *
 */
@RunWith(SpringRunner.class)
public class TestRecipeManagerController extends TestConfig{

   private MockMvc mvc;

   @Autowired
   private RecepiManagerController recepiManagerController;

   @Before
   public void init(){
       MockitoAnnotations.initMocks(this);
       mvc = MockMvcBuilders
               .standaloneSetup(recepiManagerController)
               .setControllerAdvice(new AppExceptionHandler())
               .build();
   }
   
   @Test
   public void getRecipes_success() throws Exception {
	   
	   persistRecipe("Test Recipe","2","Test Category");
	   mvc.perform(
               get("/recipes")
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
   }
   
   @Test
   public void getRecipesByTitle_success() throws Exception {
	   
	   persistRecipe("Test Recipe","2","Test Category");
	   mvc.perform(
               get("/recipes/Test Recipe")
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
   }
   
  /* @Test
   public void createRecipes_success() throws Exception {
	   
	   Category category = new Category();
		category.setDescription("test category");
		
		Recipe recipe = new Recipe();
		recipe.setRecipeTitle("test recipe");
		recipe.setRecipeYield("2");		
		
	   mvc.perform(
               post("/recipes")
                       .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recipe)))
               .andExpect(status().isOk());
   }
*/
}