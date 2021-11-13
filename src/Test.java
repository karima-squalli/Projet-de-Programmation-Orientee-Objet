import logic.XmlReader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Test
{
    public static void main(String[] args) throws ParserConfigurationException, SAXException
    {
        String file = "fournisseurs.xml";
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser saxParser = factory.newSAXParser();

            XmlReader handler = new XmlReader();
            saxParser.parse(file, handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }

}