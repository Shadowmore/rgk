package de.ar.rgk.gui.views.viewMain.actions.recipe;

import org.eclipse.jface.action.Action;

public class ActionEditRecipe extends Action {

	public static final String TEXT = "Rezept bearbeiten";

	private IRecipeEditAction editAction;

	public ActionEditRecipe(IRecipeEditAction editAction) {
		super(TEXT);
		this.editAction = editAction;
	}

	@Override
	public void run() {
		editAction.editRecipe();
	}
}