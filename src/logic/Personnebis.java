package logic;

public abstract class Personnebis {

    private int id;
    private int nombreP;
    protected int tailleCourante = 0;
    protected int tailleMaximale = 10;
    protected RectangleDeBoisbis[] bois = new RectangleDeBoisbis[tailleMaximale];

    Personnebis(int id) {
        this.id = id;
    }

    //void setBois(RectangleDeBois[] bois) {
//        this.bois = bois;
//    }

    void ajouterP(RectangleDeBoisbis element) {

//        bois[tailleCourante] = element;
//        tailleCourante++;

        if (tailleCourante == tailleMaximale) {
            tailleMaximale = tailleMaximale * 2;
            RectangleDeBoisbis[] tmp = bois.clone();
            bois = new RectangleDeBoisbis[tailleMaximale];
            for (int i = 0; i < tailleCourante; i++)
                bois[i] = tmp[i];
        }
        bois[tailleCourante] = element;
        tailleCourante++;
        nombreP = tailleCourante;
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

    public RectangleDeBoisbis getBois(int i) {
        return bois[i];
    }
}
