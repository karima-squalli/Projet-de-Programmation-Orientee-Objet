package logic;

class Client extends Personne {

    Client() {
        super(0);
    }

    Client(int id) {
        super(id);
    }

    @Override
    public void add(Generable g) {
        Planche planche = (Planche)g;
        super.add(planche);
    }
}
