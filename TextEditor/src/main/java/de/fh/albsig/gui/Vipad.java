package de.fh.albsig.gui;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

//import Listener.FileIO;
import de.fh.albsig.listener.SelectionAdapterHelp;
import de.fh.albsig.listener.SelectionAdapterNew;
import de.fh.albsig.listener.SelectionAdapterOpen;
import de.fh.albsig.listener.SelectionAdapterQuit;
import de.fh.albsig.listener.SelectionAdapterSave;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class Vipad {
	private Shell shell;
	private Display display;
	private Menu menuBar;
	private CoolBar cbar;
	
    private MenuItem fileTitle, editTitle, helpTitle;
    private Menu fileMenu, editMenu, helpMenu;
    private MenuItem fileNewItem, fileOpenItem, fileSaveItem, fileQuitItem;
    private MenuItem editTextColorItem;
    private MenuItem helpHelpItem;
    
    private CoolItem itemOpen, itemSave;
    private Button buttonOpen, buttonSave;
    
    private CTabFolder tabFolder;
    private Color color;
    
    private ResourceBundle messages;
	
	private void createDisplay() {
		display = new Display();
	}
	
	private void createShell() {
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1,true));
	}
	
	public void createMenu(){
		shell.setText("Vipad");
		// Quelle: https://elearning.hs-albsig.de/Customizing/global/skin/hs-albsig/images/HeaderIcon.svg
		Image small = new Image(display,"src/main/resources/hsalbsig_icon.gif");
		shell.setImage(small);
	        
	    menuBar = new Menu(shell, SWT.BAR);
	    shell.setMenuBar(menuBar);
	    
	    // Toolbar mit Items befuellen
	    fileTitle = new MenuItem(menuBar, SWT.CASCADE);
	    fileTitle.setText(messages.getString("menuFile"));
	 	fileMenu = new Menu(shell, SWT.DROP_DOWN);
	 	fileTitle.setMenu(fileMenu);
	 
	 	editTitle = new MenuItem(menuBar, SWT.CASCADE);
	 	editTitle.setText(messages.getString("menuEdit"));
	 	editMenu = new Menu(shell, SWT.DROP_DOWN);
	 	editTitle.setMenu(editMenu);
	 	
	 	helpTitle = new MenuItem(menuBar, SWT.CASCADE);
	 	helpTitle.setText(messages.getString("menuHelp"));
	 	helpMenu = new Menu(shell, SWT.DROP_DOWN);
	 	helpTitle.setMenu(helpMenu);
	 	
	 	// Item File mit Elemte befuellen
	 	fileNewItem = new MenuItem(fileMenu, SWT.PUSH);
	 	fileNewItem.setText(messages.getString("fileNewItem"));
	 	fileNewItem.setAccelerator(SWT.CTRL + 'N');
	 	
	 	fileOpenItem = new MenuItem(fileMenu, SWT.PUSH);
	 	fileOpenItem.setText(messages.getString("fileOpenItem"));
	 	fileOpenItem.setAccelerator(SWT.CTRL + 'O');
	 	
	 	fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	 	fileSaveItem.setText(messages.getString("fileSaveItem"));
	 	fileSaveItem.setAccelerator(SWT.CTRL + 'S');
	 	
	 	fileQuitItem = new MenuItem(fileMenu, SWT.PUSH);
	 	fileQuitItem.setText(messages.getString("fileQuitItem"));
	 	fileQuitItem.setAccelerator(SWT.CTRL + 'Q');
	 	
	 	// Item Edit mit Elemente bef�llen
	 	editTextColorItem = new MenuItem(editMenu, SWT.PUSH);
	 	editTextColorItem.setText(messages.getString("editColorItem"));
	 	editTextColorItem.setAccelerator(SWT.CTRL + 'C');
	 	
	 	// Item Help mit Elemente bef�llen
	 	helpHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	 	helpHelpItem.setText(messages.getString("helpItem"));
	 	helpHelpItem.setAccelerator(SWT.CTRL + 'V');
	}
	
	public void createCoolBar() {
		// Coolbar erstellen
		cbar = new CoolBar(shell,SWT.BORDER);
		
		// Button fuer oeffnen erstellen
		itemOpen = new CoolItem(cbar, SWT.NONE);
		buttonOpen = new Button(cbar, SWT.PUSH);
		// Quelle: https://icons8.com/icon/12775/opened-folder
		Image openImage = new Image(display,"src/main/resources/opened-folder.png");
		buttonOpen.setImage(openImage);
		Point size = buttonOpen.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		itemOpen.setPreferredSize(itemOpen.computeSize(size.x, size.y));
		itemOpen.setControl(buttonOpen);
		
		// Button f�r Speichern erstellen
		itemSave = new CoolItem(cbar, SWT.NONE);
		buttonSave = new Button(cbar, SWT.PUSH);
		// Quelle: https://icons8.com/icon/12141/file
		Image saveImage = new Image(display,"src/main/resources/save-close.png");
		buttonSave.setImage(saveImage);
		size = buttonSave.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		itemSave.setPreferredSize(itemSave.computeSize(size.x, size.y));
		itemSave.setControl(buttonSave);
		
		cbar.pack();
	}
	
	public void createTabFolder() {
		tabFolder = new CTabFolder(shell, SWT.VERTICAL);
		tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,1));
		tabFolder.setSimple(false);
		tabFolder.setUnselectedImageVisible(false);
		tabFolder.setUnselectedCloseVisible(false);
	}
	
	public void createTabItem(CTabFolder parent) {
		color = new Color(display,0,0,0);
		TabElement.createTab(parent, color, messages);
	}
	
	public void createListeners() {
		
		shell.addDisposeListener(new SelectionAdapterQuit(tabFolder, messages));
		
		fileQuitItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
		});
		
		helpHelpItem.addSelectionListener(new SelectionAdapterHelp(shell, messages));
		fileNewItem.addSelectionListener(new SelectionAdapterNew(tabFolder, color, messages));
		fileOpenItem.addSelectionListener(new SelectionAdapterOpen(shell, tabFolder));
		fileSaveItem.addSelectionListener(new SelectionAdapterSave(shell, tabFolder));
		
		buttonOpen.addSelectionListener(new SelectionAdapterOpen(shell, tabFolder));
		buttonSave.addSelectionListener(new SelectionAdapterSave(shell, tabFolder));
		
		editTextColorItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ColorEditor edit = new ColorEditor(shell, shell.getStyle(), messages);
				color = (Color) edit.open();
				Text text =  (Text) tabFolder.getSelection().getControl();
				text.setForeground(color);
			}
		});
	}
	
	public Vipad(ResourceBundle rb) {
		this.messages = rb;
		createDisplay();
		createShell();
		createMenu();
		createCoolBar();
		createTabFolder();
		createTabItem(tabFolder);
		createListeners();
	}
	
	public void open() {
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public ResourceBundle getMessageBundle() {
		return this.messages;
	}
		
}
