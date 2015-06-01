package de.ar.rgk.gui.views.viewMain.dialogs.dialogRecipe.tableViewer;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import de.ar.rgk.gui.views.abstracts.AbstractTableLabelProvider;
import de.ar.rgk.model.interfaces.IIngredient;
import de.ar.rgk.model.interfaces.IInstruction;

public class TableLableProviderDialogEditRecipe extends AbstractTableLabelProvider {

	private static final String COLUMNS_NAMES[] = { "Text für Spezialzutat", "Menge", "Einheit", "Zutat", "Text" };
	private static final int COLUMNS_SIZES[] = { 120, 50, 50, 150, 350 };

	public TableLableProviderDialogEditRecipe(TableViewer tableViewerInstructions) {
		super(tableViewerInstructions, null);

		makeTableColumns(COLUMNS_NAMES, COLUMNS_SIZES);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof IInstruction) {
			IInstruction instruction = (IInstruction) element;
			switch (columnIndex) {
			case 0: {
				return instruction.getIngredientText();
			}
			case 1: {
				if (instruction.getAmount() == 0.0) {
					return "";
				} else {
					return instruction.getAmount() + "";
				}
			}
			case 2: {
				return instruction.getUnit();
			}
			case 3: {
				IIngredient ingredient = instruction.getIngredient();
				if (ingredient == null) {
					return "";
				} else {
					return ingredient.getName();
				}
			}
			case 4: {
				return instruction.getText();
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