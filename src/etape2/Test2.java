package etape2;

import entreessorties.Writer;

import javax.xml.stream.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test2 {

    public static void main(String[] args) throws XMLStreamException {

        // send the output to a xml file
        try(FileOutputStream out = new FileOutputStream("src/etape2/decoupe.xml")){
            Writer writer = Writer.getWriter(0);
            writer.startDocDecoupe(out);
            writer.writeDecoupe(out, 0, 1, 0, 2, 10, 13, 21);
            writer.writeDecoupe(out, 1, 3, 1, 3, 5, 17, 25);
            writer.endDocDecoupe(out);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // send the output to System.out
        //writeXml2(System.out);

    }


    // StAX Iterator API
    private static void writeXml2(FileOutputStream out) throws XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        // StAX Iterator API
       // XMLEventWriter writer = output.createXMLEventWriter(out);

        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        //XMLEvent event = eventFactory.createStartDocument();
        // default
//        event = eventFactory.createStartDocument("utf-8", "1.0");
        //writer.add(event);
        //writer.writeEndDocument();

        writer.writeEmptyElement("decoupes");
        writer.writeEmptyElement("decoupe");
//        writer.add(eventFactory.createStartElement("", "", "decoupes"));
//        writer.add(eventFactory.createStartElement("", "", "decoupe"));
        // write XML comment
        //writer.add(eventFactory.createComment("This is staff 1001"));

        writer.writeEmptyElement("fournisseur");

        //writer.add(eventFactory.createEmptyElement("", "", "fournisseur"));
        // write XML attribute
        writer.writeAttribute("id", "0");
        writer.writeAttribute("panneau", "3");

        //writer.writeEndElement();
        //writer.writeAttribute("", "", "fournisseur\n");

        writer.writeEmptyElement("", "", "client");
        // write XML attribute
        writer.writeAttribute("id", "0");
        writer.writeAttribute("planche", "0");

        //writer.add(eventFactory.createEndElement("", "", "client\n"));

        writer.writeEmptyElement("", "\n", "position");
        // write XML attribute
        writer.writeAttribute(  "x", "29");
        writer.writeAttribute("y", "13");


        //writer.add(eventFactory.createEndElement("", "", "position\n"));

        //writer.add(eventFactory.createEndElement("", "", "decoupe\n"));
        //writer.writeEndElement();
        writer.writeEndDocument();
        // </staff>
        //writer.write("", "", "decoupes\n"));
        //writer.add(eventFactory.createEndElement("", "", "decoupes\n"));

        // next staff, tired to write more
        // writer.add(eventFactory.createStartElement("", "", "staff"));
        // writer.add(eventFactory.createAttribute("id", "1002"));
        // writer.add(eventFactory.createEndElement("", "", "staff"));

        // end here.
        //writer.wr;

//        TransformerFactory transfac = TransformerFactory.newInstance();
//        transfac.setAttribute("indent-number", new Integer(4));
//        Transformer trans =null;
//        try {
//            trans = transfac.newTransformer();
//        } catch (TransformerConfigurationException e) {
//            e.printStackTrace();
//        }
//        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//        trans.setOutputProperty(OutputKeys.INDENT, "yes");
//
//        //create string from xml tree
//        StringWriter sw = new StringWriter();
//        StreamResult result = new StreamResult(sw);
//        DOMSource source = new DOMSource();
//        try {
//            trans.transform(source, result);
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        }
//        String xmlString = sw.toString();

        writer.flush();
        writer.close();

    }

}