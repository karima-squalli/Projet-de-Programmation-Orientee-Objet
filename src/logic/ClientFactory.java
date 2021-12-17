package logic;

class ClientFactory implements Factory{

    @Override
    public Generable generateGenerable() {
        return new Client();
    }

    @Override
    public Generable generateGenerable(int id) {
        return new Client(id);
    }

    @Override
    public Generable generateGenerable(int parseInt, int number, int idOwner) {
        return null;
    }

}
