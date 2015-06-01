package de.ar.rgk.model.getter;

import java.util.Set;

import de.ar.rgk.model.implementations.Instruction;

public interface IIngredientGetter {

	String getName();

	Set<Instruction> getListInstructions();

}