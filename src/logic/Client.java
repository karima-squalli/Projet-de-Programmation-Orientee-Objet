package logic;

class Client extends Person {

    protected Client() {
        super(0);
    }

    protected Client(int id) {
        super(id);
    }

    @Override
    public void add(Generable g) {
        Board board = (Board)g;
        super.add(board);
    }
}
