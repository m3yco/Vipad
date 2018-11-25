package de.fh.albsig.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLread {

	// Referenzen eingehaengt werden
	private String text;
	private Color color;
	
	// Konstruktor erledigt das Einlesen der
	public XMLread(String xmlfile, Shell shell) {

		try {
			// Factory zur Erzeugung eines
			// DocumentBuilder erzeugen
			DocumentBuilderFactory textFactory = DocumentBuilderFactory.newInstance();

			// DocumentBuilder zur Erzeugung
			// eines XML-Dokuments erzeugen
			DocumentBuilder crewBuilder = textFactory.newDocumentBuilder();

			// XML-Datei einlesen und als
			// DOM-Objekt verwalten
			Document textDocument = crewBuilder.parse(xmlfile);

			// Eleminiere ggf. leere Elemente
			// aus Wurzelelement
			Element el = textDocument.getDocumentElement();
			el.normalize();

			// Ebene der <textmember .../> -Elemente als
			// Liste ermitteln
			NodeList textnodes = textDocument.getElementsByTagName("color");

			// Liste der <textmember .../>-Elemente
			// durchlaufen

			// Naechstes <textmember ../> - Element abfragen
			Node member = textnodes.item(0);

			// Wenn es sich wirklich um einen Element-Knoten
			// handelt:
			//if (member.getNodeType() == Node.ELEMENT_NODE) {
				// Attribute Vorname, Nachname, ID und
				// Rolle abfragen
				Element eMember = (Element) member;
				int red = Integer.valueOf(eMember.getAttribute("colorRed"));
				int green = Integer.valueOf(eMember.getAttribute("colorGreen"));
				int blue = Integer.valueOf(eMember.getAttribute("colorBlue"));
				this.text = eMember.getAttribute("text");

				this.color = new Color(shell.getDisplay(),red , green, blue);
			//} // end if
		} // end try
		catch (Exception e) {
			e.printStackTrace();
		} // end catch
	} // end constructor

	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}

	public String getText() {
		return this.text;
	}
	
	public void setText(String t) {
		this.text = t;
	}
}
