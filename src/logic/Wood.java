package logic;

import java.util.ArrayList;

abstract class Wood implements Generable {

    protected int id;
    protected int number;
    protected ArrayList<Validable> listV = new ArrayList<>();
    protected int initialNumber;
    protected int idOwner;

    Wood(int id, int number, int idOwner) {
        this.id = id;
        this.number = number;
        this.initialNumber = number;
        this.idOwner = idOwner;
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

    void setNumber(int number) {
        this.number = number;
    }

    int getInitialNumber() {
        return initialNumber;
    }
    public int getId(){
        return id;
    }

    int getNumber(){return number;}

    public int getIdOwner(){return idOwner;}

    Validable getDate(){
        return listV.get(0);
    }

    Validable getPrice(){
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
    public int getCurrentLength() {
        return 0;
    }

    @Override
    public Wood getWood(int j) {
        return null;
    }

    @Override
    public int getWoodNumber() {
        return 0;
    }
}
