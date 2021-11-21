package interfaces;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import logic.*;


public class XmlReader implements Reader {


    public ArrayList<Client> ReadClientsFrom(String fileNameClient) {
        FileInputStream file;
        ArrayList<Client> clients = new ArrayList<>();
        try {
            file = new FileInputStream(fileNameClient);
            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlInFact.createXMLStreamReader(file);

            Client client = new Client();
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();
                    if (name == "client" || name == "clients") {
                        if (name == "clients") reader.nextTag(); //On regarde le premier client
                        if (name == "client") {
                            clients.add(client);
                        }
                        client = new Client(Integer.parseInt(reader.getAttributeValue(0)));
                        reader.nextTag(); //On regarde la première commande de planche demandée

                    }
                    Planche planche = new Planche(Integer.parseInt(reader.getAttributeValue(0)), Integer.parseInt(reader.getAttributeValue(1)));
                    Date date = new Date(reader.getAttributeValue(2));
                    Prix prix = new Prix(reader.getAttributeValue(3));
                    reader.nextTag(); // On regarde les dimensions
                    Dimensions dimensions = new Dimensions(reader.getAttributeValue(0),reader.getAttributeValue(1));
                    planche.addValidable(date);
                    planche.addValidable(prix);
                    planche.addValidable(dimensions);
                    client.ajouterPlanche(planche);
                }
            }
            clients.add(client);

        } catch (IOException exc) {

            System.out.print("Erreur IO: " + exc);
        } catch (XMLStreamException exc) {
            System.out.print("Erreur XML: " + exc);
        }
        return clients;
    }


    public ArrayList<Fournisseur> ReadFournisseursFrom(String fileNameFournisseur) {
        FileInputStream file;
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        try {
            file = new FileInputStream(fileNameFournisseur);
            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlInFact.createXMLStreamReader(file);

            Fournisseur fournisseur = new Fournisseur();
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();
                    if (name == "fournisseur" || name == "fournisseurs") {
                        if (name == "fournisseurs")
                            reader.nextTag(); //On regarde le premier client
                        if (name == "fournisseur")
                            fournisseurs.add(fournisseur); // On ajoute le premier fournisseur à la liste des fournisseurs
                        fournisseur = new Fournisseur(Integer.parseInt(reader.getAttributeValue(0)));
                        reader.nextTag(); //On regarde la première commande de planche demandée
                    }
                    Panneau panneau = new Panneau(Integer.parseInt(reader.getAttributeValue(0)), Integer.parseInt(reader.getAttributeValue(1)));
                    Date date = new Date(reader.getAttributeValue(2));
                    Prix prix = new Prix(reader.getAttributeValue(3));
                    reader.nextTag(); // On regarde les dimensions
                    Dimensions dimensions = new Dimensions(reader.getAttributeValue(0),reader.getAttributeValue(1));
                    panneau.addValidable(date);
                    panneau.addValidable(prix);
                    panneau.addValidable(dimensions);
                    fournisseur.ajouterPanneau(panneau);
                    }
            }
            fournisseurs.add(fournisseur);

        } catch (IOException exc) {

            System.out.print("Erreur IO: " + exc);
        } catch (XMLStreamException exc) {
            System.out.print("Erreur XML: " + exc);
        }
        return fournisseurs;
        //comment traiter les exceptions si on est en train de remplir la liste directement? exple bli
        // si je change les attribus de client et fournisseur je devrais tt changer alors
    }
}