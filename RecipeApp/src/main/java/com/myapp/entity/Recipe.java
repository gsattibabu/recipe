package com.myapp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Entity class to represent the Recipe table
 * @author Madhusudan
 *
 */
@Entity
@Table(name = "RECIPE")
public class Recipe implements AppEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4335392502086254701L;
	
	@Id
	@Column(name="RECIPE_TITLE")
	@NotNull
	private String recipeTitle;
	
	@Column(name="RECIPE_YIELD")
	@NotNull
	private String recipeYield;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "RECIPE_CATEGORY_MAP", 
        joinColumns = { @JoinColumn(name = "RECIPE_TITLE") }, 
        inverseJoinColumns = { @JoinColumn(name = "CATOGORY_ID") }
    )
    private Set<Category> catagories= new HashSet<>();
	
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name= "RECIPE_TITLE")
	private Set<Ingredient> ingrediants;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name= "RECIPE_TITLE")
	private Set<RecipeStep> directions;

	public String getRecipeTitle() {
		return recipeTitle;
	}

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public String getRecipeYield() {
		return recipeYield;
	}

	public void setRecipeYield(String recipeYield) {
		this.recipeYield = recipeYield;
	}

	public void setCatagories(Set<Category> catagories) {
		this.catagories = catagories;
	}
	
	public Set<Category> getCatagories() {
		if (catagories == null) {
			catagories = new HashSet<>();
        }
		return catagories;
	}

	public Set<Ingredient> getIngrediants() {
		return ingrediants;
	}

	public void setIngrediants(Set<Ingredient> ingrediants) {
		this.ingrediants = ingrediants;
	}

	public Set<RecipeStep> getDirections() {
		return directions;
	}

	public void setDirections(Set<RecipeStep> directions) {
		this.directions = directions;
	}
	
}
