package etape1;

import interfaces.XmlReader;
import logic.*;

import java.util.ArrayList;

public class Test1
{
    public static void main(String[] args) {

        XmlReader clients_file = new XmlReader();
        XmlReader fournisseurs_file = new XmlReader();
        ArrayList<Client> clients=clients_file.ReadClientsFrom("src/etape1/clients.xml");
        ArrayList<Fournisseur> fournisseurs = fournisseurs_file.ReadFournisseursFrom("src/etape1/fournisseurs.xml");

        for (Client client : clients) {
            System.out.printf("\nLe client numéro %d effectue %d commandes.\n", client.getId(), client.getNombreP()); // comment savoir si c client ou fournisseur
            for (int j = 0; j < client.getTailleCourante(); j++) {
                Planche planche = (Planche) client.getBois(j);
                if (planche.checkAllValidable()) {
                    System.out.printf("Commande numéro %d valide.\n", j);
                } else
                    System.out.printf("Commande numéro %d non valide.\n", j);
            }
        }
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.printf("\nLe fournisseur numéro %d fournit %d commandes.\n", fournisseur.getId(), fournisseur.getNombreP()); // comment savoir si c client ou fournisseur
            for (int j = 0; j < fournisseur.getTailleCourante(); j++) {
                Panneau panneau = (Panneau) fournisseur.getBois(j);
                if (panneau.checkAllValidable()) {
                    System.out.printf("Commande numéro %d valide.\n", j);
                } else
                    System.out.printf("Commande numéro %d non valide.\n", j);

            }
        }
    }

}