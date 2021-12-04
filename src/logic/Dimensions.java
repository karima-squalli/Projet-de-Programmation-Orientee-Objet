package logic;

public class Dimensions implements Validable {

    private String longueurString;
    private String largeurString;
    private int longueur;
    private int largeur;

    public Dimensions(String longueurString, String largeurString) {
        this.longueurString = longueurString;
        this.largeurString = largeurString;
    }

    @Override
    public Boolean isValid() {

        if (!isDouble(longueurString) || !isDouble(largeurString)) {
            return false;
        }

        longueur = (int)Double.parseDouble(longueurString);
        largeur = (int)Double.parseDouble(largeurString);

        if (longueur != Double.parseDouble(longueurString) || largeur != Double.parseDouble(largeurString)) {
            return false;
        }

        if (longueur>0 && largeur>0 && longueur>=largeur)
            return true;
        return false;
    }

}
