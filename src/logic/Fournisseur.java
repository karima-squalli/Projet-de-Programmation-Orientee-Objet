package logic;


class Fournisseur extends Personne {

    public Fournisseur(int id) {
        super(id);
    }

    public Fournisseur() {
        super(0);
    }

    @Override
    public void add(Generable g) {
        Panneau panneau = (Panneau)g;
        super.add(panneau);
    }
}
