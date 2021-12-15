package logic;

public class BoardFactory implements Factory {

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
        return new Board(id, number, idOwner);
    }
}
