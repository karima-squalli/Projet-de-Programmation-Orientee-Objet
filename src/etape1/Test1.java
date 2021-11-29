package etape1;

import entreessorties.Reader;
// import entreessorties.XMLReader;
import logic.*;

import java.util.ArrayList;
import java.util.List;

public class Test1
{
    public static void main(String[] args) {

//        Reader s = null;
//        Reader r = s.getReader();
        Reader r = Reader.getReader(0);

//        ArrayList<Client> clients1= r.ReadClients("src/etape1/clients.xml");
//        XMLReader clients_file = new XMLReader();
//        XMLReader fournisseurs_file = new XMLReader();
//        ArrayList<Client> clients=clients_file.ReadClients("src/etape1/clients.xml");
//        ArrayList<Fournisseur> fournisseurs = fournisseurs_file.ReadFournisseurs("src/etape1/fournisseurs.xml");
//
//        for (Client client : clients) {
//            System.out.printf("\nLe client numéro %d effectue %d commandes.\n", client.getId(), client.getNombreP()); // comment savoir si c client ou fournisseur
//            for (int j = 0; j < client.getTailleCourante(); j++) {
//                Planche planche = (Planche) client.getBois(j);
//                if (planche.checkAllValidable()) {
//                    System.out.printf("Commande numéro %d valide.\n", j);
//                } else
//                    System.out.printf("Commande numéro %d non valide.\n", j);
//            }
//        }
//        for (Fournisseur fournisseur : fournisseurs) {
//            System.out.printf("\nLe fournisseur numéro %d fournit %d commandes.\n", fournisseur.getId(), fournisseur.getNombreP()); // comment savoir si c client ou fournisseur
//            for (int j = 0; j < fournisseur.getTailleCourante(); j++) {
//                Panneau panneau = (Panneau) fournisseur.getBois(j);
//                if (panneau.checkAllValidable()) {
//                    System.out.printf("Commande numéro %d valide.\n", j);
//                } else
//                    System.out.printf("Commande numéro %d non valide.\n", j);
//
//            }
//        }

        // Testing generable
        System.out.println("=========Testing generable=========");

        Factory cf = new ClientFactory();
        Factory ff = new FournisseurFactory();

        List<Generable> clientsG = r.readGenerable("src/etape1/clients.xml", cf);

        for (Generable clientG : clientsG) {
            System.out.printf("\nLe client numéro %d effectue %d commandes.\n", clientG.getId(), clientG.getNombreP()); // comment savoir si c client ou fournisseur
            for (int j = 0; j < clientG.getTailleCourante(); j++) {
                RectangleDeBois planche = clientG.getBois(j);
                if (planche.checkAllValidable()) {
                    System.out.printf("Commande numéro %d valide.\n", j);
                } else
                    System.out.printf("Commande numéro %d non valide.\n", j);
            }
        }

    }

}