package logic;

public class SupplierFactory implements Factory {

    @Override
    public Generable generateGenerable() {
        return new Supplier();
    }

    @Override
    public Generable generateGenerable(int id) {
        return new Supplier(id);
    }

    @Override
    public Generable generateGenerable(int id, int number, int idOwner) {
        return null;
    }
}
