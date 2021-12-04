package logic;

public class Prix implements Validable,Comparable{

    String prixString;
    double prix;

    public Prix(String prixString) {
        this.prixString = prixString;
    }
    @Override
    public Boolean isValid() {

        if (!isDouble(prixString)) {
            return false;
        }
        prix = Double.parseDouble(prixString);
        if (prix < 0) {
            return false;
        }
        return true;
    }
    public Boolean toCompare(Validable v) {
        Prix prix_to_compare = (Prix)v;
        if(prix_to_compare.prix>prix)
            return false;
        return true;
    }
    String getPrixString(){
        return prixString;
    }
}
