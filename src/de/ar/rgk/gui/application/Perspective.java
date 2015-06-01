package de.ar.rgk.gui.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public static final String ID = "de.ar.rgk.gui.application.Perspective";

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
	}
}