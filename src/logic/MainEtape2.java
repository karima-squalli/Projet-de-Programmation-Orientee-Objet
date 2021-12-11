package logic;

import entreessorties.Reader;
import entreessorties.Writer;

import javax.xml.stream.XMLStreamException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static logic.Algorithme.*;

public class MainEtape2 {

    public static void main(String[] args) {

        Reader r = Reader.getReader(0);

        Factory c1 = new ClientFactory();
        Factory c2 = new PlancheFactory();
        Factory c3 = new FournisseurFactory();
        Factory c4 = new PanneauFactory();

        ArrayList<String> data = new ArrayList<>();
        data.add("clients");
        data.add("client");
        ArrayList<Generable> clientsG = r.readGenerable(data,"src/etape2/clients.xml", c1, c2);

        ArrayList<String> data2 = new ArrayList<>();
        data2.add("fournisseurs");
        data2.add("fournisseur");
        ArrayList<Generable> fournisseursG = r.readGenerable(data2,"src/etape3/fournisseurs.xml", c3, c4);
        ArrayList<Decoupe> decoupes = algorithme2(clientsG, fournisseursG);

        try(FileOutputStream out = new FileOutputStream("src/etape3/decoupes.xml")) {
            Writer writer = Writer.getWriter(0);
            ArrayList<String> data3 = new ArrayList<>();
            data3.add("decoupes");
            data3.add("decoupe");
            data3.add("client");
            data3.add("id");
            data3.add("planche");
            data3.add("fournisseur");
            data3.add("panneau");
            data3.add("position");
            data3.add("x");
            data3.add("y");
            data3.add("/decoupes>");

            int sizeArray = decoupes.size();
            for (int i=0; i<sizeArray; i++) {
                int j = 0;
                if (i>0 && i<sizeArray-1) j = 2;
                if (i == sizeArray-1) j = 1;
                Decoupe decoupe = decoupes.get(i);
                writer.writeDecoupe(j, data3, out, decoupe.getIdFournisseur(), decoupe.getIdPanneau(), decoupe.getIdClient(), decoupe.getIdPlanche(), decoupe.getX(), decoupe.getY());

            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
