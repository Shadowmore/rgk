package de.ar.rgk.gui.views.viewMain.tableViewer;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import de.ar.rgk.gui.views.abstracts.AbstractTableLabelProvider;
import de.ar.rgk.model.interfaces.IRecipe;

public class TableLableProviderViewMain extends AbstractTableLabelProvider {

	private static final String COLUMNS_NAMES[] = { "Rezeptname", "Kochbuch", "Seite in Kochbuch" };
	private static final int COLUMNS_SIZES[] = { 200, 100, 110 };

	public TableLableProviderViewMain(TableViewer tableViewerRecipes) {
		super(tableViewerRecipes, new TableSorterRecipe());

		makeTableColumns(COLUMNS_NAMES, COLUMNS_SIZES);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof IRecipe) {
			IRecipe recipe = (IRecipe) element;
			switch (columnIndex) {
			case 0: {
				return recipe.getName();
			}
			case 1: {
				return recipe.getCookBook().getName();
			}
			case 2: {
				return recipe.getPageInCookBook() + "";
			}
			default: {
				throw new RuntimeException("Column not defined: " + 1);
			}
			}
		} else {
			throw new RuntimeException("Object Typ not defined: " + element.getClass());
		}
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
}