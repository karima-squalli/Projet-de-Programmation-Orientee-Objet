package logic;

public class Prix implements Validable,Comparable{

    private final String prixString;
    private double prix;

    public Prix(String prixString) {
        this.prixString = prixString;
    }

    @Override
    public Boolean isValid() {

        if (!isDouble(prixString)) {
            return false;
        }
        prix = Double.parseDouble(prixString);

        return !(prix < 0);
    }

    public Boolean toCompare(Validable v) {
        Prix prix_to_compare = (Prix)v;
        return !(prix_to_compare.prix > prix);
    }

    String getPrixString(){
        return prixString;
    }
}
