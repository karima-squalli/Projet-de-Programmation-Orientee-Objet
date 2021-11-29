package entreessorties;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;

class XMLWriter implements Writer {


    public void startDocDecoupe(FileOutputStream out) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        writer.writeStartElement("decoupes>");
        //writer.flush();
        writer.close();
    }

    public void writeDecoupe(FileOutputStream out, int idFournisseur, int idPanneau, int idClient, int idPlanche, int nombre, int x, int y) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        //writer.writeEmptyElement("decoupe");

        writer.writeStartElement("decoupe");

        writer.writeEmptyElement("fournisseur");
        //writer.add(eventFactory.createEmptyElement("", "", "fournisseur"));
        // write XML attribute
        writer.writeAttribute("id", String.valueOf(idFournisseur));
        writer.writeAttribute("panneau", String.valueOf(idPanneau));

        //writer.writeEndElement();
        //writer.writeAttribute("", "", "fournisseur\n");

        writer.writeEmptyElement("client");
        // write XML attribute
        writer.writeAttribute("id", String.valueOf(idClient));
        writer.writeAttribute("planche", String.valueOf(idPlanche));
        writer.writeAttribute("nombre", String.valueOf(nombre));

        //writer.add(eventFactory.createEndElement("", "", "client\n"));

        writer.writeEmptyElement("position");
        // write XML attribute
        writer.writeAttribute(  "x", String.valueOf(x));
        writer.writeAttribute("y", String.valueOf(y));

        writer.writeEndElement();

        writer.flush();
        writer.close();
    }

    public void endDocDecoupe(FileOutputStream out) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        // writer.add(eventFactory.createEndElement("", "", "decoupes\n"));
        //writer.writeEndElement();
        writer.writeStartElement("/decoupes>");

        writer.flush();
        writer.close();
    }
}