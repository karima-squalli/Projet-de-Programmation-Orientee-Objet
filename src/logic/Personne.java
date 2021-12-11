package logic;

abstract class Personne implements Generable, Validable{

    private int id;
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
            for (int i = 0; i < tailleCourante; i++)
                bois[i] = tmp[i];
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
                for (int index = 0; index < i; index++) {
                    newBois[index] = bois[index];
                }
                for (int j = i; j < bois.length - 1; j++) {
                    newBois[j] = bois[j + 1];
                }

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
        if (tailleCourante == 0)
            return false;
        return true;
    }
}
