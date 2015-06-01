package de.ar.rgk.gui.views.viewMain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

import de.ar.rgk.gui.views.viewMain.actions.recipe.ActionDeleteRecipe;
import de.ar.rgk.gui.views.viewMain.actions.recipe.ActionEditRecipe;
import de.ar.rgk.gui.views.viewMain.actions.recipe.ActionNewRecipe;
import de.ar.rgk.gui.views.viewMain.actions.recipe.IRecipeEditAction;
import de.ar.rgk.gui.views.viewMain.dialogs.dialogRecipe.DialogEditRecipe;
import de.ar.rgk.gui.views.viewMain.tableViewer.TableLableProviderViewMain;
import de.ar.rgk.model.db.HibernateDao;
import de.ar.rgk.model.db.HibernateDaoRgk;
import de.ar.rgk.model.implementations.Recipe;
import de.ar.rgk.model.interfaces.ICookBook;
import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.interfaces.IRecipe;

public class ViewMain extends ViewPart implements IRecipeEditAction {
	public static final String ID = "de.ar.rgk.gui.views.viewMain.ViewMain";

	private SashForm sashMain;

	private TableViewer tableViewerRecipes;
	private Browser browserRecipe;

	private static List<ICookBook> listAllCookbooks = new ArrayList<>();
	private static List<IIngredient> listAllIngredients = new ArrayList<>();
	private static List<IRecipe> listAllRecipes = new ArrayList<>();
	private List<IRecipe> listRecipesInTable = new ArrayList<>();

	public ViewMain() {

		listAllCookbooks = new ArrayList<>();
		listAllIngredients = new ArrayList<>();
		listAllRecipes = new ArrayList<>();
		listRecipesInTable = new ArrayList<>();

		listAllRecipes = HibernateDaoRgk.getAllRecipes();
		listRecipesInTable = listAllRecipes;

		listAllIngredients = new ArrayList<>();
		for (IRecipe recipe : listAllRecipes) {
			for (IInstruction instruction : recipe.getListInstructions()) {
				IIngredient ingredient = instruction.getIngredient();
				if (ingredient != null) {
					if (!listAllIngredients.contains(ingredient)) {
						listAllIngredients.add(ingredient);
					}
				}
			}

			ICookBook cookBook = recipe.getCookBook();
			if (!listAllCookbooks.contains(cookBook)) {
				listAllCookbooks.add(cookBook);
			}
		}

		sortAllCookBooks();
		sortAllIngredients();
	}

	@Override
	public void createPartControl(Composite parent) {

		sashMain = new SashForm(parent, SWT.HORIZONTAL);
		sashMain.setLayout(new GridLayout(2, false));
		sashMain.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		generateCompLeft();

		generateCompRight();

		updateTableRecipes();
	}

	private void generateCompRight() {
		Composite compRight = new Composite(sashMain, SWT.BORDER);
		compRight.setLayout(new GridLayout(1, false));
		compRight.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		browserRecipe = new Browser(compRight, SWT.BORDER);
		browserRecipe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		browserRecipe.setText("");
	}

