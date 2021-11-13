import logic.Client;
import logic.Fournisseur;
import logic.Panneau;
import logic.Planche;

public class Test {

    public static void main(String[] args) {

        Fournisseur fournisseur0 = new Fournisseur(0, 3);
        Panneau[] panneaux = new Panneau[fournisseur0.getNombrePanneaux()];
        fournisseur0.setPanneaux(panneaux);
        Panneau panneau0 = new Panneau(0, 80, 10, 10, 14, "26.10.22");
        Panneau panneau1 = new Panneau(1, 50, 30, 20, 12, "14.12.22");
        Panneau panneau2 = new Panneau(2, 150, 130, 1, 34, "06.01.22");
        fournisseur0.ajouterPanneau(panneau0);
        fournisseur0.ajouterPanneau(panneau1);
        fournisseur0.ajouterPanneau(panneau2);
        System.out.printf("Le fournisseur numero %d fournit %d panneaux.\n", fournisseur0.getId(), fournisseur0.getNombrePanneaux());

        Client client0 = new Client(0, 1);
        Planche[] planches = new Planche[client0.getNombrePlanches()];
        client0.setPlanches(planches);
        Planche planche0 = new Planche(0, 10, 5, 4, 270, "12.10.23");
        client0.ajouterPlanche(planche0);
        System.out.printf("Le client numero %d a commandé %d planche.", client0.getId(), client0.getNombrePlanches());
    }
    
}
