package de.ar.rgk.gui.views.abstracts;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public abstract class AbstractTableLabelProvider extends LabelProvider implements IStructuredContentProvider, ITableLabelProvider {

	private TableViewer tableViewer;
	private AbstractTableSorter sorter;
	private Table table;

	public AbstractTableLabelProvider(TableViewer tableViewer, AbstractTableSorter sorter) {
		this(tableViewer);
		this.sorter = sorter;

		tableViewer.setSorter(sorter);
	}

	public AbstractTableLabelProvider(TableViewer tableViewer) {
		this.tableViewer = tableViewer;

		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.tableViewer.setLabelProvider(this);
		this.tableViewer.setContentProvider(this);
	}

	protected void makeTableColumns(String[] colArrNames, int[] colArrSizes) {
		for (int i = 0; i < colArrNames.length; i++) {
			addTableColumn(colArrNames[i], colArrSizes[i], i);
		}
	}

	protected void addTableColumn(String name, int width, int i) {
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText(name);
		column.setWidth(width);
		column.setMoveable(true);
		column.setResizable(true);
		if (sorter != null) {
			column.addSelectionListener(createSelectionListenerSorter(column, i));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			List list = (List) inputElement;
			return list.toArray();
		}
		return null;
	}

	private SelectionAdapter createSelectionListenerSorter(final TableColumn col, final int index) {

		return new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				sorter.setColumn(index);
				int dir = tableViewer.getTable().getSortDirection();
				if (table.getSortColumn() == col) {
					dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
				} else {
					dir = SWT.DOWN;
				}
				tableViewer.getTable().setSortDirection(dir);
				tableViewer.getTable().setSortColumn(col);
				tableViewer.refresh();
			}
		};
	}
}