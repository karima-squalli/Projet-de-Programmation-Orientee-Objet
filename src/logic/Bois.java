package logic;

import java.util.ArrayList;

abstract class Bois implements Generable {

    protected int id;
    protected int nombre;
    protected ArrayList<Validable> listV = new ArrayList<>();
    protected int nombreInitial;
    protected int idProprietaire;

    Bois(int id, int nombre,int idProprietaire) {
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

    void setNombre(int nombre) {
        this.nombre = nombre;
    }

    int getNombreInitial() {
        return nombreInitial;
    }
    public int getId(){
        return id;
    }

    int getNombre(){return nombre;}

    public int getIdProprietaire(){return idProprietaire;}

    Validable getDate(){
        return listV.get(0);
    }

    Validable getPrix(){
        return listV.get(1);
    }

    Validable getDimensions(){
        return listV.get(2);
    }

    Validable getDimensions(int i) {
        return listV.get(2+i);
    }

    @Override
    public void add(Generable g) {

    }

    @Override
    public int getTailleCourante() {
        return 0;
    }

    @Override
    public Bois getBois(int j) {
        return null;
    }

    @Override
    public int getNombreP() {
        return 0;
    }
}
