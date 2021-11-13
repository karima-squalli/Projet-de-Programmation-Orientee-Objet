package logic;

public class Client extends Personne {

    public Client(int id, int nombrePlanches) {
        super(id, nombrePlanches);
    }

    public void ajouterPlanche(Planche planche) {
        super.ajouterP(planche);
    }

    public int getNombrePlanches() {
        return super.getNombreP();
    }

    public void setPlanches(Planche[] planches) {
        super.setBois(planches);
    }
}