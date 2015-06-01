package de.ar.rgk.model.implementations;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.ar.rgk.model.abstracts.HibernateDataObject;
import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.interfaces.IRecipe;
import de.ar.rgk.model.setter.IInstructionSetter;

@Entity
@Table(name = "instruction")
public class Instruction extends HibernateDataObject implements IInstruction, IInstructionSetter {

	@Column(name = "numberInList")
	private int numberInList;

	@Column(name = "text")
	private String text;

	@Column(name = "ingredientText")
	private String ingredientText;

	@Column(name = "amount")
	private double amount;

	@Column(name = "unit")
	private String unit;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Recipe recipe;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Ingredient ingredient;

	protected Instruction() {
		// Für Hibernate
	}

	public Instruction(int numberInList, String text) {
		this.numberInList = numberInList;
		this.ingredientText = "";
		this.text = text;
		this.unit = "";
		this.amount = 0.0;
	}

	public Instruction(int numberInList, String ingredientText, String text) {
		this.numberInList = numberInList;
		this.text = text;
		this.ingredientText = ingredientText;
		this.unit = "";
		this.amount = 0.0;
	}

	public Instruction(int numberInList, IIngredient ingredient, double amount, String unit) {
		this.numberInList = numberInList;
		this.text = "";
		this.ingredientText = "";
		this.ingredient = (Ingredient) ingredient;
		this.amount = amount;
		this.unit = unit;
	}

	public Instruction(int numberInList, IIngredient ingredient, double amount, String unit, String text) {
		this.numberInList = numberInList;
		this.text = text;
		this.ingredientText = "";
		this.ingredient = (Ingredient) ingredient;
		this.amount = amount;
		this.unit = unit;
	}

	@Override
	public int getNumberInList() {
		return numberInList;
	}

	@Override
	public void setNumberInList(int numberInList) {
		this.numberInList = numberInList;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String getIngredientText() {
		return this.ingredientText;
	}

	@Override
	public void setIngredientText(String ingredientText) {
		this.ingredientText = ingredientText;
	}

	@Override
	public double getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String getUnit() {
		return this.unit;
	}

	@Override
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public IRecipe getRecipe() {
		return recipe;
	}

	@Override
	public void setRecipe(IRecipe recipe) {
		this.recipe = (Recipe) recipe;
	}

	@Override
	public IIngredient getIngredient() {
		return this.ingredient;
	}

	@Override
	public void setIngredient(IIngredient ingredient) {
		this.ingredient = (Ingredient) ingredient;
	}
}