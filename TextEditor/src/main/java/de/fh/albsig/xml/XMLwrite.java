package de.fh.albsig.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.graphics.Color;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLwrite {
	  
	  // Schreiben der ArrayList in ein 
	  // CrewMember-XML-File
	  public void writeDown(String xmlFile, String xmlText,
	                        Color color) {
	    
	    try {
	      
	      /////////////////////////////////////
	      //Document soll XML-Inhalt als
	      // Baumstruktur (DOM-Baum) enthalten
	      /////////////////////////////////////
	      
	      // 1. Factory zur Erzeugung eines 
	      // DocumentBuilder erzeugen
	      DocumentBuilderFactory editorFactory = 
	        DocumentBuilderFactory.newInstance();
	      
	      // 2. DocumentBuilder zur Erzeugung 
	      // eines XML-Dokuments erzeugen
	      DocumentBuilder textBuilder = 
	    		  editorFactory.newDocumentBuilder();
	      
	      // 3. XML-Datei einlesen und als 
	      // DOM-Objekt verwalten
	      Document textDoc  = 
	        textBuilder.newDocument();
	      
	      // 4. Wurzelelement <crew> ... </crew>
	      // erzeugen ...
	      Element textRootElement = 
	        textDoc.createElement("tab");
	      
	      // 5. ... und in DOM-Baum einhaengen
	      textDoc.appendChild(textRootElement);
	      
	      // 6. Schleife ueber CrewMember-ArrayList:
	      // Haenge CrewMember-Objekte als
	      // <crewmember .../> - Eintraege
	      // in DOM-Baum
	         
	        // 6.1 <crewmember .../>-Element erzeugen
	        Element memberChildElement = 
	            textDoc.createElement("color");
	        
	        // 6.2 <crewmember .../> in Wurzelelement ein-
	        // haengen
	        textRootElement.appendChild(
	            memberChildElement);
	        
	        // 6.3 Attribute des <crewmember .../>-
	        // Elements erzeugen und einhaengen
	        Attr memberAttribute = 
	          textDoc.createAttribute("colorRed");
	        memberAttribute.setValue(
	            Integer.toString(color.getRed()));
	        memberChildElement.setAttributeNode(
	            memberAttribute);
	        
	        memberAttribute = 
	          textDoc.createAttribute("colorGreen");
	        memberAttribute.setValue(
	        		Integer.toString(color.getGreen()));
	        memberChildElement.setAttributeNode(
	          memberAttribute);
	        
	        memberAttribute = 
	          textDoc.createAttribute("colorBlue");
	        memberAttribute.setValue(
	        		Integer.toString(color.getBlue()));
	        memberChildElement.setAttributeNode(
	          memberAttribute);
	        
	        memberAttribute = 
	          textDoc.createAttribute("text");
	        memberAttribute.setValue(
	        		xmlText);
	        memberChildElement.setAttributeNode(
	          memberAttribute);
	        
	      
	      /////////////////////////////////////
	      // Transformer soll DOM-Baum in XML-Datei
	      // umwandeln 
	      /////////////////////////////////////
	      
	      // 7. Factory zur Erzeugung eines 
	      // Transformers erzeugen
	      TransformerFactory textTransformerFactory =
	        TransformerFactory.newInstance();
	      
	      // 8. Transformer erzeugen
	      Transformer textTransformer =
	        textTransformerFactory.newTransformer();
	      
	      // 9. DomSource erzeugen. Sie bereitet den
	      // DOM-Baum fuer das Schreiben auf einen Stream
	      // auf
	      DOMSource crewSource = new DOMSource(textDoc);
	      
	      // 10. Stream fuer das Schreiben der XML-
	      // Datei erzeugen.
	      StreamResult crewResult =
	           new StreamResult(xmlFile);
	      
	      // 11. DOM-Baum auf Stream (hier: auf Datei)
	      // rausschreiben
	      textTransformer.transform(
	          crewSource, crewResult); 
	      
	    } catch (ParserConfigurationException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (TransformerConfigurationException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (TransformerException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } // end catch
	  } // end method
	} // end class
