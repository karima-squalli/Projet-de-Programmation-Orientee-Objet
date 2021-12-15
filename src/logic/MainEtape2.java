package logic;

import entreessorties.Reader;
import entreessorties.Writer;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static logic.Algorithm.*;

public class MainEtape2 {

    public static void main(String[] args) {

        Reader r = Reader.getReader(0);

        Factory c1 = new ClientFactory();
        Factory c2 = new BoardFactory();
        Factory c3 = new SupplierFactory();
        Factory c4 = new PanelFactory();

        ArrayList<String> data = new ArrayList<>();
        data.add("clients");
        data.add("client");
        ArrayList<Generable> clientsG = r.readGenerable(data,"src/etape2/clients.xml", c1, c2);

        ArrayList<String> data2 = new ArrayList<>();
        data2.add("fournisseurs");
        data2.add("fournisseur");
        ArrayList<Generable> suppliersG = r.readGenerable(data2,"src/etape2/fournisseurs.xml", c3, c4);
        ArrayList<Cut> cuts = algorithme3(clientsG, suppliersG);


        try(FileOutputStream out = new FileOutputStream("src/etape3/cuts.xml")) {
            Writer writer = Writer.getWriter(0);
            ArrayList<String> data3 = new ArrayList<>();
            data3.add("cuts");
            data3.add("decoupe");
            data3.add("client");
            data3.add("id");
            data3.add("planche");
            data3.add("fournisseur");
            data3.add("panneau");
            data3.add("position");
            data3.add("x");
            data3.add("y");
            data3.add("/cuts>");

            int sizeArray = cuts.size();
            for (int i=0; i<sizeArray; i++) {
                int j = 0;
                if (i>0 && i<sizeArray-1) j = 2;
                if (i == sizeArray-1) j = 1;
                Cut cut = cuts.get(i);
                writer.writeCut(j, data3, out, cut.getIdSupplier(), cut.getIdPanel(), cut.getIdClient(), cut.getIdBoard(), cut.getX(), cut.getY());
            }
            writeSvg(2, cuts);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void writeSvg(int algorithmNumber, ArrayList<Cut> cuts) {

        try{
            int counter = 1;
            for (int i=0; i<cuts.size(); i++){

                Cut cut = cuts.get(i);
                String boardId = cut.getIdPanel();
                int supplierId = cut.getIdSupplier();
                String filePath = String.format("src/etape%d/decoupe_%d.svg",algorithmNumber+1, counter);
                PrintWriter pw = new PrintWriter(filePath);
                pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
                String svg = String.format(" version=\"1.1\" width=\"360\" height=\"%d\">", (int)Double.parseDouble(cut.getLength()) + 30);
                pw.println(svg);
                String panel = String.format("<rect width=\"%s\" height=\"%s\" style=\"fill:rgb(88,41,0)\" />", cut.getWidth(), cut.getLength());
                pw.println(panel);
                for (int j=0; j<cuts.size(); j++) {

                    Cut cut1 = cuts.get(j);
                    if (boardId.equals(cut1.getIdPanel()) && supplierId == cut1.getIdSupplier()) {

                        String xrect = cut1.getX1();
                        String yrect = cut1.getY1();
                        int height = -(int)Double.parseDouble(cut1.getY1()) + (int)Double.parseDouble(cut1.getY());
                        int width = -(int)Double.parseDouble(cut1.getX1()) + (int)Double.parseDouble(cut1.getX());

                        String board = String.format("<rect x=\"%s\" y=\"%s\"  width=\"%d\" height=\"%d\" style=\"fill:rgb(119,181,254)\" />", xrect, yrect, width, height);
                        //String panel = String.format("<rect width=\"%s\" height=\"%s\" style=\"fill:rgb(88,41,0)\" />", cut.getLargeur(), cut.getLongueur());
                        pw.println(board);
                        String verticalCut1 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", cut1.getX() , cut1.getY1(), cut1.getX(), cut1.getY());
                        String horizontalCut1 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", cut1.getX1(), cut1.getY(),cut1.getX(),cut1.getY());
                        String vercticalCut2 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", cut1.getX1(), cut1.getY1(), cut1.getX1(), cut1.getY());
                        String horizontalCut2 = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />", cut1.getX1(), cut1.getY1() , cut1.getX(), cut1.getY1());
                        pw.println(verticalCut1);
                        pw.println(horizontalCut1);
                        pw.println(vercticalCut2);
                        pw.println(horizontalCut2);
                        cuts.remove(j);
                        j--;
                    }
                }
                String outline = String.format("<rect width=\"%s\" height=\"%s\" stroke=\"#000000\" stroke-width=\"2\" fill=\"transparent\" style=\"fill-opacity: .0;\" />", cut.getWidth(), cut.getLength());
                pw.println(outline);
                String text = String.format("<text x=\"0\" y=\"%d\" fill=\"white\">Découpes effectuées sur le panel d'id %s du Fournisseur d'id %d</text>",(int)Double.parseDouble(cut.getLength()) + 20, cut.getIdPanel(), cut.getIdSupplier());
                pw.println(text);

                pw.println("</svg>");
                pw.close();
                i--;
                counter++;
            }

        }catch(FileNotFoundException ex){
            System.out.println("Name of file is incorrect " + ex.getMessage());
        }
    }
}
