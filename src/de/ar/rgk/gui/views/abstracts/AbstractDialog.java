package de.ar.rgk.gui.views.abstracts;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractDialog extends Dialog {

	private String dialogTitle;

	protected AbstractDialog(Shell parentShell, String dialogTitle) {
		super(parentShell);
		this.dialogTitle = dialogTitle;
	}

	@Override
	protected Control createButtonBar(Composite parent) {

		Composite compButtons = new Composite(parent, SWT.NONE);
		compButtons.setLayout(new GridLayout(2, true));
		compButtons.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false));

		Button btnOk = new Button(compButtons, SWT.NONE);
		btnOk.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnOk.setText("OK");
		btnOk.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String plausi = plausi();
				if (plausi != null) {
					MessageDialog dialog = new MessageDialog(getShell(), "Fehler", null, plausi, MessageDialog.ERROR, new String[] { "OK" }, 0);
					dialog.open();
				} else {
					writeDataFromWidgetsIntoObject();
					setReturnCode(OK);
					close();
				}
			}
		});

		Button btnCancel = new Button(compButtons, SWT.NONE);
		btnCancel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		btnCancel.setText("Abbrechen");
		btnCancel.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setReturnCode(CANCEL);
				close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		return compButtons;
	}

	protected abstract void createContent(Composite compMain);

	@Override
	protected Control createContents(Composite parent) {

		Composite compMain = new Composite(parent, SWT.NONE);
		compMain.setLayout(new GridLayout(1, false));
		compMain.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createContent(compMain);
		return createButtonBar(parent);
	}

	protected abstract String plausi();

	protected abstract void writeDataFromWidgetsIntoObject();

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(dialogTitle);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
}