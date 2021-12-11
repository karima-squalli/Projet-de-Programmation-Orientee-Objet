package logic;

class Panneau extends Bois {

    public Panneau(int id, int nombre,int idProprietaire) {
        super(id, nombre, idProprietaire);
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
    public int getNombreP() {
        return 0;
    }
}
