package de.ar.rgk.model.getter;

import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IRecipe;

public interface IInstructionGetter {

	int getNumberInList();

	String getText();

	String getIngredientText();

	double getAmount();

	String getUnit();

	IRecipe getRecipe();

	IIngredient getIngredient();
}
