package de.ar.rgk.model.setter;

import java.util.Set;

import de.ar.rgk.model.interfaces.ICookBook;
import de.ar.rgk.model.interfaces.IInstruction;

public interface IRecipeSetter {

	void setName(String name);

	void setCookBook(ICookBook cookBook);

	void setPageInCookBook(int pageInCookBook);

	void setAmountPersons(int amountPersons);

	void setListInstructions(Set<IInstruction> listInstructions);

}
