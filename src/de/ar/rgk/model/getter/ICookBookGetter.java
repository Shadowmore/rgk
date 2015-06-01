package de.ar.rgk.model.getter;

import java.util.Set;

import de.ar.rgk.model.interfaces.IRecipe;

public interface ICookBookGetter {

	String getName();

	Set<IRecipe> getListRecipes();
}
