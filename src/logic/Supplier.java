package logic;


class Supplier extends Person {

    public Supplier(int id) {
        super(id);
    }

    public Supplier() {
        super(0);
    }

    @Override
    public void add(Generable g) {
        Panel panel = (Panel)g;
        super.add(panel);
    }
}
