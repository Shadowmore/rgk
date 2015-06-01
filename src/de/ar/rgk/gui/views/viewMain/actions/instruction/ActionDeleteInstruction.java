package de.ar.rgk.gui.views.viewMain.actions.instruction;

import org.eclipse.jface.action.Action;

public class ActionDeleteInstruction extends Action {

	public static final String TEXT = "Anweisung löschen";

	private IInstructionEditAction editAction;

	public ActionDeleteInstruction(IInstructionEditAction editAction) {
		super(TEXT);
		this.editAction = editAction;
	}

	@Override
	public void run() {
		editAction.deleteInstruction();
	}
}