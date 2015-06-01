package de.ar.rgk.gui.views.viewMain.actions.recipe;

import org.eclipse.jface.action.Action;

public class ActionNewRecipe extends Action {

	public static final String TEXT = "Neues Rezept";

	private IRecipeEditAction editAction;

	public ActionNewRecipe(IRecipeEditAction editAction) {
		super(TEXT);
		this.editAction = editAction;
	}

	@Override
	public void run() {
		editAction.newRecipe();
	}
}