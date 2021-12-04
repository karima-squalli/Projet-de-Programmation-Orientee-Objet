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

    public void decrementNumber(int decrement){
        this.nombre = this.nombre - decrement;
    }
    public int getId(){
        return id;
    }

    public int getNombre(){return nombre;}

    public Validable getDate(){
        return listV.get(0);
    }

    public Validable getPrix(){
        return listV.get(1);
    }

    public Validable getDimensions(){
        return listV.get(2);
    }
}
