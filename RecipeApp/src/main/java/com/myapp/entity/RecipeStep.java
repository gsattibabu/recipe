package com.myapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity class to represent the Recipe Step table
 * @author Madhusudan
 *
 */
@Entity
@Table(name = "RECIPE_STEP")
public class RecipeStep implements AppEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4335392502086254701L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="RECIPE_TITLE")
	@NotNull
	private String recipeTitle;
	
	@Column(name="STEP_DETAILS", length=2000)
	@NotNull
	private String stepDetails;

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public String getStepDetails() {
		return stepDetails;
	}

	public void setStepDetails(String stepDetails) {
		this.stepDetails = stepDetails;
	}
	
}
