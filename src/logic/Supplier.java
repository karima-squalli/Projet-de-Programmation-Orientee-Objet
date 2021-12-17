package logic;


class Supplier extends Person {

    protected Supplier(int id) {
        super(id);
    }

    protected Supplier() {
        super(0);
    }

    @Override
    public void add(Generable g) {
        Panel panel = (Panel)g;
        super.add(panel);
    }
}
