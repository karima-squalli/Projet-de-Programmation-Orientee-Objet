package logic;

class Fournisseur extends Personne {

    public Fournisseur(int id) {
        super(id);
    }

    public Fournisseur() {
        super(0);
    }

    public void ajouterPanneau(Panneau panneau) {
        super.ajouterP(panneau);
    }

    public int getNombrePanneaux() {
        return super.getNombreP();
    }
}
