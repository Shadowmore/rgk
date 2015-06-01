package de.ar.rgk.gui.views.viewMain.tableViewer;

import org.eclipse.jface.viewers.Viewer;

import de.ar.rgk.gui.views.abstracts.AbstractTableSorter;
import de.ar.rgk.model.interfaces.IRecipe;

public class TableSorterRecipe extends AbstractTableSorter {

	@Override
	public int compare(Viewer viewer, Object s1, Object s2) {
		IRecipe recipe1 = (IRecipe) s1;
		IRecipe recipe2 = (IRecipe) s2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = compareStringValues(recipe1.getName(), recipe2.getName());
			break;
		case 1:
			rc = compareStringValues(recipe1.getCookBook().getName(), recipe2.getCookBook().getName());
			break;
		case 2:
			rc = compareIntValues(recipe1.getPageInCookBook(), recipe2.getPageInCookBook());
			break;
		default:
			rc = 0;
		}

		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}
}