package entreessorties;

import javax.xml.stream.XMLStreamException;
import java.io.FileOutputStream;

public interface Writer {

    static Writer getWriter(int code){
        switch(code){
            case 0:
                return new XMLWriter();
        }
        return null;
    }

    void startDocDecoupe(FileOutputStream out) throws XMLStreamException;
    void writeDecoupe(FileOutputStream out, int idFournisseur, int idPanneau, int idClient, int idPlanche, int nombre, int x, int y) throws XMLStreamException;
    void endDocDecoupe(FileOutputStream out) throws XMLStreamException;
}
