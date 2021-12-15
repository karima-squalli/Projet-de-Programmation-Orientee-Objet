package entreessorties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import logic.*;


class XMLReader implements Reader {



    @Override
    public ArrayList<Generable> readGenerable(ArrayList<String> data, String fileName, Factory f1, Factory f2) {
        FileInputStream file;
        ArrayList<Generable> generables = new ArrayList<>();
        try {
            file = new FileInputStream(fileName);
            XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
            XMLStreamReader reader = xmlInFact.createXMLStreamReader(file);

            Generable generable = f1.generateGenerable();
            int id = -1;
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();

                    if (Objects.equals(name, data.get(0)) || Objects.equals(name, data.get(1))){
                        if (Objects.equals(name, data.get(0))) reader.nextTag();
                        if (Objects.equals(name, data.get(1))) generables.add(generable);
                        id = Integer.parseInt(reader.getAttributeValue(0));
                        generable = f1.generateGenerable(id);
                        reader.nextTag();
                    }

                    ArrayList<Validable> listV = new ArrayList<>();

                    int number = Integer.parseInt(reader.getAttributeValue(1));
                    Generable g = f2.generateGenerable(Integer.parseInt(reader.getAttributeValue(0)), number, id);

                    Date date = new Date(reader.getAttributeValue(2));
                    Price price = new Price(reader.getAttributeValue(3));
                    reader.nextTag(); // To get the dimensions
                    listV.add(date);
                    listV.add(price);
                    for (int i = 0; i<number; i++) {
                        Dimensions dimensions = new Dimensions(reader.getAttributeValue(0), reader.getAttributeValue(1));
                        listV.add(dimensions);
                    }
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

}