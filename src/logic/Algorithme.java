package logic;

import java.util.ArrayList;


public class Algorithme {


    public static ArrayList<Decoupe> algorithme1(ArrayList<Generable> clients, ArrayList<Generable> fournisseurs) {
        ArrayList<Decoupe> decoupes = new ArrayList<>();
        //Ces deux variables permettront la gestion des ids des panneaux et des planches du fichier découpe
        int test=0;   //variable qui va prendre le nombre de panneaux pris d'un fournisseur si le client a pris toutes les planches qui lui faut de celui-ci et que ce dernier a toujours des panneaux
        int test2=0; //variable qui va prendre le nombre de planches traitées si le fournisseur n'a plus de panneaux et que le client a toujours des planches à traiter
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
                    Prix prix = (Prix) planche.getPrix();
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
                                    Prix prixPanneau = (Prix) planche.getPrix();
                                    Dimensions dimensionsPanneau = (Dimensions) planche.getDimensions();

                                    if (date.toCompare(datePanneau)  && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche <= nombreDePanneau) && (test==0) && (test2==0)) {

                                        for (int r = 0; r < planche.getNombre(); r++) {
                                            String plancheChar = String.valueOf(planche.getId())+"."+String.valueOf(r);
                                            String panneauChar = String.valueOf(panneau.getId())+"."+String.valueOf(r);
                                            Decoupe decoupe = new Decoupe(dimensions.getLongueurString(), dimensions.getLargeurString(), client.getId(), plancheChar, fournisseur.getId(), panneauChar);
                                            decoupes.add(decoupe);

                                            System.out.println("Découpe : planche d'id "+decoupe.getIdPlanche()+" du client "+ decoupe.getIdClient()+" a été prise du panneau "+ decoupe.getIdPanneau() +" --> x= " +decoupe.getX()+" ,y=" +decoupe.getY()+"\n");

                                        }

                                        int panneauxPris = planche.getNombre();
                                        panneau.decrementNumber(nombreDePlanche);
                                        planche.decrementNumber(nombreDePlanche);

                                        if(planche.getNombre()==0 && panneau.getNombre()!=0) {
                                            test = panneauxPris;
                                        }


                                        break;


                                    } else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche > nombreDePanneau) && (test==0) && (test2==0)) {
                                        for(int r=0;r<panneau.getNombre();r++) {
                                            String plancheChar = String.valueOf(planche.getId())+"."+String.valueOf(r);

                                            String panneauChar = String.valueOf(panneau.getId())+"."+String.valueOf(r);

                                            Decoupe decoupe = new Decoupe(dimensions.getLongueurString(), dimensions.getLargeurString(), client.getId(), plancheChar, fournisseur.getId(), panneauChar);
                                            decoupes.add(decoupe);
                                            System.out.println("Découpe : planche d'id "+decoupe.getIdPlanche()+" du client "+ decoupe.getIdClient()+" a été prise du panneau "+ decoupe.getIdPanneau() +" --> x= " +decoupe.getX()+" ,y=" +decoupe.getY()+"\n");

                                        }
                                        int planchesTraitées = test2+panneau.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);
                                        if(panneau.getNombre()==0 && planche.getNombre()!=0) {
                                            test2 = planchesTraitées;
                                        }
                                        if (panneau.getNombre() == 0) {
                                            fournisseur.removeP(panneau);
                                            System.out.println("panneau effacé\n");
                                        }

                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche > nombreDePanneau) && (test!=0) && (test2==0)) {
                                        for(int r=test;r<panneau.getNombre()+test;r++) {
                                            String plancheChar = String.valueOf(planche.getId())+"."+String.valueOf(r-test);

                                            String panneauChar = String.valueOf(panneau.getId())+"."+String.valueOf(r);

                                            Decoupe decoupe = new Decoupe(dimensions.getLongueurString(), dimensions.getLargeurString(), client.getId(), plancheChar, fournisseur.getId(), panneauChar);
                                            decoupes.add(decoupe);

                                            System.out.println("Découpe : planche d'id "+decoupe.getIdPlanche()+" du client "+ decoupe.getIdClient()+" a été prise du panneau "+ decoupe.getIdPanneau() +" --> x= " +decoupe.getX()+" ,y=" +decoupe.getY()+"\n");

                                        }
                                        int planchesTraitées = test2+panneau.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);

                                        if(panneau.getNombre()==0 && planche.getNombre()!=0) {
                                            test = 0;
                                            test2=planchesTraitées;
                                        }
                                        if (panneau.getNombre() == 0)
                                            fournisseur.removeP(panneau);

                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche <= nombreDePanneau) && (test!=0) && (test2==0)) {
                                        for(int r=test;r<planche.getNombre()+test;r++) {
                                            String plancheChar = String.valueOf(planche.getId())+"."+String.valueOf(r-test);

                                            String panneauChar = String.valueOf(panneau.getId())+"."+String.valueOf(r);

                                            Decoupe decoupe = new Decoupe(dimensions.getLongueurString(), dimensions.getLargeurString(), client.getId(), plancheChar, fournisseur.getId(), panneauChar);
                                            decoupes.add(decoupe);

                                            System.out.println("Découpe : planche d'id "+decoupe.getIdPlanche()+" du client "+ decoupe.getIdClient()+" a été prise du panneau "+ decoupe.getIdPanneau() +" --> x= " +decoupe.getX()+" ,y=" +decoupe.getY()+"\n");

                                        }
                                        int panneauxPris = planche.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);

                                        if(panneau.getNombre()!=0 && planche.getNombre()==0) {
                                            test = panneauxPris;
                                            test2=0;
                                        }

                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche <= nombreDePanneau) && (test==0) && (test2!=0)) {
                                        for(int r=test2;r<planche.getNombre()+test2;r++) {
                                            String plancheChar = String.valueOf(planche.getId())+"."+String.valueOf(r);

                                            String panneauChar = String.valueOf(panneau.getId())+"."+String.valueOf(r-test2);

                                            Decoupe decoupe = new Decoupe(dimensions.getLongueurString(), dimensions.getLargeurString(), client.getId(), plancheChar, fournisseur.getId(), panneauChar);
                                            decoupes.add(decoupe);

                                            System.out.println("Découpe : planche d'id "+decoupe.getIdPlanche()+" du client "+ decoupe.getIdClient()+" a été prise du panneau "+ decoupe.getIdPanneau() +" --> x= " +decoupe.getX()+" ,y=" +decoupe.getY()+"\n");

                                        }
                                        int panneauxPris = planche.getNombre();
                                        panneau.decrementNumber(nombreDePanneau);
                                        planche.decrementNumber(nombreDePanneau);

                                        if(panneau.getNombre()!=0 && planche.getNombre()==0) {
                                            test = panneauxPris;
                                            test2=0;
                                        }

                                    }
                                    else if (date.toCompare(datePanneau) && dimensions.toCompare(dimensionsPanneau) && (nombreDePlanche > nombreDePanneau) && (test==0) && (test2!=0)) {
                                        for(int r=test2;r<panneau.getNombre()+test2;r++) {
                                            String plancheChar = String.valueOf(planche.getId())+"."+String.valueOf(r);

                                            String panneauChar = String.valueOf(panneau.getId())+"."+String.valueOf(r-test2);

                                            Decoupe decoupe = new Decoupe(dimensions.getLongueurString(), dimensions.getLargeurString(), client.getId(), plancheChar, fournisseur.getId(), panneauChar);
                                            decoupes.add(decoupe);

                                            System.out.println("Découpe : planche d'id "+decoupe.getIdPlanche()+" du client "+ decoupe.getIdClient()+" a été prise du panneau "+ decoupe.getIdPanneau() +" --> x= " +decoupe.getX()+" ,y=" +decoupe.getY()+"\n");

                                        }
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





}
