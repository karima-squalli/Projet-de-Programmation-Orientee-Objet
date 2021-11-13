package logic;

public abstract class RectangleDeBois {
    protected int id;
    protected int longueur;
    protected int largeur;
    protected int nombre;
    protected double prix;
    protected String date;

    protected RectangleDeBois(int id, int longueur, int largeur, int nombre, double prix, String date){
        this.id = id;
        this.longueur = longueur;
        this.largeur = largeur;
        this.nombre = nombre;
        this.prix = prix;
        this.date = date;
    }

}
