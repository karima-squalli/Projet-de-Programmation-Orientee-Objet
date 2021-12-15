package logic;

public class SupplierFactory implements Factory {

    @Override
    public Generable generateGenerable() {
        Supplier supplier= new Supplier();
        return supplier;
    }

    @Override
    public Generable generateGenerable(int id) {
        Supplier supplier= new Supplier(id);
        return supplier;
    }

    @Override
    public Generable generateGenerable(int id, int number, int idOwner) {
        return null;
    }
}
