package logic;

public class Fournisseur extends Personne {

    public Fournisseur(int id, int nombrePanneaux) {
        super(id, nombrePanneaux);
    }

    public void ajouterPanneau(Panneau panneau) {
        super.ajouterP(panneau);
    }

    public int getNombrePanneaux() {
        return super.getNombreP();
    }

    public void setPanneaux(Panneau[] panneaux) {
        super.setBois(panneaux);
    }


}
