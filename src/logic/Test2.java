package logic;

import inputsoutputs.Writer;

import javax.xml.stream.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Test2 {

    public static void main(String[] args) throws XMLStreamException {

        try(FileOutputStream out = new FileOutputStream("src/etape2/decoupe.xml")){
            Writer writer = Writer.getWriter(0);
            ArrayList<String> data = new ArrayList<>();
            data.add("decoupes");
            data.add("decoupe");
            data.add("client");
            data.add("id");
            data.add("planche");
            data.add("fournisseur");
            data.add("panneau");
            data.add("position");
            data.add("x");
            data.add("y");
            data.add("/decoupes>");
            int doc = 0;
            writer.writeCut(doc, data, out, 0, "1", 0, "2.1", "10", "13");
            doc = 1;
            writer.writeCut(doc,data, out, 1, "3", 1, "3.2", "5", "17");
        } catch (IOException e) {
            e.printStackTrace();
        }

        EcrireSvg();
    }

    public static void EcrireSvg() {
        try{
            PrintWriter pw = new PrintWriter("src/etape2/exemple.svg");
            pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
            pw.println(" version=\"1.1\" width=\"300\" height=\"200\">");
            pw.println("<rect width=\"300\" height=\"100\" style=\"fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)\" />");
            pw.println("<line x1=\"0\" y1=\"0\" x2=\"200\" y2=\"200\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />");
            pw.println("<text x=\"100\" y=\"150\" fill=\"black\">I love SVG!</text>");
            pw.println("</svg>");
            pw.close();
        }catch(FileNotFoundException ex){
            System.out.println("Nom de fichier incorrect " + ex.getMessage());
        }
    }


}