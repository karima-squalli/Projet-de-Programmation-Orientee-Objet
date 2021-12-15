package logic;

import entreessorties.Reader;

import java.util.ArrayList;

import static logic.Algorithm.algorithm1;

public class Test1
{
    public static void main(String[] args) {

        Reader r = Reader.getReader(0);


        // Testing generable
        System.out.println("=========Testing generable=========");

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
        ArrayList<Cut> cuts = algorithm1(clientsG, suppliersG);

        for (Generable clientG : clientsG) {
            System.out.printf("\nClient %d has placed %d orders.\n", clientG.getId(), clientG.getWoodNumber()); // comment savoir si c client ou fournisseur
            for (int j = 0; j < clientG.getCurrentLength(); j++) {

                Generable pg = clientG.getWood(j);
                Board board = (Board)pg;
                Price price = (Price)board.getPrice();
                Date date = (Date)board.getDate();
                System.out.println("id de planche : " + board.getId() + "nombre : " + board.getNumber() + " price " + price.getPriceString() + " date " + date.getDate());

                if (board.checkAllValidable()) {
                    System.out.printf("Commande numéro %d valide.\n", j);
                } else
                    System.out.printf("Commande numéro %d non valide.\n", j);
            }
        }

    }

}