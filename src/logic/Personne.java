package logic;

public abstract class Personne {
    protected int id;
    protected int nombreP;
    protected int tailleCourante = 0;
    protected RectangleDeBois[] bois;

    protected Personne(int id, int nombreP) {
        this.id = id;
        this.nombreP = nombreP;
    }

    public void setBois(RectangleDeBois[] bois) {
        this.bois = bois;
    }

    public void ajouterP(RectangleDeBois element) {

        bois[tailleCourante] = element;
        tailleCourante++;
    }
    public int getId() {
        return id;
    }

    public int getNombreP() {
        if (bois == null) return nombreP;
        return bois.length;
    }

}
