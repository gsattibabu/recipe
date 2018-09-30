package com.myapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity class to represent the Category table
 * @author Madhusudan
 *
 */
@Entity
@Table(name = "RECIPE_CATEGORY")
public class Category implements AppEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4335392502086254701L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CATOGORY_ID")
	private Long catogoryId;
	
	@ManyToMany(mappedBy = "catagories")
    private List<Recipe> recipes = new ArrayList<>();
	
	@Column(name="DESCRIPTION")
	@NotNull
	private String description;

	public Long getCatogoryId() {
		return catogoryId;
	}

	public void setCatogoryId(Long catogoryId) {
		this.catogoryId = catogoryId;
	}

	@JsonIgnore
	public List<Recipe> getRecipes() {
		return recipes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
