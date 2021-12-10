package logic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Algorithme {

    static int numDecoupe = 1;
    public static ArrayList<Decoupe> algorithme1(ArrayList<Generable> clients, ArrayList<Generable> fournisseurs) {
        ArrayList<Decoupe> decoupes = new ArrayList<>();
        //int numDecoupe = 1;
        //Ces deux variables permettront la gestion des ids des panneaux et des planches du fichier découpe
        int test=0;   //variable qui va prendre le nombre de panneaux pris d'un fournisseur si le client a pris toutes les planches qui lui faut de celui-ci et que ce dernier a toujours des panneaux
        int test2; //variable qui va prendre le nombre de planches traitées si le fournisseur n'a plus de panneaux et que le client a toujours des planches à traiter
        for (Generable clientG : clients) {
            Client client = (Client)clientG;
            for (int j = 0; j < client.getTailleCourante(); j++) {

                test2=0;
                Generable plancheG = clientG.getBois(j);
                Planche planche = (Planche) plancheG;

                System.out.printf("-La commande à traiter du client %d est : %d\n",client.getId(),planche.getId());
                if (!planche.checkAllValidable()) {
                    System.out.printf("commande %d invalide\n",planche.getId());
                    client.removeP(planche);
                }
                else {
                    int nombreDePlanche = planche.getNombre();
                    Date date = (Date) planche.getDate();
                    Dimensions dimensions = (Dimensions) planche.getDimensions();


                    for (Generable fournisseurG : fournisseurs) {
                        Fournisseur fournisseur = (Fournisseur)fournisseurG;
                        if (fournisseur.getTailleCourante() != 0) {
                            for (int l = 0; l < fournisseur.getTailleCourante(); l++) {
                                Generable panneauG = fournisseurG.getBois(l);
                                Panneau panneau = (Panneau) panneauG;

                                if (!panneau.checkAllValidable()) {
                                    fournisseur.removeP(panneau);
                                    System.out.printf("panneau %d invalide\n",panneau.getId());
                                } else {

                                    int nombreDePanneau = panneau.getNombre();
                                    Date datePanneau = (Date) panneau.getDate();
                                    Dimensions dimensionsPanneau = (Dimensions) planche.getDimensions();

                                    if (date.toCompare(datePanneau)  && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche <= nombreDePanneau) && (test==0) && (test2==0)) {

                                        decouper(decoupes, client, planche, dimensions, fournisseur, panneau, planche.getNombre());

                                        int panneauxPris = planche.getNombre();
                                        panneau.decrementNumber(nombreDePlanche);
                                        planche.decrementNumber(nombreDePlanche);

                                        if(planche.getNombre()==0 && panneau.getNombre()!=0) {
                                            test = panneauxPris;
                                        }
                                        break;

                                    } else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche > nombreDePanneau) && (test==0) && (test2==0)) {

                                        decouper(decoupes, client, planche, dimensions, fournisseur, panneau, panneau.getNombre());

                                        int planchesTraitees = test2+panneau.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);
                                        if(panneau.getNombre()==0 && planche.getNombre()!=0) {
                                            test2 = planchesTraitees;
                                        }
                                        if (panneau.getNombre() == 0) {
                                            fournisseur.removeP(panneau);
                                            System.out.println("panneau effacé\n");
                                        }
                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche > nombreDePanneau) && (test!=0) && (test2==0)) {

                                        decouper3(decoupes, test, client, planche, dimensions, fournisseur, panneau, panneau.getNombre());

                                        int planchesTraitees = test2+panneau.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);

                                        if(panneau.getNombre()==0 && planche.getNombre()!=0) {
                                            test = 0;
                                            test2=planchesTraitees;
                                        }
                                        if (panneau.getNombre() == 0)
                                            fournisseur.removeP(panneau);

                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche <= nombreDePanneau) && (test!=0) && (test2==0)) {

                                        decouper3(decoupes, test, client, planche, dimensions, fournisseur, panneau, planche.getNombre());

                                        int panneauxPris = planche.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);

                                        if(panneau.getNombre()!=0 && planche.getNombre()==0) {
                                            test = panneauxPris;
                                            test2=0;
                                        }
                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche <= nombreDePanneau) && (test==0) && (test2!=0)) {

                                        decouper2(decoupes, test2, client, planche, dimensions, fournisseur, panneau, planche.getNombre());

                                        int panneauxPris = planche.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);

                                        if(panneau.getNombre()!=0 && planche.getNombre()==0) {
                                            test = panneauxPris;
                                            test2=0;
                                        }

                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche > nombreDePanneau) && (test==0) && (test2!=0)) {

                                        decouper2(decoupes, test2, client, planche, dimensions, fournisseur, panneau, panneau.getNombre());

                                        int plancheTraitee = test2+panneau.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);

                                        if(panneau.getNombre()==0 && planche.getNombre()!=0) {
                                            test = 0;
                                            test2=plancheTraitee;
                                        }
                                        if (panneau.getNombre() == 0)
                                            fournisseur.removeP(panneau);
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

    private static void decouper3(ArrayList<Decoupe> decoupes, int test, Client client, Planche planche, Dimensions dimensions, Fournisseur fournisseur, Panneau panneau, int nombre) {
        for(int r = test; r< nombre +test; r++) {
            String plancheChar = planche.getId() +"."+ (r - test);

            String panneauChar = panneau.getId() +"."+ r;

            decouperEtSvg(decoupes, client, dimensions, fournisseur, panneau, plancheChar, panneauChar);

        }
    }

    private static void decouper2(ArrayList<Decoupe> decoupes, int test2, Client client, Planche planche, Dimensions dimensions, Fournisseur fournisseur, Panneau panneau, int nombre) {
        for(int r = test2; r< nombre +test2; r++) {

            String plancheChar = planche.getId() +"."+ r;
            String panneauChar = panneau.getId() +"."+ (r - test2);

            decouperEtSvg(decoupes, client, dimensions, fournisseur, panneau, plancheChar, panneauChar);

        }
    }

    private static void decouperEtSvg(ArrayList<Decoupe> decoupes, Client client, Dimensions dimensions, Fournisseur fournisseur, Panneau panneau, String plancheChar, String panneauChar) {
        String y= dimensions.getLongueurString();
        String x = dimensions.getLargeurString();
        Decoupe decoupe = new Decoupe(x, y, client.getId(), plancheChar, fournisseur.getId(), panneauChar);

        decoupes.add(decoupe);
        Dimensions dimensionsPanneau = (Dimensions) panneau.getDimensions();
        String fileName = String.format("decoupe%d.svg", numDecoupe);
        writeSvg(fileName, dimensionsPanneau.getLongueurString(), dimensionsPanneau.getLargeurString(), x, y);

        System.out.println("Découpe n°" +numDecoupe+ ": planche d'id "+decoupe.getIdPlanche()+" du client "+ decoupe.getIdClient()+" a été prise du panneau "+ decoupe.getIdPanneau() +" --> x= " +decoupe.getX()+" ,y=" +decoupe.getY()+"\n");
        numDecoupe++;
    }

    private static void decouper(ArrayList<Decoupe> decoupes, Client client, Planche planche, Dimensions dimensions, Fournisseur fournisseur, Panneau panneau, int nombre) {
        for(int r = 0; r< nombre; r++) {

            String plancheChar = planche.getId() +"."+ r;
            String panneauChar = panneau.getId() +"."+ r;

            decouperEtSvg(decoupes, client, dimensions, fournisseur, panneau, plancheChar, panneauChar);

        }
    }

    public static void writeSvg(String fileName, String longueur, String largeur, String x, String y) {

        try{
            String filePath = String.format("src/etape2/%s", fileName);
            PrintWriter pw = new PrintWriter(filePath);
            pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\"");
            String svg = String.format(" version=\"1.1\" width=\"%s\" height=\"%s\">", largeur, longueur);
            pw.println(svg);
            String panneau = String.format("<rect width=\"%s\" height=\"%s\" style=\"fill:rgb(88,41,0);stroke-width:3;stroke:rgb(0,0,0)\" />", largeur, longueur);
            pw.println(panneau);
            String decoupeVerticale = String.format("<line x1=\"%s\" y1=\"0\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />",x ,x, y);
            String decoupeHorizontale = String.format("<line x1=\"0\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />",y ,x ,y);
            pw.println(decoupeVerticale);
            pw.println(decoupeHorizontale);
            pw.println("</svg>");
            pw.close();
        }catch(FileNotFoundException ex){
            System.out.println("Nom de fichier incorrect " + ex.getMessage());
        }
    }
}
