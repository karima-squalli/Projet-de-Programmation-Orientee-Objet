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

    public Generable generateGenerable(int id, int nombre) {
        Planche planche = new Planche(id, nombre);
        return planche;
    }
}
