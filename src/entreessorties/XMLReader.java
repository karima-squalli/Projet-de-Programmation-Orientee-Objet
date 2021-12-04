package entreessorties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import logic.*;


class XMLReader implements Reader {


//    public ArrayList<Client> ReadClients(String fileNameClient) {
//        FileInputStream file;
//        ArrayList<Client> clients = new ArrayList<>();
//        try {
//            file = new FileInputStream(fileNameClient);
//            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
//            XMLStreamReader reader = xmlInFact.createXMLStreamReader(file);
//
//            Client client = new Client();
//            while (reader.hasNext()) {
//                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
//                    String name = reader.getLocalName();
//                    if (name == "client" || name == "clients") {
//                        if (name == "clients") reader.nextTag(); //On regarde le premier client
//                        if (name == "client") {
//                            clients.add(client);
//                        }
//                        client = new Client(Integer.parseInt(reader.getAttributeValue(0)));
//                        reader.nextTag(); //On regarde la première commande de planche demandée
//
//                    }
//                    Planche planche = new Planche(Integer.parseInt(reader.getAttributeValue(0)), Integer.parseInt(reader.getAttributeValue(1)));
//                    Date date = new Date(reader.getAttributeValue(2));
//                    Prix prix = new Prix(reader.getAttributeValue(3));
//                    reader.nextTag(); // On regarde les dimensions
//                    Dimensions dimensions = new Dimensions(reader.getAttributeValue(0),reader.getAttributeValue(1));
//                    planche.addValidable(date);
//                    planche.addValidable(prix);
//                    planche.addValidable(dimensions);
//                    client.ajouterPlanche(planche);
//                }
//            }
//            clients.add(client);
//
//        } catch (IOException exc) {
//
//            System.out.print("Erreur IO: " + exc);
//        } catch (XMLStreamException exc) {
//            System.out.print("Erreur XML: " + exc);
//        }
//        return clients;
//    }


//    public Reader getReader() {
//        Reader r = new XMLReader();
//        return r;
//    }

    @Override
    public ArrayList<Generable> readGenerable(ArrayList<String> data, String fileName, Factory f1, Factory f2) {
        FileInputStream file;
        ArrayList<Generable> generables = new ArrayList<>();
        try {
            file = new FileInputStream(fileName);
            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlInFact.createXMLStreamReader(file);

            Generable generable = f1.generateGenerable();
            boolean start = true;
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();
                    if (start) {
                        reader.nextTag();
                        start = false;
                    }
                    if (name == "fournisseur" || name == "fournisseurs" || name == "clients" || name == "client") {
                        if (name == data.get(0) || name == data.get(1))
                            generables.add(generable);
                        generable = f1.generateGenerable(Integer.parseInt(reader.getAttributeValue(0)));
                        reader.nextTag();
                    }

                    ArrayList<Validable> listV = new ArrayList<>();

                    Generable g = f2.generateGenerable(Integer.parseInt(reader.getAttributeValue(0)), Integer.parseInt(reader.getAttributeValue(1)));
                    Date date = new Date(reader.getAttributeValue(2));
                    Prix prix = new Prix(reader.getAttributeValue(3));
                    reader.nextTag(); // On regarde les dimensions
                    Dimensions dimensions = new Dimensions(reader.getAttributeValue(0),reader.getAttributeValue(1));
                    listV.add(date);
                    listV.add(prix);
                    listV.add(dimensions);
                    g.updateGenerable(listV);
                    generable.add(g);
                }
            }
            generables.add(generable);

        } catch (IOException exc) {

            System.out.print("Erreur IO: " + exc);
        } catch (XMLStreamException exc) {
            System.out.print("Erreur XML: " + exc);
        }
        return generables;
    }

//    public ArrayList<Fournisseur> ReadFournisseurs(String fileNameFournisseur) {
//        FileInputStream file;
//        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
//        try {
//            file = new FileInputStream(fileNameFournisseur);
//            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
//            XMLStreamReader reader = xmlInFact.createXMLStreamReader(file);
//
//            Fournisseur fournisseur = new Fournisseur();
//            while (reader.hasNext()) {
//                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
//                    String name = reader.getLocalName();
//                    if (name == "fournisseur" || name == "fournisseurs") {
//                        if (name == "fournisseurs")
//                            reader.nextTag(); //On regarde le premier client
//                        if (name == "fournisseur")
//                            fournisseurs.add(fournisseur); // On ajoute le premier fournisseur à la liste des fournisseurs
//                        fournisseur = new Fournisseur(Integer.parseInt(reader.getAttributeValue(0)));
//                        reader.nextTag(); //On regarde la première commande de planche demandée
//                    }
//                    Panneau panneau = new Panneau(Integer.parseInt(reader.getAttributeValue(0)), Integer.parseInt(reader.getAttributeValue(1)));
//                    Date date = new Date(reader.getAttributeValue(2));
//                    Prix prix = new Prix(reader.getAttributeValue(3));
//                    reader.nextTag(); // On regarde les dimensions
//                    Dimensions dimensions = new Dimensions(reader.getAttributeValue(0),reader.getAttributeValue(1));
//                    panneau.addValidable(date);
//                    panneau.addValidable(prix);
//                    panneau.addValidable(dimensions);
//                    fournisseur.ajouterPanneau(panneau);
//                    }
//            }
//            fournisseurs.add(fournisseur);
//
//        } catch (IOException exc) {
//
//            System.out.print("Erreur IO: " + exc);
//        } catch (XMLStreamException exc) {
//            System.out.print("Erreur XML: " + exc);
//        }
//        return fournisseurs;
//    }
}