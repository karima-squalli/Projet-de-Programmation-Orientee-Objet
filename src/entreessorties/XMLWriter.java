package entreessorties;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

class XMLWriter implements Writer {

    public void writeDecoupe(int doc, ArrayList<String>data, FileOutputStream out, int idFournisseur, String panneau, int idClient, String planche, String x, String y) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        if (doc == 0) // Start of document
            writer.writeStartElement(data.get(0));

        writer.writeStartElement(data.get(1));

        writer.writeEmptyElement(data.get(2));
        writer.writeAttribute(data.get(3), String.valueOf(idClient));
        writer.writeAttribute(data.get(4), planche);

        writer.writeEmptyElement(data.get(5));
        writer.writeAttribute(data.get(3), String.valueOf(idFournisseur));
        writer.writeAttribute(data.get(6), panneau);

        writer.writeEmptyElement(data.get(7));
        writer.writeAttribute(data.get(8), x);
        writer.writeAttribute(data.get(9), y);

        writer.writeEndElement();

        if (doc == 1) // End of document
            writer.writeStartElement(data.get(10));
        writer.flush();
        writer.close();
    }
}