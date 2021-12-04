package entreessorties;

import javax.xml.stream.XMLStreamException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public interface Writer {

    static Writer getWriter(int code){
        switch(code){
            case 0:
                return new XMLWriter();
        }
        return null;
    }

    void writeDecoupe(int doc, ArrayList<String> data, FileOutputStream out, int idFournisseur, String panneau, int idClient, String planche, String x, String y) throws XMLStreamException;
}
