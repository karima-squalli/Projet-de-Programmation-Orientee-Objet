package logic;

public class PanneauFactory implements Factory {


    @Override
    public Generable generateGenerable() {
        return null;
    }

    @Override
    public Generable generateGenerable(int id) {
        return null;
    }

    @Override
    public Generable generateGenerable(int id, int nombre) {
        Panneau panneau = new Panneau(id, nombre);
        return panneau;
    }
}
