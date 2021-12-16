package logic;

import inputsoutputs.Reader;
import inputsoutputs.Writer;

import javax.xml.stream.XMLStreamException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static logic.Algorithm.*;

public class MainTest {

    public static void main(String[] args) {

        String folderName = args[0];

        String stepNumber = folderName.substring(5, 6);

        int step =  Integer.parseInt(stepNumber);

        Reader r = Reader.getReader(0);

        Factory c1 = new ClientFactory();
        Factory c2 = new BoardFactory();
        Factory c3 = new SupplierFactory();
        Factory c4 = new PanelFactory();

        ArrayList<String> data1 = new ArrayList<>();
        data1.add("clients");
        data1.add("client");
        String fileNameClients = String.format("src/etape%d/clients.xml", step);
        ArrayList<Generable> clientsGenerable1 = r.readGenerable(data1,fileNameClients, c1, c2);
        ArrayList<Generable> clientsGenerable2 = r.readGenerable(data1,fileNameClients, c1, c2);
        ArrayList<String> data2 = new ArrayList<>();
        data2.add("fournisseurs");
        data2.add("fournisseur");
        String fileNameSuppliers = String.format("src/etape%d/fournisseurs.xml", step);
        ArrayList<Generable> suppliersGenerable1 = r.readGenerable(data2,fileNameSuppliers, c3, c4);
        ArrayList<Generable> suppliersGenerable2 = r.readGenerable(data2,fileNameSuppliers, c3, c4);
        switch (step) {
            case 1 -> {
                checkGenerables(clientsGenerable2, 1);
                checkGenerables(suppliersGenerable2, 0);
            }
            case 2 -> {
                ArrayList<Cut> cuts1 = algorithm1(clientsGenerable2, suppliersGenerable2);
                write(cuts1, 1, step);
            }
            case 3 -> {

                System.out.println("\n...................................RESULT WITH ALGORTHM 2....................................");
                ArrayList<Cut> cuts2 = algorithm2(clientsGenerable1, suppliersGenerable1);
                write(cuts2, 2, step);
                System.out.println("\n.............................RESULT WITH THE OPTIMIZED ALGORTHM ....................................");
                ArrayList<Cut> cuts3 = optimizedAlgorithm(clientsGenerable2, suppliersGenerable2);
                write(cuts3, 3, step);
            }
            default -> System.out.println("No such step number found.");
        }
    }

    static void checkGenerables(ArrayList<Generable> people, int test) {

        ArrayList<String> data = new ArrayList<>();
        if (test == 1) {
            data.add("client");
            data.add("board");
            data.add("placed");
        }
        else {
            data.add("supplier");
            data.add("panel");
            data.add("provided");
        }

        for (Generable person : people) {

            System.out.print("\n==========================================================================================\n");
            System.out.println("The " + data.get(0) + " number " + person.getId() + " has " + data.get(2) + " " + person.getWoodNumber() + " orders.");

            for (int j = 0; j < person.getCurrentLength(); j++) {

                Wood wood = person.getWood(j);
                Price price = (Price) wood.getPrice();
                Date date = (Date)wood.getDate();
                System.out.println("--- " + data.get(1) +  " id = " + wood.getId() + "; number of " + data.get(1) +   "s = " + wood.getNumber() + "; price : " + price.getPriceString() + "; date : " + date.getDate());

                if (wood.checkAllValidable())
                    System.out.println("Order number " + j + " is valid.");
                else
                    System.out.println("Order number " + j + " is  invalid.");
            }
        }
    }

    static void write(ArrayList<Cut> cuts, int algorithmNumber, int step) {

        String fileName = String.format("src/etape%d/decoupes.xml", step);
        if (algorithmNumber == 3)
            fileName = String.format("src/etape%d/decoupes_optimised.xml", step);

        try(FileOutputStream out = new FileOutputStream(fileName)) {
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

            int sizeArray = cuts.size();
            for (int i=0; i<sizeArray; i++) {
                int j = 0;
                if (i>0 && i<sizeArray-1) j = 2;
                if (i == sizeArray-1) j = 1;
                Cut cut = cuts.get(i);
                writer.writeCut(j, data3, out, cut.getIdSupplier(), cut.getIdPanel(), cut.getIdClient(), cut.getIdBoard(), cut.getX(), cut.getY());
            }
            writer = Writer.getWriter(1);
            writer.writeCut(algorithmNumber, cuts);

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
