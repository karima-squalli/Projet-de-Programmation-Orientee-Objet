package logic;

public class PanelFactory implements Factory {

    @Override
    public Generable generateGenerable() {
        return null;
    }

    @Override
    public Generable generateGenerable(int id) {
        return null;
    }

    @Override
    public Generable generateGenerable(int id, int number, int idOwner) {
        return new Panel(id, number, idOwner);
    }
}
