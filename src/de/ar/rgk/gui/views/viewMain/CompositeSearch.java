package de.ar.rgk.gui.views.viewMain;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;
import de.ar.rgk.model.interfaces.IRecipe;

public class CompositeSearch extends Composite {

	private ViewMain viewMain;

	private Text txtSearch;

	private Button btnCheckName;
	private Button btnCheckIngredient;
	private Button btnCheckCookbook;

	private Button btnAND;
	private Button btnOR;

	public CompositeSearch(Composite parent, int style, ViewMain viewMain) {
		super(parent, style);
		this.viewMain = viewMain;

		this.setLayout(new GridLayout(2, false));
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createContents(this);
	}

	private void createContents(CompositeSearch compSearch) {

		txtSearch = new Text(compSearch, SWT.BORDER);
		txtSearch.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Button btnSearchStart = new Button(compSearch, SWT.NONE);
		btnSearchStart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnSearchStart.setText("Suche Rezept");
		btnSearchStart.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				searchRecipe();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		ExpandBar expandBar = new ExpandBar(compSearch, SWT.VERTICAL);

		Composite compAdvancedSearch = new Composite(expandBar, SWT.NONE);
		compAdvancedSearch.setLayout(new GridLayout(1, false));
		compAdvancedSearch.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Group groupSearchCriteries = new Group(compAdvancedSearch, SWT.NONE);
		groupSearchCriteries.setLayout(new GridLayout(1, false));
		groupSearchCriteries.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		groupSearchCriteries.setText("Suchkriterien");
		fillAdvacnedSearch(groupSearchCriteries);

		Group groupOperators = new Group(compAdvancedSearch, SWT.NONE);
		groupOperators.setLayout(new GridLayout(1, false));
		groupOperators.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		groupOperators.setText("Suchoperatoren");
		fillSerchOperators(groupOperators);

		makeExpandItem(expandBar, compAdvancedSearch, "Erweiterte Suche").setExpanded(true);

		GridData layoutDataExpandBar = new GridData(SWT.FILL, SWT.FILL, true, true);
		layoutDataExpandBar.horizontalSpan = 2;
		expandBar.setLayoutData(layoutDataExpandBar);
		expandBar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

	}

	private void fillAdvacnedSearch(Composite comp) {

		btnCheckName = new Button(comp, SWT.RADIO);
		addLayoutToCheckButtons(btnCheckName, "Name", true);
		btnCheckIngredient = new Button(comp, SWT.RADIO);
		addLayoutToCheckButtons(btnCheckIngredient, "Zutat", false);
		btnCheckCookbook = new Button(comp, SWT.RADIO);
		addLayoutToCheckButtons(btnCheckCookbook, "Kochbuch", false);
	}

	private void fillSerchOperators(Composite comp) {
		btnAND = new Button(comp, SWT.RADIO);
		addLayoutToCheckButtons(btnAND, "UND", true);
		btnOR = new Button(comp, SWT.RADIO);
		addLayoutToCheckButtons(btnOR, "ODER", false);
	}

	private void addLayoutToCheckButtons(Button btnCheck, String text, boolean selection) {
		btnCheck.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnCheck.setText(text);
		btnCheck.setSelection(selection);
	}

	protected ExpandItem makeExpandItem(ExpandBar expandBar, Composite comp, String text) {
		ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setText(text);
		expandItem.setControl(comp);
		expandItem.setHeight(comp.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		return expandItem;
	}

	@SuppressWarnings("static-access")
	private void searchRecipe() {
		String[] searchSplit = txtSearch.getText().toUpperCase().split(";");

		List<IRecipe> listRecipesInTable = new ArrayList<>();

		if (btnCheckName.getSelection()) {
			for (String searchString : searchSplit) {
				for (IRecipe recipe : viewMain.getListAllRecipes()) {
					String name = recipe.getName().toUpperCase();
					if (name.contains(searchString)) {
						if (!listRecipesInTable.contains(recipe)) {
							listRecipesInTable.add(recipe);
						}
					} else if (searchString.contains(name)) {
						if (!listRecipesInTable.contains(recipe)) {
							listRecipesInTable.add(recipe);
						}
					}
				}
			}
		} else if (btnCheckIngredient.getSelection()) {
			for (String searchString : searchSplit) {
				for (IRecipe recipe : viewMain.getListAllRecipes()) {
					for (IInstruction instruction : recipe.getListInstructions()) {
						IIngredient ingredient = instruction.getIngredient();
						if (ingredient != null) {
							String name = ingredient.getName().toUpperCase();
							if (name.equals(searchString)) {
								if (!listRecipesInTable.contains(recipe)) {
									listRecipesInTable.add(recipe);
								}
							} else if (searchString.equals(name)) {
								if (!listRecipesInTable.contains(recipe)) {
									listRecipesInTable.add(recipe);
								}
							}
						}
					}
				}
			}
		} else if (btnCheckCookbook.getSelection()) {
			for (String searchString : searchSplit) {
				for (IRecipe recipe : viewMain.getListAllRecipes()) {
					String name = recipe.getCookBook().getName().toUpperCase();
					if (name.contains(searchString)) {
						if (!listRecipesInTable.contains(recipe)) {
							listRecipesInTable.add(recipe);
						}
					} else if (searchString.contains(name)) {
						if (!listRecipesInTable.contains(recipe)) {
							listRecipesInTable.add(recipe);
						}
					}
				}
			}
		}

		viewMain.setListRecipesInTable(listRecipesInTable);
		viewMain.updateTableRecipes();
	}
}