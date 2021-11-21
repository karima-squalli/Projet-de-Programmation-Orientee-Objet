package logic;

import interfaces.Validable;

import java.util.ArrayList;

public class RectangleDeBoisbis {
    protected int id;
    protected int nombre;
    ArrayList<Validable> listV = new ArrayList<>();

    protected RectangleDeBoisbis(int id, int nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void addValidable(Validable v) {
        listV.add(v);
    }

    public Boolean checkAllValidable() {
        for (int i=0; i<listV.size(); i++) {
            if (!listV.get(i).isValid())
                return false;
        }
        return true;
    }
}
