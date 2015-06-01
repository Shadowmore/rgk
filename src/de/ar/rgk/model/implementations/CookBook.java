package de.ar.rgk.model.implementations;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.ar.rgk.model.abstracts.HibernateDataObject;
import de.ar.rgk.model.interfaces.ICookBook;
import de.ar.rgk.model.interfaces.IRecipe;
import de.ar.rgk.model.setter.ICookBookSetter;

@Entity
@Table(name = "cookBook")
public class CookBook extends HibernateDataObject implements ICookBook, ICookBookSetter {

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "cookBook", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Recipe> listRecipes;

	protected CookBook() {
		// Für Hibernate
	}

	public CookBook(String name) {
		this.name = name;
		this.listRecipes = new LinkedHashSet<>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<IRecipe> getListRecipes() {
		Set<IRecipe> retList = new LinkedHashSet<>();
		for (Recipe recipe : this.listRecipes) {
			retList.add(recipe);
		}
		return Collections.unmodifiableSet(retList);
	}

	@Override
	public void setListRecipes(Set<IRecipe> listRecipes) {
		Set<Recipe> retList = new LinkedHashSet<>();
		for (IRecipe ingredient : listRecipes) {
			retList.add((Recipe) ingredient);
		}
		this.listRecipes = retList;
	}

	public void addRecipe(IRecipe recipe) {
		((Recipe) recipe).setCookBook(this);
		this.listRecipes.add((Recipe) recipe);
	}
}