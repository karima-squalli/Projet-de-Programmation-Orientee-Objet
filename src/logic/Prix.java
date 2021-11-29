package logic;

import entreessorties.Validable;

public class Prix implements Validable {

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
        if (prix < 0){
            return false;
        }
        return true;
    }
}
