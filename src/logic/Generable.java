package logic;

import java.util.ArrayList;

public interface Generable {

    void add(Generable g);

    int getTailleCourante();

    Bois getBois(int j);

    int getId();

    int getNombreP();

    void updateGenerable(ArrayList<Validable> v);

    Boolean checkAllValidable();
}