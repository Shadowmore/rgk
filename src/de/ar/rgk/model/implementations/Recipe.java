package de.ar.rgk.model.implementations;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.ar.rgk.model.abstracts.HibernateDataObject;
import de.ar.rgk.model.interfaces.ICookBook;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.interfaces.IRecipe;
import de.ar.rgk.model.setter.IRecipeSetter;

@Entity
@Table(name = "recipe")
public class Recipe extends HibernateDataObject implements IRecipe, IRecipeSetter {

	@Column(name = "name")
	private String name;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CookBook cookBook;

	@Column(name = "pageInCookBook")
	private int pageInCookBook;

	@Column(name = "amountPersons")
	private int amountPersons;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Instruction> listInstructions;

	protected Recipe() {
		// Für Hibernate
	}

	public Recipe(String name, int pageInCookBook, int amountPersons) {
		this.name = name;
		this.pageInCookBook = pageInCookBook;
		this.amountPersons = amountPersons;
		this.listInstructions = new LinkedHashSet<>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ICookBook getCookBook() {
		return this.cookBook;
	}

	@Override
	public int getPageInCookBook() {
		return this.pageInCookBook;
	}

	@Override
	public int getAmountPersons() {
		return this.amountPersons;
	}

	@Override
	public Set<IInstruction> getListInstructions() {
		Set<IInstruction> retList = new LinkedHashSet<>();
		for (Instruction instruction : this.listInstructions) {
			retList.add(instruction);
		}
		return Collections.unmodifiableSet(retList);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setCookBook(ICookBook cookBook) {
		this.cookBook = (CookBook) cookBook;
	}

	@Override
	public void setPageInCookBook(int pageInCookBook) {
		this.pageInCookBook = pageInCookBook;
	}

	@Override
	public void setAmountPersons(int amountPersons) {
		this.amountPersons = amountPersons;
	}

	@Override
	public void setListInstructions(Set<IInstruction> listInstructions) {
		Set<Instruction> retList = new LinkedHashSet<>();
		for (IInstruction ingredient : listInstructions) {
			retList.add((Instruction) ingredient);
		}
		this.listInstructions = retList;
	}

	public void addInstruction(IInstruction instruction) {
		((Instruction) instruction).setRecipe(this);
		this.listInstructions.add((Instruction) instruction);
	}

	public void removeInstruction(IInstruction instruction) {
		this.listInstructions.remove(instruction);
	}
}