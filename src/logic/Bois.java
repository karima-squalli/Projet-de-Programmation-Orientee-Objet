package logic;

import java.util.ArrayList;

abstract class Bois implements Generable {

    protected int id;
    protected int nombre;
    protected ArrayList<Validable> listV = new ArrayList<>();

    public Bois(int id, int nombre) {
        this.id = id;
        this.nombre = nombre;
    }



    public void updateGenerable(ArrayList<Validable> v) {
        listV = v;
    }

    public Boolean checkAllValidable() {
        for (int i=0; i<listV.size(); i++) {
            if (!listV.get(i).isValid())
                return false;
        }
        return true;
    }
}
