package de.ar.rgk.gui.views.viewMain.actions.recipe;

import org.eclipse.jface.action.Action;

public class ActionDeleteRecipe extends Action {

	public static final String TEXT = "Rezept löschen";

	private IRecipeEditAction editAction;

	public ActionDeleteRecipe(IRecipeEditAction editAction) {
		super(TEXT);
		this.editAction = editAction;
	}

	@Override
	public void run() {
		editAction.deleteRecipe();
	}
}