	private void generateCompLeft() {
		Composite compLeft = new Composite(sashMain, SWT.BORDER);
		compLeft.setLayout(new GridLayout(1, false));
		compLeft.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		{
			new CompositeSearch(compLeft, SWT.BORDER, this);
		}

		Composite compTable = new Composite(compLeft, SWT.BORDER);
		compTable.setLayout(new GridLayout(1, false));
		compTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		tableViewerRecipes = new TableViewer(compTable, SWT.FULL_SELECTION | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewerRecipes.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				tableViewerRecipeSelectionChanged();
			}

		});

		new TableLableProviderViewMain(tableViewerRecipes);

		Composite compButtons = new Composite(compLeft, SWT.BORDER);
		compButtons.setLayout(new GridLayout(3, false));
		compButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Button btnNewRecipe = new Button(compButtons, SWT.NONE);
		btnNewRecipe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnNewRecipe.setText(ActionNewRecipe.TEXT);
		btnNewRecipe.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnNewRecipeSelected();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button btnEditRecipe = new Button(compButtons, SWT.NONE);
		btnEditRecipe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnEditRecipe.setText(ActionEditRecipe.TEXT);
		btnEditRecipe.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnEditRecipeSelected();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button btnDelete = new Button(compButtons, SWT.NONE);
		btnDelete.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnDelete.setText(ActionDeleteRecipe.TEXT);
		btnDelete.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDeleteRecipeSelected();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	private void tableViewerRecipeSelectionChanged() {
		TableItem[] selection = tableViewerRecipes.getTable().getSelection();
		if (selection.length > 0) {
			Object data = selection[0].getData();
			if (data instanceof Recipe) {
				Recipe recipeToShow = (Recipe) data;

				RecipeToHtmlGenerator generator = new RecipeToHtmlGenerator(recipeToShow);
				String generatedHtml = generator.generateHtml();

				browserRecipe.setText(generatedHtml);
			}
		}
	}

	private void btnNewRecipeSelected() {
		newRecipe();
	}

	private void btnEditRecipeSelected() {
		editRecipe();
	}

	private void btnDeleteRecipeSelected() {
		deleteRecipe();
	}

	public void setListRecipesInTable(List<IRecipe> listRecipesInTable) {
		this.listRecipesInTable = listRecipesInTable;
	}

	public void updateTableRecipes() {
		tableViewerRecipes.setInput(listRecipesInTable);
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void newRecipe() {

		Shell shell = getSite().getShell();
		IRecipe recipe = new Recipe("", 0, 4);
		DialogEditRecipe editRecipe = new DialogEditRecipe(shell, recipe, "Neues Rezept erstellen");

		int result = editRecipe.open();
		if (result == 0) {
			listAllRecipes.add(recipe);

			HibernateDao.saveObject(editRecipe.getRecipe());
			updateTableRecipes();
			sortAllCookBooks();
			sortAllIngredients();
		}
	}

	@Override
	public void editRecipe() {
		TableItem[] selection = tableViewerRecipes.getTable().getSelection();
		if (selection != null && selection.length > 0) {
			Object data = selection[0].getData();
			if (data instanceof IRecipe) {
				IRecipe recipeToEdit = (Recipe) data;
				Shell shell = getSite().getShell();
				DialogEditRecipe editRecipe = new DialogEditRecipe(shell, recipeToEdit, "Rezept bearbeiten");

				int result = editRecipe.open();
				if (result == 0) {
					HibernateDao.saveObject(editRecipe.getRecipe());
					updateTableRecipes();
					sortAllCookBooks();
					sortAllIngredients();
				}
			}
		}
	}

	@Override
	public void deleteRecipe() {

		TableItem[] selection = tableViewerRecipes.getTable().getSelection();
		if (selection != null && selection.length > 0) {
			Object data = selection[0].getData();
			if (data instanceof IRecipe) {
				IRecipe recipeToDelete = (Recipe) data;
				MessageDialog dialog = new MessageDialog(getSite().getShell(), "Rezept Löschen", null, "Soll das ausgewählte Rezept \"" + recipeToDelete.getName() + "\" wirklich gelöscht werden??",
						MessageDialog.CONFIRM, new String[] { "OK", "Abbrechen" }, 0);
				int result = dialog.open();
				if (result == 0) {
					listAllRecipes.remove(recipeToDelete);
					listRecipesInTable.remove(recipeToDelete);
					HibernateDao.deleteObject(recipeToDelete);
					updateTableRecipes();
					sortAllCookBooks();
					browserRecipe.setText("");
				}
			}
		}
	}

	public static List<IIngredient> getListAllIngredients() {
		return listAllIngredients;
	}

	public static List<IRecipe> getListAllRecipes() {
		return listAllRecipes;
	}

	public static List<ICookBook> getListAllCookbooks() {
		return listAllCookbooks;
	}

	private void sortAllCookBooks() {
		Collections.sort(listAllCookbooks, new Comparator<ICookBook>() {

			@Override
			public int compare(ICookBook o1, ICookBook o2) {
				if (o1.getName() == null && o2.getName() == null) {
					return 0;
				}
				if (o1.getName() == null) {
					return 1;
				}
				if (o2.getName() == null) {
					return -1;
				}
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	private void sortAllIngredients() {
		Collections.sort(listAllIngredients, new Comparator<IIngredient>() {

			@Override
			public int compare(IIngredient o1, IIngredient o2) {
				String name = o1.getName().toUpperCase();
				String name2 = o2.getName().toUpperCase();

				if (name == null && name2 == null) {
					return 0;
				}
				if (name == null) {
					return 1;
				}
				if (name2 == null) {
					return -1;
				}
				return name.compareTo(name2);
			}
		});
	}
}