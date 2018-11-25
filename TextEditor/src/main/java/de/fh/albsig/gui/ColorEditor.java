package de.fh.albsig.gui;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

public class ColorEditor extends Dialog{

	private Color returnColor;
	private Label colorLabel;
	private Label textLabel;
	private Label [] colorNames;
	private Group gSpinner;
	private Spinner [] spinBoxRGB;
	private Button buttonOK, buttonCancel;
	private ResourceBundle messages;
	
	public ColorEditor(Shell parent, int style, ResourceBundle rb) {
		super(parent, style);
		this.messages = rb;
	}
		public Object open() {
			Shell dialogShell = new Shell(getParent());
			//dialogShell.setSize(330, 250);
			createContent(dialogShell);
			dialogShell.pack();
			dialogShell.open();
			Display display = getParent().getDisplay();
			
			while(!dialogShell.isDisposed()) {
				if(!display.readAndDispatch()) {
					display.sleep();
				}
			}
			return returnColor;
		}
		
		public void createContent(Shell shell) {
			//Shell Layout			
			GridLayout layout = new GridLayout(2,true);
			shell.setLayout(layout);
			int [] rgbvalues = new int[3];
			
			shell.setText(messages.getString("colorEditor"));
			
			//Label Text
			textLabel = new Label(shell,SWT.LEFT);
			textLabel.setText(messages.getString("colorTextLabel"));
			GridData labelData = new GridData(SWT.FILL,SWT.FILL,true,true,2,1);
			textLabel.setLayoutData(labelData);
			
			//Label Color
			colorLabel = new Label(shell, SWT.CENTER);
			colorLabel.setBackground(new Color(shell.getDisplay(),0,0,0));
			// Parameter 1 & 2: Ausrichtungsverhalten innerhalt der Zelle
			// Parameter 3 & 4: Mitwachsen mit Zelle in x/y Richtung
			// Parameter 5 & 6: Ausdehnung innerhalb der Zelle(n)
			GridData gdata = new GridData(SWT.FILL,SWT.FILL,true,true,1,2);
			colorLabel.setLayoutData(gdata);
			
			//Spinner RGB
			gSpinner = new Group(shell,SWT.NO_RADIO_GROUP | SWT.SHADOW_ETCHED_IN);
			// Verhalten innerhalb der Zelle innerhalb der Shell
			gSpinner.setText(messages.getString("colorSpinner"));
			GridData gdataGroup = new GridData(SWT.FILL,SWT.FILL,true,true,1,2);
			gSpinner.setLayoutData(gdataGroup);
			
			// Verhalten der Group als Parent weiterer Widgets(hier: Spinner)
			GridLayout layoutGroup = new GridLayout(2,true);
			layoutGroup.horizontalSpacing = 5;
			gSpinner.setLayout(layoutGroup);
			
			// Kindwidgets erzeugen: Platz f�r 3 Spinner
			spinBoxRGB = new Spinner[3];
			
			// Spinner Labels f�r die Farbwertnamen
			colorNames = new Label[3];
			
			for(int i =0; i<spinBoxRGB.length; i++) {
				colorNames[i] = new Label(gSpinner, SWT.CENTER);
				spinBoxRGB[i] = new Spinner(gSpinner, SWT.NONE);
				spinBoxRGB[i].setMinimum(0);
				spinBoxRGB[i].setMaximum(255);
			}
			colorNames[0].setText(messages.getString("red"));
			colorNames[1].setText(messages.getString("green"));
			colorNames[2].setText(messages.getString("blue"));
			
			//Buttons
			GridData buttonData = new GridData(SWT.FILL,SWT.FILL,true,true,1,1);
			buttonOK = new Button(shell, SWT.PUSH);
			buttonOK.setText(messages.getString("colorButtonOk"));
			buttonOK.setLayoutData(buttonData);
			
			buttonCancel = new Button(shell, SWT.PUSH);
			buttonCancel.setText(messages.getString("colorButtonCancel"));
			buttonCancel.setLayoutData(buttonData);
			
			//Anonyme Modify Listener f�r die Farbauswahl
			for(int i=0; i<spinBoxRGB.length; i++) {
				spinBoxRGB[i].addModifyListener(new ModifyListener() {
					
					@Override
					public void modifyText(ModifyEvent arg0) {
						// Display kann von jedem g�ltigen Widget abgefragt werden.
						Display display = colorLabel.getDisplay();
						Color nColor = null;
						// Array der Farbwerte
						for(int i=0; i<rgbvalues.length; i++) {
							try {
							rgbvalues[i] = Integer.parseInt(spinBoxRGB[i].getText());
							nColor = new Color(display,rgbvalues[0],rgbvalues[1],rgbvalues[2]);
							}
							catch(NumberFormatException e) {
								rgbvalues[i] = 0;
								spinBoxRGB[i].setSelection(0);
								nColor = new Color(display,rgbvalues[0],rgbvalues[1],rgbvalues[2]);
							}
							catch(IllegalArgumentException e) {
								rgbvalues[i] = 255;
								spinBoxRGB[i].setSelection(255);
								nColor = new Color(display,rgbvalues[0],rgbvalues[1],rgbvalues[2]);
							}
						}
						Color old = colorLabel.getBackground();
						old.dispose();
						colorLabel.setBackground(nColor);
					}
					
				});
			}
			
			//Listener Buttons
			buttonOK.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					returnColor = new Color(shell.getDisplay(),rgbvalues[0], rgbvalues[1], rgbvalues[2]);
					shell.dispose();
				}
				
			});
			
			buttonCancel.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					returnColor = null;
					shell.dispose();
				}
				
			});
		}
		
}


