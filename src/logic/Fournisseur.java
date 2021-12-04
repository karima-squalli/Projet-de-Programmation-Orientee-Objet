package logic;

import java.util.ArrayList;

class Fournisseur extends Personne {

    public Fournisseur(int id) {
        super(id);
    }

    public Fournisseur() {
        super(0);
    }

    public int getNombrePanneaux() {
        return super.getNombreP();
    }

    @Override
    public void add(Generable g) {
        Panneau panneau = (Panneau)g;
        super.add(panneau);
    }

    @Override
    public void updateGenerable(ArrayList<Validable> v) {
    }

    @Override
    public Boolean checkAllValidable() {
        return null;
    }
}
