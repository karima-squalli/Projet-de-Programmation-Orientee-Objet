package logic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
                System.out.printf("==========Commande n° %d à traiter du client %d==============\n", planche.getId(), client.getId());
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

                                System.out.printf("==========Panneau n° %d.%d à traiter du fournisseur %d==============\n", panneau.getId(), panneau.getNombreInitial() - nombrePanneaux, fournisseur.getId());

                                decouper(decoupes, dimensionsPlanche, planche, nombrePlanches, panneau, nombrePanneaux, j, panneau.getNombreInitial() - nombrePanneaux, client, fournisseur, dimensionsPanneau);
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

        for (Client client : clients) {

            for (int i = 0; i < client.getTailleCourante(); i++) {

                Generable plancheGenerable = client.getBois(i);
                Planche planche = (Planche) plancheGenerable;
                System.out.printf("==========Commande n° %d à traiter du client %d==============\n", planche.getId(), client.getId());

                int nombrePlanches = planche.getNombre();
                Date datePlanche = (Date) planche.getDate();

                for (int j=0; j<nombrePlanches; j++) {

                    for (int s = 0; s<fournisseurs.size(); s++) {
                        Fournisseur fournisseur = fournisseurs.get(s);
                        for (int l = 0; l < fournisseur.getTailleCourante(); l++) {

                            Generable panneauGenerable = fournisseur.getBois(l);
                            Panneau panneau = (Panneau) panneauGenerable;

                            int nombrePanneaux = panneau.getNombre();
                            Date datePanneau = (Date) panneau.getDate();

                            if (datePlanche.toCompare(datePanneau)) {
                                for (int k = 0; k<nombrePanneaux; k++) {


                                    Dimensions dimensionsPanneau = (Dimensions) panneau.getDimensions(k);
                                    Dimensions dimensionsPlanche = (Dimensions) planche.getDimensions();

                                    if (dimensionsPlanche.toCompare(dimensionsPanneau)) {
                                        System.out.printf("==========Panneau n° %d à traiter du fournisseur %d==============\n", panneau.getId(), fournisseur.getId());

                                        decouper(decoupes, dimensionsPlanche, planche, nombrePlanches, panneau, nombrePanneaux, j, k, client, fournisseur, dimensionsPanneau);

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
        }
        return decoupes;
    }

    // =======================================================FIN ALGORITHME 2 ==============================================================================================================

    public static void decouper(ArrayList<Decoupe>decoupes, Dimensions dimensionsPlanche, Planche planche, int nombrePlanches, Panneau panneau, int nombrePanneaux, int k, int l, Client client, Fournisseur fournisseur, Dimensions dimensionsPanneau) {

        String x = String.format("%d.00", dimensionsPanneau.getLargeurInitiale() - dimensionsPanneau.getLargeur() + dimensionsPlanche.getLargeur());
        String y = String.format("%d.00", dimensionsPanneau.getLongueurInitiale() - dimensionsPanneau.getLongueur() +  dimensionsPlanche.getLongueur());
        String x1 =  String.format("%d", dimensionsPanneau.getLargeurInitiale() - dimensionsPanneau.getLargeur());
        String y1 =  String.format("%d", dimensionsPanneau.getLongueurInitiale() - dimensionsPanneau.getLongueur());
        String idPlanche = String.format("%d.%d", planche.getId(), k);
        String idPanneau = String.format("%d.%d", panneau.getId(), l);
        Decoupe decoupe = new Decoupe(x, y, client.getId(), idPlanche, fournisseur.getId(), idPanneau);
        decoupes.add(decoupe);
        String fileName = String.format("decoupe%d.svg", numDecoupe);
        writeSvg(fileName, dimensionsPanneau.getLongueurInitialeString(), dimensionsPanneau.getLargeurInitialeString(), x, y, x1, y1);
        System.out.println("Découpe n°" + numDecoupe + ": planche d'id " + decoupe.getIdPlanche() + " du client " + decoupe.getIdClient() + " a été prise du panneau " + decoupe.getIdPanneau() + " ---> x= " + decoupe.getX() + " ,y=" + decoupe.getY() + "\n");
        numDecoupe++;
    }

    public static void writeSvg(String fileName, String longueur, String largeur, String x, String y, String x1, String y1) {

        try{
            String filePath = String.format("src/etape%d/%s",numAlgorithme+1, fileName);
            PrintWriter pw = new PrintWriter(filePath);
            pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
            String svg = String.format(" version=\"1.1\" width=\"%s\" height=\"%s\">", largeur, longueur);
            pw.println(svg);
            String panneau = String.format("<rect width=\"%s\" height=\"%s\" style=\"fill:rgb(88,41,0);stroke-width:3;stroke:rgb(0,0,0)\" />", largeur, longueur);
            pw.println(panneau);
            String decoupeVerticale = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />",x , y1, x, y);
            String decoupeHorizontale = String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />",x1, y ,x ,y);
            pw.println(decoupeVerticale);
            pw.println(decoupeHorizontale);
            pw.println("</svg>");
            pw.close();
        }catch(FileNotFoundException ex){
            System.out.println("Nom de fichier incorrect " + ex.getMessage());
        }
    }
}
