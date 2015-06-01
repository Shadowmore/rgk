package de.ar.rgk.gui.views.viewMain.actions.instruction;

import org.eclipse.jface.action.Action;

public class ActionNewInstruction extends Action {

	public static final String TEXT = "Neue Anweisung";

	private IInstructionEditAction editAction;

	public ActionNewInstruction(IInstructionEditAction editAction) {
		super(TEXT);
		this.editAction = editAction;
	}

	@Override
	public void run() {
		editAction.newInstruction();
	}
}