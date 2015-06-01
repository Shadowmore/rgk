package de.ar.rgk.model.setter;

import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IRecipe;

public interface IInstructionSetter {

	void setNumberInList(int numberInList);

	void setText(String text);

	void setIngredientText(String ingredientText);

	void setAmount(double amount);

	void setUnit(String unit);

	void setRecipe(IRecipe recipe);

	void setIngredient(IIngredient ingredient);
}
