package com.myapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity class to represent the Ingredients table
 * @author Madhusudan
 *
 */
@Entity
@Table(name = "RECIPE_INGREDITANT_DIV")
public class IngredientDiv implements AppEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4335392502086254701L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DIV_ID")
	private Long id;
	
	@Column(name="TITLE")
	@NotNull
	private String title;
	
	@Column(name="RECIPE_TITLE")
	@NotNull
	private String recipeTitle;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name= "DIV_ID")
	private Set<Ingredient> ingrediant;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public Set<Ingredient> getIngrediant() {
		return ingrediant;
	}

	public void setIngrediant(Set<Ingredient> ingrediant) {
		this.ingrediant = ingrediant;
	}

}
