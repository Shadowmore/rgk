package de.ar.rgk.gui.views.viewMain.dialogs.dialogRecipe;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.ar.rgk.gui.views.abstracts.AbstractDialog;
import de.ar.rgk.gui.views.viewMain.ViewMain;
import de.ar.rgk.gui.views.viewMain.actions.instruction.IInstructionEditAction;
import de.ar.rgk.gui.views.viewMain.dialogs.dialogCookBook.DialogEditCookBook;
import de.ar.rgk.gui.views.viewMain.dialogs.dialogInstruction.DialogEditInstruction;
import de.ar.rgk.gui.views.viewMain.dialogs.dialogRecipe.tableViewer.TableLableProviderDialogEditRecipe;
import de.ar.rgk.model.implementations.CookBook;
import de.ar.rgk.model.implementations.Instruction;
import de.ar.rgk.model.implementations.Recipe;
import de.ar.rgk.model.interfaces.ICookBook;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.interfaces.IRecipe;
import de.ar.rgk.model.tools.SortTool;

public class DialogEditRecipe extends AbstractDialog implements IInstructionEditAction {

	private Recipe recipe;
	private Spinner spinnerAmountPersons;
	private Text txtPageInCookBook;
	private Text txtRecipeName;
	private Combo comboCookBooks;
	private TableViewer tableViewerInstruction;

	private List<IInstruction> listInstructions;

	public DialogEditRecipe(Shell parentShell, IRecipe recipe, String dialogTitle) {
		super(parentShell, dialogTitle);

		this.recipe = (Recipe) recipe;
		listInstructions = new ArrayList<>();
		for (IInstruction instruction : recipe.getListInstructions()) {
			Instruction instructionNew = new Instruction(instruction.getNumberInList(), instruction.getIngredient(), instruction.getAmount(), instruction.getUnit(), instruction.getText());
			instructionNew.setIngredientText(instruction.getIngredientText());
			listInstructions.add(instructionNew);
		}
	}

