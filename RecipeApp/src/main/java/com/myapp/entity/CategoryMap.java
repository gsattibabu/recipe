package com.myapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class to represent the Category Map table
 * @author Madhusudan
 *
 */
@Entity
@Table(name = "RECIPE_CATEGORY_MAP")
public class CategoryMap implements AppEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4335392502086254701L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECIPE_TITLE", nullable = false)
	private Recipe recipes;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATOGORY_ID", nullable = false)
	private Category catagories;
	
	public Recipe getRecipe() {
		return recipes;
	}

	public Category getCategory() {
		return catagories;
	}

	public Recipe getRecipes() {
		return recipes;
	}

	public void setRecipes(Recipe recipes) {
		this.recipes = recipes;
	}

	public Category getCatagories() {
		return catagories;
	}

	public void setCatagories(Category catagories) {
		this.catagories = catagories;
	}
	
}
