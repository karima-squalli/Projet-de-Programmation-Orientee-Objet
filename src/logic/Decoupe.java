package logic;

class Decoupe {

    private final String x;
    private final String y;
    private final String idPlanche;
    private final String idPanneau;
    private final int idClient;
    private final int idFournisseur;
    private final String longueur;
    private final String largeur;
    private final String x1;
    private final String y1;

    public Decoupe(String x1, String y1, String longueur,String largeur,String x, String y, int idClient, String idPlanche, int idFournisseur, String idPanneau) {
        this.x = x;
        this.y = y;
        this.idClient = idClient;
        this.idPlanche = idPlanche;
        this.idFournisseur = idFournisseur;
        this.idPanneau = idPanneau;
        this.longueur = longueur;
        this.largeur = largeur;
        this.x1 = x1;
        this.y1 = y1;
    }

    public int getIdClient(){
        return idClient;
    }
    public int getIdFournisseur(){
        return idFournisseur;
    }
    public String getX(){
        return x;
    }
    public String getY(){
        return y;
    }
    public String getIdPlanche(){
        return idPlanche;
    }
    public String getIdPanneau(){
        return idPanneau;
    }


    public String getLongueur() {
        return longueur;
    }

    public String getLargeur() {
        return largeur;
    }

    public String getX1() {
        return x1;
    }

    public String getY1() {
        return y1;
    }
}
