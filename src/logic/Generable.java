package logic;

import java.util.ArrayList;

public interface Generable {

    void add(Generable g);

    int getCurrentLength();

    Wood getWood(int j);

    int getId();

    int getWoodNumber();

    int getIdOwner();

    void updateGenerable(ArrayList<Validable> v);

    Boolean checkAllValidable();
}
