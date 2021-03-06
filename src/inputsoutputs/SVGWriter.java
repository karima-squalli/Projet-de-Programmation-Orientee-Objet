package inputsoutputs;

import logic.Cut;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

class SVGWriter implements Writer {


    @Override
    public void writeCut(int algorithmNumber, ArrayList<Cut> cuts) {
        try {
            int counter = 1;
            for (int i=0; i<cuts.size(); i++){

                Cut cut = cuts.get(i);
                String boardId = cut.getIdPanel();
                int supplierId = cut.getIdSupplier();
                String filePath = String.format("src/etape%d/decoupe%d.svg",algorithmNumber+1, counter);

                if (algorithmNumber == 3)
                    filePath = String.format("src/etape%d/decoupe_optimisee%d.svg",algorithmNumber, counter);

                PrintWriter pw = new PrintWriter(filePath);
                pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
                String svg = String.format(" version=\"1.1\" width=\"400\" height=\"%d\">", (int)Double.parseDouble(cut.getLength()) + 30);
                pw.println(svg);
                String panel = String.format("<rect width=\"%s\" height=\"%s\" style=\"fill:rgb(88,41,0)\" />", cut.getWidth(), cut.getLength());
                pw.println(panel);
                for (int j=0; j<cuts.size(); j++) {

                    Cut cut1 = cuts.get(j);
                    if (boardId.equals(cut1.getIdPanel()) && supplierId == cut1.getIdSupplier()) {

                        String xRect = cut1.getX1();
                        String yRect = cut1.getY1();
                        int height = -(int)Double.parseDouble(cut1.getY1()) + (int)Double.parseDouble(cut1.getY());
                        int width = -(int)Double.parseDouble(cut1.getX1()) + (int)Double.parseDouble(cut1.getX());

                        String board = String.format("<rect x=\"%s\" y=\"%s\"  width=\"%d\" height=\"%d\" stroke=\"#F00020\" stroke-width=\"2\" style=\"fill:rgb(119,181,254)\" />", xRect, yRect, width, height);
                        pw.println(board);
                        cuts.remove(j);
                        j--;
                    }
                }
                String outline = String.format("<rect width=\"%s\" height=\"%s\" stroke=\"#000000\" stroke-width=\"2\" fill=\"transparent\" style=\"fill-opacity: .0;\" />", cut.getWidth(), cut.getLength());
                pw.println(outline);
                String text = String.format("<text x=\"0\" y=\"%d\" fill=\"green\">Cutouts made on the panel of id %s from the Supplier of id %d</text>",(int)Double.parseDouble(cut.getLength()) + 20, cut.getIdPanel(), cut.getIdSupplier());
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


     @Override
     public void writeCut(int doc, ArrayList<String> data, FileOutputStream out, int idProvider, String panel, int idClient, String board, String x, String y) {

     }
 }
