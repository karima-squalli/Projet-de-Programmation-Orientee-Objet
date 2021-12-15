package logic;

import entreessorties.Reader;
import entreessorties.Writer;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
        ArrayList<Generable> fournisseursG = r.readGenerable(data2,"src/etape2/fournisseurs.xml", c3, c4);
        ArrayList<Decoupe> decoupes = algorithme3(clientsG, fournisseursG);


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
            writeSvg(2, decoupes);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void writeSvg(int numAlgo, ArrayList<Decoupe> decoupes) {

        try{
            int compteur = 1;
            for (int i=0; i<decoupes.size(); i++){

                Decoupe decoupe = decoupes.get(i);
                String idPanneau = decoupe.getIdPanneau();
                int idFournisseur = decoupe.getIdFournisseur();
                String filePath = String.format("src/etape%d/decoupe_%d.svg",numAlgo+1, compteur);
                PrintWriter pw = new PrintWriter(filePath);
                pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
                String svg = String.format(" version=\"1.1\" width=\"360\" height=\"%d\">", (int)Double.parseDouble(decoupe.getLongueur()) + 30);
                pw.println(svg);
                String panneau = String.format("<rect width=\"%s\" height=\"%s\" style=\"fill:rgb(88,41,0)\" />", decoupe.getLargeur(), decoupe.getLongueur());
                pw.println(panneau);
                for (int j=0; j<decoupes.size(); j++) {

                    Decoupe decoupe1 = decoupes.get(j);
                    if (idPanneau.equals(decoupe1.getIdPanneau()) && idFournisseur == decoupe1.getIdFournisseur()) {

                        String xrect = decoupe1.getX1();
                        String yrect = decoupe1.getY1();
                        int height = -(int)Double.parseDouble(decoupe1.getY1()) + (int)Double.parseDouble(decoupe1.getY());
                        int width = -(int)Double.parseDouble(decoupe1.getX1()) + (int)Double.parseDouble(decoupe1.getX());

                        String planche = String.format("<rect x=\"%s\" y=\"%s\"  width=\"%d\" height=\"%d\" style=\"fill:rgb(119,181,254)\" />", xrect, yrect, width, height);
                        //String panneau = String.format("<rect width=\"%s\" height=\"%s\" style=\"fill:rgb(88,41,0)\" />", decoupe.getLargeur(), decoupe.getLongueur());
                        pw.println(planche);
                        String decoupeVerticale1 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", decoupe1.getX() , decoupe1.getY1(), decoupe1.getX(), decoupe1.getY());
                        String decoupeHorizontale1 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", decoupe1.getX1(), decoupe1.getY(),decoupe1.getX(),decoupe1.getY());
                        String decoupeVerticale2 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", decoupe1.getX1(), decoupe1.getY1(), decoupe1.getX1(), decoupe1.getY());
                        String decoupeHorizontale2 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", decoupe1.getX1(), decoupe1.getY1() , decoupe1.getX(), decoupe1.getY1());
                        pw.println(decoupeVerticale1);
                        pw.println(decoupeHorizontale1);
                        pw.println(decoupeVerticale2);
                        pw.println(decoupeHorizontale2);
                        decoupes.remove(j);
                        j--;
                    }
                }
                String contour = String.format("<rect width=\"%s\" height=\"%s\" stroke=\"#000000\" stroke-width=\"2\" fill=\"transparent\" style=\"fill-opacity: .0;\" />", decoupe.getLargeur(), decoupe.getLongueur());
                pw.println(contour);
                String text = String.format("<text x=\"0\" y=\"%d\" fill=\"white\">Découpes effectuées sur le panneau d'id %s du Fournisseur d'id %d</text>",(int)Double.parseDouble(decoupe.getLongueur()) + 20, decoupe.getIdPanneau(), decoupe.getIdFournisseur());
                pw.println(text);

                pw.println("</svg>");
                pw.close();
                i--;
                compteur++;
            }

        }catch(FileNotFoundException ex){
            System.out.println("Nom de fichier incorrect " + ex.getMessage());
        }
    }
}
