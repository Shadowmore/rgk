package de.ar.rgk.gui.views.abstracts;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public abstract class AbstractTableSorter extends ViewerSorter {

	protected int propertyIndex;
	protected static final int ASCENDING = 0;
	protected static final int DESCENDING = 1;

	protected int direction;

	public AbstractTableSorter() {
		this.propertyIndex = 0;
		direction = ASCENDING;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	protected int compareStringValues(String s1, String s2) {
		return s1.toUpperCase().compareTo(s2.toUpperCase());
	}

	protected int compareIntValues(int i1, int i2) {
		if (i1 > i2) {
			return 1;
		} else if (i1 < i2) {
			return -1;
		} else {
			return 0;
		}
	}

	protected int compareDoubleValues(double d1, double d2) {
		if (d1 > d2) {
			return 1;
		} else if (d1 < d2) {
			return -1;
		} else {
			return 0;
		}
	}

	public abstract int compare(Viewer viewer, Object s1, Object s2);
}