	@Override
	protected void createContent(Composite compMain) {

		Composite compTxts = new Composite(compMain, SWT.NONE);
		compTxts.setLayout(new GridLayout(2, false));
		compTxts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		{
			Label lblName = new Label(compTxts, SWT.NONE);
			lblName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			lblName.setText("Rezeptname");

			txtRecipeName = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
			txtRecipeName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			txtRecipeName.setText(recipe.getName());
		}
		{
			Label lblAmountPersons = new Label(compTxts, SWT.NONE);
			lblAmountPersons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			lblAmountPersons.setText("Anzahl Personen");

			spinnerAmountPersons = new Spinner(compTxts, SWT.SINGLE | SWT.BORDER);
			spinnerAmountPersons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			spinnerAmountPersons.setMinimum(1);
			spinnerAmountPersons.setSelection(recipe.getAmountPersons());
		}
		{
			Label lblPageInCookBook = new Label(compTxts, SWT.NONE);
			lblPageInCookBook.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			lblPageInCookBook.setText("Seite in Kochbuch");

			txtPageInCookBook = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
			txtPageInCookBook.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			txtPageInCookBook.setText(recipe.getPageInCookBook() + "");
		}
		{
			Composite compCookBooks = new Composite(compMain, SWT.NONE);
			compCookBooks.setLayout(new GridLayout(3, false));
			compCookBooks.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			Label lblCookBook = new Label(compCookBooks, SWT.NONE);
			lblCookBook.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lblCookBook.setText("Kochbuch");

			comboCookBooks = new Combo(compCookBooks, SWT.READ_ONLY);
			comboCookBooks.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			List<ICookBook> listAllCookbooks = ViewMain.getListAllCookbooks();
			for (ICookBook cookBook : listAllCookbooks) {
				comboCookBooks.add(cookBook.getName());
			}
			if (listAllCookbooks.size() > 0) {
				comboCookBooks.select(0);
			}

			Button btnNewCookBook = new Button(compCookBooks, SWT.NONE);
			btnNewCookBook.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			btnNewCookBook.setText("Neues Kochbuch");
			btnNewCookBook.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					btnNewCookBookSelected();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
		}

		tableViewerInstruction = new TableViewer(compMain, SWT.FULL_SELECTION | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		new TableLableProviderDialogEditRecipe(tableViewerInstruction);

		Composite compoButtons = new Composite(compMain, SWT.NONE);
		compoButtons.setLayout(new GridLayout(3, false));
		compoButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Button btnNewInstruction = new Button(compoButtons, SWT.NONE);
		btnNewInstruction.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnNewInstruction.setText("Neue Anweisung");
		btnNewInstruction.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnNewInstructionSelected();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button btnEditInstruction = new Button(compoButtons, SWT.NONE);
		btnEditInstruction.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnEditInstruction.setText("Anweisung bearbeiten");
		btnEditInstruction.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnEditInstructionSelected();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button btnDeleteInstruction = new Button(compoButtons, SWT.NONE);
		btnDeleteInstruction.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnDeleteInstruction.setText("Anweisung löschen");
		btnDeleteInstruction.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDeleteInstructionSelected();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		updateTableInstructions();
	}

	private void btnNewInstructionSelected() {
		newInstruction();
	}

	private void btnEditInstructionSelected() {
		editInstruction();
	}

	private void btnDeleteInstructionSelected() {
		deleteInstruction();
	}

	private void updateTableInstructions() {
		SortTool.sortListInstructions(listInstructions);
		tableViewerInstruction.setInput(listInstructions);
	}

	private void btnNewCookBookSelected() {
		ICookBook cookBook = new CookBook("");
		DialogEditCookBook dialogEditCookBook = new DialogEditCookBook(getShell(), cookBook, "Neues Kochbuch erstellen");
		int open = dialogEditCookBook.open();

		if (open == Window.OK) {
			comboCookBooks.add(cookBook.getName());
			comboCookBooks.select(comboCookBooks.getItemCount() - 1);
		}
	}

	@Override
	protected String plausi() {
		String name = txtRecipeName.getText();
		if (name == null || name.equals("")) {
			return "Das Rezept besitzt noch keinen Namen";
		}

		String amount = spinnerAmountPersons.getText();
		int amountAsInt = -1;
		try {
			amountAsInt = Integer.parseInt(amount);
		} catch (Exception e) {
			return "Bitte nur Zahlen für die Personenanzahl angeben";
		}
		if (amountAsInt < 0) {
			return "Bitte keine negativen Zahlen als Personenanzahl angeben";
		}

		String page = txtPageInCookBook.getText();
		int pageAsInt = 0;
		try {
			pageAsInt = Integer.parseInt(page);
		} catch (Exception e) {
			return "Bitte nur Zahlen für die Seitenzahl angeben";
		}
		if (pageAsInt < 0) {
			return "Bitte keine negativen Zahlen als Seitenzahlen angeben";
		}

		String cookBookName = comboCookBooks.getText();
		if (cookBookName == null || cookBookName.equals("")) {
			return "Es wurde noch kein Kochbuch ausgewählt";
		}

		return null;
	}

	@Override
	protected void writeDataFromWidgetsIntoObject() {
		recipe.setName(txtRecipeName.getText());
		recipe.setAmountPersons(Integer.parseInt(spinnerAmountPersons.getText()));
		recipe.setPageInCookBook(Integer.parseInt(txtPageInCookBook.getText()));

		ICookBook cookBookToSet = null;
		for (ICookBook cookBook : ViewMain.getListAllCookbooks()) {
			if (cookBook.getName().equals(comboCookBooks.getText())) {
				cookBookToSet = cookBook;
			}
		}
		if (cookBookToSet == null) {
			cookBookToSet = new CookBook(comboCookBooks.getText());
		}
		recipe.setCookBook(cookBookToSet);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(800, 600);
	}

	@Override
	public void newInstruction() {
		IInstruction instruction = new Instruction(-1, "");
		DialogEditInstruction editInstruction = new DialogEditInstruction(getShell(), instruction, "Neue Anweisung erstellen");

		int result = editInstruction.open();
		if (result == 0) {
			// recipe.addInstruction(instruction);
			listInstructions.add(instruction);
			updateTableInstructions();
		}
	}

	@Override
	public void editInstruction() {
		TableItem[] selection = tableViewerInstruction.getTable().getSelection();
		if (selection != null && selection.length > 0) {
			Object data = selection[0].getData();
			if (data instanceof IInstruction) {
				IInstruction instructionToEdit = (Instruction) data;
				DialogEditInstruction editInstruction = new DialogEditInstruction(getShell(), instructionToEdit, "Anweisung bearbeiten");

				int result = editInstruction.open();
				if (result == 0) {
					updateTableInstructions();
				}
			}
		}
	}

	@Override
	public void deleteInstruction() {
		TableItem[] selection = tableViewerInstruction.getTable().getSelection();
		if (selection != null && selection.length > 0) {
			Object data = selection[0].getData();
			if (data instanceof IInstruction) {
				IInstruction instructionToDelete = (Instruction) data;
				MessageDialog dialog = new MessageDialog(getShell(), "Anweisung Löschen", null, "Soll die ausgewählte Anweisung Nr. " + instructionToDelete.getNumberInList()
						+ " wirklich gelöscht werden??", MessageDialog.CONFIRM, new String[] { "OK", "Abbrechen" }, 0);
				int result = dialog.open();
				if (result == 0) {
					// recipe.removeInstruction(instructionToDelete);
					listInstructions.remove(instructionToDelete);
					updateTableInstructions();
				}
			}
		}
	}

	public IRecipe getRecipe() {
		return recipe;
	}
}