package de.ar.rgk.gui.views.viewMain;

import java.util.ArrayList;
import java.util.List;

import de.ar.rgk.model.implementations.Recipe;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.tools.SortTool;

public class RecipeToHtmlGenerator {

	private static final int WIDTH_TD1_IN_PERCENT = 30;
	private static final int WIDTH_TD2_IN_PERCENT = 70;

	private Recipe recipe;
	private StringBuffer sbHtml;

	public RecipeToHtmlGenerator(Recipe recipe) {
		this.recipe = recipe;
		this.sbHtml = new StringBuffer();
	}

	// public Instruction(int numberInList, Ingredient ingredient, double amount, String unit) {
	// this.numberInList = numberInList;
	// this.text = "";
	// this.ingredientText = "";
	// this.ingredient = ingredient;
	// this.amount = amount;
	// this.unit = unit;
	// }
	//
	// public Instruction(int numberInList, Ingredient ingredient, double amount, String unit, String text) {
	// this.numberInList = numberInList;
	// this.text = text;
	// this.ingredientText = "";
	// this.ingredient = ingredient;
	// this.amount = amount;
	// this.unit = unit;
	// }

	public String generateHtml() {
		addStandardStartHtml();
		appendRowToStringBuffer("<br/>", 3);
		appendRowToStringBuffer("<br/>", 3);
		appendRowToStringBuffer("<table border=\"0\" width=\"100%\">", 3);
		appendRowToStringBuffer("<tr>", 4);
		appendRowToStringBuffer("<td colspan=\"2\" align=\"center\">", 5);
		appendRowToStringBuffer("<b><font size=\"6\">" + recipe.getName() + "</font></b>", 6);
		appendRowToStringBuffer("</br>", 6);
		appendRowToStringBuffer("<font size=\"2\">Für " + recipe.getAmountPersons() + " Personen</font>", 6);
		appendRowToStringBuffer("<br/>", 6);
		appendRowToStringBuffer("<br/>", 6);
		appendRowToStringBuffer("<br/>", 6);
		appendRowToStringBuffer("</td>", 5);
		appendRowToStringBuffer("</tr>", 4);
		List<IInstruction> listInstructions = new ArrayList<>(recipe.getListInstructions());
		SortTool.sortListInstructions(listInstructions);
		for (IInstruction instruction : listInstructions) {
			appendRowToStringBuffer("<tr>", 4);

			if (!instruction.getIngredientText().equals("")) {
				appendIngredientTextRowToStringBuffer(instruction);
			} else if (instruction.getIngredientText().equals("") && instruction.getIngredient() == null) {
				appendOnlyTextRowToStringBuffer(instruction);
			} else if (instruction.getIngredient() != null && instruction.getText().equals("")) {
				appendIngredientWithoutTextToStringBuffer(instruction);
			} else if (instruction.getIngredient() != null && !instruction.getText().equals("")) {
				appendIngredientWithTextToStringBuffer(instruction);
			}

			appendRowToStringBuffer("</tr>", 4);
		}

		addStandardEndHtml();
		return sbHtml.toString();
	}

	private void appendIngredientTextRowToStringBuffer(IInstruction instruction) {
		appendRowToStringBuffer("<td width=\"" + WIDTH_TD1_IN_PERCENT + "%\" valign=\"top\">", 5);
		appendRowToStringBuffer("<p>" + instruction.getIngredientText() + "</p>", 6);
		appendRowToStringBuffer("</td>", 5);
		appendRowToStringBuffer("<td width=\"" + WIDTH_TD2_IN_PERCENT + "%\" valign=\"top\">", 5);
		appendRowToStringBuffer("<p>" + instruction.getText() + "</p>", 6);
		appendRowToStringBuffer("</td>", 5);
	}

	private void appendOnlyTextRowToStringBuffer(IInstruction instruction) {
		appendRowToStringBuffer("<td colspan=\"2\" valign=\"top\">", 5);
		appendRowToStringBuffer("<p>" + instruction.getText() + "</p>", 6);
		appendRowToStringBuffer("</td>", 5);
	}

	private void appendIngredientWithoutTextToStringBuffer(IInstruction instruction) {

		String ingredentString = "";

		double amount = instruction.getAmount();
		if (amount > 0) {
			String amountString = amount + " ";
			amountString = amountString.replace(".0 ", "");
			amountString = amountString.trim();
			ingredentString += amountString + " ";
		}

		String unit = instruction.getUnit();
		if (!unit.equals("")) {
			ingredentString += unit + " ";
		}

		String ingredientName = instruction.getIngredient().getName();
		ingredentString += ingredientName;

		appendRowToStringBuffer("<td width=\"" + WIDTH_TD1_IN_PERCENT + "%\" valign=\"top\">", 5);
		appendRowToStringBuffer("<p>" + ingredentString + "</p>", 6);
		appendRowToStringBuffer("</td>", 5);
		appendRowToStringBuffer("<td width=\"" + WIDTH_TD2_IN_PERCENT + "%\" valign=\"top\">", 5);
		appendRowToStringBuffer("<p>" + instruction.getText() + "</p>", 6);
		appendRowToStringBuffer("</td>", 5);
	}

	private void appendIngredientWithTextToStringBuffer(IInstruction instruction) {

		String ingredentString = "";

		double amount = instruction.getAmount();
		if (amount > 0) {
			String amountString = amount + " ";
			amountString = amountString.replace(".0 ", "");
			amountString = amountString.trim();
			ingredentString += amountString + " ";
		}

		String unit = instruction.getUnit();
		if (!unit.equals("")) {
			ingredentString += unit + " ";
		}

		String ingredientName = instruction.getIngredient().getName();
		ingredentString += ingredientName;

		appendRowToStringBuffer("<td width=\"" + WIDTH_TD1_IN_PERCENT + "%\" valign=\"top\">", 5);
		appendRowToStringBuffer("<p>" + ingredentString + "</p>", 6);
		appendRowToStringBuffer("</td>", 5);
		appendRowToStringBuffer("<td width=\"" + WIDTH_TD2_IN_PERCENT + "%\" valign=\"top\">", 5);
		appendRowToStringBuffer("<p>" + instruction.getText() + "</p>", 6);
		appendRowToStringBuffer("</td>", 5);
	}

	private void addStandardStartHtml() {
		appendRowToStringBuffer("<html>", 0);
		appendRowToStringBuffer("<head>", 1);
		appendRowToStringBuffer("</head>", 1);
		appendRowToStringBuffer("<body>", 1);
		appendRowToStringBuffer("<div>", 2);
	}

	private void addStandardEndHtml() {
		appendRowToStringBuffer("</div>", 2);
		appendRowToStringBuffer("</body>", 1);
		appendRowToStringBuffer("</html>", 0);
	}

	private void appendRowToStringBuffer(String toAppend, int amountTabs) {
		String tabs = "";
		for (int i = 0; i < amountTabs; i++) {
			tabs += "\t";
		}
		sbHtml.append(tabs + toAppend + "\r\n");
	}
}