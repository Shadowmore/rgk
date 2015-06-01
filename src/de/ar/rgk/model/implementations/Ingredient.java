package de.ar.rgk.model.implementations;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.ar.rgk.model.abstracts.HibernateDataObject;
import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.setter.IIngredientSetter;

@Entity
@Table(name = "ingredient")
public class Ingredient extends HibernateDataObject implements IIngredient, IIngredientSetter {

	@Column(name = "name", unique = true)
	private String name;

	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Instruction> listInstructions;

	protected Ingredient() {
		// Für Hibernate
	}

	public Ingredient(String name) {
		this.name = name;
		this.listInstructions = new LinkedHashSet<>();
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
	public Set<Instruction> getListInstructions() {
		return listInstructions;
	}

	@Override
	public void setListInstructions(Set<Instruction> listInstructions) {
		this.listInstructions = listInstructions;
	}

	public void addInstruction(IInstruction instruction) {
		((Instruction) instruction).setIngredient(this);
		this.listInstructions.add((Instruction) instruction);
	}
}