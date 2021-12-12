package logic;

import java.util.ArrayList;


public class Algorithme {

    static int numDecoupe;
    static int numAlgorithme;

    // ========================================================= ALGORITHME 1 ==============================================================================================================

    public static ArrayList<Decoupe> algorithme1(ArrayList<Generable> clientsGenerable, ArrayList<Generable> fournisseursGenerable) {

        numDecoupe = 1;
        numAlgorithme = 1;
        ArrayList<Decoupe> decoupes = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

        for (Generable clientGenerable : clientsGenerable) {

            Client client = (Client) clientGenerable;
            if (client.isValid())
                clients.add(client);
        }
        for (Generable fournisseurGenerable : fournisseursGenerable) {

            Fournisseur fournisseur = (Fournisseur) fournisseurGenerable;
            if (fournisseur.isValid())
                fournisseurs.add(fournisseur);
        }

        for (Client client : clients) {

            for (int i = 0; i < client.getTailleCourante(); i++) {

                Generable plancheGenerable = client.getBois(i);
                Planche planche = (Planche) plancheGenerable;
                System.out.printf("\n\n\n===============================Commande n° %d à traiter du client %d=======================================\n", planche.getId(), client.getId());
                Date datePlanche = (Date) planche.getDate();
                Dimensions dimensionsPlanche = (Dimensions) planche.getDimensions();

                int nombrePlanches = planche.getNombre();

                for (int j=0; j<nombrePlanches; j++) {
                    for (Fournisseur fournisseur : fournisseurs) {
                        for (int l = 0; l < fournisseur.getTailleCourante(); l++) {

                            Generable panneauGenerable = fournisseur.getBois(l);
                            Panneau panneau = (Panneau) panneauGenerable;
                            int nombrePanneaux = panneau.getNombre();
                            Date datePanneau = (Date) panneau.getDate();
                            Dimensions dimensionsPanneau = (Dimensions) panneau.getDimensions();
                            if (nombrePanneaux > 0 && datePlanche.toCompare(datePanneau) && dimensionsPlanche.toCompare(dimensionsPanneau)) {

                                System.out.println("_______________________________________________________");

                                decouper(decoupes, dimensionsPlanche, planche, panneau, j, panneau.getNombreInitial() - nombrePanneaux, planche.getIdProprietaire(), fournisseur, dimensionsPanneau);
                                panneau.setNombre(nombrePanneaux - 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return decoupes;
    }

    // =======================================================FIN ALGORITHME 1 ==============================================================================================================

    // ========================================================= ALGORITHME 2 ==============================================================================================================


    public static ArrayList<Bois> tableauPlanche(ArrayList<Client> clients){
        ArrayList<Bois> planches = new ArrayList<>();

        for (Client client : clients) {
            for (int i = 0; i < client.getTailleCourante(); i++) {
                planches.add(client.getBois(i));
            }
        }
        return planches;
    }

    public static Bois plancheDeLongueurMax(ArrayList<Bois> planches){
        Bois max = planches.get(0);
        for(int i=1;i<planches.size();i++){
            Dimensions dimensions = (Dimensions)planches.get(i).getDimensions();
            Dimensions dimensionsMax = (Dimensions)max.getDimensions();
            if(dimensions.getLongueur()>dimensionsMax.getLongueur())
                max=planches.get(i);
        }
        return max;
    }

    public static ArrayList<Bois> trierPlanches(ArrayList<Bois> planches){
        int size = planches.size();
        ArrayList<Bois> planchesTriees = new ArrayList<>();
        while(planchesTriees.size() != size){
            Bois max = plancheDeLongueurMax(planches);
            planchesTriees.add(max);
            planches.remove(max);

        }
        return planchesTriees;
    }

    public static ArrayList<Decoupe> algorithme2(ArrayList<Generable> clientsGenerable, ArrayList<Generable> fournisseursGenerable) {

        numDecoupe = 1;
        numAlgorithme = 2;
        ArrayList<Decoupe> decoupes = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        for (Generable clientGenerable : clientsGenerable) {

            Client client = (Client) clientGenerable;
            if (client.isValid())
                clients.add(client);
        }
        for (Generable fournisseurGenerable : fournisseursGenerable) {

            Fournisseur fournisseur = (Fournisseur) fournisseurGenerable;
            if (fournisseur.isValid())
                fournisseurs.add(fournisseur);
        }
        ArrayList<Bois> tableauDePlanches = tableauPlanche(clients);
        ArrayList<Bois> tableauDePlancheTrie = trierPlanches(tableauDePlanches);
        for (Bois bois : tableauDePlancheTrie) {

            Planche planche = (Planche) bois;
            System.out.printf("\n\n\n===============================Commande n° %d à traiter du client %d=======================================\n", planche.getId(), planche.getIdProprietaire());

            int nombrePlanches = planche.getNombre();
            Date datePlanche = (Date) planche.getDate();
            Dimensions dimensionsPlanche = (Dimensions) planche.getDimensions();

            for (int j = 0; j < nombrePlanches; j++) {

                for (int s = 0; s < fournisseurs.size(); s++) {
                    Fournisseur fournisseur = fournisseurs.get(s);
                    for (int l = 0; l < fournisseur.getTailleCourante(); l++) {

                        Generable panneauGenerable = fournisseur.getBois(l);
                        Panneau panneau = (Panneau) panneauGenerable;

                        int nombrePanneaux = panneau.getNombre();
                        Date datePanneau = (Date) panneau.getDate();

                        if (datePlanche.toCompare(datePanneau)) {
                            for (int k = 0; k < nombrePanneaux; k++) {


                                Dimensions dimensionsPanneau = (Dimensions) panneau.getDimensions(k);

                                if (dimensionsPlanche.toCompare(dimensionsPanneau)) {
                                    System.out.println("_______________________________________________________");

                                    decouper(decoupes, dimensionsPlanche, (Planche) bois, panneau, j, k, bois.getIdProprietaire(), fournisseur, dimensionsPanneau);

                                    dimensionsPanneau.setDimensions(dimensionsPanneau.getLongueur() - dimensionsPlanche.getLongueur(), dimensionsPanneau.getLargeur());
                                    k = nombrePanneaux;
                                    l = fournisseur.getTailleCourante();
                                    s = fournisseurs.size();
                                }
                            }
                        }
                    }
                }
            }
        }


        return decoupes;
    }

    // =======================================================FIN ALGORITHME 2 ==============================================================================================================

    public static void decouper(ArrayList<Decoupe>decoupes, Dimensions dimensionsPlanche, Planche planche, Panneau panneau, int k, int l, int idClient, Fournisseur fournisseur, Dimensions dimensionsPanneau) {

        String x = String.format("%d.00", dimensionsPanneau.getLargeurInitiale() - dimensionsPanneau.getLargeur() + dimensionsPlanche.getLargeur());
        String y = String.format("%d.00", dimensionsPanneau.getLongueurInitiale() - dimensionsPanneau.getLongueur() +  dimensionsPlanche.getLongueur());
        String x1 =  String.format("%d", dimensionsPanneau.getLargeurInitiale() - dimensionsPanneau.getLargeur());
        String y1 =  String.format("%d", dimensionsPanneau.getLongueurInitiale() - dimensionsPanneau.getLongueur());
        String idPlanche = String.format("%d.%d", planche.getId(), k);
        String idPanneau = String.format("%d.%d", panneau.getId(), l);
        Decoupe decoupe = new Decoupe(x1, y1,dimensionsPanneau.getLongueurInitialeString(), dimensionsPanneau.getLargeurInitialeString(), x, y, idClient, idPlanche, fournisseur.getId(), idPanneau);
        decoupes.add(decoupe);

        System.out.println("Découpe n°" + numDecoupe + ": planche d'id " + decoupe.getIdPlanche() + " du client " + decoupe.getIdClient() + " a été prise du panneau " + decoupe.getIdPanneau() + " ---> x= " + decoupe.getX() + " ,y=" + decoupe.getY() + "\n");
        numDecoupe++;
    }

}
