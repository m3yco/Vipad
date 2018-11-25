package de.fh.albsig.gui;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class TabElement {
	
	public static void createTab(CTabFolder folder, Color color, ResourceBundle rb) {
		CTabItem tabItem = new CTabItem(folder, SWT.NONE);
		tabItem.setText(rb.getString("newTxt"));
		tabItem.setControl(openText(folder, color));
		folder.setSelection(tabItem);
	}
	
	//Color dazu!!!
	public static void createTab(CTabFolder folder, String name, String content, Color c) {
		CTabItem tabItem = new CTabItem(folder, SWT.NONE);
		int index = name.lastIndexOf("\\");
		String n = name.substring(index + 1);
		tabItem.setText(n);
		tabItem.setControl(openText(folder,content,c));
		folder.setSelection(tabItem);
	}
	
	public static Text openText(CTabFolder folder, Color color) {
		Text textField = new Text(folder, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		GridData gdataText = new GridData(SWT.FILL,SWT.FILL,true,true,2,1);
		textField.setLayoutData(gdataText);
		textField.setForeground(color);
		return textField;
	}
	
	public static Text openText(CTabFolder folder, String content, Color c) {
		Text textField = new Text(folder, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		GridData gdataText = new GridData(SWT.FILL,SWT.FILL,true,true,2,1);
		textField.setLayoutData(gdataText);
		textField.setText(content);
		textField.setForeground(c);
		return textField;
	}
	
	public static String getFileName(CTabFolder folder) {
		return folder.getSelection().getText();
	}
}
