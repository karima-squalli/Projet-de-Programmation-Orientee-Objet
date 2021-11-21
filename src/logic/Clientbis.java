package logic;

public class Clientbis extends Personnebis {

    public Clientbis() {
        super(0);
    }

    public Clientbis(int id) {
        super(id);
    }

    public void ajouterPlanche(Planchebis planche) {
        super.ajouterP(planche);
    }

    public int getNombrePlanches() {
        return super.getNombreP();
    }
}
