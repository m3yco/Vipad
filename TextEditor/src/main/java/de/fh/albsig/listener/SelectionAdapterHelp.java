package de.fh.albsig.listener;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SelectionAdapterHelp extends SelectionAdapter {
	private Shell shell;
	private ResourceBundle messages;
	
	public SelectionAdapterHelp(Shell shell, ResourceBundle rb) {
		this.messages = rb;
		this.shell = shell;
	}
	
	public void widgetSelected(SelectionEvent e) {
		MessageBox info = new MessageBox(shell,SWT.ICON_INFORMATION | SWT.OK);
		info.setMessage(messages.getString("helpInfo"));
		info.open();
	}
}
