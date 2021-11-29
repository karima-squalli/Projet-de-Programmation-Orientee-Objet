package logic;

 class Client extends Personne {

    Client() {
        super(0);
    }

    Client(int id) {
        super(id);
    }

    void ajouterPlanche(Planche planche) {
        super.ajouterP(planche);
    }

    int getNombrePlanches() {
        return super.getNombreP();
    }
}
