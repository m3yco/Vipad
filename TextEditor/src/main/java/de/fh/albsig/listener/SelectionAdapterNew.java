package de.fh.albsig.listener;
import java.util.ResourceBundle;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;

import de.fh.albsig.gui.TabElement;

public class SelectionAdapterNew  extends SelectionAdapter{
	private CTabFolder parent;
	private Color color;
	private ResourceBundle messages;

	public SelectionAdapterNew(CTabFolder p, Color c, ResourceBundle rb) {
		this.parent = p;
		this.color = c;
		this.messages = rb;
	}
	
	public void widgetSelected(SelectionEvent e) {
		TabElement.createTab(parent, color, messages);
	}
}
