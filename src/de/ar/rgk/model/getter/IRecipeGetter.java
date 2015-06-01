package de.ar.rgk.model.getter;

import java.util.Set;

import de.ar.rgk.model.interfaces.ICookBook;
import de.ar.rgk.model.interfaces.IInstruction;

public interface IRecipeGetter {

	String getName();

	ICookBook getCookBook();

	int getPageInCookBook();

	int getAmountPersons();

	Set<IInstruction> getListInstructions();
}
