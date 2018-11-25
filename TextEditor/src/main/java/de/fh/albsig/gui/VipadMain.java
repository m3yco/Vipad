package de.fh.albsig.gui;

import java.util.Locale;
import java.util.ResourceBundle;

public class VipadMain {

	public static void main(String[] args) {
		Locale locale;
		ResourceBundle messages;
		String language = "en"; 
		String country = "US";
		if (args.length > 0) {
			language = new String(args[0]);
			country = new String(args[1]);
		}
		locale = new Locale(language, country);
		messages = ResourceBundle.getBundle("MessageBundle", locale);
		Vipad gui = new Vipad(messages);
		gui.open();
	}

}
