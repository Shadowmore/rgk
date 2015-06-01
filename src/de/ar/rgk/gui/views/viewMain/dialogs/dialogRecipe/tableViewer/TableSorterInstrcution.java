package de.ar.rgk.gui.views.viewMain.dialogs.dialogRecipe.tableViewer;

import org.eclipse.jface.viewers.Viewer;

import de.ar.rgk.gui.views.abstracts.AbstractTableSorter;
import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;

public class TableSorterInstrcution extends AbstractTableSorter {

	@Override
	public int compare(Viewer viewer, Object s1, Object s2) {
		IInstruction instruction1 = (IInstruction) s1;
		IInstruction instruction2 = (IInstruction) s2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = compareDoubleValues(instruction1.getAmount(), instruction2.getAmount());
			break;
		case 1:
			rc = compareStringValues(instruction1.getUnit(), instruction2.getUnit());
			break;
		case 2:
			IIngredient ingredient1 = instruction1.getIngredient();
			IIngredient ingredient2 = instruction2.getIngredient();
			String i1 = "";
			String i2 = "";
			if (ingredient1 != null) {
				i1 = ingredient1.getName();
			}
			if (ingredient2 != null) {
				i2 = ingredient2.getName();
			}
			rc = compareStringValues(i1, i2);
			break;
		case 3:
			rc = compareStringValues(instruction1.getText(), instruction2.getText());
			break;
		case 4:
			rc = compareStringValues(instruction1.getIngredientText(), instruction2.getIngredientText());
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