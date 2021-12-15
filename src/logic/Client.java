package logic;

class Client extends Person {

    Client() {
        super(0);
    }

    Client(int id) {
        super(id);
    }

    @Override
    public void add(Generable g) {
        Board board = (Board)g;
        super.add(board);
    }
}
