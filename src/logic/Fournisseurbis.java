package logic;

public class Fournisseurbis extends Personnebis {

    public Fournisseurbis(int id) {
        super(id);
    }

    public Fournisseurbis() {
        super(0);
    }

    public void ajouterPanneau(Panneaubis panneau) {
        super.ajouterP(panneau);
    }

    public int getNombrePanneaux() {
        return super.getNombreP();
    }
}
