package de.ar.rgk.gui.views.viewMain.dialogs.dialogInstruction;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.ar.rgk.gui.views.abstracts.AbstractDialog;
import de.ar.rgk.gui.views.viewMain.ViewMain;
import de.ar.rgk.gui.views.viewMain.dialogs.dialogIngredient.DialogEditIngredient;
import de.ar.rgk.model.implementations.Ingredient;
import de.ar.rgk.model.implementations.Instruction;
import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;

public class DialogEditInstruction extends AbstractDialog {

	private List<IIngredient> listIngredients;

	private Instruction instruction;
	private Text txtInstructionText;
	private Text txtInstructionIngredientText;
	private Text txtInstructionAmount;
	private Text txtInstructionUnit;
	private Combo comboIngredients;

	public DialogEditInstruction(Shell parentShell, IInstruction instruction, String dialogTitle) {
		super(parentShell, dialogTitle);

		this.instruction = (Instruction) instruction;
		this.listIngredients = ViewMain.getListAllIngredients();
	}

	@Override
	protected void createContent(Composite compMain) {

		Composite compTxts = new Composite(compMain, SWT.NONE);
		compTxts.setLayout(new GridLayout(2, false));
		compTxts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Button btnNormal = new Button(compTxts, SWT.RADIO);
		btnNormal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnNormal.setText("Menge Einheit Anweisung");
		btnNormal.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				txtInstructionIngredientText.setEnabled(false);

				txtInstructionText.setEnabled(true);
				txtInstructionAmount.setEnabled(true);
				txtInstructionUnit.setEnabled(true);
				comboIngredients.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button btnSpecial = new Button(compTxts, SWT.RADIO);
		btnSpecial.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnSpecial.setText("Zutatentext");
		btnSpecial.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				txtInstructionIngredientText.setEnabled(true);
				txtInstructionText.setEnabled(true);

				txtInstructionAmount.setEnabled(false);
				txtInstructionUnit.setEnabled(false);
				comboIngredients.setEnabled(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		{
			Label lblInstructionIngredientText = new Label(compTxts, SWT.NONE);
			lblInstructionIngredientText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			lblInstructionIngredientText.setText("Text");

			txtInstructionText = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
			txtInstructionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			txtInstructionText.setText(instruction.getText());
		}
		{
			Label lblInstructionAmount = new Label(compTxts, SWT.NONE);
			lblInstructionAmount.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			lblInstructionAmount.setText("Menge");

			txtInstructionAmount = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
			txtInstructionAmount.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			txtInstructionAmount.setText(instruction.getAmount() + "");
		}
		{
			Label lblInstructionUnit = new Label(compTxts, SWT.NONE);
			lblInstructionUnit.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			lblInstructionUnit.setText("Einheit");

			txtInstructionUnit = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
			txtInstructionUnit.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			txtInstructionUnit.setText(instruction.getUnit());
		}
		{
			Composite compIngredients = new Composite(compMain, SWT.NONE);
			compIngredients.setLayout(new GridLayout(3, false));
			compIngredients.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			Label lblIngredients = new Label(compIngredients, SWT.NONE);
			lblIngredients.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lblIngredients.setText("Zutat");

			comboIngredients = new Combo(compIngredients, SWT.READ_ONLY);
			comboIngredients.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

			for (IIngredient ingredient : listIngredients) {
				comboIngredients.add(ingredient.getName());
			}
			if (listIngredients.size() > 0) {
				comboIngredients.select(0);
			}

			Button btnNewIngredient = new Button(compIngredients, SWT.NONE);
			btnNewIngredient.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			btnNewIngredient.setText("Neue Zutat");
			btnNewIngredient.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					btnNewIngredientSelected();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		}
		{
			Label lblInstructionText = new Label(compTxts, SWT.NONE);
			lblInstructionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			lblInstructionText.setText("Zutat-Sondertext");

			txtInstructionIngredientText = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
			txtInstructionIngredientText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			txtInstructionIngredientText.setText(instruction.getIngredientText());
		}
	}

	private void btnNewIngredientSelected() {
		IIngredient ingredient = new Ingredient("");
		DialogEditIngredient dialogEditIngredient = new DialogEditIngredient(getShell(), ingredient, "Neue Zutat erstellen");
		int open = dialogEditIngredient.open();

		if (open == Window.OK) {
			listIngredients.add(ingredient);
			comboIngredients.add(ingredient.getName());
			comboIngredients.select(comboIngredients.getItemCount() - 1);
		}
	}

	@Override
	protected String plausi() {

		double amount = -1;
		try {
			amount = Double.parseDouble(txtInstructionAmount.getText());
		} catch (Exception e) {
			return "Bitte nur Zahlen für Menge angeben";
		}
		if (amount <= 0) {
			return "Bitte nur positive Zahlen für Menge angeben";
		}

		return null;
	}

	@Override
	protected void writeDataFromWidgetsIntoObject() {
		instruction.setAmount(Double.parseDouble(txtInstructionAmount.getText()));
		instruction.setIngredient(listIngredients.get(comboIngredients.getSelectionIndex()));
		instruction.setUnit(txtInstructionUnit.getText());
		instruction.setText(txtInstructionText.getText());
		instruction.setIngredientText(txtInstructionIngredientText.getText());
	}
}