package de.ar.rgk.gui.views.viewMain.actions.instruction;

import org.eclipse.jface.action.Action;

public class ActionEditInstruction extends Action {

	public static final String TEXT = "Anweisung bearbeiten";

	private IInstructionEditAction editAction;

	public ActionEditInstruction(IInstructionEditAction editAction) {
		super(TEXT);
		this.editAction = editAction;
	}

	@Override
	public void run() {
		editAction.editInstruction();
	}
}