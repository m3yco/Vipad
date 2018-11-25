package de.fh.albsig.listener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.fh.albsig.xml.XMLwrite;

public class SelectionAdapterSave extends SelectionAdapter{
	private Shell shell;
	private CTabFolder parent;
	
	public SelectionAdapterSave(Shell shell, CTabFolder c) {
		this.shell = shell;
		this.parent = c;
	}
	
	public void widgetSelected(SelectionEvent e) {
		CTabItem item = parent.getSelection();
		Text text =  (Text) item.getControl();
		FileDialog fileSave = new FileDialog(shell, SWT.SAVE);
		try {
			String fileName = fileSave.open();
			if(fileName != null) {
				Color c = text.getForeground();
				
				//FileIO.write(fileName, text.getText());
				//item.setText(fileName);
				
				//XML Parster Write!
				XMLwrite writer = new XMLwrite();
				writer.writeDown(fileName, text.getText(), c);
				int index = fileName.lastIndexOf("\\");
				String n = fileName.substring(index + 1);
				item.setText(n);
			}
		}
		catch (NullPointerException ex) {	
			// wenn kein Name oder Datei gew�hlt worden ist!
		}
		
		
	}
}
