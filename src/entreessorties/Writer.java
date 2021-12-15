package entreessorties;

import javax.xml.stream.XMLStreamException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public interface Writer {

    static Writer getWriter(int code){
        return switch (code) {
            case 0 -> new XMLWriter();
            default -> null;
        };
    }

    void writeCut(int doc, ArrayList<String> data, FileOutputStream out, int idProvider, String panel, int idClient, String board, String x, String y) throws XMLStreamException;
}
