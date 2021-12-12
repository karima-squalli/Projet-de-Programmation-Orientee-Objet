package logic;

public class PlancheFactory implements Factory {

    @Override
    public Generable generateGenerable() {
        return null;
    }

    @Override
    public Generable generateGenerable(int id) {
        return null;
    }

    @Override
    public Generable generateGenerable(int id, int nombre, int idProprietaire) {
        return new Planche(id, nombre, idProprietaire);
    }
}
