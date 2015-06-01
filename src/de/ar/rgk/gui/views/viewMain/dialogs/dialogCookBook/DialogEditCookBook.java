package de.ar.rgk.gui.views.viewMain.dialogs.dialogCookBook;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.ar.rgk.gui.views.abstracts.AbstractDialog;
import de.ar.rgk.model.implementations.CookBook;
import de.ar.rgk.model.interfaces.ICookBook;

public class DialogEditCookBook extends AbstractDialog {

	private CookBook cookBook;
	private Text txtCookBookName;

	public DialogEditCookBook(Shell parentShell, ICookBook cookBook, String dialogTitle) {
		super(parentShell, dialogTitle);

		this.cookBook = (CookBook) cookBook;
	}

	@Override
	protected void createContent(Composite compMain) {

		Composite compTxts = new Composite(compMain, SWT.NONE);
		compTxts.setLayout(new GridLayout(2, false));
		compTxts.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label lblName = new Label(compTxts, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		lblName.setText("Name des Kochbuchs");

		txtCookBookName = new Text(compTxts, SWT.SINGLE | SWT.BORDER);
		txtCookBookName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		txtCookBookName.setText(cookBook.getName());
	}

	@Override
	protected String plausi() {
		String text = txtCookBookName.getText();
		if (text == null || text.equals("")) {
			return "Das Kochbuch besitzt noch keinen Namen";
		}
		return null;
	}

	@Override
	protected void writeDataFromWidgetsIntoObject() {
		String text = txtCookBookName.getText();
		cookBook.setName(text);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(400, 300);
	}
}