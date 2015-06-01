package de.ar.rgk.gui.views.viewMain.dialogs.dialogIngredient;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.ar.rgk.gui.views.abstracts.AbstractDialog;
import de.ar.rgk.model.implementations.Ingredient;
import de.ar.rgk.model.interfaces.IIngredient;

public class DialogEditIngredient extends AbstractDialog {

	private Ingredient ingredient;
	private Text txtIngredientName;

	public DialogEditIngredient(Shell parentShell, IIngredient ingredient, String dialogTitle) {
		super(parentShell, dialogTitle);

		this.ingredient = (Ingredient) ingredient;
	}

	@Override
	protected void createContent(Composite compMain) {

		Composite compTxts = new Composite(compMain, SWT.NONE);
		compTxts.setLayout(new GridLayout(2, false));
		compTxts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label lblName = new Label(compTxts, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		lblName.setText("Name der Zutat");

		txtIngredientName = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
		txtIngredientName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		txtIngredientName.setText(ingredient.getName());
	}

	@Override
	protected String plausi() {
		String text = txtIngredientName.getText();
		if (text == null || text.equals("")) {
			return "Die Zutat besitzt noch keinen Namen";
		}
		return null;
	}

	@Override
	protected void writeDataFromWidgetsIntoObject() {
		String text = txtIngredientName.getText();
		ingredient.setName(text);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(400, 300);
	}
}