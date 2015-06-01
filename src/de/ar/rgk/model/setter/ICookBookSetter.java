package de.ar.rgk.model.setter;

import java.util.Set;

import de.ar.rgk.model.interfaces.IRecipe;

public interface ICookBookSetter {

	void setName(String name);

	void setListRecipes(Set<IRecipe> listRecipes);

}
