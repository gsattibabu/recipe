package com.myapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity class to represent the Ingredients table
 * @author Madhusudan
 *
 */
@Entity
@Table(name = "RECIPE_INGREDITANT")
public class Ingredient implements AppEntity{

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
	
	@Column(name="ITEM")
	@NotNull
	private String item;

	@Column(name="INGGREDIANT_QANTITY")
	@NotNull
	private String quantity;
	
	@Column(name="INGGREDIANT_UNIT")
	@NotNull
	private String unit;
	
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
