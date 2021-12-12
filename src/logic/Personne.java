package logic;

import java.util.ArrayList;

abstract class Personne implements Generable, Validable{

    private final int id;
    private int nombreP;
    private int tailleCourante = 0;
    private int tailleMaximale = 10;
    private Bois[] bois = new Bois[tailleMaximale];

    Personne(int id) {
        this.id = id;
    }


    public void add(Bois element) {

        if (tailleCourante == tailleMaximale) {
            tailleMaximale = tailleMaximale * 2;
            Bois[] tmp = bois.clone();
            bois = new Bois[tailleMaximale];
            if (tailleCourante >= 0) System.arraycopy(tmp, 0, bois, 0, tailleCourante);
        }
        bois[tailleCourante] = element;
        tailleCourante++;
        nombreP = tailleCourante;
    }

    public void removeP(Bois elementToBeDeleted) {
        Bois[] newBois = null;
        for (int i = 0; i < bois.length - 1; i++) {
            if (bois[i] == elementToBeDeleted) {

                newBois = new Bois[bois.length - 1];
                System.arraycopy(bois, 0, newBois, 0, i);
                if (bois.length - 1 - i >= 0) System.arraycopy(bois, i + 1, newBois, i, bois.length - 1 - i);

                break;
            }
        }

        this.bois = newBois;
        tailleCourante--;
    }

    public int getId() {
        return id;
    }

    public int getNombreP() {
        return nombreP;
    }

    public int getTailleCourante() {
        return tailleCourante;
    }

    public Bois getBois(int i) {
        return bois[i];
    }

    public Boolean isValid() {

        for (int l = 0; l < tailleCourante; l++) {

            Bois bois = this.bois[l];

            if (!bois.checkAllValidable()) {
                removeP(bois);
                l--;
            }
        }
        return tailleCourante != 0;
    }

    public int getIdProprietaire() {
        return 0;
    }

    @Override
    public void updateGenerable(ArrayList<Validable> v) {

    }

    @Override
    public Boolean checkAllValidable() {
        return null;
    }
}
