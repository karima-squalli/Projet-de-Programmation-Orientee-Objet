package logic;

class Planche extends Bois {

    public Planche(int id, int nombre) {
        super(id, nombre);
    }


    @Override
    public void add(Generable g) {

    }

    @Override
    public int getTailleCourante() {
        return 0;
    }

    @Override
    public Bois getBois(int j) {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getNombreP() {
        return 0;
    }
}
