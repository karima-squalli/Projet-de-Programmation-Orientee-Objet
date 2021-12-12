package logic;

import entreessorties.Reader;

import java.util.ArrayList;

import static logic.Algorithme.algorithme1;

public class Test1
{
    public static void main(String[] args) {

        Reader r = Reader.getReader(0);


        // Testing generable
        System.out.println("=========Testing generable=========");

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
        ArrayList<Decoupe> decoupes = algorithme1(clientsG, fournisseursG);

        for (Generable clientG : clientsG) {
            System.out.printf("\nLe client numéro %d effectue %d commandes.\n", clientG.getId(), clientG.getNombreP()); // comment savoir si c client ou fournisseur
            for (int j = 0; j < clientG.getTailleCourante(); j++) {

                Generable pg = clientG.getBois(j);
                Planche planche = (Planche)pg;
                Prix prix = (Prix)planche.getPrix();
                Date date = (Date)planche.getDate();
                System.out.println("id de planche : " + planche.getId() + "nombre : " + planche.getNombre() + " prix " + prix.getPrixString() + " date " + date.getDate());

                if (planche.checkAllValidable()) {
                    System.out.printf("Commande numéro %d valide.\n", j);
                } else
                    System.out.printf("Commande numéro %d non valide.\n", j);
            }
        }

    }

}