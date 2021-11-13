package logic;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XmlReader extends DefaultHandler{

    private int nombrePlanche ;
    private int client;
    //méthode redéfinie
    public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {

            if((qName.compareToIgnoreCase("client") == 0) || (qName.compareToIgnoreCase("fournisseur") == 0)) {

                System.out.println(qName + " " + attributes.getValue("id"));
                nombrePlanche = 0;

            }

            if((qName.compareToIgnoreCase("planche") == 0) || (qName.compareToIgnoreCase("panneau") == 0)) {
                System.out.println("--id " + qName + attributes.getValue("id") + " , " + "nombre " + attributes.getValue("nombre"));
                nombrePlanche++;
            }

            if(qName.compareToIgnoreCase("dim") == 0) {
                System.out.println("de longueur :" + attributes.getValue("l") + " " + "et de largeur : " + attributes.getValue("L"));
            }

    }


}
