package logic;

import java.util.ArrayList;

abstract class Bois implements Generable {

    protected int id;
    protected int nombre;
    protected ArrayList<Validable> listV = new ArrayList<>();
    protected int nombreInitial;
    protected int idProprietaire;

    public Bois(int id, int nombre,int idProprietaire) {
        this.id = id;
        this.nombre = nombre;
        this.nombreInitial = nombre;
        this.idProprietaire = idProprietaire;
    }

    public void updateGenerable(ArrayList<Validable> v) {

        listV = v;
    }

    public Boolean checkAllValidable() {
        for (Validable validable : listV) {
            if (!validable.isValid())
                return false;
        }
        return true;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getNombreInitial() {
        return nombreInitial;
    }
    public int getId(){
        return id;
    }

    public int getNombre(){return nombre;}

    public int getIdProprietaire(){return idProprietaire;}

    public Validable getDate(){
        return listV.get(0);
    }

    public Validable getPrix(){
        return listV.get(1);
    }

    public Validable getDimensions(){
        return listV.get(2);
    }

    public Validable getDimensions(int i) {
        return listV.get(2+i);
    }
}
