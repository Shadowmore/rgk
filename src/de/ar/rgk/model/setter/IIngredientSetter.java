package de.ar.rgk.model.setter;

import java.util.Set;

import de.ar.rgk.model.implementations.Instruction;

public interface IIngredientSetter {

	void setName(String name);

	void setListInstructions(Set<Instruction> listInstructions);

}